import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { OrderHistory } from '../model/order-history.model';
import { OrderDetail } from '../model/order-detail.model';
import { Invoice } from '../model/invoice.model';
@Injectable({ providedIn: 'root' })
export class OrderHistoryService {
  private apiUrl = 'http://localhost:8080/api/orders';

  constructor(private http: HttpClient) {}

  getOrdersByUser(userId: number): Observable<OrderHistory[]> {
    return this.http.get<OrderHistory[]>(`${this.apiUrl}/user/${userId}`);
  }

  getOrderDetails(orderId: number): Observable<OrderDetail> {
    return this.http.get<OrderDetail>(`${this.apiUrl}/${orderId}`);
  }

  getInvoice(orderId: number): Observable<Invoice> {
    return this.http.get<Invoice>(`${this.apiUrl}/${orderId}/invoice`);
  }

  sendInvoice(orderId: number): Observable<string> {
  return this.http.post(`http://localhost:8080/api/invoice/send/${orderId}`, {}, { responseType: 'text' });
}

}
