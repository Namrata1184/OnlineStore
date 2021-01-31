import { ToastrService } from 'ngx-toastr';
import { DataService } from './../data.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { stringify } from '@angular/compiler/src/util';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {


  items = []
  newItems=[]
  totalAmount = 0
  usern:any = JSON.parse(sessionStorage.getItem('user'));
  cart_id=0
  item1:any;
 

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
            this.item1 = this.items[index];
           
            const user=this.item1['user_id'];
            
            if(user.user_id==this.usern.user_id)
            {
              
              const item=this.item1;
              this.newItems.push(item);
              console.log(item)
              console.log(this.item1)

              this.totalAmount += parseFloat(item['total_amount'])
            }

           
            
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
          this.router.navigate['/cart']
          this.toastr.success("book item deleted successfully..!")
          //this.toastr.success(response['data'])
         
          
          }
      

      })

  }

  addPayment()
  {
    this.service.addPayment(this.usern.user_id)
    .subscribe(response => {
      //if(response['status']=='success'){
        this.toastr.success("Your order Placed successfully")
        //window.sessionStorage.setItem("isActive", "1");
        //this.router.navigate(['/logout'])
        this.router.navigate(['/login'])
        //this.router.navigate(['/login']);
     // }

  })

  }

}