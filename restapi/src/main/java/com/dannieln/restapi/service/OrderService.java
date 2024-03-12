package com.dannieln.restapi.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dannieln.restapi.dto.OperationType;
import com.dannieln.restapi.dto.OrderMessageDto;
import com.dannieln.restapi.model.LineItem;
import com.dannieln.restapi.model.Order;
import com.dannieln.restapi.rabbitmq.MessageSender;
import com.dannieln.restapi.repository.OrderRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * This is the Service class for the Order model. It's composed by methods 
 * responsible to intermediate the Controller and the Repository classes.
 */
@Service
public class OrderService {
	
	@Autowired OrderRepository repository;
	@Autowired MessageSender messageSender;
	@Autowired ObjectMapper mapper;
	
	/**
	 * Returns all Orders that exist in the system.
	 * 
	 * @return {@link Map}
	 */
	public Map<String, Object> getAllOrders() {
		Map<String, Object> response = new HashMap<>();
		response.put("orders", repository.findAll());
		return response;
	}
	
	/**
	 * Returns the Order details for a specific Order
	 * 
	 * @param order_id
	 * @return {@link Map}
	 */
	public Map<String, Object> getOrderDetailsById(Integer order_id) {
		Map<String, Object> response = new HashMap<>();
		response.put("order", repository.findById(order_id));
		return response;
	}
	
	/**
	 * Creates an Order in the database and sends a CREATE message to RabbitMQ
	 * 
	 * @param order_id
	 * @return {@link Map}
	 */
	public Map<String, Object> createOrder(Order order) {
		Map<String, Object> response = new HashMap<>();
		response.put("msg", "Created order with order_id");
		repository.save(order);
		response.put("order", order);
		messageSender.send(serializeOrder(order, OperationType.CREATE));
		return response;
	}
	
	/**
	 * Deletes an Order from the database and sends a DELETE message to RabbitMQ
	 * 
	 * @param order_id
	 * @return {@link Map}
	 */
	public Map<String, Object> deleteOrder(Integer order_id) {
		Map<String, Object> response = new HashMap<>();
		Order order = repository.findById(order_id).get();
		String message = serializeOrder(order, OperationType.DELETE);
		repository.deleteById(order_id);
		response.put("msg", "Deleted order for order_id " + order_id);
		messageSender.send(message);
		return response;
	}
	
	/**
	 * This method populates and OrderMessageDto and serializes it into a String.
	 * 
	 * @param order_id
	 * @return {@link String}
	 */
	private String serializeOrder(Order order, OperationType type) {
		try {
			OrderMessageDto dto = new OrderMessageDto();
			dto.setOperationType(type);
			dto.setCustomerId(order.getCustomer().getId());
			Float amount = 0f;
			for (LineItem item : order.getItems()) {
				amount += item.getAmount();
			}
			dto.setAmount(amount);
			return mapper.writeValueAsString(dto);
		} catch (Exception e) {
			return "";
		}
	}
	
}
