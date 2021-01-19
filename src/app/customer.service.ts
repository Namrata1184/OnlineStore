import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';


@Injectable({
  providedIn: 'root'
})
export class CustomerService implements CanActivate{

  url='http://localhost:7071/users'

  constructor(
    private httpClient:HttpClient,
    private router:Router) { }

    getUsers(){
      console.log('in get user');
      return this.httpClient.get(this.url)
    }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot){
      // sessionStorage['name']
       if(sessionStorage['role']=='CUSTOMER'){
           //user is logged in
          //launch the componnent
         return true
        }
        this.router.navigate(['/login'])
 
       return false
     }



}
