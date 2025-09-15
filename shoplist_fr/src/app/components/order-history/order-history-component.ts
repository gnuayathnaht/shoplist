import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule, JsonPipe } from '@angular/common';
import { OrderHistory } from '../../model/order-history.model';
import { OrderHistoryService } from '../../services/order-history.service';

@Component({
  selector: 'app-order-history',
  imports: [CommonModule],
  templateUrl: './order-history-component.html',
  styleUrl: './order-history-component.css'
})
export class OrderHistoryComponent implements OnInit {
  orders: OrderHistory[] = [];
  userId: number = Number(JSON.parse(localStorage.getItem('user')!).user.id);

  constructor(
    private orderHistoryService: OrderHistoryService,
    private router: Router
  ) {}

  ngOnInit(): void {
    console.log("User's Id");
    console.log(this.userId);
    this.orderHistoryService.getOrdersByUser(this.userId).subscribe((data) => {
      this.orders = data;
      console.log('Received data :: ');
      console.table(data);
    });
  }

  viewOrder(orderId: number): void {
    this.router.navigate(['/orders', orderId]);
  }
}
