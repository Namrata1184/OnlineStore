package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.pojos.Cart;


public interface ICartDao extends JpaRepository<Cart, Integer> {
	
	//@Query(value = "SELECT u FROM User u where u.email=?1 and u.password=?2")
	//String addToCart(Cart item);
	
	
}
