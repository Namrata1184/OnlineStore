package com.app.controller;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.dto.Login;
import com.app.dto.ResponseDTO;
import com.app.pojos.User;
import com.app.service.IUserService;

@RequestMapping("/users")
@ControllerAdvice
@Validated // To enable : validation err handling on request params or path variables
@CrossOrigin
public class UserController {

	@Autowired
	IUserService service;
	
	/*
	 * @Autowired private IUserDao dao;
	 */
	@Autowired
	private JavaMailSender sender;

	public UserController() {
		super();
		System.out.println("in user Controller");
	}


	  @PostMapping("payment/{user_id}")
	  public ResponseEntity<?> processPayment(@PathVariable Integer user_id){
		  System.out.println("inside payment");
		  Optional<User>opt=service.findById(user_id);
			/*
			 * Set<User>orders=dao.getOrders(user_id); for(User c:orders) {
			 * System.out.println(c); }
			 */
		 User u=opt.get();
		  //System.out.println("User"+u);
		   SimpleMailMessage mesg=new SimpleMailMessage();
		   //Set<Cart>c=u.getOrders();
			mesg.setTo(u.getEmail());
			System.out.println("email"+u.getEmail());
			mesg.setSubject("Order details");
			
			  String msg="Your Order placed successfully. ";
			 //mesg.setText("Your OrderDetails as follows"+dao.getOrders(user_id));
			  mesg.setText(msg);
			 			//System.out.println(dao.getOrders(user_id));
			sender.send(mesg);
			return new ResponseEntity<>(new ResponseDTO("success","mail send",null), HttpStatus.OK);
	  }
	
		/*
		 * @GetMapping("/{role}") public ResponseEntity<?> getUserList(@PathVariable
		 * String role) { System.out.println("in service get user list"); List<User>
		 * user_list = service.getUserList(Role.valueOf(role.toUpperCase())); if
		 * (user_list.isEmpty()) return new ResponseEntity<>(new ResponseDTO("error",
		 * "User list fetching failed", null), HttpStatus.NO_CONTENT);
		 * 
		 * return new ResponseEntity<>(new ResponseDTO("success", "List of All Users",
		 * user_list), HttpStatus.OK); }
		 */

	/*
	 * @GetMapping public ResponseEntity<?> listAllCustomers() {
	 * System.out.println("in list of all customers"); List<User> customers =
	 * service.getAllCustomers(); if(customers.isEmpty()) { return new
	 * ResponseEntity<>(new ResponseDTO("error","Customer details list is Empty."
	 * ,null),HttpStatus.NO_CONTENT); } return new ResponseEntity<>(new
	 * ResponseDTO("success", "List of all Customers", customers), HttpStatus.OK); }
	 */

	
	  
	  @GetMapping public ResponseEntity<?> listAllCustomers() {
	  System.out.println("in list of all customers");
	  List<User> customers = service.getAllCustomers();
	  if(customers.isEmpty()) { 
		  return new ResponseEntity<>(new ResponseDTO("error","Customer details list is Empty." ,null),
				  HttpStatus.NO_CONTENT); }
	  CustomOrdering ref=new CustomOrdering();
	 Collections.sort(customers, ref);
	  return new ResponseEntity<>(new ResponseDTO("success", "List of all Customers", customers), HttpStatus.OK); }
	 
	@PostMapping("/auth")
	public ResponseEntity<?> authenticateUser(@RequestBody Login u) {
		System.out.println("in login " + u.getUsername());
		System.out.println("in login " + u.getPassword());
		Optional<User> user = service.authenticateUser(u.getUsername(), u.getPassword());

		if (user.isPresent()) {
			System.out.println("in login success ");
			return new ResponseEntity<>(new ResponseDTO("success", "Login success", user.get()), HttpStatus.OK);
		} else {
			System.out.println("in login fail ");
			return new ResponseEntity<>(new ResponseDTO("error", "Login failed", "Login failed"),
					HttpStatus.UNAUTHORIZED);

		}

	}

	@PostMapping("/registration")
	public ResponseEntity<?> createUser(@RequestBody @Valid User user) {
		String msg = service.newUser(user);
		return new ResponseEntity<>(new ResponseDTO("success", msg, null), HttpStatus.OK);
	}

	@DeleteMapping("/del/{id}")
	public ResponseEntity<?> unsubscribeUser(@PathVariable Integer id) {
		String msg = service.deleteUser(id);
		return new ResponseEntity<>(new ResponseDTO("success", msg, null), HttpStatus.OK);

	}

	@GetMapping("/details/{id}")
	public ResponseEntity<?> getUserDetail(@PathVariable @Min(value = 1, message = "id > 0") Integer id) {
		System.out.println("in get user details of " + id);
		Optional<User> user = service.getUserDetail(id);
		// return new ResponseEntity<>(, HttpStatus.OK);
		return new ResponseEntity<>(new ResponseDTO("success", "user details", user.get()), HttpStatus.OK);

	}

	// req handling method to update existing customer
	@PutMapping("/{customerId}")
	public ResponseEntity<?> updateCustomerDetails(@PathVariable int customerId, @RequestBody User c) {
		System.out.println("in update " + customerId + " " + c);
		try {
			User updatedDetails = service.updateUser(customerId, c);
			return new ResponseEntity<>(new ResponseDTO("success", "Updated existing Customer", updatedDetails),
					HttpStatus.OK);
		} catch (RuntimeException e) {
			e.printStackTrace();
			return new ResponseEntity<>(new ResponseDTO("error", "Customer Updation failed.", null),
					HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/count")
	public ResponseEntity<?> totalCartItems() {
		System.out.println("in display cart");
		long count = service.totalCount();
		if (count == 0) {
			return new ResponseEntity<>(new ResponseDTO("error", "cart is empty .", null), HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(new ResponseDTO("success", "Total items as follows", count), HttpStatus.OK);
	}

}
