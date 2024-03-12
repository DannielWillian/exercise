package com.dannieln.consumer;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.dannieln.consumer.rabbitmq.MessageReader;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * This is the entrypoint of the Consumer service. It loads and Spring Context, 
 * configures the connection to RabbitMQ and registers the listener class.
 * 
 */
@SpringBootApplication
public class ConsumerApplication {
	
	@Value("${com.dannieln.topic.exchange.name}")
	private String topicExchangeName;
	
	@Value("${com.dannieln.queue.name}")
	private String queueName;
	
	@Value("${com.dannieln.routing.key}")
	private String routingKey;

    @Bean
    Queue queue() {
        return new Queue(queueName, false);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(topicExchangeName);
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(routingKey);
    }
    
    @Bean
    ObjectMapper objectMapper() {
    	return new ObjectMapper();
    }

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(queueName);
        container.setMessageListener(listenerAdapter);
        return container;
    }

    /**
     * This method registers the Listener for the RabbitMQ messages.
     * 
     * @param {@link MessageReader} reader
     * @return
     */
    @Bean
    MessageListenerAdapter listenerAdapter(MessageReader reader) {
        return new MessageListenerAdapter(reader, "processMessage");
    }

    /**
     * Loads the Spring Context and boots up the web server
     * 
     * @param args
     */
	public static void main(String[] args) {
		SpringApplication.run(ConsumerApplication.class, args);
	}

}
