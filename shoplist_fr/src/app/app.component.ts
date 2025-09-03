import { Component, inject, signal } from '@angular/core';
import { RouterLink, RouterOutlet } from '@angular/router';
import { Category } from './model/category.model';
import { HeaderComponent } from "./components/header/header.component";
import { CategoryService } from './services/category.service';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, HeaderComponent, RouterLink],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  categories = signal<Category[]>([]);
  categoryService = inject(CategoryService);

  ngOnInit() {
    this.categoryService.getAllCategories().subscribe({
      next: resp => this.categories.set(resp),
      complete: () => console.log("completed get all categories.")
    })
  }
}
