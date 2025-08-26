import { Component, inject, signal } from '@angular/core';
import { Category } from '../../model/category.model';
import { Item } from '../../model/item.model';
import { KeyValuePipe } from '@angular/common';
import { CategoryService } from '../../services/category.service';
import { ItemsService } from '../../services/items.service';
import { ItemComponent } from "../item/item.component";
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-items',
  imports: [KeyValuePipe, ItemComponent, RouterLink],
  templateUrl: './items.component.html',
  styleUrl: './items.component.css'
})
export class ItemsComponent {
  categories = signal<Category[]>([]);
  categoryService = inject(CategoryService);

  items = signal<Item[]>([]);
  itemsService = inject(ItemsService);

  categoryMap: Map<string, Item[]> = new Map<string, Item[]>();

  ngOnInit() {

    this.categoryService.getAllCategories().subscribe({
      next: resp => this.categories.set(resp),
      complete: () => {
        console.log("completed get all categories.")
        this.categories().forEach(category => {
          this.itemsService.getItemsByCategoryId(category.id).subscribe({
            next: resp => this.items.set(resp),
            complete: () => {
              console.log("completed get all items retrieved with category id.")
              this.categoryMap.set(category.name, this.items())
            }
          })

        })
      }
    })

  }
}
