import { DataService } from './../data.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.css']
})
export class OrdersComponent implements OnInit {

  items:[]
  user:any = JSON.parse(sessionStorage.getItem('user'));
  users:[]


  constructor(private service:DataService) { }

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
            const user=item['user_id']

           /*  {user_id: 124, first_name: "Vaibhav", address: "kolhapur", city: null, country: null, …} */
            console.log(item['user_id'])
            console.log(user)
           

            
          }
          
        }
      })
  }


}
