package com.geeta;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class RabbitMQTest {

  private final static String QUEUE_1 = "Onsite";
  private final static String QUEUE_2 = "Offshore";


  public static void main(String[] argv) throws Exception {
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("localhost");
    Connection connection = factory.newConnection();
    Channel channel = connection.createChannel();

    channel.queueDeclare(QUEUE_1, false, false, false, null);
    channel.queueDeclare(QUEUE_2, false, false, false, null);
    String message = "Hello World!";
    channel.basicPublish("", QUEUE_1, null, message.getBytes("UTF-8"));
    channel.basicPublish("", QUEUE_2, null, message.getBytes("UTF-8"));

    System.out.println(" [x] Sent '" + message + "'");

    channel.close();
    connection.close();
  }
}