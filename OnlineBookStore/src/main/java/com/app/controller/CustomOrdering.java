package com.app.controller;

import java.util.Comparator;

import com.app.pojos.User;

public class CustomOrdering implements Comparator<User> {
	
	@Override
	public int compare(User u1,User u2) {
		int val=u1.getRole().compareTo(u2.getRole());
		if(val == 0) {
			return u1.getFirst_name().compareTo(u2.getFirst_name());
		}
		return val;
	}



}
