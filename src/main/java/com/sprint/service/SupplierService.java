package com.sprint.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sprint.entity.Supplier;
import com.sprint.repository.SupplierRepository;

@Service
public class SupplierService {
   
	@Autowired
	SupplierRepository supplierrepository;
	
	@Transactional(readOnly = true)
	public Supplier getSupplier(String supplierId) {
		Optional<Supplier> supplier = supplierrepository.findById(supplierId);
		if(supplier.isPresent()) {
			return supplier.get();
		}
		throw new RuntimeException("Supplier Details Does not exist");
	}
	@Transactional(readOnly = true)
	public List<Supplier> getSuppliers(){
		List<Supplier> supplier = supplierrepository.findAll();
		if(supplier.size()>0) {
			return supplier;
		}
		throw new RuntimeException("Suppliers Details Does not exist");
	}
	@Transactional
	public boolean addOrModifySupplier(Supplier supplier) {
		Supplier supplier1 = supplierrepository.save(supplier);
		return supplier1 != null;
	}
	
	@Transactional
	public void removeSuppliers(String supplierId) {
		supplierrepository.deleteById(supplierId);
	}
	
	@Transactional(readOnly = true)
	public Supplier getSupplierBySupplierName(String supplierName)
	{
		Supplier supplier =supplierrepository.findSupplierDetailsBySupplierName(supplierName);
		return supplier;		
	}
}