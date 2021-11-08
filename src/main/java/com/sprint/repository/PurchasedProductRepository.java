package com.sprint.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sprint.entity.PurchasedProduct;

public interface PurchasedProductRepository extends JpaRepository<PurchasedProduct, String> {
         Optional<PurchasedProduct> findByProductCode(String productCode); 
         @Modifying 
         @Query("delete from PurchasedProduct pp where pp.productCode=:pCode")
         int deleteByProductCode(@Param("pCode") String productCode);
}