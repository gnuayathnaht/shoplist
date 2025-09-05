import { Component, OnInit } from '@angular/core';
import { CartService } from '../../services/cart.service';
import { UserServiceService } from '../../services/user-service.service';
import { CartItem } from '../../model/cart-item.model';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { OrderService } from '../../services/order.service';
import { Router } from '@angular/router';
import { Order } from '../../model/order.model';

@Component({
  selector: 'app-checkout',
  imports: [CommonModule, FormsModule],
  templateUrl: './checkout.component.html',
  styleUrl: './checkout.component.css'
})
export class CheckoutComponent implements OnInit {
  cartItems: CartItem[] = [];
  total: number = 0;
  userId: number = 1;
  address: string = '';
  cartId:number = 0;
  paymentMethod: string = 'credit';

  constructor(
    private cartService: CartService,
    private userService: UserServiceService,
    private orderService: OrderService,
    private router: Router
  ) {

  }
  ngOnInit(): void {
    this.cartService.getCartByUserId(this.userId).subscribe(cart => {
      console.log(cart);
      this.cartId = cart.cartId;
      this.cartItems = cart.items;
      this.total = cart.total;
    });

    this.userService.getUserById(this.userId).subscribe(user => {
      this.address = user.address;
    });
  }

  orderConfirm() {
    const order:Order = {
     userId:this.userId,
     cartId:this.cartId,
     cartItems:this.cartItems,
      total:this.total,
      payment:this.paymentMethod
    }
    this.orderService.orderConfirm(order)
    .subscribe((value) =>this.router.navigate(['/order/success']));
  }
}
