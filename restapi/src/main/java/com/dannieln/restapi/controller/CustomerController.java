package com.dannieln.restapi.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dannieln.restapi.service.CustomerService;

/**
 * This class defines the endpoints related to Customer. It provides 3 endpoints:
 * 
 * <ul>
 * 	<li>All Orders of a Customer</li>
 * 	<li>Total Items ordered by Customer and Category</li>
 * 	<li>Total Amount ordered by Customer and Category</li>
 * </ul>
 */
@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired CustomerService customerService;
	
	@GetMapping(value = "/{cust_id}/orders", produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> getOrders(@PathVariable Integer cust_id) {
		return customerService.getOrders(cust_id);
	}
	
	@GetMapping(value = "/{cust_id}/items/{cat_id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> getTotalItems(@PathVariable Integer cust_id, @PathVariable Integer cat_id) {
		return customerService.getTotalItems(cust_id, cat_id);
	}
	
	@GetMapping(value = "/{cust_id}/amount/{cat_id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> getTotalAmount(@PathVariable Integer cust_id, @PathVariable Integer cat_id) {
		return customerService.getTotalAmount(cust_id, cat_id);
	}
	
}
