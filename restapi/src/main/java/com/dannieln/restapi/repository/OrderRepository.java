package com.dannieln.restapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.dannieln.restapi.model.Order;

/**
 * Repository class for database operations of the Order class.
 */
public interface OrderRepository extends CrudRepository<Order, Integer> {
	
	/**
	 * This method list all Orders of one Customer by customer id.
	 * 
	 * @param customerId
	 * @return
	 */
	List<Order> findByCustomerId(Integer customerId);
	
	/**
	 * This method finds all Orders of a Customer and a specific Item Category,
	 * and aggregates the quantity of those ordered Items.
	 * 
	 * @param customerId
	 * @param categoryId
	 * @return
	 */
	@Query("select sum(li.quantity) from Order o "
			+ "left join o.items li "
			+ "left join li.item i "
			+ "left join i.category c "
			+ "where o.customer.id = ?1 "
			+ "and c.id = ?2")
	Long getTotalItems(Integer customerId, Integer categoryId);
	
	/**
	 * This method finds all Orders of a Customer and a specific Item Category,
	 * and aggregates the total amount of those ordered Items.
	 * 
	 * @param customerId
	 * @param categoryId
	 * @return
	 */
	@Query("select sum(li.amount) from Order o "
			+ "left join o.items li "
			+ "left join li.item i "
			+ "left join i.category c "
			+ "where o.customer.id = ?1 "
			+ "and c.id = ?2")
	Double getTotalAmount(Integer customerId, Integer categoryId);
	
}
