package com.dannieln.restapi.dto;

import java.util.List;

public class OrderRequest {
	
	private Integer customerId;
	private List<LineItemRequest> items;
	
	public Integer getCustomerId() {
		return customerId;
	}
	
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	
	public List<LineItemRequest> getItems() {
		return items;
	}
	
	public void setItems(List<LineItemRequest> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "OrderRequest [customerId=" + customerId + ", items=" + items + "]";
	}

}
