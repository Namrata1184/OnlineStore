import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class BookService {
 
  
  
 
  httpClient:HttpClient
  //ProductService class is dependent on HttpClient
  //Dependancy Injection
  //Angular will inject an object of httpclient
  //while creating object of this class

  constructor(httpClient:HttpClient) { 
    this.httpClient=httpClient
    }

    getBooks(){
      const url='http://localhost:7071/books'
      const request=this.httpClient.get(url)
      return request
    }

    
    getBooksDetails(Book_id:number){
      const url='http://localhost:7071/books'
      const request=this.httpClient.get(url+"/details/"+Book_id)
      return request
    }

    DeleteBook(Book_id:number) {
      const url='http://localhost:7071/books'
      return this.httpClient.delete(url+"/"+Book_id)
    }

   
/* 
    uploadFile(selectedFile: File,user:UserDetails) {
      const uploadData = new FormData();
      uploadData.append("imageFile", selectedFile);
      console.log(`sending ${user}`);
    //  const userDtls=new UserDetails("madhura@gmail",27);
     // uploadData.append("dtls", "{'email' : 'rama@gmail.com','age' : 27}");
     uploadData.append("dtls",JSON.stringify(user));
     return this.http.post(this.baseURL.concat("upload"), uploadData, { responseType: 'text' });
    }
 */
    updateBook(Book_id:number, Title: string, Description: string, ISBN: string, Price: number, count: number, Author: string) {
      
      const body={
          
        Title:Title,
        Price:Price,
        Author:Author,
        ISBN:ISBN,
        count:count,
        Description:Description
        } 
         
        const url='http://localhost:7071/books'
      const request=this.httpClient.put(url+"/"+Book_id,body)
      return request


    }

   /*  addBook(Title: string, Description: string, ISBN: string, Price: number, count: number, Author: string) {
     console.log('in UploadImage')
     
      const url='http://localhost:7071/books'
      const book = {
        Title:Title,
        Description:Description,
        Price:Price,
        Author:Author,
        ISBN:ISBN,
        count:count
      }
      const body={
        Book: book
      }
      return this.httpClient.post(url,body);
    } */
   
    addBook(Title: string, Description: string, ISBN: string, Price: number, count: number, Author: string) {
      
      const body={
          
        Title:Title,
        Price:Price,
        Author:Author,
        ISBN:ISBN,
        count:count,
        Description:Description
        } 
         
        const url='http://localhost:7071/books'
      const request=this.httpClient.post(url+"/",body)
      return request


    }
  



  }
