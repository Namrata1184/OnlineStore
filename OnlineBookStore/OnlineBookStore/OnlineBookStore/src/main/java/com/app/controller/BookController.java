package com.app.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.app.dto.ResponseDTO;
import com.app.pojos.Book;
import com.app.service.IBookService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController //@Controller +@ResponseBody(placed auto on request handling methods)
@RequestMapping("/books") //recom :use name of resource:to be manupulated by front end appl using url
//@CrossOrigin(origins = "*")
@CrossOrigin
public class BookController {
	
	@Autowired
	private IBookService service;
	
	public BookController() {
		System.out.println("in ctor of "+getClass().getName());
	}
	
	@GetMapping
	public ResponseEntity<?> getAvailableBooks()
	{
		System.out.println("in get avail books");
		List<Book>books=service.getAllBooks(); //Rest controller-->@REsponseBody list-->DS-->marshlling
		if(books.isEmpty())
			return new ResponseEntity<>(new ResponseDTO("error","Books list fetching failed" ,null),HttpStatus.NO_CONTENT);
	
	return new ResponseEntity<>(new ResponseDTO("success", "List of All books", books),HttpStatus.OK);	
	
	}
	//get book details by title:supplied by client using path variable
	@GetMapping("/{BookTitle}")
	public ResponseEntity<?> getBookDetails(@PathVariable String BookTitle)
	{
		System.out.println("in get details by book title");
		Optional<Book> bookDetails=service.getBookDetails(BookTitle);
		//in case invalid name:HTTP 404
		//valid name:http 200, marshaled book details
		if(bookDetails.isPresent()) {
			return new ResponseEntity<>(new ResponseDTO("success", "book details",bookDetails.get()), HttpStatus.OK);
		}
		
		return new ResponseEntity<>(new ResponseDTO("error","Books list fetching failed" ,null),HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("details/{BookId}")
	public ResponseEntity<?> getBookDetails(@PathVariable int BookId)
	{
		System.out.println("in get details by book title");
		Optional<Book> bookDetails=service.getBookDetails(BookId);
		//in case invalid name:HTTP 404
		//valid name:http 200, marshaled book details
		if(bookDetails.isPresent()) {
			return new ResponseEntity<>(new ResponseDTO("success", "book details",bookDetails.get()), HttpStatus.OK);
		}
		
		return new ResponseEntity<>(new ResponseDTO("error","Books list fetching failed" ,null),HttpStatus.NOT_FOUND);
	}
	
	
	
	//update existing product
	@PutMapping("/{id}")
	public ResponseEntity<?>updateBook(@PathVariable int id,@RequestBody Book book){
		System.out.println("in update product");
		try {
			Book updatedBook=service.updateBookDetails(id, book);
			return new ResponseEntity<>(new ResponseDTO("success", "book updated",updatedBook), HttpStatus.OK);
		}catch(RuntimeException e){
			e.printStackTrace();
			return new ResponseEntity<>(new ResponseDTO("error","Invalid book Id" ,null),HttpStatus.NOT_FOUND);
		}
		
	}
	
	   //req handling method to delete existing book
		@DeleteMapping("/{eid}")
		public ResponseEntity<?> deleteBook(@PathVariable int book_id)
		{
			System.out.println("in del customer "+book_id);
			return new ResponseEntity<>(new ResponseDTO("success",service.deleteBook(book_id),null), HttpStatus.OK);
		}
		
		
		@PostMapping
		public ResponseEntity<?>addBook(@RequestParam(value = "Book", required = false) Book p,@RequestParam(value ="uploadData", required = false) MultipartFile imageFile)
		{
			System.out.println("in add book "+p+" "+imageFile);
			System.out.println("data " + p + " " + imageFile.getOriginalFilename());
			
			
			try {
				p.setImage(imageFile.getBytes());
				p.setImageContentType(imageFile.getContentType());
				Book newBook=service.addBookDetails(p);
				
				return new ResponseEntity<>(new ResponseDTO("success", "New book added", newBook), HttpStatus.OK);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<>(new ResponseDTO("error","book Registration failed" ,null),HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		
		/*
		 * @PostMapping("/upload") public ResponseDTO fileUploadWithParams(@RequestParam
		 * String dtls, @RequestParam MultipartFile imageFile) {
		 * System.out.println("data " + dtls + " " + imageFile.getOriginalFilename());
		 * try { //un marshalling json--> java 
		 * User u = new ObjectMapper().readValue(dtls, User.class); u.setImage(imageFile.getBytes());
		 * u.setImageContentType(imageFile.getContentType());
		 * 
		 * // imageFile.transferTo(new File(location, imageFile.getOriginalFilename()));
		 * // System.out.println("user dtls " + u); userRepo.save(u);
		 * 
		 * } catch (Exception e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } return new ResponseDTO("File uploaded :" +
		 * imageFile.getOriginalFilename() + " @ ", LocalDateTime.now()); }
		 */
}
