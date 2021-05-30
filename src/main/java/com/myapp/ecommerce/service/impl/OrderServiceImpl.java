package com.myapp.ecommerce.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myapp.ecommerce.dto.OrderDto;
import com.myapp.ecommerce.dto.OrderItemDto;
import com.myapp.ecommerce.dto.SubOrderItemDto;
import com.myapp.ecommerce.model.Order;
import com.myapp.ecommerce.model.OrderItem;
import com.myapp.ecommerce.model.SubOrderItem;
import com.myapp.ecommerce.repository.OrderRepositoryImpl;
import com.myapp.ecommerce.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepositoryImpl orderRepository;

    @Override
    public Set<OrderDto> getAllOrders(Integer customerId) {
        Set<Order> orders = orderRepository.findByCustomerId(customerId);
    	return convertEntitiesToDtos(orders);
    }

    @Override
    public OrderDto getOrderById(Integer orderId) {
        Order order = orderRepository.findByOrderId(orderId);
        return convertEntityToDto(order);
    }

    @Override
    @Transactional(rollbackFor=Throwable.class)
    public Order saveOrder(OrderDto orderDto) {
    	Order order = convertDtoToEntity(orderDto);
        return orderRepository.save(order);
    }

	private Order convertDtoToEntity(OrderDto orderDto) {
		Order order = new Order();
    	order.setCustomerId(orderDto.getCustomerId());
    	order.setCreatedAt(new Date());
    	order.setOrderAmount(orderDto.getOrderAmount());
    	order.setOrderStatus(orderDto.getOrderStatus());
    	
    	Set<OrderItemDto> orderItemsDto = orderDto.getOrderItems();
    	
    	if (orderItemsDto != null && orderItemsDto.size() > 0) {
        	
    		Set<OrderItem> orderItems = new HashSet<>();
    		
        	orderItemsDto.forEach(item -> {
        		
        		OrderItem orderItem = new OrderItem();
        		orderItem.setCreatedAt(new Date());
        		orderItem.setOrderId(item.getOrderId());
        		orderItem.setOrderItemStatus(item.getOrderItemStatus());
        		orderItem.setProductName(item.getProductName());
        		
        		Set<SubOrderItemDto> subOrderItemsDto = item.getSubOrderItems();
            	
        		Set<SubOrderItem> subOrderItems = new HashSet<>();
        		
            	if (subOrderItemsDto != null && subOrderItemsDto.size() > 0) {
            		subOrderItemsDto.forEach(subOrderItemDto -> {
	            		
	            		SubOrderItem subOrderItem = new SubOrderItem();
	            		subOrderItem.setCreatedAt(new Date());
	            		subOrderItem.setOrderItemId(subOrderItemDto.getOrderItemId());
	            		subOrderItem.setOrderItemStatus(subOrderItemDto.getSubOrderItemStatus());
	            		subOrderItem.setSizeName(subOrderItemDto.getSizeName());
	            		subOrderItems.add(subOrderItem);
	                });
            	}
            	orderItem.setSubOrderItems(subOrderItems);
                orderItems.add(orderItem);
            });
        	order.setOrderItems(orderItems);
        }
		return order;
	}
	
	private Set<OrderDto> convertEntitiesToDtos(Set<Order> orders) {
		Set<OrderDto> ordersDto = new HashSet<>();
    	orders.forEach(order -> {
    		OrderDto orderDto = new OrderDto();
    		orderDto.setCustomerId(order.getCustomerId());
    		orderDto.setCreatedAt(order.getCreatedAt());
    		orderDto.setOrderAmount(order.getOrderAmount());
    		orderDto.setOrderStatus(order.getOrderStatus());
    		orderDto.setOrderId(order.getOrderId());
    		
    		Set<OrderItem> orderItems = order.getOrderItems();
    		Set<OrderItemDto> ordersItemDto = new HashSet<>();
    		
    		orderItems.forEach(orderItem -> {
        		OrderItemDto orderItemDto = new OrderItemDto();
        		orderItemDto.setCreatedAt(orderItem.getCreatedAt());
        		orderItemDto.setOrderId(order.getOrderId());
        		orderItemDto.setOrderItemStatus(orderItem.getOrderItemStatus());
        		orderItemDto.setProductName(orderItem.getProductName());
        		orderItemDto.setOrderItemId(orderItem.getOrderItemId());
        		
        		Set<SubOrderItemDto> subOrdersItemDto = new HashSet<>();
        		orderItem.getSubOrderItems().forEach(subOrderItem -> {
            		SubOrderItemDto subOrderItemDto = new SubOrderItemDto();
            		subOrderItemDto.setCreatedAt(subOrderItem.getCreatedAt());
            		subOrderItemDto.setOrderItemId(orderItem.getOrderItemId());
            		subOrderItemDto.setSizeName(subOrderItem.getSizeName());
            		subOrderItemDto.setSubOrderItemStatus(subOrderItem.getOrderItemStatus());
            		subOrderItemDto.setSubOrderItemId(subOrderItem.getSubOrderItemId());
            		
            		subOrdersItemDto.add(subOrderItemDto);
            	});
        		orderItemDto.setSubOrderItems(subOrdersItemDto);
        		ordersItemDto.add(orderItemDto);
        	});
    		orderDto.setOrderItems(ordersItemDto);
    		ordersDto.add(orderDto);
    	});
		return ordersDto;
	}
	
	@Override
	public OrderDto convertEntityToDto(Order order) {
		OrderDto orderDto = new OrderDto();
		orderDto.setCustomerId(order.getCustomerId());
		orderDto.setCreatedAt(order.getCreatedAt());
		orderDto.setOrderAmount(order.getOrderAmount());
		orderDto.setOrderStatus(order.getOrderStatus());
		orderDto.setOrderId(order.getOrderId());
		
		Set<OrderItem> orderItems = order.getOrderItems();
		Set<OrderItemDto> ordersItemDto = new HashSet<>();
		
		orderItems.forEach(orderItem -> {
    		OrderItemDto orderItemDto = new OrderItemDto();
    		orderItemDto.setCreatedAt(orderItem.getCreatedAt());
    		orderItemDto.setOrderId(order.getOrderId());
    		orderItemDto.setOrderItemStatus(orderItem.getOrderItemStatus());
    		orderItemDto.setProductName(orderItem.getProductName());
    		orderItemDto.setOrderItemId(orderItem.getOrderItemId());
    		
    		Set<SubOrderItemDto> subOrdersItemDto = new HashSet<>();
    		orderItem.getSubOrderItems().forEach(subOrderItem -> {
        		SubOrderItemDto subOrderItemDto = new SubOrderItemDto();
        		subOrderItemDto.setCreatedAt(subOrderItem.getCreatedAt());
        		subOrderItemDto.setSubOrderItemId(subOrderItem.getSubOrderItemId());
        		subOrderItemDto.setOrderItemId(orderItem.getOrderItemId());
        		subOrderItemDto.setSizeName(subOrderItem.getSizeName());
        		subOrderItemDto.setSubOrderItemStatus(subOrderItem.getOrderItemStatus());
        		
        		subOrdersItemDto.add(subOrderItemDto);
        	});
    		orderItemDto.setSubOrderItems(subOrdersItemDto);
    		ordersItemDto.add(orderItemDto);
    	});
		orderDto.setOrderItems(ordersItemDto);
		return orderDto;
	}
}
