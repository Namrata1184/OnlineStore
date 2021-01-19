import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css']
})
export class PaymentComponent implements OnInit {
  message=""
 sum:number
  constructor(
    private router:Router
  ) { }

  ngOnInit(): void {
  }

  cancel(){
    this.router.navigate(['cart'])

  }

  pay(){
    const msg= confirm("Order Placed SuccessFully!!!")

  }

}
