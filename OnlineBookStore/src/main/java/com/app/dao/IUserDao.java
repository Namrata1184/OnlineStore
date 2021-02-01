package com.app.dao;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Query;

import com.app.pojos.Role;
import com.app.pojos.User;


public interface IUserDao extends JpaRepository<User, Integer>{
	
	List<User> findByRole(Role role);
	//Login
	//@Query(value = "SELECT u FROM User u where u.email=?1 and u.password=?2")
	
	Optional<User> findByUsernameAndPassword(String email, String pass);
	
	@Query(value="select u from User u join fetch u.orders where u.user_id=?1")
	Set<User>getOrders(int id);
	

	
}
