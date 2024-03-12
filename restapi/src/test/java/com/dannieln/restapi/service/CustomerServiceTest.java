package com.dannieln.restapi.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

import com.dannieln.restapi.model.Order;
import com.dannieln.restapi.repository.OrderRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
class CustomerServiceTest {
	
	@Autowired 
	private CustomerService service;
	
	@MockBean 
	private OrderRepository repository;
	
	@MockBean
	private Order order;
	
	@Bean
	ObjectMapper mapper() {
		return new ObjectMapper(); 
	}
	
	@Test
	void testGetOrders() throws Exception {
		
		Integer customerId = 1;
		
		when(repository.findByCustomerId(customerId)).thenReturn(List.of(order));
		
		Map<String, Object> response = service.getOrders(customerId);
		assertThat(response).isNotNull();
	}
	
	@Test
	void testGetTotalItems() throws Exception {		
		Integer customerId = 1;
		Integer categoryId = 1;
		
		when(repository.getTotalItems(customerId, categoryId)).thenReturn(10L);
		
		Map<String, Object> response = service.getTotalItems(customerId, categoryId);
		assertThat(response).isNotNull();
	}
	
	@Test
	void testGetTotalAmount() throws Exception {
		Integer customerId = 1;
		Integer categoryId = 1;
		
		when(repository.getTotalAmount(customerId, categoryId)).thenReturn(12.3d);
		
		Map<String, Object> response = service.getTotalAmount(customerId, categoryId);
		assertThat(response).isNotNull();
	}
	
}
