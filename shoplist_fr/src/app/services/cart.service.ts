import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Cart } from '../model/cart.model';

@Injectable({
  providedIn: 'root'
})
export class CartService {
  BASE_URL = "http://localhost:8080/api/cart";

  constructor(private http:HttpClient) { }

  getCartByUserId(userId:number):Observable<Cart>{
    return this.http.get<Cart>(`${this.BASE_URL}/view/${userId}`);
  }
  updateQuantity(cartItemId:number,quantity:number):Observable<void>{
    return this.http.put<void>(`${this.BASE_URL}/update/${cartItemId}?quantity=${quantity}`,{});
  }

  removeCartItem(itemId:number):Observable<void>{
    return this.http.delete<void>(`${this.BASE_URL}/delete/${itemId}`);
  }

  

}
