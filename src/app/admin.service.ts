import { ToastrService } from 'ngx-toastr';
import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';


@Injectable({
  providedIn: 'root'
})
export class AdminService implements CanActivate{

 
  constructor( private router:Router,
    private httpclient:HttpClient,
    private toastr:ToastrService) { }
 

login(email:string,password:string)
{
     var url='http://localhost:7071/users/auth'
     
     const body=
     {
       username:email,
       password:password
      }
      return this.httpclient.post(url,body)
    
  }

    

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot){
     // sessionStorage['name']
      if(sessionStorage['role']=='ADMIN'){
          //user is logged in
         //launch the componnent
        return true
      }
       console.log("User Has not Logged in");
       //force user to login
      this.router.navigate(['/login'])
      //stop launching the component

      return false
    }

    

}
