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

import com.sprint.entity.Customer;
import com.sprint.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController 
{
	@Autowired
	CustomerService customerService;
	
	@GetMapping(value="/{customerId}",produces="application/json")
	public ResponseEntity<Customer> getCustomerDetails(@PathVariable String customerId)
	{
		Customer cust= customerService.getCustomer(customerId);
		return new ResponseEntity<Customer>(cust,HttpStatus.OK);
	}
	
	@GetMapping(produces="application/json")
	public ResponseEntity<List<Customer>> getCustomersDetails()
	{
		return new ResponseEntity<List<Customer>>(customerService.getCustomers(),HttpStatus.OK);
	}
	
	@PostMapping(consumes="application/json")
	public HttpStatus addCustomerDetails(@RequestBody Customer customer)
	{
		if(customerService.addOrModifyCustomer(customer))
			return HttpStatus.OK;
		return HttpStatus.NOT_MODIFIED;
	}
	
	@PutMapping(consumes="application/json")
	public HttpStatus editCustomerDetails(@RequestBody Customer customer)
	{
		if(customerService.addOrModifyCustomer(customer))
			return HttpStatus.OK;
		return HttpStatus.NOT_MODIFIED;
	}
	
	@DeleteMapping(value="/{customerId}")
	public HttpStatus removeCustomerDetails(@PathVariable String customerId)
	{
		customerService.removeCustomer(customerId);
		return HttpStatus.OK;
	}
}