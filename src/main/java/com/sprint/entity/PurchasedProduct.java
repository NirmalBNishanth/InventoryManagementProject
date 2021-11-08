package com.sprint.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "purchased_products")
public class PurchasedProduct {
       
	@Id
	@Column(name = "id")
	String id;
	
	@Column(name = "invoice_no")
	String invoice_no;		
	
	@Column(name = "product_code")
	String productCode;
	
	@Column(name = "quantity")
	int quantity;
	
	@Column(name = "unit_price")
	double unitPrice;
	
	@Column(name = "amount")
	double amount;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="invoice_no",referencedColumnName="invoice_no",insertable = false, updatable = false)
	Purchase purchase;
	
	public PurchasedProduct(String id, String invoice_no, String productCode, int quantity, double unitPrice,
			double amount) {
		super();
		this.id = id;
		this.invoice_no = invoice_no;
		this.productCode = productCode;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
		this.amount = amount;
	}

	public PurchasedProduct() {
		super();
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getInvoice_no() {
		return invoice_no;
	}

	public void setInvoice_no(String invoice_no) {
		this.invoice_no = invoice_no;
	}

	public String getProduct_code() {
		return productCode;
	}

	public void setProduct_code(String product_code) {
		this.productCode = product_code;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
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
}
