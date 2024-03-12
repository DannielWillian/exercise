package com.dannieln.restapi.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

import com.dannieln.restapi.model.Order;
import com.dannieln.restapi.rabbitmq.MessageSender;
import com.dannieln.restapi.repository.OrderRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
class OrderServiceTest {
	
	@Autowired 
	private OrderService service;
	
	@MockBean 
	private OrderRepository repository;
	
	@MockBean 
	private MessageSender messageSender;
	
	@MockBean
	private Order order;
	
	@Bean
	ObjectMapper mapper() {
		return new ObjectMapper(); 
	}
	
	@Test
	void testGetAllOrders() throws Exception {
		
		when(repository.findAll()).thenReturn(List.of(order));
		
		Map<String, Object> response = service.getAllOrders();
		assertThat(response).isNotNull();
	}
	
	@Test
	void testGetOrderDetailsById() throws Exception {		
		Integer orderId = 1;
		
		when(repository.findById(orderId)).thenReturn(Optional.of(order));
		
		Map<String, Object> response = service.getOrderDetailsById(orderId);
		assertThat(response).isNotNull();
	}
	
	@Test
	void testCreateOrder() throws Exception {
		
		when(repository.save(order)).thenReturn(order);
		
		Map<String, Object> response = service.createOrder(order);
		assertThat(response).isNotNull();
		
	}
	
	@Test
	void testDeleteOrder() throws Exception {
		
		Integer orderId = 1;
		
		when(repository.findById(orderId)).thenReturn(Optional.of(order));
		doNothing().when(repository).deleteById(orderId);
		
		Map<String, Object> response = service.deleteOrder(orderId);
		assertThat(response).isNotNull();
		
	}
	
}
