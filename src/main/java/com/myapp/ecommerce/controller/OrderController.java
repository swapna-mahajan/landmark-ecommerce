package com.myapp.ecommerce.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myapp.ecommerce.dto.OrderDto;
import com.myapp.ecommerce.exception.EmptyResourceException;
import com.myapp.ecommerce.exception.IllegalIdException;
import com.myapp.ecommerce.exception.ResourceNotFoundException;
import com.myapp.ecommerce.exception.ResourceNotSaveableException;
import com.myapp.ecommerce.model.Order;
import com.myapp.ecommerce.service.OrderService;

@RestController
@RequestMapping(value = "/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping(value = "/listAllOrders/{customerId}")
    public ResponseEntity<Object> listAllOrders(@PathVariable Integer customerId) {
        Set<OrderDto> orders = orderService.getAllOrders(customerId);
        return ResponseEntity.status(HttpStatus.OK).body(orders);
    }

    @GetMapping(value = "/viewOrder/{orderId}")
    public ResponseEntity<Object> viewOrder(@PathVariable Integer orderId) {
        if (orderId <= 0)
            throw new IllegalIdException();

        OrderDto orderDto = orderService.getOrderById(orderId);
        if (orderDto == null)
            throw new ResourceNotFoundException(orderId);

        return ResponseEntity.status(HttpStatus.OK).body(orderDto);
    }

    @PostMapping(value = "/createOrder")
    public ResponseEntity<Object> createOrder(@RequestBody OrderDto order) {
        if (order == null)
            throw new EmptyResourceException();

        Order savedOrder = orderService.saveOrder(order);
        if (savedOrder == null)
            throw new ResourceNotSaveableException();

        return ResponseEntity.status(HttpStatus.OK).body(orderService.convertEntityToDto(savedOrder));
    }
}
