import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Item } from '../model/item.model';

@Injectable({
  providedIn: 'root'
})
export class ItemsService {

  private API_URL = "http://localhost:8080/api/items"
  httpClient = inject(HttpClient);

  getAllItems() {
    return this.httpClient.get<Item[]>(this.API_URL);
  }

  saveItem(item: Item) {
    return this.httpClient.post<Item>(this.API_URL, item);
  }

  getItemsByCategoryId(categoryId?: number) {
    return this.httpClient.get<Item[]>(`${this.API_URL}/${categoryId}`);
  }
}
