package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ResponseDTO;
import com.app.pojos.Cart;
import com.app.service.ICartService;

@RestController
@RequestMapping("/cart")
@Validated
@CrossOrigin(origins = "*")
public class CartController {
	@Autowired
	private ICartService service;
	
	public CartController() {
		super();
		System.out.println("in ctor of"+getClass().getName());
	}



	@PostMapping("/add")
	public ResponseEntity<?> addToCart(@RequestBody Cart item)
	{
		System.out.println("in add to cart  "+item);
		int q=item.getQuantity();
		int p=item.getPrice();
		double total=p*q;
		
		String msg=service.addToCart(item);
		return new ResponseEntity<>(new ResponseDTO("success","New Items added in cart.", msg), HttpStatus.OK);
	}
	
	@DeleteMapping("/del/{id}")
	public ResponseEntity<?>removeFromCart(@PathVariable int id){
		System.out.println("in remove cart "+id);
		String msg=service.removeFromCart(id);
		return new ResponseEntity<>(new ResponseDTO("success","order get deleted.", msg), HttpStatus.OK);
		}
	
	@GetMapping()
	public ResponseEntity<?>displayCart()
	{
		System.out.println("in display cart");
		List<Cart> items=service.displayCart();
		if(items.isEmpty())
		{
			return new ResponseEntity<>(new ResponseDTO("error","cart is empty .", null), HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(new ResponseDTO("success","cart details as follows.", items), HttpStatus.OK);
	}
	


}
