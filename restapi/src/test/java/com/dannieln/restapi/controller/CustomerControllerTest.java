package com.dannieln.restapi.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.dannieln.restapi.model.Order;
import com.dannieln.restapi.service.CustomerService;

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CustomerService service;
	
	@MockBean
	private Order order;
	
	private static Map<String, Object> baseResponse;
	
	@BeforeAll
	public static void setUp() {
		baseResponse = new HashMap<>();
		baseResponse.put("msg", "ok");
	}


	@Test
	void testGetOrders() throws Exception {
		
		Integer customerId = 1;
		
		when(service.getOrders(customerId)).thenReturn(baseResponse);
		
		this.mockMvc
			.perform(get("/customer/" + customerId + "/orders"))
			.andExpect(status().isOk());
		
	}	
	
	@Test
	void testGetTotalItems() throws Exception {
		
		Integer customerId = 1;
		Integer categoryId = 1;
		
		when(service.getTotalItems(customerId, categoryId)).thenReturn(baseResponse);
		
		this.mockMvc
			.perform(get("/customer/" + customerId + "/items/" + categoryId))
			.andExpect(status().isOk());
		
	}	
	
	@Test
	void testGetTotalAmount() throws Exception {
		
		Integer customerId = 1;
		Integer categoryId = 1;
		
		when(service.getTotalItems(customerId, categoryId)).thenReturn(baseResponse);
		
		this.mockMvc
			.perform(get("/customer/" + customerId + "/amount/" + categoryId))
			.andExpect(status().isOk());
		
	}	
}
