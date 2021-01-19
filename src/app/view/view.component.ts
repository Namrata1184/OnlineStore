import { ToastrService } from 'ngx-toastr';
import { DataService } from './../data.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-view',
  templateUrl: './view.component.html',
  styleUrls: ['./view.component.css']
})
export class ViewComponent implements OnInit {

  Book_id=0
  Title=''
  Description=''
  Price=0
  count=0
  ISBN=''
  Author=''
  selectedFile: File = null;
  quantity:number;
  user:any = JSON.parse(sessionStorage.getItem('user'));

  book=null
  

  constructor(private activatedRoute:ActivatedRoute,
    private router:Router,
    private service:DataService,
    private tostre:ToastrService) { }

  ngOnInit(): void {

    const id= this.activatedRoute.snapshot.queryParams['id']
    console.log(id);
   if(id){
     this.service.getBooksDetails(id).subscribe(response=>{
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
        this.Description=this.book['Description']
       }
     })
   }
  }

  Previous()
  {
    this.router.navigate(['/gallery'])
  }

  addToCart(){
    console.log('ts method')
    this.service.addCartItems(this.book['Book_id'],this.book['Price'],this.quantity,this.book['Title'],this.user.user_id)
    .subscribe(Response=>{
      if(Response['status']=='success'){
        this.tostre.success('added to cart')
        this.router.navigate(['/cart']); 
      }else{
        console.log('not added to cart')
        this.router.navigate(['/gallery']); 
      }
    })

  }

}
