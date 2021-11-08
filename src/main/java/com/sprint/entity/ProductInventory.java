package com.sprint.entity;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="product_inventory")
public class ProductInventory 
{
	@Id
	@Column(name = "product_code")
	String productCode;
	
	@Column(name = "product_name")
	String productName;
	
	@Column(name = "product_description")
	String productDescription;
	
	@Column(name = "manufacturer_name")
	String manufacturerName;
	
	@Column(name = "price")
	double price;
	
	@Column(name = "selling_price")
	double sellingPrice;
	
	@Column(name = "available_quantity")
	int availableQuantity;
	
	@Column(name = "sold_quantity")
	int soldQuantity;
	
	@Column(name = "expiry_date")
	Date date;
	
	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="product_code",referencedColumnName="product_code",insertable = false, updatable = false)
	PurchasedProduct purchasedProduct;	
	
	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="product_code",referencedColumnName="product_code",insertable = false, updatable = false)
	OrderedProducts orderedProducts;	
	
	public ProductInventory(String productCode, String productName, String productDescription, String manufacturerName,
		double price, double sellingPrice, int availableQuantity, int soldQuantity, Date date,
		PurchasedProduct purchasedProduct, OrderedProducts orderedProducts) {
	super();
	this.productCode = productCode;
	this.productName = productName;
	this.productDescription = productDescription;
	this.manufacturerName = manufacturerName;
	this.price = price;
	this.sellingPrice = sellingPrice;
	this.availableQuantity = availableQuantity;
	this.soldQuantity = soldQuantity;
	this.date = date;
	this.purchasedProduct = purchasedProduct;
	this.orderedProducts = orderedProducts;
}

	public ProductInventory()
	{
		super();
	}
	
	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public String getManufacturerName() {
		return manufacturerName;
	}

	public void setManufacturerName(String manufacturerName) {
		this.manufacturerName = manufacturerName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(double sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public int getAvailableQuantity() {
		return availableQuantity;
	}

	public void setAvailableQuantity(int availableQuantity) {
		this.availableQuantity = availableQuantity;
	}

	public int getSoldQuantity() {
		return soldQuantity;
	}

	public void setSoldQuantity(int soldQuantity) {
		this.soldQuantity = soldQuantity;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
