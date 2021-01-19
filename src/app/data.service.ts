import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { identifierModuleUrl, Identifiers } from '@angular/compiler';

@Injectable({
  providedIn: 'root'
})
export class DataService {
 

   url='http://localhost:7071/cart';

  constructor(public http:HttpClient) { }



  getBooks(){
    const url='http://localhost:7071/books'
    const request=this.http.get(url)
    return request
  }

  getCartItems(){
   
    const request=this.http.get(this.url)
    return request
  }
// this.service.addCartItems(book['Book_id'],book['Price'],1,book['Title'],this.user.user_id)
  
  addCartItems(Book_id:number,Price:number,Quantity:number,Title:string,user_id:number){
    console.log('service method')
    console.log(user_id)
    
    const body={
      Book_id:Book_id,
      Price:Price,
      quantity:Quantity,
      user_id:user_id,
      Title:Title,

   
    }
    sessionStorage['cartItem']=body.Title;
    console.log(body)
    
    return this.http.post(this.url + "/add", body)
  }

  getBooksDetails(Book_id:number){
    const url='http://localhost:7071/books'
    const request=this.http.get(url+"/details/"+Book_id)
    return request
  }

  deleteItem(id:number){
    const delurl='http://localhost:7071/cart';
    // this.router.navigate(['/view'],{queryParams:{id: book['Book_id']}})
   return this.http.delete(delurl+"/"+id )
  }

  AddUserData(user)
  {
    console.log(user);
    return this.http.post("http://localhost:7071/users/registration",user);
  }

}
