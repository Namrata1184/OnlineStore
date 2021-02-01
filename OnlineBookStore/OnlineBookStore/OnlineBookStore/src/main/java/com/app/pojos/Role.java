package com.app.pojos;

public enum Role {
	ADMIN,CUSTOMER;
	
	@Override
	public String toString() {
		return name().toLowerCase();
	}
	
}
