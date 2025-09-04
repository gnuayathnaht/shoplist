import { CommonModule, NgIf } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { CanDeactivate, Router, RouterLink } from '@angular/router';
import { AuthServiceService } from '../../services/auth-service.service';
import { CanComponentDeactivate } from '../../guard/unsaved-changes.guard';

@Component({
  selector: 'app-login',
  imports: [ReactiveFormsModule, NgIf, CommonModule, RouterLink],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  loginForm !: FormGroup;
  errorMessage: string = '';
  showPassword: boolean = false;

  constructor(private fb: FormBuilder,
    private httpClient: HttpClient,
    private router: Router,
    private authService: AuthServiceService) { }

  togglePassword() {
    this.showPassword = !this.showPassword;
  }
  ngOnInit(): void {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required]],
      password: ['', [Validators.required]]
    });
  }

  onSubmit() {
    if (this.loginForm.valid) {
      const { email, password } = this.loginForm.value;
      this.authService.login(email, password).subscribe({
        next: (response) => {
          if (response && response.token) {
            console.log('JWT Token:', response.token);
            this.authService.saveToken(response.token);
            localStorage.setItem('user', JSON.stringify(response));
            alert('Login successful!...');
            this.authService.setAuthentication(true);
            this.router.navigate(['/']);
          } else {
            this.errorMessage = 'Invalid email or password!';
          }
        },
        error: (err) => {
          this.errorMessage = err.error || 'Login failed!';
          console.error(err);
        }
      });
    }
  }

}
