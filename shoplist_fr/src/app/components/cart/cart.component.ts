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
  cartCount: number = 0;
  totalPrice: number = 0;
  userId: number = 1;

  authService = inject(AuthServiceService);

  constructor(
    private cartService: CartService,
    private router: Router
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
          this.cartItems.flatMap((cartItem) => {
            console.log(cartItem.quantity);
            // this.cartCount += item.quantity;
          });

        },
        (error) => {
          console.error('An error occurred:', error);
        }
      );
  }

  increaseQty(cartItem: CartItem): void {
    if (!cartItem.cartItemId) {
      console.error('CartItem ID is undefined');
      return;
    } else {
      this.cartService.updateQuantity(cartItem.cartItemId, cartItem.cartItemId + 1).subscribe(() => this.loadCart());
    }

  }

  decreaseQty(cartItem: CartItem): void {
    if (!cartItem.cartItemId) {
      console.error('CartItem ID is undefined');
      return;
    } else {
      if (cartItem.quantity > 1) {
        this.cartService.updateQuantity(cartItem.cartItemId, cartItem.quantity - 1).subscribe(() => this.loadCart());
      } else {
        console.log("itemId" + cartItem.itemId);
        this.removeItem(cartItem);
      }
    }
  }

  removeItem(cartItem: CartItem) {
    if (!cartItem.cartItemId) {
      console.error('CartItem ID is undefined');
      return;
    } else {
      this.cartService.removeCartItem(cartItem.cartItemId).subscribe(() => this.loadCart());
    }
  }

  proceedToCheckout(): void {
    console.log("....checkout");
    this.router.navigate(['/checkout']);
  }

}
