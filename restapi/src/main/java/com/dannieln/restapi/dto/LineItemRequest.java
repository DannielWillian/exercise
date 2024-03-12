package com.dannieln.restapi.dto;

public class LineItemRequest {
	
	private Integer itemId;
	private Integer quantity;
	
	public Integer getItemId() {
		return itemId;
	}
	
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	
	public Integer getQuantity() {
		return quantity;
	}
	
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "LineItemRequest [itemId=" + itemId + ", quantity=" + quantity + "]";
	}

}
