package com.myapp.ecommerce.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

        @Id
        @GeneratedValue(strategy=GenerationType.IDENTITY)
        @Column(name = "id")
        private Integer customerId;
        
        @Column(name = "full_name", nullable = false)
        private String customerFullName;
        
        @Column(name = "created_at")
        @Temporal(TemporalType.TIMESTAMP)
        private Date createdAt;
        
        @Column(name = "country_code")
        private Integer countryCode;
        
        @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
        @JoinColumn(name="user_id",insertable=true,updatable=true)
        private Set<Order> orders = new HashSet<>();
}
