package com.sprint.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="orders")
public class Orders 
{
	@Id
	@Column(name="order_no")
	String orderNo;
	
	@Column(name="customer_id")
	String customerId;
	
	@Column(name="order_date")
	Date date;
	
	@Column(name=" amount")
	double  amount;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="order_no",referencedColumnName="order_no")
	public List<OrderedProducts> orderedProducts;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="customer_id",referencedColumnName="customer_id",insertable = false, updatable = false)
	Customer customer;	
	
	public Orders(String orderNo, String customerId, Date date, double amount, List<OrderedProducts> orderedProducts,
			Customer customer) {
		super();
		this.orderNo = orderNo;
		this.customerId = customerId;
		this.date = date;
		this.amount = amount;
		this.orderedProducts = orderedProducts;
		this.customer = customer;
	}

	public List<OrderedProducts> getOrderedProducts() {
		return orderedProducts;
	}

	public void setOrderedProducts(List<OrderedProducts> orderedProducts) {
		this.orderedProducts = orderedProducts;
	}

	public Orders()
	{
		super();
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	
}
