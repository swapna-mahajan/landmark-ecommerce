package com.myapp.ecommerce.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myapp.ecommerce.model.Order;

@Repository
public interface OrderRepositoryImpl extends JpaRepository<Order, Long> {

    Set<Order> findByCustomerId(Integer customerId);

    Order findByOrderId(Integer orderId);
}
