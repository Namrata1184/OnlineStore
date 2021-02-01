package com.app.controller;

import java.util.List;
import java.util.Optional;

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
		item.setTotal_amount(total);
		String msg=service.addToCart(item);
		return new ResponseEntity<>(new ResponseDTO("success","New Items added in cart.", msg), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?>removeFromCart(@PathVariable Integer id){
		System.out.println("in remove cart "+id);
		Optional<Cart> optional = service.findById(id);
		if (optional.isPresent()) {
			System.out.println("details  "/* +optional.get() */);
			Cart c=optional.get() ;
			service.removeFromCart(c.getCart_id());
			return new ResponseEntity<>(new ResponseDTO("success","Employee deleted with ID " + id,null), HttpStatus.OK);
		} else
			return new ResponseEntity<>(new ResponseDTO("error","Employee deletion failed" ,null), HttpStatus.NOT_ACCEPTABLE);		
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

	@GetMapping("/count")
	public ResponseEntity<?>totalCartItems()
	{
		System.out.println("in display cart");
		long count=service.totalCount();
		if(count==0)
		{
			return new ResponseEntity<>(new ResponseDTO("error","cart is empty .", null), HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(new ResponseDTO("success","Total items as follows", count), HttpStatus.OK);
	}
	
	
	 
}
