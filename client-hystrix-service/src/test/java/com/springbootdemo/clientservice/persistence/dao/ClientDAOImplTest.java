package com.springbootdemo.clientservice.persistence.dao;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.springbootdemo.clientservice.business.model.Client;

/**
 * 
 */
public class ClientDAOImplTest {
	
	private ClientDAO dao;
		
	@Before
	public void setUp() {
	
		dao = new ClientDAOImpl();
	}
	
	@Test
	public void testFindClientWithValidID() {
		
		Client actual = dao.readClientById("1");
		
		assertNotNull(actual);
		assertThat(actual.getId(), is("1"));
		assertThat(actual.getLastName(), is("Wiggins"));
		assertThat(actual.getFirstName(), is("Bob"));
	}
	
	@Test(expected = IllegalStateException.class)
	public void testFindClientWithInvalidID() {
	
		dao.readClientById("5");
	}
	
	@Test(expected = IllegalStateException.class)
	public void testFindClientWithNullID() {
	
		dao.readClientById("5");
	}
}
