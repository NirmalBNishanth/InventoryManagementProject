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
import com.sprint.entity.OrderedProducts;
import com.sprint.service.OrderedProductsService;

@RestController
@RequestMapping("/orderedProducts")
public class OrderedProductsController 
{
	@Autowired
	OrderedProductsService orderedProductsService;
	
	@GetMapping(value="/{id}",produces="application/json")
	public ResponseEntity<OrderedProducts> getSoldItemDetail(@PathVariable String id)
	{
		OrderedProducts op= orderedProductsService.getOrderedItem(id);
		return new ResponseEntity<OrderedProducts>(op,HttpStatus.OK);
	}
	
	@GetMapping(produces="application/json")
	public ResponseEntity<List<OrderedProducts>> getSoldItemDetails()
	{
		return new ResponseEntity<List<OrderedProducts>>(orderedProductsService.getOrderedItems(),HttpStatus.OK);
	}
	
	@PostMapping(consumes="application/json")
	public HttpStatus addSoldItemDetails(@RequestBody OrderedProducts orderedProducts)
	{
		if(orderedProductsService.addOrModifySoldItem(orderedProducts))
			return HttpStatus.OK;
		return HttpStatus.NOT_MODIFIED;
	}
	
	@PutMapping(consumes="application/json")
	public HttpStatus editSoldItemDetails(@RequestBody OrderedProducts orderedProducts)
	{
		if(orderedProductsService.addOrModifySoldItem(orderedProducts))
			return HttpStatus.OK;
		return HttpStatus.NOT_MODIFIED;
	}
	
	@DeleteMapping(value="/{id}")
	public HttpStatus removeSoldItemDetails(@PathVariable String id)
	{
		orderedProductsService.removeOrderItems(id);
		return HttpStatus.OK;
	}

}
