import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterLink, RouterLinkActive } from '@angular/router';
import { UserServiceService } from '../../services/user-service.service';
import { User } from '../../model/user.model';
import { CommonModule, NgIf } from '@angular/common';
import { LoginComponent } from '../login/login.component';

@Component({
  selector: 'app-register',
  imports: [ReactiveFormsModule,NgIf,RouterLinkActive,RouterLink,CommonModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
   
   registerForm : FormGroup;
  showPassword: boolean = false;
 
  constructor(private fb: FormBuilder , private router: Router, private userService: UserServiceService) { 

    this.registerForm = this.fb.group({
      name : ['', [Validators.required, Validators.pattern('^[a-zA-Z ]+$')]],
      email : ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]],
      phone: ['', [Validators.required, Validators.pattern('^[0-9]{9,11}$')]],
      address: ['', [Validators.required]]
    });
  }
  get f() {
    return this.registerForm.controls;
  }
  
     togglePassword() {
  this.showPassword = !this.showPassword;
}

canDeactivate(): boolean {
    if (this.registerForm.dirty) {
      return confirm('You have unsaved changes. Are you sure you want to leave?');
    }
    return true;
  }

    onSubmit(): void {
    if (this.registerForm.valid) {
      const user: User = this.registerForm.value;
      this.userService.registerUser(user).subscribe({
        next: (savedUser) => {
          alert('User registered successfully: ' + savedUser.name);
         this.router.navigate(['/login']); 
          
        },
        error: (err) => {
          alert('Error: ' + err.error.message || 'Registration failed!');
        }
      });
    }
  }


}
