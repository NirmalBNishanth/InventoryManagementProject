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
import com.sprint.entity.PurchasedProduct;
import com.sprint.service.PurchasedProductService;

@RestController
@RequestMapping("/purchasedProducts")
public class PurchasedProductController {
	@Autowired
	PurchasedProductService purchasedproductservice;
	
	@GetMapping(value="/{id}",produces="application/json")
	public ResponseEntity<PurchasedProduct> getPurchase(@PathVariable String id)
	{
		PurchasedProduct purchase = purchasedproductservice.getPurchasedProduct(id);
		return new ResponseEntity<PurchasedProduct>(purchase,HttpStatus.OK);
	}
	
	@GetMapping(produces="application/json")
	public ResponseEntity<List<PurchasedProduct>> getProducts()
	{
		return new ResponseEntity<List<PurchasedProduct>>(purchasedproductservice.getPurchaseProducts(),HttpStatus.OK);
	}
	
	@PostMapping(consumes="application/json")
	public HttpStatus addPurchaseDetails(@RequestBody PurchasedProduct purchase)
	{
		if(purchasedproductservice.addOrModifyPurchasedProducts(purchase)){
			return HttpStatus.OK;
		}		
		return HttpStatus.NOT_MODIFIED;
	}
	
	@PutMapping(consumes="application/json")
	public HttpStatus editPurchaseDetails(@RequestBody PurchasedProduct purchase)
	{
		if(purchasedproductservice.addOrModifyPurchasedProducts(purchase)){
			return HttpStatus.OK;
		}		
		return HttpStatus.NOT_MODIFIED;
	}
	
	@DeleteMapping(value="/{id}")
	public HttpStatus removeProductDetails(@PathVariable String id)
	{
		purchasedproductservice.removePurchasedProducts(id);
		return HttpStatus.OK;
	}

}