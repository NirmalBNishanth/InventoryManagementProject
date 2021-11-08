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
@Table(name = "supplier")
public class Supplier {
         
	
	@Id
	@Column(name = "supplier_id")
	String supplierId;
	
	@Column(name = "supplier_name")
	String supplierName;
	
	@Column(name = "mobile")
	long supplierPhoneNumber;
	
	@Column(name = "address")
	String supplierAddress;
	
	@Column(name = "emailid")
	String emailId;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="supplier_id",referencedColumnName="supplier_id",insertable = false, updatable = false)
	public List<Purchase> purchase;		
	
	public Supplier(String supplierId, String supplierName, long supplierPhoneNumber, String supplierAddress,
			String emailId) {
		super();
		this.supplierId = supplierId;
		this.supplierName = supplierName;
		this.supplierPhoneNumber = supplierPhoneNumber;
		this.supplierAddress = supplierAddress;
		this.emailId = emailId;
	}

	public Supplier() {
		super();
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public long getSupplierPhoneNumber() {
		return supplierPhoneNumber;
	}

	public void setSupplierPhoneNumber(long supplierPhoneNumber) {
		this.supplierPhoneNumber = supplierPhoneNumber;
	}

	public String getSupplierAddress() {
		return supplierAddress;
	}

	public void setSupplierAddress(String supplierAddress) {
		this.supplierAddress = supplierAddress;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	
}