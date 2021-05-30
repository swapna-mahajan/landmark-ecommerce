package com.myapp.ecommerce.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myapp.ecommerce.dto.CustomerDto;
import com.myapp.ecommerce.dto.OrderDto;
import com.myapp.ecommerce.dto.OrderItemDto;
import com.myapp.ecommerce.dto.SubOrderItemDto;
import com.myapp.ecommerce.model.Customer;
import com.myapp.ecommerce.service.CustomerService;

@EnableWebMvc
@WebMvcTest(controllers = CustomerController.class)
@ExtendWith(MockitoExtension.class)
public class CustomerControllerTest {
	
	@Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @Autowired
    private ObjectMapper objectMapper;
    
    @InjectMocks
    private CustomerController customerController;

    private List<CustomerDto> customers;
    private Set<OrderDto> orders1;
    private Set<OrderDto> orders2;
    private Set<OrderItemDto> orderItems1;
    private Set<OrderItemDto> orderItems2;
    private Set<SubOrderItemDto> subOrderItems1;
    private Set<SubOrderItemDto> subOrderItems2;
    private Set<OrderDto> orders3;
    private Set<OrderItemDto> orderItems3;
    private Set<SubOrderItemDto> subOrderItems3;
    private Set<OrderDto> orders4;
    private Set<OrderItemDto> orderItems4;
    private Set<SubOrderItemDto> subOrderItems4;
    private Set<OrderDto> orders5;
    private Set<OrderItemDto> orderItems5;
    private Set<SubOrderItemDto> subOrderItems5;
    private CustomerDto customer1;
    private CustomerDto customer2;
    
    @Before
    public void init() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(customerController)
                .build();
    }

    @BeforeEach
    void setUp() {
        this.customers = new ArrayList<>();
        
        this.orders1 = new HashSet<>();
        this.orderItems1 = new HashSet<>();
        this.subOrderItems1 = new HashSet<>();
        this.subOrderItems1.add(new SubOrderItemDto(Integer.valueOf(10), "PLACED", new Date(), "XXL", Integer.valueOf(50)));
        this.subOrderItems1.add(new SubOrderItemDto(Integer.valueOf(10), "PLACED", new Date(), "XL", Integer.valueOf(51)));
        this.subOrderItems1.add(new SubOrderItemDto(Integer.valueOf(10), "PLACED", new Date(), "L", Integer.valueOf(52)));
        this.orderItems1.add(new OrderItemDto(Integer.valueOf(1), "PLACED", "Calvin Klein Men T-Shirt", new Date(), Integer.valueOf(10), subOrderItems1));
        this.orderItems1.add(new OrderItemDto(Integer.valueOf(1), "PLACED", "Calvin Klein Men T-Shirt", new Date(), Integer.valueOf(10), subOrderItems1));
        this.orderItems1.add(new OrderItemDto(Integer.valueOf(1), "PLACED", "Calvin Klein Men T-Shirt", new Date(), Integer.valueOf(10), subOrderItems1));
        this.orders1.add(new OrderDto(Integer.valueOf(1234), 1, "PLACED", Double.valueOf(4000.0), new Date(), orderItems1));
        customer1 = new CustomerDto("Andrew Edison", 23, Integer.valueOf(1234), new Date(), orders1);
        this.customers.add(customer1);
        
        this.orders2 = new HashSet<>();
        this.orderItems2 = new HashSet<>();
        this.subOrderItems2 = new HashSet<>();
        this.subOrderItems2.add(new SubOrderItemDto(Integer.valueOf(11), "PLACED", new Date(), "M", Integer.valueOf(53)));
        this.orderItems2.add(new OrderItemDto(Integer.valueOf(2), "PLACED", "Calvin Klein Men Jeans", new Date(), Integer.valueOf(11), subOrderItems2));
        this.orders2.add(new OrderDto(Integer.valueOf(1234), 2, "PLACED", Double.valueOf(5000.0), new Date(), orderItems2));
        this.customers.add(new CustomerDto("Andrew Edison", 31, Integer.valueOf(1234), new Date(), orders2));
        
        this.orders3 = new HashSet<>();
        this.orderItems3 = new HashSet<>();
        this.subOrderItems3 = new HashSet<>();
        this.subOrderItems3.add(new SubOrderItemDto(Integer.valueOf(12), "PLACED", new Date(), "XXL", Integer.valueOf(54)));
        this.subOrderItems3.add(new SubOrderItemDto(Integer.valueOf(12), "PLACED", new Date(), "XL", Integer.valueOf(55)));
        this.subOrderItems3.add(new SubOrderItemDto(Integer.valueOf(12), "PLACED", new Date(), "L", Integer.valueOf(56)));
        this.orderItems3.add(new OrderItemDto(Integer.valueOf(1), "PLACED", "Calvin Klein Women T-Shirt", new Date(), Integer.valueOf(10), subOrderItems3));
        this.orderItems3.add(new OrderItemDto(Integer.valueOf(1), "PLACED", "Calvin Klein Women T-Shirt", new Date(), Integer.valueOf(10), subOrderItems3));
        this.orderItems3.add(new OrderItemDto(Integer.valueOf(1), "PLACED", "Calvin Klein Women T-Shirt", new Date(), Integer.valueOf(10), subOrderItems3));
        this.orders3.add(new OrderDto(Integer.valueOf(1234), 1, "PLACED", Double.valueOf(4000.0), new Date(), orderItems3));
        this.customers.add(new CustomerDto("Andrew Edison", 8, Integer.valueOf(1234), new Date(), orders3));
        
        this.orders4 = new HashSet<>();
        this.orderItems4 = new HashSet<>();
        this.subOrderItems4 = new HashSet<>();
        this.subOrderItems4.add(new SubOrderItemDto(Integer.valueOf(10), "PLACED", new Date(), "XXL", Integer.valueOf(57)));
        this.subOrderItems4.add(new SubOrderItemDto(Integer.valueOf(10), "PLACED", new Date(), "XL", Integer.valueOf(58)));
        this.subOrderItems4.add(new SubOrderItemDto(Integer.valueOf(10), "PLACED", new Date(), "L", Integer.valueOf(59)));
        this.orderItems4.add(new OrderItemDto(Integer.valueOf(1), "PLACED", "Calvin Klein Women T-Shirt", new Date(), Integer.valueOf(10), subOrderItems4));
        this.orderItems4.add(new OrderItemDto(Integer.valueOf(1), "PLACED", "Calvin Klein Women T-Shirt", new Date(), Integer.valueOf(10), subOrderItems4));
        this.orderItems4.add(new OrderItemDto(Integer.valueOf(1), "PLACED", "Calvin Klein Women T-Shirt", new Date(), Integer.valueOf(10), subOrderItems4));
        this.orders4.add(new OrderDto(Integer.valueOf(1234), 1, "PLACED", Double.valueOf(4000.0), new Date(), orderItems4));
        this.customers.add(new CustomerDto("Patrick Jones", 31, Integer.valueOf(5678), new Date(), orders4));
        
        this.orders5 = new HashSet<>();
        this.orderItems5 = new HashSet<>();
        this.subOrderItems5 = new HashSet<>();
        this.subOrderItems5.add(new SubOrderItemDto(Integer.valueOf(10), "PLACED", new Date(), "XXL", Integer.valueOf(60)));
        this.subOrderItems5.add(new SubOrderItemDto(Integer.valueOf(10), "PLACED", new Date(), "XL", Integer.valueOf(61)));
        this.subOrderItems5.add(new SubOrderItemDto(Integer.valueOf(10), "PLACED", new Date(), "L", Integer.valueOf(62)));
        this.orderItems5.add(new OrderItemDto(Integer.valueOf(1), "PLACED", "Calvin Klein Women T-Shirt", new Date(), Integer.valueOf(10), subOrderItems5));
        this.orderItems5.add(new OrderItemDto(Integer.valueOf(1), "PLACED", "Calvin Klein Women T-Shirt", new Date(), Integer.valueOf(10), subOrderItems5));
        this.orderItems5.add(new OrderItemDto(Integer.valueOf(1), "PLACED", "Calvin Klein Women T-Shirt", new Date(), Integer.valueOf(10), subOrderItems5));
        this.orders5.add(new OrderDto(Integer.valueOf(1234), 1, "PLACED", Double.valueOf(4000.0), new Date(), orderItems3));
        customer2 = new CustomerDto("Patrick Jones", 3, Integer.valueOf(5678), new Date(), orders5);
    }
    
    @Test
    void shouldFetchAllCustomers() throws Exception {
        when(customerService.getAllCustomers()).thenReturn(this.customers);

        this.mockMvc.perform(get("/api/customer/getAllCustomers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(customers.size())))
                .andExpect(jsonPath("$[0].customerId", is(1234)))
                .andExpect(jsonPath("$[0].customerFullName", is("Andrew Edison")))
                .andExpect(jsonPath("$[0].countryCode", is(23)))
                .andExpect(jsonPath("$[1].customerId", is(1234)))
                .andExpect(jsonPath("$[1].customerFullName", is("Andrew Edison")))
                .andExpect(jsonPath("$[1].countryCode", is(31)));
        
        verify(customerService, times(1)).getAllCustomers();
        verifyNoMoreInteractions(customerService);
    }
    
    @Test
    void shouldFindAllCustomersByCountry() throws Exception {
        CustomerDto customer = new CustomerDto("Patrick Jones", 3, Integer.valueOf(5678), new Date(), orders5);
        CustomerDto customer1 = new CustomerDto("Andrew Edison", 3, Integer.valueOf(1234), new Date(), orders3);
        List<CustomerDto> customersList = new ArrayList<>();
        customersList.add(customer);
        customersList.add(customer1);

        when(customerService.getAllCustomersByCountry(Integer.valueOf(3))).thenReturn(customersList);

        this.mockMvc.perform(get("/api/customer/getAllCustomersByCountry/{countryCode}", Integer.valueOf(3)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.size()", is(customersList.size())))
        .andExpect(jsonPath("$[0].customerId", is(5678)))
        .andExpect(jsonPath("$[0].customerFullName", is("Patrick Jones")))
        .andExpect(jsonPath("$[0].countryCode", is(3)))
        .andExpect(jsonPath("$[1].customerId", is(1234)))
        .andExpect(jsonPath("$[1].customerFullName", is("Andrew Edison")))
        .andExpect(jsonPath("$[1].countryCode", is(3)));
        
        verify(customerService, times(1)).getAllCustomersByCountry(Integer.valueOf(3));
        verifyNoMoreInteractions(customerService);
    }
    
    @Test
    void shouldFindCustomerById() throws Exception {
        Integer customerId = Integer.valueOf(5678);
        when(customerService.getCustomerById(customerId)).thenReturn(customer2);

        this.mockMvc.perform(get("/api/customer/{id}", customerId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerId", is(customer2.getCustomerId())))
                .andExpect(jsonPath("$.countryCode", is(customer2.getCountryCode())))
                .andExpect(jsonPath("$.customerFullName", is(customer2.getCustomerFullName())));
        
        verify(customerService, times(1)).getCustomerById(customerId);
        verifyNoMoreInteractions(customerService);
    }
    
    @Test
    void shouldFindCustomerByCountry() throws Exception {
        Integer customerId = Integer.valueOf(5678);
        when(customerService.getCustomerByCountry(customerId, 3)).thenReturn(customer2);

        this.mockMvc.perform(get("/api/customer/{id}/country/{countryCode}", customerId, 3))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerId", is(customer2.getCustomerId())))
                .andExpect(jsonPath("$.countryCode", is(customer2.getCountryCode())))
                .andExpect(jsonPath("$.customerFullName", is(customer2.getCustomerFullName())));
        
        verify(customerService, times(1)).getCustomerByCountry(customerId, 3);
        verifyNoMoreInteractions(customerService);
    }
    
    @Test
    void shouldReturn404WhenFetchingNonExistingCustomer() throws Exception {
    	Integer customerId = Integer.valueOf(5);
        when(customerService.getCustomerById(customerId)).thenReturn(null);

        this.mockMvc.perform(get("/api/customer/{id}", customerId))
                .andExpect(status().isNotFound());
        
        verify(customerService, times(1)).getCustomerById(customerId);
        verifyNoMoreInteractions(customerService);
    }
    
    @Test
    void shouldCreateNewCustomer() throws Exception {
    	
    	CustomerDto customerDTO = new CustomerDto("Patrick Jones", 3, Integer.valueOf(5678), new Date(), new HashSet<>());
    	Customer newCustomer = new Customer(Integer.valueOf(5678), "Patrick Jones", new Date(), Integer.valueOf(3), new HashSet<>());
        when(customerService.saveCustomer(customerDTO)).thenReturn(newCustomer);
       
        this.mockMvc.perform(post("/api/customer/createCustomer")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(newCustomer)))
                .andExpect(jsonPath("$.customerId", is(newCustomer.getCustomerId())))
                .andExpect(jsonPath("$.countryCode", is(newCustomer.getCountryCode())))
                .andExpect(jsonPath("$.customerFullName", is(newCustomer.getCustomerFullName())));
        
        verify(customerService, times(1)).saveCustomer(customerDTO);
        verifyNoMoreInteractions(customerService);
    }
    
    @Test
    public void shouldNotCreateCustomerWithoutCustomerFullName() throws Exception {
    	
    	CustomerDto customerDTO = new CustomerDto("", 3, Integer.valueOf(5678), new Date(), new HashSet<>());
        when(customerService.saveCustomer(customerDTO)).thenReturn(null);
       
        this.mockMvc.perform(post("/api/customer/createCustomer"))
        		 .andExpect(status().isBadRequest());
        
        verifyNoMoreInteractions(customerService);
    }
}
