package com.myapp.ecommerce.service;

import java.util.Set;

import com.myapp.ecommerce.dto.OrderDto;
import com.myapp.ecommerce.model.Order;

public interface OrderService {

    Set<OrderDto> getAllOrders(Integer customerId);

    OrderDto getOrderById(Integer orderId);

    Order saveOrder(OrderDto orderDto);
    
    public OrderDto convertEntityToDto(Order order);
}
