import { Component, inject } from '@angular/core';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { ItemsService } from '../../services/items.service';
import { Item } from '../../model/item.model';
import { ItemComponent } from '../item/item.component';

@Component({
  selector: 'app-category',
  imports: [ItemComponent, RouterLink],
  templateUrl: './category.component.html',
  styleUrl: './category.component.css'
})
export class CategoryComponent {

  items!: Item[];
  itemCount: number = 0;
  itemsService = inject(ItemsService)

  categoryId: number = 1;
  categoryName: string = '';

  constructor(
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.categoryId = params['id'];
      this.getItemsByCategoryId(this.categoryId);
    })

    this.route.queryParams.subscribe(params=> {
      this.categoryName = params['category'];
    })
  }

  //fetch the items from backend based on user clicked by calling method from service
  public getItemsByCategoryId(id: number) {
    this.itemsService.getItemsByCategoryId(id).subscribe(data => {
      this.items = data;
      this.itemCount = data.length;
    })
  }

}
