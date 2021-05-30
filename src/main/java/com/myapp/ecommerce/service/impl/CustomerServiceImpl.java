package com.myapp.ecommerce.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myapp.ecommerce.dto.CustomerDto;
import com.myapp.ecommerce.dto.OrderDto;
import com.myapp.ecommerce.dto.OrderItemDto;
import com.myapp.ecommerce.dto.SubOrderItemDto;
import com.myapp.ecommerce.model.Customer;
import com.myapp.ecommerce.model.Order;
import com.myapp.ecommerce.model.OrderItem;
import com.myapp.ecommerce.repository.CustomerRepositoryImpl;
import com.myapp.ecommerce.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepositoryImpl customerRepository;

    @Override
    public List<CustomerDto> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return convertCustomerEntitiesToDtos(customers);
    }

    @Override
    public List<CustomerDto> getAllCustomersByCountry(Integer countryCode) {
        List<Customer> customers = customerRepository.findAllByCountryCode(countryCode);
        return convertCustomerEntitiesToDtos(customers);
    }

    @Override
    public CustomerDto getCustomerById(Integer id) {
        Customer customer = customerRepository.findByCustomerId(id);
        return convertEntitytoDto(customer);
    }

    @Override
    public CustomerDto getCustomerByCountry(Integer customerId, Integer countryCode) {
        Customer customer = customerRepository.findByCustomerIdAndCountryCode(customerId, countryCode);
        return convertEntitytoDto(customer);
    }

    @Override
    public Customer saveCustomer(CustomerDto customerDto) {
    	Customer customer = new Customer();
    	customer.setCreatedAt(new Date());
    	customer.setCustomerFullName(customerDto.getCustomerFullName());
    	customer.setCountryCode(customerDto.getCountryCode());
        return customerRepository.save(customer);
    }
    
    private List<CustomerDto> convertCustomerEntitiesToDtos(List<Customer> customers) {
		List<CustomerDto> customersDto = new ArrayList<>();
        customers.forEach(customer -> {
        	customersDto.add(convertEntitytoDto(customer));
        });
		return customersDto;
	}
    
    private CustomerDto convertEntitytoDto(Customer customer) {
		CustomerDto customerDto = null;
        if(customer != null) {
        	customerDto = new CustomerDto();
        	customerDto.setCountryCode(customer.getCountryCode());
        	customerDto.setCreatedAt(customer.getCreatedAt());
        	customerDto.setCustomerFullName(customer.getCustomerFullName());
        	customerDto.setCustomerId(customer.getCustomerId());
        	Set<Order> orders = customer.getOrders();
        	
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
            		orderItemDto.setOrderId(orderItem.getOrderId());
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
        		ordersDto.add(orderDto);
        	});
        	customerDto.setOrders(ordersDto);
        }
		return customerDto;
	}

}
