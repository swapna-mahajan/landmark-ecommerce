package com.myapp.ecommerce.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubOrderItemDto {

	 private Integer orderItemId;
     private String subOrderItemStatus;
     private Date createdAt;
     private String sizeName;
     private Integer subOrderItemId;
}
