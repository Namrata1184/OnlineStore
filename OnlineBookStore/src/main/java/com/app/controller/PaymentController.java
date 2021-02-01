package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dao.IPaymentDao;
import com.app.dto.ResponseDTO;

import com.app.pojos.Payment;

@RestController
@RequestMapping("/payment")
@Validated
@CrossOrigin(origins = "*")
public class PaymentController {
	
	@Autowired
	IPaymentDao dao;
	
	@PostMapping("/add")
	public ResponseEntity<?> addPayment(@RequestBody Payment item)
	{
		System.out.println("in add to cart  "+item);
		
		Payment msg=dao.save(item);
		return new ResponseEntity<>(new ResponseDTO("success","New Items added in cart.", msg), HttpStatus.OK);
	}

}
