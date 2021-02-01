package com.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.cust_excs.ResourceNotFoundException;
import com.app.dao.IUserDao;
import com.app.pojos.Role;
import com.app.pojos.User;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserDao dao;
	@Override
	public List<User> getUserList(Role role) {
		System.out.println("in get user list "+getClass().getName());
		return dao.findByRole(role);
	}
	
	@Override
	public List<User> getAllCustomers() {
		System.out.println("dao impl class "+getClass().getName() );
		return dao.findAll();			
	}
	
	@Override
	public Optional<User> authenticateUser(String email,String pass) {
		System.out.println("in authenticate user"+getClass().getName());
		//Optional<User>user=dao.findByUsernameAndPassword(email,pass);
		
		return dao.findByUsernameAndPassword(email,pass);
		
		/*
		 * if(user.isPresent()) { return user; } throw new
		 * ResourceNotFoundException("Invalid Username or password");
		 */
	}
	@Override
	public String newUser(User user) {
		dao.save(user);
		return "registration successful";
	}
	@Override
	public String deleteUser(int id) {
		dao.deleteById(id);
		return "Deletion Successfull";
	}

	@Override
	public User updateUser(int id,User user) {
		//check if product exist
				System.out.println("book to update "+user);
				Optional<User>u1=dao.findById(id);
				if(u1.isPresent()) {
					User u=u1.get();
					u.setFirst_name(user.getFirst_name());
					u.setLast_name(user.getLast_name());
					u.setAddress(u.getAddress());
					u.setCity(user.getCity());
					u.setContact_no(user.getContact_no());
					u.setUsername(user.getUsername());
					u.setZip(user.getZip());
					return u;
				}
				throw new ResourceNotFoundException("Invalid User ID");
			
	}
	@Override
	public Optional<User> getUserDetail(int id) {
		Optional<User>user=dao.findById(id);
		if(user.isPresent())
		{
			return user;
		}else {
			throw new ResourceNotFoundException("Emp ID Invalid");
		}
	}
	
	}


