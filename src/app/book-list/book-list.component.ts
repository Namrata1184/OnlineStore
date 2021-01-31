import { ToastrService } from 'ngx-toastr';
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { BookService } from '../book.service';

@Component({
  selector: 'app-book-list',
  templateUrl: './book-list.component.html',
  styleUrls: ['./book-list.component.css']
})
export class BookListComponent implements OnInit {
  title:''
  description:''
  price:0
  author:''
  isbn:''



 books= [];

  //dependanccy Injection via constructor
  constructor(
    private bookService:BookService,
    private router:Router ,
    private toastr:ToastrService,
    private activatedRoute:ActivatedRoute
    ) {}

  ngOnInit(): void {
    this.loadBooks()
  }

  loadBooks(){
    const request=this.bookService.getBooks()
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

    onEdit(book){
      this.router.navigate(['/book-add'],{queryParams:{id:book['Book_id']}})
    }

     addBook(){
      this.router.navigate(['/book-add'])
     }

     onDelete(book){
     
          this.bookService.DeleteBook(book['Book_id']).subscribe(response=>{
            console.log('delete book')
            if(response[status]=='error'){
              this.toastr.error('book doesnt exist..! ')
             
            }else{
              this.toastr.success(response['message'])
              //this.router.navigate(['/book-list'])
             
             
            }
            this.loadBooks();
           
          })
     }
      


    }
     
  