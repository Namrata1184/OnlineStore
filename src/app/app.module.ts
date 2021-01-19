//import { BookService } from './../../../bookStore/src/app/book.service';
import { OrderService } from './order.service';
import { AdminService } from './admin.service';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { CustomerListComponent } from './customer-list/customer-list.component';
import { BookListComponent } from './book-list/book-list.component';
import { CartComponent } from './cart/cart.component';
import { LoginComponent } from './login/login.component';
import { SignupComponent } from './signup/signup.component';
import { BookAddComponent } from './book-add/book-add.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { UploadImageComponent } from './upload-image/upload-image.component';
import { ProfileComponent } from './profile/profile.component';
import { AddressAddComponent } from './address-add/address-add.component';
import { GalleryComponent } from './gallery/gallery.component';
import { PreviewComponent } from './preview/preview.component';
import { BookService } from './book.service';
import{ToastrModule} from 'ngx-toastr';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ViewComponent } from './view/view.component';
import { OrdersComponent } from './orders/orders.component';
import { PaymentComponent } from './payment/payment.component';



@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    CustomerListComponent,
    BookListComponent,
    CartComponent,
    LoginComponent,
    SignupComponent,
    BookAddComponent,
    DashboardComponent,
    UploadImageComponent,
    ProfileComponent,
    AddressAddComponent,
    GalleryComponent,
    PreviewComponent,
    ViewComponent,
    OrdersComponent,
    PaymentComponent,
   
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ToastrModule.forRoot(),
    BrowserAnimationsModule
   
  ],
  providers: [
    AdminService,
    OrderService,
    BookService,
    OrderService

  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
