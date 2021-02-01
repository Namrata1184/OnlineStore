package com.app.service;

import java.util.List;
import java.util.Optional;
import com.app.pojos.Book;

public interface IBookService {

	List<Book>getAllBooks();
	
	//get book details by name
	Optional<Book> getBookDetails(String title);
	//add new Book details
	Book addBookDetails(Book transientPOJO);
	
	Book updateBookDetails(int bookid,Book detachedPOJO);
	//delete existing product
	String deleteBook(int productID);

	Optional<Book> getBookDetails(int id);
}
