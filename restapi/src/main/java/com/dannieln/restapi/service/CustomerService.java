package com.dannieln.restapi.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dannieln.restapi.model.Order;
import com.dannieln.restapi.repository.OrderRepository;

/**
 * This is the Service class for the Customer model. It's composed by methods 
 * responsible to intermediate the Controller and the Repository classes.
 */
@Service
public class CustomerService {
	
	@Autowired OrderRepository repository;
	
	/**
	 * Returns all the Orders of a single Customer.
	 * 
	 * @param cust_id
	 * @return
	 */
	public Map<String, Object> getOrders(Integer cust_id) {
		Map<String, Object> response = this.buildBaseResponse(cust_id);
		List<Order> orders = repository.findByCustomerId(cust_id);
		response.put("orders", orders);
		return response;
	}
	
	/**
	 * Returns the total number of Items ordered by a Customer for a 
	 * specific Item Category.
	 * 
	 * @param cust_id
	 * @param cat_id
	 * @return
	 */
	public Map<String, Object> getTotalItems(Integer cust_id, Integer cat_id) {
		Map<String, Object> response = this.buildBaseResponse(cust_id, cat_id);
		Long total = repository.getTotalItems(cust_id, cat_id);
		response.put("total_items", total);
		return response;
	}
	
	/**
	 * Returns the total amount value of the Items ordered by a Customer for a 
	 * specific Item Category.
	 * 
	 * @param cust_id
	 * @param cat_id
	 * @return
	 */
	public Map<String, Object> getTotalAmount(Integer cust_id, Integer cat_id) {
		Map<String, Object> response = this.buildBaseResponse(cust_id, cat_id);
		Double total = repository.getTotalAmount(cust_id, cat_id);
		response.put("total_amount", total);
		return response;
	}
	
	/**
	 * Creates a base response to return to the Controller
	 * 
	 * @param cust_id
	 * @return
	 */
	private Map<String, Object> buildBaseResponse(Integer cust_id) {
		Map<String, Object> response = new HashMap<>();
		response.put("customer_id", cust_id);
		return response;
	}
	
	/**
	 * Creates a base response to return to the Controller
	 * 
	 * @param cust_id
	 * @return
	 */
	private Map<String, Object> buildBaseResponse(Integer cust_id, Integer cat_id) {
		Map<String, Object> response = this.buildBaseResponse(cust_id);
		response.put("category_id", cat_id);
		return response;
	}

}
