package com.sprint.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sprint.entity.PurchasedProduct;
import com.sprint.repository.PurchasedProductRepository;

@Service
public class PurchasedProductService {
     
	@Autowired
	PurchasedProductRepository purchasedproductrepository;
	
	@Transactional(readOnly = true)
	public PurchasedProduct getPurchasedProduct(String id) {
		Optional<PurchasedProduct> pur = purchasedproductrepository.findByProductCode(id);
		if(pur.isPresent()) {
			return pur.get();
		}
		throw new RuntimeException("PurchasedProduct Does not Exist");
	}
	@Transactional(readOnly=true)
	public List<PurchasedProduct> getPurchaseProducts()
	{
		List<PurchasedProduct> purchasedlist = purchasedproductrepository.findAll();
		if(purchasedlist.size() > 0) return purchasedlist;
		throw new RuntimeException("PurchasedProducts Does not Exist");
	}
	
	@Transactional
	public boolean addOrModifyPurchasedProducts(PurchasedProduct purchase)
	{
		double amount=purchase.getUnitPrice()*purchase.getQuantity();
		purchase.setAmount(amount);
		PurchasedProduct purchase1 = purchasedproductrepository.save(purchase);
		return purchase1 != null;
	}
	
	@Transactional
	public boolean removePurchasedProducts(String id)
	{
		return purchasedproductrepository.deleteByProductCode(id) > 0;
	}
}