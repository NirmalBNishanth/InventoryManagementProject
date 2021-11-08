package com.sprint.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sprint.entity.Customer;
import com.sprint.repository.CustomerRepository;

@Service
public class CustomerService 
{
	@Autowired
	CustomerRepository customerRepository;
	
	@Transactional(readOnly = true)
	public Customer getCustomer(String customerId)
	{
		Optional<Customer> optCust=customerRepository.findById(customerId);
		if(optCust.isPresent())return optCust.get();
		throw new RuntimeException("Customer Does not Exist");
	}
	
	@Transactional(readOnly=true)
	public List<Customer> getCustomers()
	{
		List<Customer> clist = customerRepository.findAll();
		if(clist.size() > 0) return clist;
		throw new RuntimeException("Customer Does not Exist");
	}
	
	@Transactional
	public boolean addOrModifyCustomer(Customer customer)
	{
		Customer cust = customerRepository.save(customer);
		return cust!=null;
	}
	
	@Transactional
	public void removeCustomer(String customerId)
	{
		customerRepository.deleteById(customerId);
	}
	
}