import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthServiceService {

  private baseUrl = 'http://localhost:8080/api/users/';

  constructor(private http: HttpClient, private router: Router) { }


    login(email: string, password: string): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}login`, { email, password });
  }

    saveToken(token: string) {
    localStorage.setItem('token', token);
  }

  getToken(): string | null {
    return localStorage.getItem('token');
  }

  logout() {
    const confirmLogout = window.confirm("Are you sure you want to log out?");
  if (confirmLogout) {
    localStorage.removeItem("token");
    this.router.navigate(['/login']);
    
  }
}}
