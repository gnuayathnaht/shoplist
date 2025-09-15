import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { OrderHistoryService } from '../../services/order-history.service';
import { OrderDetail } from '../../model/order-detail.model';
import { CommonModule } from '@angular/common';


@Component({
  selector: 'app-order-detail',
  imports : [CommonModule],
  templateUrl: './order-detail-component.html'
})
export class OrderDetailComponent implements OnInit {
  order!: OrderDetail;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private orderHistoryService: OrderHistoryService
  ) {}

  ngOnInit(): void {
    const orderId = this.route.snapshot.params['id'];
    this.orderHistoryService.getOrderDetails(orderId).subscribe(data => {
      this.order = data;
      console.log('Received OrderDetails !');
      console.table(data);
    });
  }

  viewInvoice(): void {
    this.router.navigate(['/invoice', this.order.orderId]);
  }
}

