import { Routes } from '@angular/router';
import { ItemsComponent } from './components/items/items.component';
import { ItemDetailsComponent } from './components/item-details/item-details.component';
import { RegisterComponent } from './components/register/register.component';
import { LoginComponent } from './components/login/login.component';
import { ProfileComponent } from './components/profile/profile.component';
import { unsavedChangesGuard } from './guard/unsaved-changes.guard';
import { CategoryComponent } from './components/category/category.component';
import { SearchedItemsComponent } from './components/searched-items/searched-items.component';
import { CartComponent } from './components/cart/cart.component';
import { CheckoutComponent } from './components/checkout/checkout.component';
import { OrderSuccessComponent } from './components/order-success/order-success.component';
import { OrderHistoryComponent } from './components/order-history/order-history-component';
import { OrderDetailComponent } from './components/order-detail/order-detail-component';

export const routes: Routes = [
  {
    path: '',
    component: ItemsComponent,
  },
  {
    path: 'items/:itemId',
    component: ItemDetailsComponent,
  },
  {
    path: 'register',
    component: RegisterComponent,
    canDeactivate: [unsavedChangesGuard],
  },
  {
    path: 'login',
    component: LoginComponent,
    canDeactivate: [unsavedChangesGuard],
  },
  {
    path: 'profile',
    component: ProfileComponent,
    canDeactivate: [unsavedChangesGuard],
  },
  {
    path: 'items/:itemId',
    component: ItemDetailsComponent,
  },
  {
    path: 'category/:id',
    component: CategoryComponent,
  },
  {
    path: 'items',
    component: SearchedItemsComponent,
  },
  {
    path: 'cart',
    component: CartComponent,
  },
  {
    path: 'checkout',
    component: CheckoutComponent,
  },
  {
    path: 'order/success',
    component: OrderSuccessComponent,
  },
  { path: 'orders', component: OrderHistoryComponent },
  { path: 'orders/:orderId', component: OrderDetailComponent },
];
