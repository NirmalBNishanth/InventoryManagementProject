package com.sprint.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer 
{	
	@Id
	@Column(name = "customer_id")
	String customerId;
	
	@Column(name = "customer_name")
	String customerName;
	
	@Column(name = "mobile")
	double customerPhoneNumber;
	
	@Column(name = "emailid")
	String emailId;
	
	@Column(name = "address")
	String customerAddress;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="customer_id",referencedColumnName="customer_id",insertable = false, updatable = false)
	public List<Orders> orders;		

	public Customer(String customerId, String customerName, double customerPhoneNumber, String emailId,
			String customerAddress) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.customerPhoneNumber = customerPhoneNumber;
		this.emailId = emailId;
		this.customerAddress = customerAddress;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public Customer() 
	{
		super();
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public double getCustomerPhoneNumber() {
		return customerPhoneNumber;
	}

	public void setCustomerPhoneNumber(double customerPhoneNumber) {
		this.customerPhoneNumber = customerPhoneNumber;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}
	
	
}
