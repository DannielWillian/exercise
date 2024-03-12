package com.dannieln.restapi.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.dannieln.restapi.model.Customer;
import com.dannieln.restapi.model.Item;
import com.dannieln.restapi.model.LineItem;
import com.dannieln.restapi.model.Order;

@DataJpaTest
public class OrderRepositoryTest {
	
    @Autowired
    private OrderRepository orderRepository;
   
    @Autowired
    private ItemRepository itemRepository;
    
	@Test
	void testCreateOrder() throws Exception {
		Order order = orderRepository.save(createOrder());
		assertThat(order).isNotNull();
	}
	
	@Test
	void testFindByCustomerId() throws Exception {
		
		orderRepository.save(createOrder());
		
		List<Order> order = orderRepository.findByCustomerId(1);
		
		assertThat(order).isNotNull().hasSize(1);
	}
    
	@Test
	void testGetTotalItems() throws Exception {
		
		orderRepository.save(createOrder());
		
		Long total = orderRepository.getTotalItems(1, 1);
		
		assertThat(total).isNotNull();
		assertTrue(total == 10);
		
	}
	
	@Test
	void testGetTotalAmount() throws Exception {
		
		orderRepository.save(createOrder());
		
		Double total = orderRepository.getTotalAmount(1, 1);
		
		assertThat(total).isNotNull();
		assertTrue(total == 30.0d);
		
	}
    
    private Order createOrder() {
		Order order = new Order();
		
		Customer customer = new Customer();
		customer.setId(1);
		order.setCustomer(customer);
		
		
		Item item = itemRepository.findById(1).get();
		
		Float value = item.getPrice();
		Integer qtty = 10;
		
		LineItem lineItem = new LineItem();
		lineItem.setItem(item);
		lineItem.setQuantity(qtty);
		lineItem.setAmount(qtty * value);
				
		order.setItems(List.of(lineItem));
		
		return order;
	}

}
