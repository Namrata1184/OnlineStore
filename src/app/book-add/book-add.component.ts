import { BookService } from './../../../../admin-panel/src/app/book.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-book-add',
  templateUrl: './book-add.component.html',
  styleUrls: ['./book-add.component.css']
})
export class BookAddComponent implements OnInit {
  Book_id=0
  Title=''
  Description=''
  Price=0
  count=0
  ISBN=''
  Author=''
  selectedFile: File = null;

  book=null
  constructor(
    private router:Router,
    private activatedRoute:ActivatedRoute,
    private bookService:BookService) { }

  ngOnInit(): void {
   const id= this.activatedRoute.snapshot.queryParams['id']
   if(id){
     this.bookService.getBooksDetails(id).subscribe(response=>{
       if(response['status']=='error'){

       }else{
         console.log('get book details')
       this.book=response['data']
         console.log(this.book)
        /*  console.log('length'+books.length)
         if(books.length>0){
           const book=books[0]
           console.log(book)
         } */
        this.Title=this.book['Title']
        this.Description=this.book['Description']
        this.Price=this.book['Price']
        this.Author=this.book['Author']
        this.count=this.book['count']
        this.ISBN=this.book['ISBN']
       }
     })
   }
  }


  onUpdate(){

    if(this.book){
      //update
      this.bookService.updateBook(this.book['Book_id'],this.Title,this.Description, this.ISBN, this.Price, this.count,this.Author)
      .subscribe(response=>{
        if(response['status']=='success'){
          this.router.navigate(['/book-list'])
  
        }
      })
  }else{
      //insert
      this.bookService.addBook(this.Title,this.Description, this.ISBN, this.Price, this.count,this.Author,this.selectedFile)
      .subscribe(response=>{
        if(response['status']=='success'){
          console.log('add book in bookcomponent')
          this.router.navigate(['/book-list'])
  
        }
      })
   }

}

  onFileChanged(file: File): void {
    this.selectedFile=file;

  }

 /*  onUpload(){
    this.router.navigate(['/upload-image'], {queryParams: {id: this.book['id']}})
  }
 */
}
