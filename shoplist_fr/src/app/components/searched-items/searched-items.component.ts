import { Component, inject } from '@angular/core';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { ItemsService } from '../../services/items.service';
import { Item } from '../../model/item.model';
import { ItemComponent } from '../item/item.component';


@Component({
  selector: 'app-searched-items',
  imports: [
    RouterLink,
    ItemComponent
  ],
  templateUrl: './searched-items.component.html',
  styleUrl: './searched-items.component.css'
})
export class SearchedItemsComponent {

  items !: Item[];
  itemCount: number = 0;
  itemsService = inject(ItemsService);

  searchKeyword: string = '';
  route = inject(ActivatedRoute);

  ngOnInit() {
    this.route.queryParamMap.subscribe(params => {
      this.searchKeyword =  params.get('search') || '';
      this.itemsService.getItemsByKeyword(this.searchKeyword).subscribe(items => {
        this.items = items;
        this.itemCount = items.length;
      });
    })
  }

}
