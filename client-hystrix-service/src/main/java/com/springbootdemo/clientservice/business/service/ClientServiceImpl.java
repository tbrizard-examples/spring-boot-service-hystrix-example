package com.springbootdemo.clientservice.business.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codahale.metrics.annotation.Timed;
import com.springbootdemo.clientservice.business.model.Client;
import com.springbootdemo.clientservice.persistence.dao.ClientDAO;

/**
 *
 */
@Service
public class ClientServiceImpl implements ClientService {

	private static final Logger logger = LoggerFactory.getLogger(ClientServiceImpl.class);

	@Autowired
	private ClientDAO clientDAO;
	
	public ClientServiceImpl() {
		super();
	}
	
	public ClientServiceImpl(ClientDAO clientDAO) {
		this.clientDAO = clientDAO;
	}
		
	@Timed
	public Client findClient(String clientId) {
		
		logger.debug(String.format("finding client with ID: %s", clientId));
		
		return clientDAO.readClientById(clientId);
	}
}
