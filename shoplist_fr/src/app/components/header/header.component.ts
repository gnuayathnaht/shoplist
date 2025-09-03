import { Component, inject } from '@angular/core';
import { Router, RouterLink, RouterLinkActive, RouterModule } from '@angular/router';
import { AuthServiceService } from '../../services/auth-service.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-header',
  imports: [RouterModule,RouterLink, FormsModule],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent {

  searchKeyword: string = '';
  router = inject(Router);
  public authService = inject(AuthServiceService)

  searchByKeyword() {
    this.router.navigate(['/items'], { queryParams: { search: this.searchKeyword } });
  }

  logout() {
    this.authService.logout();
  }

}
