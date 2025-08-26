import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Category } from '../model/category.model';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  private API_URL = "http://localhost:8080/api/category"
  httpClient = inject(HttpClient);

  getAllCategories() {
    return this.httpClient.get<Category[]>(this.API_URL);
  }

  saveCategory(category: Category) {
    return this.httpClient.post<Category>(this.API_URL, category);
  }
}
