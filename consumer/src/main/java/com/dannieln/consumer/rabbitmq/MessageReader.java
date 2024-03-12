package com.dannieln.consumer.rabbitmq;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.dannieln.consumer.dto.OperationType;
import com.dannieln.consumer.dto.OrderMessageDto;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * This class processes the messages that come from RabbitMQ in the {@link #processMessage} method.
 * 
 */
@Component
@Scope("singleton")
public class MessageReader {
	
	/**
	 * Jackson ObjectMapper to deserialize the Message from RabbitMQ.
	 */
	@Autowired ObjectMapper mapper;
	
	/**
	 * In memory cache for the total amount spent for each Customer.
	 * 
	 */
	private Map<Integer, Float> totalAmount = new HashMap<Integer, Float>();

	/**
	 * Gets the messages that come in from RabbitMQ, deserializes the message into
	 * a Java Object, and aggregates the values in the {@link MessageReader#totalAmount} field,
	 * based on the OperationType (CREATE or DELETE Order).
	 * It then prints the current sum spent by the Customer so far.
	 * 
	 * @param message
	 */
    public void processMessage(String message) {
    	try {
        	OrderMessageDto messageDto = mapper.readValue(message, OrderMessageDto.class);
        	Integer customerId = messageDto.getCustomerId();
        	if (!totalAmount.containsKey(customerId)) {
        		totalAmount.put(customerId, 0f);
        	}
        	Float amount = totalAmount.get(customerId);
        	Float operationValue = messageDto.getAmount();
        	String consoleMessage;
        	if (messageDto.getOperationType().equals(OperationType.CREATE)) {
        		amount += operationValue;
        		totalAmount.put(customerId, amount);
        		consoleMessage = String.format("New Order for customerId %s. Total spent so far %s", customerId, amount);
        	} else {
        		amount -= operationValue;
        		totalAmount.put(customerId, amount);
        		consoleMessage = String.format("Cancelled Order for customerId %s. Total spent so far %s", customerId, amount);
        	}
        	
            System.out.println(String.format(">>>>>>> Order operation: %s", consoleMessage));
    		
    	} catch (Exception e) {
    		System.out.println(String.format(">>>>>>> Error serializing message: %s / error: %s", message, e.getMessage()));
    	}
    }

}