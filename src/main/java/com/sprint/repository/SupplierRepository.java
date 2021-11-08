package com.sprint.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.sprint.entity.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, String> 
{
	@Query(value="select * from supplier where supplier_name=:sname",nativeQuery=true)
	Supplier  findSupplierDetailsBySupplierName(@Param("sname")String supplierName);
}