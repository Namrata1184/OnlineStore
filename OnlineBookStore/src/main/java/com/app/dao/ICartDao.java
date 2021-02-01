package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.pojos.Cart;



public interface ICartDao extends JpaRepository<Cart, Integer> {
	
	//@Query(value = "SELECT u FROM User u where u.email=?1 and u.password=?2")
	//String addToCart(Cart item);
	
	//@Query(value="delete  from Cart c where c.cart_id = ?1")
	//void removeFromCart(int id);
	
	//Optional<Cart> findById(int id);
	
	//Optional<Book> findByTitle(String title);
	
}
