package com.sprint.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.sprint.entity.Orders;

public interface OrdersRepository extends JpaRepository<Orders,String>
{
	
	@Query(value="select * from orders where amount=(select max(amount) from orders)",nativeQuery=true)
	Orders  findDetailsByHighestCost();
	
	@Query(value="select * from orders where customer_id=:custid",nativeQuery=true)
	Orders  findByCustomerId(@Param("custid") String supplierId);
	
	@Query(value="select * from orders where customer_id=(select customer_id from orders group by customer_id order by count(*) desc limit 1)",nativeQuery=true)
	Orders  findDetailsByHighestCustomer();	
	
	@Query(value = "SELECT * FROM orders p WHERE p.order_date >= cast(cast(:startDate as text) as timestamp) and order_date < cast(cast(:endDate as text) as timestamp)",nativeQuery=true)
	List<Orders> findWeeklyReport(@Param("startDate")Date date1 ,@Param("endDate")Date date2);
	
	@Query("select ord.orderNo,ord.date,ordpro.productCode,pi.productName,pi.productDescription,ordpro.unitPrice,ordpro.quantity,ordpro.amount from ProductInventory pi,Orders ord, OrderedProducts ordpro where ord.orderNo =cast(:inv as text)")
	List<Object> findDataReport(@Param("inv")String invoiceNumber);
}
