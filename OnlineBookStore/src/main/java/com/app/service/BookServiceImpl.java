package com.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.cust_excs.ResourceNotFoundException;

import com.app.dao.IBookDao;
import com.app.pojos.Book;

@Service
@Transactional
public class BookServiceImpl implements IBookService{
	//Dependency
	@Autowired
	private IBookDao dao;
	
	@Override
	public List<Book> getAllBooks() {
		System.out.println("dao impl class  "+dao.getClass().getName());
		return dao.findAll();
	}
	@Override
	public Optional<Book> getBookDetails(String title) {
		
		return dao.findByTitle(title);
	}
	
	
	@Override
	public Optional<Book> getBookDetails(int id) {
		
		return dao.findById(id);
	}
	
	@Override
	public Book addBookDetails(Book transientPOJO) {
		
		return dao.save(transientPOJO);
	}
	@Override
	public Book updateBookDetails(int bookid, Book b1) {
		//check if product exist
		System.out.println("book to update "+b1);
		Optional<Book>b=dao.findById(bookid);
		if(b.isPresent()) {
			//b.get():persistent  b1:detached pojo :contains updates send by client
			Book book=b.get();
			book.setAuthor(b1.getAuthor());
			book.setCount(b1.getCount());
			book.setIsbn(b1.getIsbn());
			book.setPrice(b1.getPrice());
			book.setTitle(b1.getTitle());
			
			
			return book;
		}
		throw new ResourceNotFoundException("Invalid Book ID");
	}
	@Override
	public String deleteBook(int bookId) {
		
		dao.deleteById(bookId);
		return "Book with id "+bookId+"deleted successfully";
	}

	@Override
	public long totalCount() {
		return dao.count();
	}

}
