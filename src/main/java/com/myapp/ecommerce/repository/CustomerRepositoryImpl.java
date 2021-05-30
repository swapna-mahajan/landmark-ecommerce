package com.myapp.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myapp.ecommerce.model.Customer;

@Repository
public interface CustomerRepositoryImpl extends JpaRepository<Customer, Long> {

    List<Customer> findAllByCountryCode(Integer countryCode);

    Customer findByCustomerId(Integer id);

    Customer findByCustomerIdAndCountryCode(Integer customerId, Integer countryCode);

}
