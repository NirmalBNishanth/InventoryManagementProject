package com.sprint.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sprint.entity.Supplier;
import com.sprint.service.SupplierService;

@RestController
@RequestMapping("/supplier")
public class SupplierController {
	@Autowired
	SupplierService supplierservice;
	
	@GetMapping(value="/{supplierId}",produces="application/json")
	public ResponseEntity<Supplier> getSupplierDetails(@PathVariable String supplierId)
	{
		Supplier supplier= supplierservice.getSupplier(supplierId);
		return new ResponseEntity<Supplier>(supplier,HttpStatus.OK);
	}
	
	@GetMapping(value="/supplierName/{supplierName}",produces="application/json")
	public ResponseEntity<Supplier> getSupplierDetailsBySupplierName(@PathVariable String supplierName)
	{
		System.out.println(supplierName);
		Supplier s=supplierservice.getSupplierBySupplierName(supplierName);
		return new ResponseEntity<Supplier> (s,HttpStatus.OK);
	}
	
	@GetMapping(produces="application/json")
	public ResponseEntity<List<Supplier>> getSuppliersDetails()
	{
		return new ResponseEntity<List<Supplier>>(supplierservice.getSuppliers(),HttpStatus.OK);
	}
	
	@PostMapping(consumes="application/json")
	public HttpStatus addSupplierDetails(@RequestBody Supplier supplier)
	{
		if(supplierservice.addOrModifySupplier(supplier))
			return HttpStatus.OK;
		return HttpStatus.NOT_MODIFIED;
	}
	
	@PutMapping(consumes="application/json")
	public HttpStatus editSupplierDetails(@RequestBody Supplier supplier)
	{
		if(supplierservice.addOrModifySupplier(supplier))
			return HttpStatus.OK;
		return HttpStatus.NOT_MODIFIED;
	}
	
	@DeleteMapping(value="/{supplierId}")
	public HttpStatus removeSupplierDetails(@PathVariable String supplierId)
	{
		supplierservice.removeSuppliers(supplierId);
		return HttpStatus.OK;
	}

}