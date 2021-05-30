package com.myapp.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myapp.ecommerce.dto.CustomerDto;
import com.myapp.ecommerce.exception.EmptyResourceException;
import com.myapp.ecommerce.exception.IllegalIdException;
import com.myapp.ecommerce.exception.ResourceNotFoundException;
import com.myapp.ecommerce.exception.ResourceNotSaveableException;
import com.myapp.ecommerce.model.Customer;
import com.myapp.ecommerce.service.CustomerService;

@RestController
@RequestMapping(value = "/api/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping(value = "/getAllCustomers")
    public ResponseEntity<Object> getAllCustomers() {
        List<CustomerDto> customers = customerService.getAllCustomers();
        return ResponseEntity.status(HttpStatus.OK).body(customers);
    }

    @GetMapping(value = "/getAllCustomersByCountry/{countryCode}")
    public ResponseEntity<Object> getAllCustomersByCountry(@PathVariable Integer countryCode) {
        List<CustomerDto> customers = customerService.getAllCustomersByCountry(countryCode);
        return ResponseEntity.status(HttpStatus.OK).body(customers);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getCustomer(@PathVariable Integer id) {
        if (id <= 0)
            throw new IllegalIdException();

        CustomerDto customerDto = customerService.getCustomerById(id);
        if (customerDto == null)
            throw new ResourceNotFoundException(id);

        return ResponseEntity.status(HttpStatus.OK).body(customerDto);
    }

    @GetMapping(value = "/{customerId}/country/{countryCode}")
    public ResponseEntity<Object> getCustomerByCountry(@PathVariable Integer customerId, @PathVariable Integer countryCode) {

        CustomerDto customerDto = customerService.getCustomerByCountry(customerId, countryCode);
        if (customerDto == null)
            throw new ResourceNotFoundException(countryCode);

        return ResponseEntity.status(HttpStatus.OK).body(customerDto);
    }

    @PostMapping(value = "/createCustomer")
    public ResponseEntity<Object> createCustomer(@RequestBody CustomerDto customer) {
        if (customer == null)
            throw new EmptyResourceException();

        Customer savedCustomer = customerService.saveCustomer(customer);
        if (savedCustomer == null)
            throw new ResourceNotSaveableException();

        return ResponseEntity.status(HttpStatus.OK).body(savedCustomer);
    }
}
