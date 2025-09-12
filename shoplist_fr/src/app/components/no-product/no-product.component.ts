import { Component, inject } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-no-product',
  imports: [],
  templateUrl: './no-product.component.html',
  styleUrl: './no-product.component.css'
})
export class NoProductComponent {

  router = inject(Router);

  refresh(){
    window.location.reload();
  }

  goHome(){
    this.router.navigate(['/']);
  }

}
