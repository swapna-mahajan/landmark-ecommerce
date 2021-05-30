package com.myapp.ecommerce.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "sub_order_items")
@AllArgsConstructor
@NoArgsConstructor
public class SubOrderItem {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer subOrderItemId;
    
    @JsonBackReference
    @Column(name = "order_item_id")
    private Integer orderItemId;
    
    @Column(name = "status")
    private String orderItemStatus;
    
    @Column(name = "size_name")
    private String sizeName;
    
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
}
