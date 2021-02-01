package com.app.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//import org.hibernate.validator.constraints.*;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "books")
public class Book{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BookId", insertable = false, updatable = false)
	@JsonProperty("Book_id")
	private Integer book_id;
	// Validation rules in back end
	// @NotBlank(message="title cant be blank")
	@Column(length = 30, unique = true)
	@JsonProperty("Title")
	private String title;
	@Column(length = 500)
	@JsonProperty("Description")
	private String description;
	@JsonProperty("Price")
	private double price;
	@Column(unique = true)
	@JsonProperty("ISBN")
	private String isbn;
	@Column(length = 50)
	@JsonProperty("Author")
	private String author;
	@JsonProperty("count")
	private int count;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Book() {
		System.out.println("in ctor of Book");
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Integer getBook_id() {
		return book_id;
	}

	public void setBook_id(Integer book_id) {
		this.book_id = book_id;
	}

	
	  
	/*
	 * public int compareTo(Book b) { System.out.println("in compare To"); return
	 * 
	 * this.getTitle().compareToIgnoreCase(b.getTitle()); }
	 */ /*compareTo(); }*/
	  
	 

	@Override
	public String toString() {
		return "Book [book_id=" + book_id + ", title=" + title + ", author=" + author + ", count=" + count
				+ ", description=" + description + ", price=" + price + ", isbn=" + isbn + "]";
	}

}
