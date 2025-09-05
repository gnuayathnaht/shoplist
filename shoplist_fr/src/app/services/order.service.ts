import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Order } from '../model/order.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class OrderService {
 BASE_URL:string = "http://localhost:8080/api/order";

  constructor(private http:HttpClient) { }
  
  orderConfirm(order:Order):Observable<Order>{
   return this.http.post<Order>(`${this.BASE_URL}/create`,order);
    }
}
