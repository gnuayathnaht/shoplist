import { Component, inject, OnInit } from '@angular/core';
import { Router, RouterLink, RouterModule } from '@angular/router';
import { AuthServiceService } from '../../services/auth-service.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-header',
  imports: [RouterModule, RouterLink, FormsModule],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent implements OnInit{

  searchKeyword: string = '';
  isLoggedIn: Boolean = false;
  router = inject(Router);
  public authService = inject(AuthServiceService)

  ngOnInit() {
    const token = localStorage.getItem('token');
    if (token) {
      this.authService.checkToken();
    }

    this.authService.isLoggedIn$.subscribe(status => {
      this.isLoggedIn = status;
    });
  }

  searchByKeyword() {
    this.router.navigate(['/items'], { queryParams: { search: this.searchKeyword } });
  }

  logout() {
    this.authService.logout();
  }

}
