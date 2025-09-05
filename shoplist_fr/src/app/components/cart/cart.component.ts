import { Component, OnInit } from '@angular/core';
import { CartService } from '../../services/cart.service';
import { CartItem } from '../../model/cart-item.model';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';


@Component({
  selector: 'app-cart',
  imports: [CommonModule],
  templateUrl: './cart.component.html',
  styleUrl: './cart.component.css'
})
export class CartComponent implements OnInit {
  cartItems: CartItem[] = [];
  cartCount:number = 0;
  totalPrice:number = 0;
  userId: number = 1;
  
  constructor(
    private cartService: CartService,
    private router:Router
  ) {

  }
  ngOnInit(): void {
    this.loadCart();
  }

  private loadCart() {
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
    this.cartService.updateQuantity(item.id, item.quantity + 1).subscribe(() => this.loadCart());
  }

  decreaseQty(item: CartItem): void {
    if (item.quantity > 1) {
      this.cartService.updateQuantity(item.id, item.quantity - 1).subscribe(() => this.loadCart());
    } else {
      this.removeItem(item.id);
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
