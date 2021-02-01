package com.app.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "cart")
public class Cart {

	@Id // pk
	@GeneratedValue(strategy = GenerationType.IDENTITY) // for autoincrement
	private int cart_id;
	@Column(nullable = false)
	private int Quantity;
	@JsonProperty("Book_id")
	private int book_id;
	@JsonProperty("Price")
	private int price;
	//@Formula("price*Quantity")
	private double Total_amount;

	// many to one side Orders:child,owning side(since fk column appears here)
	// joincolumn:to give the fk column name and to avoid null in fk
	
	
	  @ManyToOne(fetch = FetchType.LAZY)
	  @JoinColumn(name="user_id",referencedColumnName = "user_id",nullable = false) 
	  private User user_id;
	  
		/*
		 * //1 cart many books
		 * 
		 * @OneToMany
		 * 
		 * @JoinColumn(name="book_id", referencedColumnName="book_id") private
		 * List<Book> book_id=new ArrayList<>();
		 * 
		 */
	public Cart() {
		System.out.println("in ctor of cart");
	}
	
	public User getUser_id() {
		return user_id;
	}

	public void setUser_id(User user_id) {
		this.user_id = user_id;
	}

	/*
	 * public List<Book> getBook_id() { return book_id; }
	 * 
	 * public void setBook_id(List<Book> book_id) { this.book_id = book_id; }
	 */
	public int getCart_id() {
		return cart_id;
	}

	public void setCart_id(int cart_id) {
		this.cart_id = cart_id;
	}
	

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getQuantity() {
		return Quantity;
	}

	public void setQuantity(int quantity) {
		Quantity = quantity;
	}

	public double getTotal_amount() {
		return Total_amount;
	}

	public void setTotal_amount(double total_amount) {
		Total_amount = total_amount;
	}
	
	public int getBook_id() {
		return book_id;
	}

	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}

	@Override
	public String toString() {
		return "Cart [cart_id=" + cart_id + ", Quantity=" + Quantity + ", Total_amount=" + Total_amount 
				+ "user_id=" + user_id +  "book_id=" +book_id+"]" ;
	}

}
