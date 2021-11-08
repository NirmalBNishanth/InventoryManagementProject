package com.sprint.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="ordered_products")
public class OrderedProducts 
{
	@Id
	@Column(name="id")
	String id;
		
	@Column(name="order_no")
	String orderNo;
			
	@Column(name="product_code")
	String productCode;
	
	@Column(name="quantity")
	int quantity;
	
	@Column(name="unit_price")
	double unitPrice;
	
	@Column(name="amount")
	double amount;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="order_no",referencedColumnName="order_no",insertable = false, updatable = false)
	Orders orders;
	
	public OrderedProducts()
	{
		super();
	}

	public OrderedProducts(String id, String orderNo, String productCode, int quantity, double unitPrice, double amount,
			Orders orders) {
		super();
		this.id = id;
		this.orderNo = orderNo;
		this.productCode = productCode;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
		this.amount = amount;
		this.orders = orders;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}

