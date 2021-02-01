package com.app.service;

import java.util.List;
import java.util.Optional;


import com.app.pojos.Role;
import com.app.pojos.User;

public interface IUserService {
	
	List<User>getUserList(Role role);
	// list all the Customers
		List<User> getAllCustomers();
	
	Optional<User>authenticateUser(String email,String pass);
	
	//String SubscribeUser(User user);
	String newUser(User user);
	
	//delete user by id
	String deleteUser(int id);
	
	//update User
	User updateUser(int id,User user);
	
	Optional<User>getUserDetail(int id);
	


}
