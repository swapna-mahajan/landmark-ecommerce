package com.myapp.ecommerce.service;

import java.util.List;

import com.myapp.ecommerce.dto.CustomerDto;
import com.myapp.ecommerce.model.Customer;

public interface CustomerService {
    
    public List<CustomerDto> getAllCustomers();

    public List<CustomerDto> getAllCustomersByCountry(Integer countryCode);

    public CustomerDto getCustomerById(Integer id);

    public CustomerDto getCustomerByCountry(Integer customerId, Integer countryCode);

    public Customer saveCustomer(CustomerDto customer);

}
