import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthServiceService {

  private baseUrl = 'http://localhost:8080/api/users/';

  private loggedIn = new BehaviorSubject<Boolean>(false);
  isLoggedIn$ = this.loggedIn.asObservable();

  constructor(private http: HttpClient, private router: Router) {
  }

  public setAuthentication(isValid: Boolean) {
    this.loggedIn.next(isValid);
  }

  login(email: string, password: string): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}login`, { email, password });
  }

  saveToken(token: string) {
    localStorage.setItem('token', token);
  }

  getToken(): string | null {
    return localStorage.getItem('token');
  }

  getUserId() {
    const userData = JSON.parse(localStorage.getItem("user") || 'null');
    if (userData && userData.user) {
      const userId = userData.user.id;
      return userId;
    } else {
      console.log("User data not found.");
    }
  }

  checkToken() {
    this.validateToken().subscribe({
      next: (response) => this.loggedIn.next(response),
      error: () => this.loggedIn.next(false)
    });
  }

  validateToken(): Observable<boolean> {
    const token = localStorage.getItem('token');
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });

    return this.http.get<boolean>(`${this.baseUrl}validate`, { headers });
  }

  logout() {
    const confirmLogout = window.confirm("Are you sure you want to log out?");
    if (confirmLogout) {
      this.setAuthentication(false);
      localStorage.removeItem("token");
      this.router.navigate(['/']);

    }
  }

}
