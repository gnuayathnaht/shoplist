import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';
import { Invoice } from '../../model/invoice.model';
import { OrderService } from '../../services/order.service';
import { OrderHistoryService } from '../../services/order-history.service';

@Component({
  selector: 'app-invoice',
  imports : [CommonModule],
  templateUrl: './invoice-component.component.html'
})
export class InvoiceComponent implements OnInit {
  invoice!: Invoice;
  orderId!: number;
  emailStatus: string = '';

  constructor(private route: ActivatedRoute, private orderHistoryService: OrderHistoryService) {}

  ngOnInit(): void {
    this.orderId = this.route.snapshot.params['id'];
    this.orderHistoryService.getInvoice(this.orderId).subscribe(data => {
      this.invoice = data;
      console.table(data);
    });
  }

  sendEmail(): void {
    this.orderHistoryService.sendInvoice(this.orderId).subscribe({
      next: (response) => this.emailStatus = response,
      error: () => this.emailStatus = '❌ Failed to send invoice'
    });
  }
}

