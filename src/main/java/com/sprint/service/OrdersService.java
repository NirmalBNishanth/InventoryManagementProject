package com.sprint.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sprint.entity.OrderedProducts;
import com.sprint.entity.Orders;
import com.sprint.entity.ProductInventory;
import com.sprint.repository.OrderedProductsRepository;
import com.sprint.repository.OrdersRepository;
import com.sprint.repository.ProductInventoryRepository;

@Service
public class OrdersService 
{
	@Autowired
	OrdersRepository ordersRepository;
	
	@Autowired
	ProductInventoryService productInventoryService;
	
	@Autowired
	OrderedProductsService orderedProductsService;
	
	@Autowired
	OrderedProductsRepository orderedProductsRepository;
	
	ProductInventoryRepository productinventoryrepository;
	public OrdersService(@Autowired ProductInventoryRepository productinventoryrepository) 
	{
		    this.productinventoryrepository =  productinventoryrepository;
	}
	
	@Transactional(readOnly = true)
	public Orders getOrder(String orderNo)
	{
		Optional<Orders> optOrder=ordersRepository.findById(orderNo);
		if(optOrder.isPresent())return optOrder.get();
		throw new RuntimeException("Order Does not Exist");
	}
	
	@Transactional(readOnly=true)
	public List<Orders> getOrders()
	{
		List<Orders> olist = ordersRepository.findAll();
		if(olist.size() > 0) return olist;
		throw new RuntimeException("Order Does not Exist");
	}
	
	@Transactional
	public boolean addOrModifyOders(Orders orders) 
	{
		Orders order = ordersRepository.save(orders);
		ProductInventory productInventory = new ProductInventory();
		List<OrderedProducts> orderedProducts = orders.getOrderedProducts();
		if(orderedProducts!=null)
		{
			System.out.println("Runs");
			double amount =0;
			for(OrderedProducts O :orderedProducts)
			{
				System.out.println("This runs");
				int q = O.getQuantity();
				double u = O.getUnitPrice();
				O.setAmount(q*u);
				orderedProductsService.addOrModifySoldItem(O);
				
				String productCode=O.getProductCode();
				int Quantity=O.getQuantity();
				O.getUnitPrice();
				productInventory=productInventoryService.getProductInventory(productCode);
				if(productInventory.getProductCode().equals(productCode))
				{
					if(productInventory.getAvailableQuantity()-O.getQuantity()<1)
					{
						throw new RuntimeException("Product is Understocked");
					}
					else if(productInventory.getAvailableQuantity()-O.getQuantity()>1)
					{
						productInventory.setAvailableQuantity(productInventory.getAvailableQuantity()-Quantity);
						productInventory.setProductCode(productCode);
						productInventory.setSoldQuantity(Quantity+productInventory.getSoldQuantity());
						amount=O.getAmount()+amount;
						System.out.println(amount);
					}
					productInventoryService.addOrModifyProducts(productInventory);
				}
			}
			order.setAmount(amount);
		}
		return order != null;
	}
	
	
	@Transactional(readOnly = true)
	public Orders getByMaxSuppliedSupplier()
	{
		Orders orders =ordersRepository.findDetailsByHighestCustomer();
		return orders;		
	}
	
	
	@Transactional(readOnly = true)
	public Orders getBySupplierID(String customerId)
	{
		Orders orders =ordersRepository.findByCustomerId(customerId);
		return orders;		
	}
	
	@Transactional(readOnly = true)
	public Orders getByMaxCost()
	{
		Orders orders =ordersRepository.findDetailsByHighestCost();
		return orders;		
	}
	
	@Transactional
	public List<Orders> getWeeklyReports(Date date1,Date date2){
		List<Orders> orders = ordersRepository.findWeeklyReport(date1, date2);
        return orders;
	}
	
	public List<Object> getDataReport(String orderNo){
		List<Object> orders = ordersRepository.findDataReport(orderNo);
		if(orders.size() > 0) {
			return orders;
		}
		throw new RuntimeException("No Data found"); 
	}
	
	@Transactional
	public void removeOrder(String orderNo)
	{
		ordersRepository.deleteById(orderNo);
	}
}
