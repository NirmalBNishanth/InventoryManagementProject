package com.sprint.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sprint.entity.OrderedProducts;
import com.sprint.entity.ProductInventory;
import com.sprint.repository.OrderedProductsRepository;


@Service
public class OrderedProductsService 
{
	@Autowired
	OrderedProductsRepository orderedProductsRepository;
	
	@Autowired
	ProductInventoryService productInventoryService;
	
	@Transactional(readOnly = true)
	public OrderedProducts getOrderedItem(String id)
	{
		Optional<OrderedProducts> optSoldItems=orderedProductsRepository.findById(id);
		if(optSoldItems.isPresent())return optSoldItems.get();
		throw new RuntimeException("Item Does not Exist");
	}
	
	@Transactional(readOnly=true)
	public List<OrderedProducts> getOrderedItems()
	{
		System.out.println("Runs");
		List<OrderedProducts> olist = orderedProductsRepository.findAll();
		if(olist.size() > 0) return olist;
		throw new RuntimeException("Item Does not Exist");
	}
	
	@Transactional
	public boolean addOrModifySoldItem(OrderedProducts orderedItems)
	{	
		System.out.println("runs");
		double amount=orderedItems.getUnitPrice()*orderedItems.getQuantity();
		orderedItems.setAmount(amount);
		String productCode = orderedItems.getProductCode();
		ProductInventory productInventory = new ProductInventory();
		productInventory=productInventoryService.getProductInventory(productCode);
		orderedItems.setUnitPrice(productInventory.getSellingPrice());		
		OrderedProducts op = orderedProductsRepository.save(orderedItems);
		return op!=null;
	}
	
	@Transactional
	public void removeOrderItems(String id)
	{
		orderedProductsRepository.deleteById(id);
	}
}
