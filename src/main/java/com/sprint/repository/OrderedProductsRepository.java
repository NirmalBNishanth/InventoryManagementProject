package com.sprint.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sprint.entity.OrderedProducts;

public interface OrderedProductsRepository extends JpaRepository<OrderedProducts,String>{

}
