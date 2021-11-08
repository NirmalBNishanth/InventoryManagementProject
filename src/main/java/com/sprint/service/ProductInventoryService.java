package com.sprint.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sprint.entity.ProductInventory;
import com.sprint.repository.ProductInventoryRepository;


@Service
public class ProductInventoryService {
    	

	ProductInventoryRepository productinventoryrepository;
	
	public ProductInventoryService(@Autowired ProductInventoryRepository productinventoryRepository) 
	{
		    this.productinventoryrepository =  productinventoryRepository;
	}
	
	@Transactional(readOnly = true)
	public ProductInventory getProductInventory(String productCode) {
		Optional<ProductInventory> productinventory = productinventoryrepository.findById(productCode);
		if(productinventory.isPresent()) {
			return productinventory.get();
		}
		throw new RuntimeException("Product Does not Exist");
	}
	@Transactional(readOnly=true)
	public List<ProductInventory> getProducts()
	{
		List<ProductInventory> productlist = productinventoryrepository.findAll();
		if(productlist.size() > 0) return productlist;
		throw new RuntimeException("products Does not Exist");
	}
	
	@Transactional
	public boolean addOrModifyProducts(ProductInventory prod_inventory)
	{
		ProductInventory product = productinventoryrepository.save(prod_inventory);
		return product != null;
	}
	
	@Transactional
	public boolean editOrModifyProducts(ProductInventory prod_inventory)
	{
		ProductInventory product = productinventoryrepository.save(prod_inventory);
		return product != null;
	}
	
	@Transactional
	public void removeProducts(String productCode)
	{
		productinventoryrepository.deleteById(productCode);
	}
	
}