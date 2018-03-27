package com.geeta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.geeta.model.Employee;
import com.geeta.service.RabbitMQReceiver;
import com.geeta.service.RabbitMQSender;

@RestController
@RequestMapping(value = "/geeta-rabbitmq/")
public class RabbitMQWebController {

	@Autowired
	RabbitMQSender rabbitMQSender;
	
	@Autowired
	RabbitMQReceiver rabbitMQReceiver;

	@GetMapping(value = "/producer")
	public String producer(@RequestParam("empName") String empName,@RequestParam("empId") String empId) {
	
	Employee emp=new Employee();
	emp.setEmpId(empId);
	emp.setEmpName(empName);
	rabbitMQSender.send(emp);

		return "Message sent to the RabbitMQ Geeta Successfully";
	}
	
	@GetMapping(value = "/consumer")
	public String consumer(@RequestParam("empName") String empName,@RequestParam("empId") String empId) {
	
	Employee emp=new Employee();
	emp.setEmpId(empId);
	emp.setEmpName(empName);
		rabbitMQReceiver.receive(emp);

		return "Message sent to the RabbitMQ Geeta Successfully";
	}

}

