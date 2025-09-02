import { Component, OnInit } from '@angular/core';
import { User } from '../../model/user.model';
import { HttpClient } from '@angular/common/http';
import { UserServiceService } from '../../services/user-service.service';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { AuthServiceService } from '../../services/auth-service.service';
import { CommonModule } from '@angular/common';
import { CanComponentDeactivate } from '../../guard/unsaved-changes.guard';

@Component({
  selector: 'app-profile',
  imports: [ReactiveFormsModule,CommonModule],
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css'
})


export class ProfileComponent implements OnInit, CanComponentDeactivate{

  profileForm!: FormGroup;
  currentUser!: User;
  showPassword: boolean = false;


  constructor(private fb: FormBuilder, private userService: UserServiceService) {}
  
  togglePassword() {
  this.showPassword = !this.showPassword;
}


  ngOnInit(): void {
    this.profileForm = this.fb.group({
      name: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: [''], 
      phone: ['', Validators.required],
      address: ['', Validators.required]
    });

    this.userService.getProfile().subscribe(user => {
      this.currentUser = user;
      this.profileForm.patchValue({
        name: user.name,
        email: user.email,
        password: user.password,
        phone: user.phone,
        address: user.address
      });
    });
  }

  onSubmit(): void {
    if (this.profileForm.valid) {
      const updatedUser: User = {
        ...this.currentUser,
        ...this.profileForm.value
      };

      this.userService.updateUser(updatedUser).subscribe(res => {
        alert('Profile updated successfully!');
        this.currentUser = res;
        this.profileForm.patchValue({
          name: res.name,
          email: res.email,
          password: res.password,
          phone: res.phone,
          address: res.address
        });
      });
    }
  }

   canDeactivate(): boolean {
    if (this.profileForm.dirty) {
      return confirm('You have unsaved changes. Are you sure you want to leave?');
    }
    return true;
  }
}
