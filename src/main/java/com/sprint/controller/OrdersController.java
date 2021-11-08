package com.sprint.controller;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.sprint.entity.Orders;
import com.sprint.service.OrdersService;

@RestController
@RequestMapping("/orders")
public class OrdersController 
{
	@Autowired
	OrdersService ordersService;
	
	@GetMapping(value="/{orderNo}",produces="application/json")
	public ResponseEntity<Orders> getOrder(@PathVariable String orderNo)
	{
		Orders orders= ordersService.getOrder(orderNo);
		return new ResponseEntity<Orders>(orders,HttpStatus.OK);
	}
	
	@GetMapping(produces="application/json")
	public ResponseEntity<List<Orders>> getOrders()
	{
		return new ResponseEntity<List<Orders>>(ordersService.getOrders(),HttpStatus.OK);
	}
	
	
	@GetMapping(value="/highestSoldCustomer",produces="application/json")
	public ResponseEntity<Orders> getByHighestSuppliedSupplier()
	{
		Orders o=ordersService.getByMaxSuppliedSupplier();
		return new ResponseEntity<Orders> (o,HttpStatus.OK);
	}
	
	
	@GetMapping(value="/customer/{customerId}",produces="application/json")
	public ResponseEntity<Orders> getCustomerDetailsBySupplierName(@PathVariable String customerId)
	{
		Orders o=ordersService.getBySupplierID(customerId);
		return new ResponseEntity<Orders> (o,HttpStatus.OK);
	}
	
	@GetMapping(value="/highestAmount",produces="application/json")
	public ResponseEntity<Orders> getByHighestCost()
	{
		Orders orders=ordersService.getByMaxCost();
		return new ResponseEntity<Orders> (orders,HttpStatus.OK);
	}
	
	
	@PostMapping(consumes="application/json")
	public HttpStatus addCustomerDetails(@RequestBody Orders orders) 
	{
		if(ordersService.addOrModifyOders(orders))
			return HttpStatus.OK;
		return HttpStatus.NOT_MODIFIED;
	}
	
	@PutMapping(consumes="application/json")
	public HttpStatus editCustomerDetails(@RequestBody Orders orders) 
	{
		if(ordersService.addOrModifyOders(orders))
			return HttpStatus.OK;
		return HttpStatus.NOT_MODIFIED;
	}
	
	@GetMapping(value = "/weeklyreport",produces="application/json")
	public ResponseEntity<List<Orders>> getWeeklyRepost(@RequestParam @DateTimeFormat(pattern="yyyy-dd-MM") Date fromDate,@RequestParam @DateTimeFormat(pattern="yyyy-dd-MM") Date toDate)
	{
		return new ResponseEntity<List<Orders>>(ordersService.getWeeklyReports(fromDate, toDate),HttpStatus.OK);
	}
	
	@GetMapping(value = "/products/{orderNo}",produces="application/json")
	public ResponseEntity<List<Object>> getDataReportOfProducts(String orderNo)
	{
		return new ResponseEntity<List<Object>>(ordersService.getDataReport(orderNo),HttpStatus.OK);
	}

}
