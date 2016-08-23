package com.springbootdemo.clientservice.persistence.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.springbootdemo.clientservice.business.model.Client;


/**
 *
 */
@Component
public class ClientDAOImpl implements ClientDAO {

	private static final Logger logger = LoggerFactory.getLogger(ClientDAOImpl.class);
	
	@Override
	@HystrixCommand(fallbackMethod = "getStubbedClient")
	public Client readClientById(String clientId) {
		
	logger.debug(String.format("finding client with ID: %s", clientId));
		
		// incredibly fake code here just to illustrate that this class gets called
		// and show how circuit breakers work when they get opened.
		if ("1".equalsIgnoreCase(clientId)) {
			return new Client("1", "Wiggins", "Bob");
		} else {
			// this should open the circuit and the fallback method should be called
			throw new IllegalStateException("open circuit"); 
		}
	}

	public Client getStubbedClient(String clientId) {
		return new Client("999999", "Default", "Client");
	}

}
