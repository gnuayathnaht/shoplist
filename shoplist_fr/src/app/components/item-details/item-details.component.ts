import { Component, inject, Input, viewChild } from '@angular/core';
import { Item } from '../../model/item.model';
import { ItemsService } from '../../services/items.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Category } from '../../model/category.model';
import { CategoryService } from '../../services/category.service';

@Component({
  selector: 'app-item-details',
  imports: [],
  templateUrl: './item-details.component.html',
  styleUrl: './item-details.component.css'
})
export class ItemDetailsComponent {

  item!: Item;
  itemCount: number = 1;
  itemsService = inject(ItemsService);

  categories!: Category[];
  categoryName: string = '';
  categoryService = inject(CategoryService);

  router = inject(Router);
  route = inject(ActivatedRoute);

  @Input({ required: true }) itemId!: number;

  ngOnInit() {
    console.log("ItemDetailsComponent initialized with itemId: " + this.itemId);
    this.getItemById(Number(this.itemId));

    this.categoryService.getAllCategories().subscribe(data => {
      console.log('data ', data);
      this.categories = data;
    })
  }

  increaseItemCount() {
    this.itemCount += 1;
  }

  decreaseItemCount() {
    this.itemCount -= 1;
    if (this.itemCount <= 1) this.itemCount = 1;
  }

  getItemById(id: number) {
    if (id) {
      //fetch the item by using id from server or maybe from service
      this.itemsService.getItemByID(this.itemId).subscribe(data => {
        this.item = data;
      })
    }
  }

  addToCart(item: Item) {

    // looking item's category
    this.categoryName = this.categories.find(category =>
      category.items.some(i => i.id === item.id)
    )?.name || '';

    console.log('category name', this.categoryName);


    let isLogin: boolean = false;
    if (isLogin) {
      //add item to cart
      if (item.id) {
        let cart = {
          id: item.id,
          categoryName: this.categoryName,
          orderItem: item.name,
          price: item.price,
          imgPath: item.imagePath,
          quantity: this.itemCount
        }
        console.log('add to cart item', cart);
      }
    }
    else {
      let isExistAcc: boolean = false;
      if (isExistAcc) {
        //ask user to login

      }
      else {
        //ask user to register

        //after register, force user to login
      }
    }
  }
}
