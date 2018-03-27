package com.geeta.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.geeta.model.Employee;

@Service
public class RabbitMQReceiver {
	
	@Autowired
	private AmqpTemplate amqpTemplate;
	
	@Value("${geeta.rabbitmq.exchange}")
	private String exchange;
	
	@Value("${geeta.rabbitmq.routingkey1}")
	private String routingkey1;	
	
	@Value("${geeta.rabbitmq.routingkey2}")
	private String routingkey2;	
	
	String kafkaTopic = "geeta_topic";
	
	public void receive(Employee company) {
		amqpTemplate.convertSendAndReceive(company);
		System.out.println("Send msg = " + company);
	    
	}
}