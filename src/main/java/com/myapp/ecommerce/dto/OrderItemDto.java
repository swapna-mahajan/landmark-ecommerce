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
public class OrderItemDto {
	
	private Integer orderId;
	private String orderItemStatus;
	private String productName;
	private Date createdAt;
	private Integer orderItemId;
	private Set<SubOrderItemDto> subOrderItems = new HashSet<>();
}
