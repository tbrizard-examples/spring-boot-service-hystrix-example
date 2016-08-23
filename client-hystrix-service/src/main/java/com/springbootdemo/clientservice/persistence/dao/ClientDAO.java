package com.springbootdemo.clientservice.persistence.dao;

import com.springbootdemo.clientservice.business.model.Client;

public interface ClientDAO {

	
	public Client readClientById(String clientId);
}
