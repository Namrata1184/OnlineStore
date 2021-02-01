package com.app.dto;

public class Login {
	private String username;
	private String password;
	public Login(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public Login()
	{
		super();		
	}
	public String getUsername() {
		System.out.println("in getUsername of "+getClass().getName());
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		System.out.println("in getPassword of "+getClass().getName());
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	

}
