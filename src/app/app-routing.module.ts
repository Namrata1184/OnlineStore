import { PaymentComponent } from './payment/payment.component';
import { OrdersComponent } from './orders/orders.component';
import { CustomerService } from './customer.service';
import { ViewComponent } from './view/view.component';

import { GalleryComponent } from './gallery/gallery.component';
import { AddressAddComponent } from './address-add/address-add.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { AdminService } from './admin.service';
import { BookAddComponent } from './book-add/book-add.component';
import { SignupComponent } from './signup/signup.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { CartComponent } from './cart/cart.component';
import { BookListComponent } from './book-list/book-list.component';
import { CustomerListComponent } from './customer-list/customer-list.component';
import { NgModule, Component } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ProfileComponent } from './profile/profile.component';

const routes: Routes = [
  {path:'',component:LoginComponent},
  {path:'customer-list',component:CustomerListComponent , canActivate:[AdminService]},
  {path:'book-list',component:BookListComponent, canActivate:[AdminService]},
  {path:'cart',component:CartComponent , canActivate:[CustomerService]},
  {path:'home',component:HomeComponent , canActivate:[AdminService]},
  {path:'book-add',component:BookAddComponent , canActivate:[AdminService]},
  {path:'dashboard',component:DashboardComponent,canActivate:[AdminService]},
  {path:'profile',component:ProfileComponent,canActivate:[AdminService]},
  {path:'address-add',component:AddressAddComponent,canActivate:[AdminService]},
  {path:'gallery',component:GalleryComponent,canActivate:[CustomerService]},
  {path:'view',component:ViewComponent,canActivate:[CustomerService]},
  {path:'orders',component:OrdersComponent,canActivate:[AdminService]},
  {path:'payment',component:PaymentComponent,canActivate:[CustomerService]},
 

  {path:'login',component:LoginComponent},
  {path:'signup',component:SignupComponent},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
