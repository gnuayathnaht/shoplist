import { Component, inject, OnInit } from '@angular/core';
import { CartService } from '../../services/cart.service';
import { CartItem } from '../../model/cart-item.model';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { AuthServiceService } from '../../services/auth-service.service';
import { NoProductComponent } from '../no-product/no-product.component';


@Component({
  selector: 'app-cart',
  imports: [CommonModule, NoProductComponent],
  templateUrl: './cart.component.html',
  styleUrl: './cart.component.css'
})
export class CartComponent implements OnInit {
  cartItems: CartItem[] = [];
  cartCount:number = 0;
  totalPrice:number = 0;
  userId: number = 1;

  authService = inject(AuthServiceService);
  
  constructor(
    private cartService: CartService,
    private router:Router
  ) {

  }
  ngOnInit(): void {
    this.loadCart();
  }

  private loadCart() {
    this.userId = this.authService.getUserId();
    this.cartService.getCartByUserId(this.userId)
      .subscribe(
        (cart) => {
          this.cartItems = cart.items;
          this.totalPrice = cart.total;
          this.cartItems.flatMap((item) => {
            console.log(item.quantity);
           // this.cartCount += item.quantity;
          });

        },
        (error) => {
          console.error('An error occurred:', error);
        }
      );
  }

  increaseQty(item: CartItem): void {
    this.cartService.updateQuantity(item.itemId, item.quantity + 1).subscribe(() => this.loadCart());
  }

  decreaseQty(item: CartItem): void {
    if (item.quantity > 1) {
      this.cartService.updateQuantity(item.itemId, item.quantity - 1).subscribe(() => this.loadCart());
    } else {
      this.removeItem(item.itemId);
    }
  }

  removeItem(itemId:number){
    this.cartService.removeCartItem(itemId).subscribe(() => this.loadCart());
  }

  proceedToCheckout(): void {
    console.log("....checkout"); 
  this.router.navigate(['/checkout']) ;
}

}
