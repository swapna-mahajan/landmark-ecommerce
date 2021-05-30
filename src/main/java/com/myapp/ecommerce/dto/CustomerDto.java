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
public class CustomerDto {
	
	private String customerFullName;
	private int countryCode;
	private Integer customerId;
	private Date createdAt;
	private Set<OrderDto> orders = new HashSet<>();
}
