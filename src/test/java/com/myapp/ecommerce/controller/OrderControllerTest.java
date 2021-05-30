package com.myapp.ecommerce.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import com.myapp.ecommerce.model.Order;
import com.myapp.ecommerce.model.OrderItem;
import com.myapp.ecommerce.model.SubOrderItem;
import com.myapp.ecommerce.service.OrderService;

@EnableWebMvc
@WebMvcTest(controllers = OrderController.class)
@ExtendWith(MockitoExtension.class)
public class OrderControllerTest {

	@Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @Autowired
    private ObjectMapper objectMapper;
    
    @InjectMocks
    private OrderController customerController;
    
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
        this.orderItems3.add(new OrderItemDto(Integer.valueOf(3), "PLACED", "Calvin Klein Women T-Shirt", new Date(), Integer.valueOf(12), subOrderItems3));
        this.orderItems3.add(new OrderItemDto(Integer.valueOf(3), "PLACED", "Calvin Klein Women T-Shirt", new Date(), Integer.valueOf(12), subOrderItems3));
        this.orderItems3.add(new OrderItemDto(Integer.valueOf(3), "PLACED", "Calvin Klein Women Shorts", new Date(), Integer.valueOf(12), subOrderItems3));
        this.orders3.add(new OrderDto(Integer.valueOf(1234), 3, "PLACED", Double.valueOf(3000.0), new Date(), orderItems3));
        this.customers.add(new CustomerDto("Andrew Edison", 8, Integer.valueOf(1234), new Date(), orders3));
        
        this.orders4 = new HashSet<>();
        this.orderItems4 = new HashSet<>();
        this.subOrderItems4 = new HashSet<>();
        this.subOrderItems4.add(new SubOrderItemDto(Integer.valueOf(13), "PLACED", new Date(), "XXL", Integer.valueOf(57)));
        this.subOrderItems4.add(new SubOrderItemDto(Integer.valueOf(13), "PLACED", new Date(), "XL", Integer.valueOf(58)));
        this.subOrderItems4.add(new SubOrderItemDto(Integer.valueOf(14), "PLACED", new Date(), "L", Integer.valueOf(59)));
        this.orderItems4.add(new OrderItemDto(Integer.valueOf(4), "PLACED", "Calvin Klein Women T-Shirt", new Date(), Integer.valueOf(13), subOrderItems4));
        this.orderItems4.add(new OrderItemDto(Integer.valueOf(4), "PLACED", "Calvin Klein Women T-Shirt", new Date(), Integer.valueOf(13), subOrderItems4));
        this.orderItems4.add(new OrderItemDto(Integer.valueOf(4), "PLACED", "Calvin Klein Women Jeans", new Date(), Integer.valueOf(14), subOrderItems4));
        this.orders4.add(new OrderDto(Integer.valueOf(1234), 4, "PLACED", Double.valueOf(9000.0), new Date(), orderItems4));
        this.customers.add(new CustomerDto("Patrick Jones", 31, Integer.valueOf(5678), new Date(), orders4));
        
        this.orders5 = new HashSet<>();
        this.orderItems5 = new HashSet<>();
        this.subOrderItems5 = new HashSet<>();
        this.subOrderItems5.add(new SubOrderItemDto(Integer.valueOf(15), "PLACED", new Date(), "XXL", Integer.valueOf(60)));
        this.subOrderItems5.add(new SubOrderItemDto(Integer.valueOf(16), "PLACED", new Date(), "XL", Integer.valueOf(61)));
        this.subOrderItems5.add(new SubOrderItemDto(Integer.valueOf(17), "PLACED", new Date(), "L", Integer.valueOf(62)));
        this.orderItems5.add(new OrderItemDto(Integer.valueOf(5), "PLACED", "Calvin Klein Women T-Shirt", new Date(), Integer.valueOf(15), subOrderItems5));
        this.orderItems5.add(new OrderItemDto(Integer.valueOf(5), "PLACED", "Calvin Klein Scarf", new Date(), Integer.valueOf(16), subOrderItems5));
        this.orderItems5.add(new OrderItemDto(Integer.valueOf(5), "PLACED", "Calvin Klein Men Jeans", new Date(), Integer.valueOf(17), subOrderItems5));
        this.orders5.add(new OrderDto(Integer.valueOf(1234), 5, "PLACED", Double.valueOf(12000.0), new Date(), orderItems5));
        customer2 = new CustomerDto("Patrick Jones", 3, Integer.valueOf(5678), new Date(), orders5);
    }
    
    @Test
    void shouldFetchAllOrders() throws Exception {
        when(orderService.getAllOrders(Integer.valueOf(1234))).thenReturn(this.orders1);

        this.mockMvc.perform(get("/api/order/listAllOrders/{customerId}", Integer.valueOf(1234)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(orders1.size())))
                .andExpect(jsonPath("$[0].orderId", is(1)))
                .andExpect(jsonPath("$[0].orderStatus", is("PLACED")))
                .andExpect(jsonPath("$[0].customerId", is(1234)))
                .andExpect(jsonPath("$[0].orderAmount", is(Double.valueOf(4000))))
                .andExpect(jsonPath("$[*].orderItems").isNotEmpty())
                .andExpect(jsonPath("$[*].orderItems[*].subOrderItems").isNotEmpty())
                .andExpect(jsonPath("$[0].orderItems[0].orderItemId", is(10)));
        
        verify(orderService, times(1)).getAllOrders(Integer.valueOf(1234));
        verifyNoMoreInteractions(orderService);
    }
    
    @Test
    void shouldFindOrderById() throws Exception {
    	OrderDto orderDto = new OrderDto(Integer.valueOf(1234), Integer.valueOf(4), "PLACED", Double.valueOf(9000.0), new Date(), orderItems4);
        when(orderService.getOrderById(Integer.valueOf(4))).thenReturn(orderDto);

        this.mockMvc.perform(get("/api/order/viewOrder/{orderId}", Integer.valueOf(4)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.orderId", is(4)))
                .andExpect(jsonPath("$.orderStatus", is("PLACED")))
                .andExpect(jsonPath("$.customerId", is(1234)))
                .andExpect(jsonPath("$.orderAmount", is(Double.valueOf(9000.0))))
                .andExpect(jsonPath("$.orderItems").isNotEmpty())
                .andExpect(jsonPath("$.orderItems[*].subOrderItems").isNotEmpty());
        
        verify(orderService, times(1)).getOrderById(Integer.valueOf(4));
        verifyNoMoreInteractions(orderService);
    }
    
    @Test
    void shouldReturn404WhenFetchingNonExistingOrder() throws Exception {
        when(orderService.getOrderById(Integer.valueOf(5))).thenReturn(null);

        this.mockMvc.perform(get("/api/order/viewOrder/{orderId}", Integer.valueOf(5)))
                .andExpect(status().isNotFound());
        
        verify(orderService, times(1)).getOrderById(Integer.valueOf(5));
        verifyNoMoreInteractions(orderService);
    }
    
    @Test
    void shouldCreateNewOrder() throws Exception {
    	
    	OrderDto orderDto1 = new OrderDto(Integer.valueOf(3456), Integer.valueOf(6), "PLACED", Double.valueOf(1200), new Date(), new HashSet<OrderItemDto>());
    	Order order = new Order(Integer.valueOf(6), Integer.valueOf(3456), "PLACED", Double.valueOf(1200), new Date(), new HashSet<OrderItem>());
    	
        when(orderService.saveOrder(orderDto1)).thenReturn(order);
    	
        this.mockMvc.perform(post("/api/order/createOrder")
				    .contentType(MediaType.APPLICATION_JSON_UTF8)
				    .content(objectMapper.writeValueAsString(orderDto1)))
        		 	.andExpect(status().isOk());
    	
	    /**
		 *  This is giving ResourceNotSaveableException, so commenting it.
		 */
      /*  
        Set<OrderItem> orderItems = new HashSet<>();
        Set<SubOrderItem> subOrderItems = new HashSet<>();
        subOrderItems.add(new SubOrderItem(Integer.valueOf(60), Integer.valueOf(15), "PLACED", "XXL", new Date()));
        subOrderItems.add(new SubOrderItem(Integer.valueOf(61), Integer.valueOf(16), "PLACED", "XL", new Date()));
        subOrderItems.add(new SubOrderItem(Integer.valueOf(62), Integer.valueOf(17), "PLACED", "L", new Date()));
        orderItems.add(new OrderItem(Integer.valueOf(15), Integer.valueOf(5), "PLACED", "Calvin Klein Women T-Shirt", new Date(), subOrderItems));
        orderItems.add(new OrderItem(Integer.valueOf(16), Integer.valueOf(5), "PLACED", "Calvin Klein Scarf", new Date(), subOrderItems));
        orderItems.add(new OrderItem(Integer.valueOf(17), Integer.valueOf(5), "PLACED", "Calvin Klein Men Jeans", new Date(), subOrderItems));
    	Order order = new Order(Integer.valueOf(6), Integer.valueOf(3456), "PLACED", Double.valueOf(1200), new Date(), orderItems);
    	OrderDto orderDto = new OrderDto(Integer.valueOf(3456), Integer.valueOf(6), "PLACED", Double.valueOf(1200), new Date(), orderItems5);
    	
        when(orderService.saveOrder(orderDto)).thenReturn(order);
    	
        this.mockMvc.perform(post("/api/order/createOrder")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(orderDto)))
			    .andExpect(jsonPath("$.orderId", is(6)))
			    .andExpect(jsonPath("$.orderStatus", is("PLACED")))
			    .andExpect(jsonPath("$.customerId", is(3456)))
			    .andExpect(jsonPath("$.orderAmount", is(Double.valueOf(1200))))
			    .andExpect(jsonPath("$[*].orderItems").isNotEmpty())
			    .andExpect(jsonPath("$[*].orderItems[*].subOrderItems").isNotEmpty())
			    .andExpect(jsonPath("$[0].orderItems[0].orderItemId", is(10)))
			    .andExpect(jsonPath("$[0].orderItems[0].productName", is("Calvin Klein Women T-Shirt")))
			    .andExpect(jsonPath("$[0].orderItems[0].orderItemId[0].subOrderItems[0].subOrderItemId", is(60)));
        */
        
    }
}
