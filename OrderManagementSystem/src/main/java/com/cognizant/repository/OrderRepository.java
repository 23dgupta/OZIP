package com.cognizant.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cognizant.model.OrderDetails;

public interface OrderRepository extends JpaRepository<OrderDetails, Integer>{

}
