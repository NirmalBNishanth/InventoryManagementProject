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
import com.sprint.entity.ProductInventory;
import com.sprint.service.ProductInventoryService;

@RestController
@RequestMapping("/productInventory")

public class ProductInventoryController {
         
	
		@Autowired
		ProductInventoryService productinventoryservice;
		
		@GetMapping(value="/{productCode}",produces="application/json")
		public ResponseEntity<ProductInventory> getProduct(@PathVariable String productCode)
		{
			ProductInventory productinventory = productinventoryservice.getProductInventory(productCode);
			return new ResponseEntity<ProductInventory>(productinventory,HttpStatus.OK);
		}
		
		@GetMapping(produces="application/json")
		public ResponseEntity<List<ProductInventory>> getProducts()
		{
			return new ResponseEntity<List<ProductInventory>>(productinventoryservice.getProducts(),HttpStatus.OK);
		}
		
		@PostMapping(consumes="application/json")
		public HttpStatus addInventoryDetails(@RequestBody ProductInventory inventory)
		{
			if(productinventoryservice.addOrModifyProducts(inventory)){
				return HttpStatus.OK;
			}		
			return HttpStatus.NOT_MODIFIED;
		}
		
		@PutMapping(consumes="application/json")
		public HttpStatus editInventoryDetails(@RequestBody ProductInventory inventory)
		{
			if(productinventoryservice.addOrModifyProducts(inventory)){
				return HttpStatus.OK;
			}		
			return HttpStatus.NOT_MODIFIED;
		}
		
		/*
		@GetMapping(value = "/products/{invoiceNumber}",produces="application/json")
		public ResponseEntity<List<Purchase>> getDataReportOfProducts(String invoiceNumber)
		{
			return new ResponseEntity<List<Purchase>>(productinventoryservice.getDataReport(invoiceNumber),HttpStatus.OK);
		}
		*/
		
		@DeleteMapping(value="/{productCode}")
		public HttpStatus removeProductDetails(@PathVariable String productCode)
		{
			productinventoryservice.removeProducts(productCode);
			return HttpStatus.OK;
		}
		
}