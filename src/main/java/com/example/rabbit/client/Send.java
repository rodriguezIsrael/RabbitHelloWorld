package com.example.rabbit.client;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;

public class Send {

	private final static String QUEUE_NAME = "hello";
	private final static String HOST = "localhost";
	private final static String MSG_TO_SEND = "Hola mundo";

	public static final void main(String args[]) {

		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost(HOST);
		factory.setPort(5672);

		Connection connection;
		try {
			connection = factory.newConnection();
			Channel channel = connection.createChannel();
			channel.queueDeclare(QUEUE_NAME, false, false, false, null);
			channel.basicPublish("", QUEUE_NAME, null, (MSG_TO_SEND + new Date().toString()).getBytes());

			System.out.println("Mensaje enviado " + MSG_TO_SEND);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
