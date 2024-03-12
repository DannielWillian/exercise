package com.dannieln.restapi.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.dannieln.restapi.dto.LineItemRequest;
import com.dannieln.restapi.dto.OrderRequest;
import com.dannieln.restapi.model.Order;
import com.dannieln.restapi.service.OrderService;
import com.dannieln.restapi.utils.RequestMapper;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(OrderController.class)
public class OrderControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private OrderService service;
	
	@MockBean
	private RequestMapper mapper;
	
	@MockBean
	private Order order;
	
	private static Map<String, Object> baseResponse;
	private static ObjectMapper objectMapper;
	
	@BeforeAll
	public static void setUp() {
		baseResponse = new HashMap<>();
		baseResponse.put("msg", "ok");
		objectMapper = new ObjectMapper();
	}

	@AfterEach
	public void tearDown() {
	}

	@Test
	void testGetAll() throws Exception {
		
		when(service.getAllOrders()).thenReturn(baseResponse);
		
		this.mockMvc
			.perform(get("/order"))
			.andExpect(status().isOk());
		
	}
	
	@Test
	void testGetById() throws Exception {
		
		Integer orderId = 1;
		
		when(service.getOrderDetailsById(orderId)).thenReturn(baseResponse);
		
		this.mockMvc
			.perform(get("/order/" + orderId))
			.andExpect(status().isOk());
		
	}	
	
	@Test
	void testPost() throws Exception {
		
		when(service.createOrder(order)).thenReturn(baseResponse);
		
		OrderRequest request = new OrderRequest();
		request.setCustomerId(1);
		LineItemRequest lineItem = new LineItemRequest();
		lineItem.setItemId(1);
		lineItem.setQuantity(1);
		request.setItems(List.of(lineItem));
		
		this.mockMvc
			.perform(post("/order")
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(request)))
			.andExpect(status().isOk());
		
	}
	
	@Test
	void deleteById() throws Exception {
		
		Integer orderId = 1;
		
		when(service.deleteOrder(orderId)).thenReturn(baseResponse);
		
		this.mockMvc
			.perform(delete("/order/" + orderId))
			.andExpect(status().isOk());
		
	}	

}
