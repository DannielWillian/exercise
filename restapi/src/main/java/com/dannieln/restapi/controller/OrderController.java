package com.dannieln.restapi.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dannieln.restapi.dto.OrderRequest;
import com.dannieln.restapi.model.Order;
import com.dannieln.restapi.service.OrderService;
import com.dannieln.restapi.utils.RequestMapper;

/**
 * This class defines the endpoints related to Orders. It provides 4 endpoints:
 * 
 * <ul>
 * 	<li>Get all Orders</li>
 * 	<li>Get Order details by order id</li>
 * 	<li>Create Order</li>
 *  <li>Delete Order</li>
 * </ul>
 */
@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired OrderService orderService;
	@Autowired RequestMapper mapper;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> getAllOrders() {
		return orderService.getAllOrders();
	}
	
	@GetMapping(value = "/{order_id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> getOrderDetailsById(@PathVariable Integer order_id) {
		return orderService.getOrderDetailsById(order_id);
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> createOrder(@RequestBody OrderRequest order_req) {
		System.out.println(order_req);
		Order order = mapper.mapOrder(order_req);
		return orderService.createOrder(order);
	}
	
	@DeleteMapping("/{order_id}")
	public Map<String, Object> deleteOrder(@PathVariable Integer order_id) {
		return orderService.deleteOrder(order_id);
	}
	
	
}
