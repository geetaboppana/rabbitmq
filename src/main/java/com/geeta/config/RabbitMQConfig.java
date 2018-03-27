package com.geeta.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class RabbitMQConfig {

	@Value("${geeta.rabbitmq.queue1}")
	String queueName1;
	@Value("${geeta.rabbitmq.queue2}")
	String queueName2;


	@Value("${geeta.rabbitmq.exchange}")
	String exchange;

	@Value("${geeta.rabbitmq.routingkey1}")
	private String routingkey1;
	
	@Value("${geeta.rabbitmq.routingkey2}")
	private String routingkey2;

	@Bean
	@Primary
	Queue queue1() {
		return new Queue(queueName1, false);
	}
	
	@Bean
	Queue queue2() {
		return new Queue(queueName2, false);
	}

	@Bean
	DirectExchange exchange() {
		return new DirectExchange(exchange);
	}

	@Bean
	Binding binding(Queue queue, DirectExchange exchange) {
		
		return BindingBuilder.bind(queue).to(exchange).with(routingkey1);
	}

	@Bean
	public MessageConverter jsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(jsonMessageConverter());
		return rabbitTemplate;
	}
}
