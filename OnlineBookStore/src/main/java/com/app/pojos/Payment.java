package com.app.pojos;

import javax.persistence.*;

@Entity
@Table(name = "payment")
public class Payment 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "p_id")
	private Integer p_id;
	/*
	 * @OneToOne(cascade = CascadeType.ALL)
	 * 
	 * @JoinColumn(name = "cart_id", referencedColumnName = "cart_id") private Cart
	 * order;
	 */
	
	private double total_amount;
	
	public Payment() {
		System.out.println("inside ctor of payments");
	}

	public Payment(double amount) {
		this.total_amount = amount;
	}

	
	public Integer getP_id() {
		return p_id;
	}

	public void setP_id(Integer p_id) {
		this.p_id = p_id;
	}

	/*
	 * public Cart getOrder() { return order; } public void setOrder(Cart order) {
	 * this.order = order; }
	 */
	
	@Column(name = "amount", columnDefinition = "double(6,1)")
	public double getAmount() {
		return total_amount;
	}
	public void setAmount(double amount) {
		this.total_amount = amount;
	}
	
	
	
}
