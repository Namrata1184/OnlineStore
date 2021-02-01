package com.app.service;

import java.util.List;
import java.util.Optional;

import com.app.pojos.Cart;

public interface ICartService {
	
	//add to cart
		String addToCart(Cart item);
		
		//remove from cart
		//String removeFromCart(int id);
		
		//display cart
		
		long totalCount();
		
		List<Cart>displayCart();

		void removeFromCart(int id);
		//void deleteById(int eid);
		Optional<Cart> findById(int id);

}
