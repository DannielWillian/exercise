package com.dannieln.restapi.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * This class is responsible for sending messages to RabbitMQ. It gets the
 * parameter values from the application.properties file.
 */
@Component
public class MessageSender {

	@Value("${com.dannieln.topic.exchange.name}")
	private String topicExchangeName;
	
	@Value("${com.dannieln.routing.key}")
	private String routingKey;
	
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(String message) {
        rabbitTemplate.convertAndSend(topicExchangeName, routingKey, message);
    }
}
