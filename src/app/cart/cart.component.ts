import { ToastrService } from 'ngx-toastr';
import { DataService } from './../data.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  items = []
  totalAmount = 0
 

  constructor(private service:DataService,
    private toastr:ToastrService,
    private router:Router) { }

  ngOnInit(): void {
    this.loadCartItems()
  }

  loadCartItems() {
    this.service
      .getCartItems()
      .subscribe(response => {
        console.log('loadCartItems')
        console.log(response)
        if (response['status'] == 'success') {
          this.items = response['data']
          for (let index = 0; index < this.items.length; index++) {
            const item = this.items[index];
            console.log(item)
            this.totalAmount += parseFloat(item['total_amount'])
          }
          
        }
      })
  }

  onDelete(item){
    this.service
      .deleteItem(item['cart_id'])
      .subscribe(response => { 
        console.log('in delete cart item')
        if (response['status'] == 'success') {
          let item = response['data']
          console.log(item)
          this.toastr.success("book item deleted successfully..!")
          //this.toastr.success(response['data'])
          this.router.navigate['/cart']
          
          }
      

      })

  }


}
