package com.myapp.ecommerce.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
	
	private Integer customerId;
	private Integer orderId;
	private String orderStatus;
	private Double orderAmount;
	private Date createdAt;
	private Set<OrderItemDto> orderItems = new HashSet<>();
}
