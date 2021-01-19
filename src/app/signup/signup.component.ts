import { ToastrService } from 'ngx-toastr';
import { DataService } from './../data.service';
import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  public city = [{name:"kolhapur"},{name:"Pune"},{name:"Satara"},{name:"Nagpur"},{name:"Sangali"}];

public state = [{name:"Maharashtra"},{name:"AP"},{name:"MP"},{name:"UP"},{name:"Panjab"},{name:"Keral"}];
 
  user:{"role":"CUSTOMER"};
  constructor(private router:Router,
    private service:DataService,
    private toastr:ToastrService) { }

  ngOnInit(): void {
  }

  GoBackHome()
  {
      this.router.navigate(['/login'])
  }

  /* if(response['status']=='error'){

  }else{
    console.log('get book details')
  this.book=response['data']
    console.log(this.book)
 
   this.Title=this.book['Title']
   this.Description=this.book['Description']
   this.Price=this.book['Price']
   this.Author=this.book['Author']
   this.count=this.book['count']
   this.ISBN=this.book['ISBN']
  }
}) */

  AddUser(myForm)
  {
    console.log(myForm.form.value);
    this.user=myForm.form.value;
    this.user.role="CUSTOMER"
    console.log(this.user);
    this.service.AddUserData(this.user).subscribe(response=>{
      if(response['status']=='success')
      {
        this.toastr.success('registered successfully')
        this.router.navigate(['/login'])
      }
      
    }
      
    )
  }


}
