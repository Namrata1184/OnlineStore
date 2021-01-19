import { CustomerService } from './../customer.service';

import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-customer-list',
  templateUrl: './customer-list.component.html',
  styleUrls: ['./customer-list.component.css']
})
export class CustomerListComponent implements OnInit {

  users=[]

  constructor( private customerService:CustomerService) { }

  ngOnInit(): void {
    this.loadUsers()
  }

  loadUsers(){
    this.customerService.getUsers().subscribe(response=>{
      if(response['status']=='error'){
        
          console.log(response['error'])
      }else{
        console.log('inside loadusers');
        console.log(response['data'])

        for (let pet of response['data']) {
          
          console.log(pet); // "species"
        }
          this.users=response['data']
        
      }
    })
  }

}
