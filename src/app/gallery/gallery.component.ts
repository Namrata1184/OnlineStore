import { ToastrService } from 'ngx-toastr';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { DataService } from '../data.service';

@Component({
  selector: 'app-gallery',
  templateUrl: './gallery.component.html',
  styleUrls: ['./gallery.component.css']
})
export class GalleryComponent implements OnInit {
  title:''
  description:''
  price:0
  author:''
  isbn:''
  


  books:{}

  constructor(public service:DataService,private router: Router,
    private toster:ToastrService) { }

  ngOnInit(): void {
    this. loadBooks();
  }
  //(click)="onView(book)"
  onView(book){
    this.router.navigate(['/view'],{queryParams:{id: book['Book_id']}})

  }

  loadBooks(){
    const request=this.service.getBooks()
    request.subscribe(response=>{
     /* console.log(response) */
      if(response[status]=='error'){
        console.log('Books list fetching failed')
       
      }else{
        /* console.log(response['error']) */
        this.books=response['data'];
       /*  console.log(this.books);
        console.log('inside if'); */
        
        
      }
      console.log('books loaded');
    })
    }


}
