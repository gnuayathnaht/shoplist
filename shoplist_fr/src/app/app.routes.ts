import { Routes } from '@angular/router';
import { ItemsComponent } from './components/items/items.component';
import { ItemDetailsComponent } from './components/item-details/item-details.component';

export const routes: Routes = [
    {
        path: '',
        component: ItemsComponent
    },
    {
        path: 'items/:itemId',
        component: ItemDetailsComponent
    }
];
