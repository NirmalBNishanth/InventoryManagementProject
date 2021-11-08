package com.sprint.controller;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.sprint.entity.Purchase;
import com.sprint.service.PurchaseService;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {
     
	
	@Autowired
	PurchaseService purchaseservice;
	
	@GetMapping(value="/{invoiceNumber}",produces="application/json")
	public ResponseEntity<Purchase> getPurchase(@PathVariable String invoiceNumber)
	{
		Purchase purchase = purchaseservice.getPurchasedProduct(invoiceNumber);
		return new ResponseEntity<Purchase>(purchase,HttpStatus.OK);
	}
	
	@GetMapping(value="/supplier/{supplierId}",produces="application/json")
	public ResponseEntity<Purchase> getSupplierDetailsBySupplierName(@PathVariable String supplierId)
	{

		Purchase p=purchaseservice.getBySupplierID(supplierId);
		return new ResponseEntity<Purchase> (p,HttpStatus.OK);
	}
	
	@GetMapping(value="/highestAmount",produces="application/json")
	public ResponseEntity<Purchase> getByHighestCost()
	{
		Purchase p=purchaseservice.getByMaxCost();
		return new ResponseEntity<Purchase> (p,HttpStatus.OK);
	}
	
	@GetMapping(value = "/weeklyreport",produces="application/json")
	public ResponseEntity<List<Purchase>> getWeeklyRepost(@RequestParam @DateTimeFormat(pattern="yyyy-dd-MM") Date fromDate,@RequestParam @DateTimeFormat(pattern="yyyy-dd-MM") Date toDate)
	{
		return new ResponseEntity<List<Purchase>>(purchaseservice.getWeeklyReports(fromDate, toDate),HttpStatus.OK);
	}
	
	@GetMapping(value="/highestBoughtSupplier",produces="application/json")
	public ResponseEntity<Purchase> getByHighestSuppliedSupplier()
	{
		Purchase p=purchaseservice.getByMaxSuppliedSupplier();
		return new ResponseEntity<Purchase> (p,HttpStatus.OK);
	}
	
	@GetMapping(value = "/products/{invoiceNumber}",produces="application/json")
	public ResponseEntity<List<Object>> getDataReportOfProducts(String invoiceNumber)
	{
		return new ResponseEntity<List<Object>>(purchaseservice.getDataReport(invoiceNumber),HttpStatus.OK);
	}
	
	@GetMapping(produces="application/json")
	public ResponseEntity<List<Purchase>> getProducts()
	{
		return new ResponseEntity<List<Purchase>>(purchaseservice.getPurchaseProducts(),HttpStatus.OK);
	}
	
	@PostMapping(consumes="application/json")
	public HttpStatus addPurchaseDetails(@RequestBody Purchase purchase)
	{
		if(purchaseservice.addOrModifyPurchaseProducts(purchase)){
			return HttpStatus.OK;
		}		
		return HttpStatus.NOT_MODIFIED;
	}
	
	@PutMapping(consumes="application/json")
	public HttpStatus editPurchaseDetails(@RequestBody Purchase purchase)
	{
		if(purchaseservice.addOrModifyPurchaseProducts(purchase)){
			return HttpStatus.OK;
		}		
		return HttpStatus.NOT_MODIFIED;
	}
	
	@DeleteMapping(value="/{invoiceNumber}")
	public HttpStatus removeProductDetails(@PathVariable String invoiceNumber)
	{
		purchaseservice.removeProducts(invoiceNumber);
		return HttpStatus.OK;
	}
	
}
