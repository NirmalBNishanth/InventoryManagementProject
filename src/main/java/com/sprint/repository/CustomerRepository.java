package com.sprint.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sprint.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer,String> {

}

