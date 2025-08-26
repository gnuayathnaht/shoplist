import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-item-details',
  imports: [],
  templateUrl: './item-details.component.html',
  styleUrl: './item-details.component.css'
})
export class ItemDetailsComponent {

  @Input({required: true}) itemId!: number;

  ngOnInit() {
    console.log("ItemDetailsComponent initialized with itemId: " + this.itemId);
  }
}
