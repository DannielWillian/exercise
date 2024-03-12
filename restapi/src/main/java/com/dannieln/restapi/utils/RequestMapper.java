package com.dannieln.restapi.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.dannieln.restapi.dto.LineItemRequest;
import com.dannieln.restapi.dto.OrderRequest;
import com.dannieln.restapi.model.Customer;
import com.dannieln.restapi.model.Item;
import com.dannieln.restapi.model.LineItem;
import com.dannieln.restapi.model.Order;
import com.dannieln.restapi.repository.ItemRepository;

@Component
public class RequestMapper {
	
	@Autowired ItemRepository itemRepository;
	
	public Order mapOrder(OrderRequest orderRequest) {
		Order order = new Order();
		
		Customer customer = new Customer();
		customer.setId(orderRequest.getCustomerId());
		order.setCustomer(customer);
		
		if (!CollectionUtils.isEmpty(orderRequest.getItems())) {
			List<LineItem> items = new ArrayList<>();
			for (LineItemRequest lineItemReq : orderRequest.getItems()) {
				Integer itemId = lineItemReq.getItemId();
				Item item = itemRepository.findById(itemId).get();
				
				Float value = item.getPrice();
				Integer qtty = lineItemReq.getQuantity();
				
				LineItem lineItem = new LineItem();
				lineItem.setItem(item);
				lineItem.setQuantity(qtty);
				lineItem.setAmount(qtty * value);
				
				items.add(lineItem);
			}
			order.setItems(items);
		}
		
		return order;
	}

}
