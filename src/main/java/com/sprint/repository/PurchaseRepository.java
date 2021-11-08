package com.sprint.repository;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.sprint.entity.Purchase;

public interface PurchaseRepository extends JpaRepository<Purchase, String>
{
	
	@Query(value="select * from purchase where total_amount=(select max(total_amount) from purchase)",nativeQuery=true)
	Purchase  findDetailsByHighestCost();
	
	@Query(value="select * from purchase where supplier_id=:supid",nativeQuery=true)
	Purchase  findBySupplierId(@Param("supid") String supplierId);
	
	@Query(value="select * from purchase where supplier_id=(select supplier_id from purchase group by supplier_id order by count(*) desc limit 1)",nativeQuery=true)
	Purchase  findDetailsByHighestSupplier();		
		
	@Query(value = "SELECT * FROM purchase p WHERE p.purchase_date >= cast(cast(:startDate as text) as timestamp) and purchase_date < cast(cast(:endDate as text) as timestamp)",nativeQuery=true)
	List<Purchase> findWeeklyReport(@Param("startDate")Date date1 ,@Param("endDate")Date date2);
	
	@Query("select pur.invoiceNo,pur.purchaseDate,purpro.productCode,purpro.unitPrice,purpro.quantity,purpro.amount from Purchase pur, PurchasedProduct purpro where pur.invoiceNo =cast(:inv as text)")
	List<Object> findDataReport(@Param("inv")String invoiceNumber);
	
}
