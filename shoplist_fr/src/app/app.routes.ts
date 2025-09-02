import { Routes } from '@angular/router';
import { ItemsComponent } from './components/items/items.component';
import { ItemDetailsComponent } from './components/item-details/item-details.component';
import { RegisterComponent } from './components/register/register.component';
import { LoginComponent } from './components/login/login.component';
import { ProfileComponent } from './components/profile/profile.component';
import { unsavedChangesGuard } from './guard/unsaved-changes.guard';

export const routes: Routes = [
    {
        path: '',
        component: ItemsComponent
    },
    {
        path: 'items/:itemId',
        component: ItemDetailsComponent
    },

    {
        path: 'register',
        component: RegisterComponent,
        canDeactivate: [unsavedChangesGuard]
    },
    {
        path: 'login',
        component: LoginComponent
    },
    { path: 'profile',
         component: ProfileComponent,
         canDeactivate: [unsavedChangesGuard] }
         
];
