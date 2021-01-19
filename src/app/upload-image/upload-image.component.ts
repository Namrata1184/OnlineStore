import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { BookService } from '../book.service';

@Component({
  selector: 'app-upload-image',
  templateUrl: './upload-image.component.html',
  styleUrls: ['./upload-image.component.css']
})
export class UploadImageComponent implements OnInit {

  selectedFile = null
  constructor(
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private bookService: BookService) { }
  

  ngOnInit(): void {
  }
  onImageSelect(event) {
    this.selectedFile = event.target.files[0]
  }

  /* onUploadImage() {
    const id = this.activatedRoute.snapshot.queryParams['id']
    this.bookService
      .uploadImage(id, this.selectedFile)
      .subscribe(response => {
        if (response['status'] == 'success') {
          this.router.navigate(['/product-list'])
        } else {
          console.log(response['error'])
        }
      })
  } */

}
