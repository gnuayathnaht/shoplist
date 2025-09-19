import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { OrderHistoryService } from '../../services/order-history.service';
import { OrderDetail } from '../../model/order-detail.model';
import { CommonModule } from '@angular/common';
import { Invoice } from '../../model/invoice.model';


@Component({
  selector: 'app-order-detail',
  imports : [CommonModule],
  templateUrl: './order-detail-component.html',
  styleUrl: './order-detail-component.css'
})
export class OrderDetailComponent implements OnInit {
  order!: OrderDetail;
  invoice!: Invoice;
  orderId!: number;
  emailStatus: string = '';

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private orderHistoryService: OrderHistoryService
  ) {}

  ngOnInit(): void {
    this.orderId = this.route.snapshot.params['id'];
    this.orderHistoryService.getOrderDetails(this.orderId).subscribe(data => {
      this.order = data;
      console.log('Received OrderDetails !');
      console.table(data);
    });

     this.orderHistoryService.getInvoice(this.orderId).subscribe(data => {
      this.invoice = data;
      console.table(data);
    });

  }
  
  sendInvoice(): void {
  const confirmed = confirm("Do you want to send invoice via email?");
  if (confirmed) {
    this.orderHistoryService.sendInvoice(this.orderId).subscribe({
      next: (response) => this.emailStatus = response,
      error: () => this.emailStatus = '❌ Failed to send invoice'
    });
  } else {
    console.log("Invoice sending cancelled by user.");
  }
}

}

