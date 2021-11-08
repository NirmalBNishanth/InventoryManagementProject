package com.sprint.entity;

import java.sql.Date;
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
@Table(name = "purchase")
public class Purchase {
     
	@Id
	@Column(name = "invoice_no")
	String invoiceNo;
	
	@Column(name = "supplier_id")
	String supplierId;
	
	@Column(name = "purchase_date")
	Date purchaseDate;
	
	@Column(name = "total_amount")
	double totalAmount;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="invoice_no",referencedColumnName="invoice_no")
	public List<PurchasedProduct> purchasedProducts;		
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="supplier_id",referencedColumnName="supplier_id",insertable = false, updatable = false)
	Supplier supplier;	
	
	public Purchase() {
		super();
	}

	public Purchase(String invoiceNo, String supplierId, Date purchaseDate, double totalAmount,
			List<PurchasedProduct> purchasedProducts, Supplier supplier) {
		super();
		this.invoiceNo = invoiceNo;
		this.supplierId = supplierId;
		this.purchaseDate = purchaseDate;
		this.totalAmount = totalAmount;
		this.purchasedProducts = purchasedProducts;
		this.supplier = supplier;
	}

	public List<PurchasedProduct> getPurchasedProducts() {
		return purchasedProducts;
	}

	public void setPurchasedProducts(List<PurchasedProduct> purchasedProducts) {
		this.purchasedProducts = purchasedProducts;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
		
}
