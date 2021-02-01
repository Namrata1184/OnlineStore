package com.app.pojos;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
/*import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;*/
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "users")
public class User {
	@Id // pk
	@GeneratedValue(strategy = GenerationType.IDENTITY) // for auto increment
	@Column(insertable = false, updatable = false)
	private Integer user_id;
	@NotBlank(message = "name must be supplied")
	@Column(length = 30)
	private String first_name;
	@NotBlank(message = "name must be supplied")
	@Column(length = 30)
	private String Last_name;
	@NotBlank(message = "name must be supplied")
	@Column(length = 150)
	private String address;
	@Column(length = 40)
	private String city;
	@Column(length = 50)
	private String country;	
	private Integer zip;
	@Column(length = 10, unique = true)
	private int contact_no;
	@Column(length = 30, unique = true)
	private String username;
	@Column(length = 30)
	private String password;
	@Enumerated(EnumType.STRING)
	@Column(length = 20, name = "Role")
	private Role role;

	 @JsonIgnore
	 @OneToMany(mappedBy="user_id",cascade = CascadeType.ALL,orphanRemoval = true, fetch = FetchType.EAGER)
	 private Set<Cart> orders;
	 

	public User() {
		System.out.println("in ctor of User");
	}

	

	public User(Integer user_id) {
		super();
		this.user_id = user_id;
	}



	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getContact_no() {
		return contact_no;
	}

	public void setContact_no(int contact_no) {
		this.contact_no = contact_no;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}



	public String getLast_name() {
		return Last_name;
	}



	public void setLast_name(String last_name) {
		Last_name = last_name;
	}



	public String getCity() {
		return city;
	}



	public void setCity(String city) {
		this.city = city;
	}



	public String getCountry() {
		return country;
	}



	public void setCountry(String country) {
		this.country = country;
	}



	public Integer getZip() {
		return zip;
	}



	public void setZip(Integer zip) {
		this.zip = zip;
	}



	public Set<Cart> getOrders() {
		return orders;
	}



	public void setOrders(Set<Cart> orders) {
		this.orders = orders;
	}



	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", first_name=" + first_name + ", Last_name=" + Last_name + ", address="
				+ address + ", city=" + city + ", country=" + country + ", zip=" + zip + ", contact_no=" + contact_no
				+ ", username=" + username + ", password=" + password + ", role=" + role + ", orders=" + orders + "]";
	}

	

}
