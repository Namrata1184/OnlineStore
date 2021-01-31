import { ToastrService } from 'ngx-toastr';
import { DataService } from './../data.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  public cities = [{name:"kolhapur"},{name:"Pune"},{name:"Satara"},{name:"Nagpur"},{name:"Sangali"}];

  public state = [{name:"Maharashtra"},{name:"AP"},{name:"MP"},{name:"UP"},{name:"Panjab"},{name:"Keral"}];

  usern:any = JSON.parse(sessionStorage.getItem('user'));
  user1:any

  user_id:number
  first_name:String
  Last_name:string
  contavt_no:string
  email:string
  contact_no:number
  username:string
  zip:number
  state1:string
  city:string
  address:string
  password:string
 
  user:{"role":"CUSTOMER"};
  constructor(private router:Router,
    private service:DataService,
    private activatedRoute:ActivatedRoute,
    private toastr:ToastrService) { }

  ngOnInit(): void {

    const id= this.usern.user_id
   if(id){
     this.service. getUserDetails(id).subscribe(response=>{
       if(response['status']=='error'){

       }else{
         console.log('get book details')
       this.user1=response['data']
         console.log(this.user1)
       this.first_name=this.user1['first_name']
       this.Last_name=this.user1['last_name']
       this.username=this.user1['username']
       this.email=this.user1['email']
       this.zip=this.user1['zip']
       this.city=this.user1['city']
       this.state=this.user1['country']
       this.contact_no=this.user1['contact_no']
       this.address=this.user1['address']
       this.password=this.user1['password']
       }
     })
   }
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
