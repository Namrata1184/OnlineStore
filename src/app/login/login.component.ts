import { AdminService } from './../admin.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

// export const toMd5 = (input: string): string => Md5.hashStr(input).toString();

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
 
  username=''
  password=''
  message='';

  constructor(
    private router:Router,
    private adminService:AdminService,
    private toastr:ToastrService
    ) {
   
   }

  ngOnInit(): void {}

  onLogin(){
    //let isUserValid=true;
    if(this.username.length==0){
      this.toastr.error('please enter email')
    }else if(this.password.length==0){
      this.toastr.error('please enter password')
    }else{
     this.adminService
    .login(this.username,this.password).subscribe((response)=>
    {
      if(response['status']=='success')
      {
         const data=response['data']
        sessionStorage['role']=data['role']

         //go to dashboard
          if(data['role']=='ADMIN')
          {
            this.toastr.success('welcome To Online Book Store ')
              window.sessionStorage.setItem("isActive", "1");
              sessionStorage.setItem("user",JSON.stringify(data));
              this.router.navigate(['/dashboard'])
             
          }else if( data['role']=='CUSTOMER'){
           
              sessionStorage['user_id']=data['user_id']
              this.toastr.success('welcome To Online Book Store ')
              window.sessionStorage.setItem("isActive", "2");
              sessionStorage.setItem("user",JSON.stringify(data));
              this.router.navigate(['/gallery'])
              }else{
                this.toastr.error('please check ur credentials')
              }
      }else{
        this.toastr.error('please check ur credentials')
        this.router.navigate(['/login']);
      }
      
    })
   
  }    
     
  }

}
