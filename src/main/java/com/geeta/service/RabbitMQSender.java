package com.geeta.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.geeta.model.Employee;

@Service
public class RabbitMQSender {
	
	@Autowired
	private AmqpTemplate amqpTemplate;
	
	@Value("${geeta.rabbitmq.exchange}")
	private String exchange;
	
	@Value("${geeta.rabbitmq.routingkey1}")
	private String routingkey1;	
	
	@Value("${geeta.rabbitmq.routingkey2}")
	private String routingkey2;	
	
	String kafkaTopic = "geeta_topic";
	
	public void send(Employee company) {
		if(company.getEmpId().equals("emp001")) {
			amqpTemplate.convertAndSend(exchange, routingkey1, company);
		} else {
			amqpTemplate.convertAndSend(exchange, routingkey2, company);
		}
		
        //amqpTemplate.send(routingkey2,company);
        amqpTemplate.convertAndSend(routingkey2, company);
		System.out.println("Send msg = " + company);
	    
	}
}