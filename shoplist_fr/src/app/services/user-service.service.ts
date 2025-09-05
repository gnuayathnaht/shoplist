import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../model/user.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserServiceService {

  private apiUrl = 'http://localhost:8080/api/users';

  constructor(private httpClient : HttpClient) {

   }

   registerUser(user : User){
    return this.httpClient.post<User>(this.apiUrl, user);
   }

   getProfile(): Observable<User> {
    return this.httpClient.get<User>(`${this.apiUrl}/profile`);
  }

  updateUser(user: User): Observable<User> {
    return this.httpClient.put<User>(`${this.apiUrl}/${user.id}`, user);
  }

  getUserAddress(userId:number):Observable<string>{
    return this.httpClient.get(`${this.apiUrl}/address/${userId}`,{ responseType: 'text' });
  }

  getUserById(userId:number):Observable<User>{
     return this.httpClient.get<User>(`${this.apiUrl}/${userId}`);
  }
}
