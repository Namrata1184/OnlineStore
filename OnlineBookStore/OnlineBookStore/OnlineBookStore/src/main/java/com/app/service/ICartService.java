package com.app.service;

import java.util.List;

import com.app.pojos.Cart;

public interface ICartService {
	
	//add to cart
		String addToCart(Cart item);
		
		//remove from cart
		String removeFromCart(int id);
		
		//display cart
		
		List<Cart>displayCart();


}
