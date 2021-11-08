package com.sprint.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sprint.entity.ProductInventory;


public interface ProductInventoryRepository extends JpaRepository<ProductInventory, String>
{	

}
