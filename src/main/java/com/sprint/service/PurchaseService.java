package com.sprint.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sprint.entity.ProductInventory;
import com.sprint.entity.Purchase;
import com.sprint.entity.PurchasedProduct;
import com.sprint.repository.ProductInventoryRepository;
import com.sprint.repository.PurchaseRepository;
import com.sprint.repository.PurchasedProductRepository;


@Service
public class PurchaseService {
       
	@Autowired
	PurchaseRepository purchaserepository;
	
	@Autowired
	ProductInventoryService productInventoryService;
	
	@Autowired
	PurchasedProductService purchasedProductService;
	
	@Autowired
	PurchasedProductRepository purchasedProductRepository;
	
	
	ProductInventoryRepository productinventoryrepository;
	
	public PurchaseService(@Autowired ProductInventoryRepository productinventoryRepository) 
	{
		    this.productinventoryrepository =  productinventoryRepository;
	}
	
	@Transactional(readOnly = true)
	public Purchase getPurchasedProduct(String invoiceNumber) {
		Optional<Purchase> purchase = purchaserepository.findById(invoiceNumber);
		if(purchase.isPresent()) {
			return purchase.get();
		}
		throw new RuntimeException("PurchasedProduct Does not Exist");
	}
	
	
	@Transactional(readOnly=true)
	public List<Purchase> getPurchaseProducts()
	{
		List<Purchase> purchaselist = purchaserepository.findAll();
		if(purchaselist.size() > 0) return purchaselist;
		throw new RuntimeException("PurchasedProducts Does not Exist");
	}
	
	@Transactional
	public boolean addOrModifyPurchaseProducts(Purchase purchase)
	{
		Purchase purchase1 = purchaserepository.save(purchase);
		//PurchasedProduct pp = new PurchasedProduct();
		ProductInventory productInventory = new ProductInventory();
		List<PurchasedProduct> purchasedProducts = purchase.getPurchasedProducts();
		if(purchasedProducts!=null)
		{
			double amount =0;
			for(PurchasedProduct P :purchasedProducts)
			{
				int q = P.getQuantity();
				double u = P.getUnitPrice();
				P.setAmount(q*u);
				purchasedProductService.addOrModifyPurchasedProducts(P);
				
				
				String productCode=P.getProduct_code();
				int Quantity=P.getQuantity();
				P.getUnitPrice();
				
				productInventory=productInventoryService.getProductInventory(productCode);
				if(productInventory.getProductCode().equals(productCode))
				{	
					productInventory.setAvailableQuantity(productInventory.getAvailableQuantity()+Quantity);
					productInventory.setProductCode(productCode);
					amount=P.getAmount()+amount;
					productInventory.setPrice(P.getUnitPrice());
					productInventory.setSellingPrice(P.getUnitPrice()+P.getUnitPrice()*0.01);
					productInventoryService.addOrModifyProducts(productInventory);
				}
			}
			purchase1.setTotalAmount(amount);
		}
		return purchase1 != null;
	}
	
	@Transactional
	public void removeProducts(String productCode)
	{
		purchaserepository.deleteById(productCode);
	}
	
	@Transactional(readOnly = true)
	public Purchase getByMaxCost()
	{
		Purchase purchase =purchaserepository.findDetailsByHighestCost();
		return purchase;		
	}
	
	@Transactional
	public List<Purchase> getWeeklyReports(Date date1,Date date2){
		List<Purchase> purchase2 = purchaserepository.findWeeklyReport(date1, date2);
        return purchase2;
	}
	
	@Transactional(readOnly = true)
	public Purchase getByMaxSuppliedSupplier()
	{
		Purchase purchase =purchaserepository.findDetailsByHighestSupplier();
		return purchase;		
	}
	
	public List<Object> getDataReport(String invoiceNumber){
		List<Object> purchase2 = purchaserepository.findDataReport(invoiceNumber);
		if(purchase2.size() > 0) {
			return purchase2;
		}
		throw new RuntimeException("No Data found"); 
	}
	
	@Transactional(readOnly = true)
	public Purchase getBySupplierID(String supplierId)
	{
		Purchase purchase =purchaserepository.findBySupplierId(supplierId);
		return purchase;		
	}
	
	
}
