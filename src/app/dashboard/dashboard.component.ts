import { AdminService } from './../admin.service';
import { DataService } from './../data.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  userCount:number
  bookCount:number
  cartCount:number

  constructor(
    private service:AdminService
  ) { }

  ngOnInit(): void {
    this.getUserCount()
    this.getBooksCount()
    this.getCartCount()
  }

  
  getUserCount(){
    this.service.getUser().subscribe(response=>{
      if(response['status']=='success'){
        this.userCount=response['data']
      }
    }
      )
  }

  getBooksCount(){
    this.service.getBooks().subscribe(response=>{
      if(response['status']=='success'){
        this.bookCount=response['data']
      }
    })
  }

  getCartCount(){
    this.service.getcart().subscribe(response=>{
      if(response['status']=='success'){
        this.cartCount=response['data']
      }
    })
  }


}
