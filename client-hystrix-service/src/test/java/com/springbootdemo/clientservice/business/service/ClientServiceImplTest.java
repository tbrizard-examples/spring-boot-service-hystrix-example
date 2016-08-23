package com.springbootdemo.clientservice.business.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import com.springbootdemo.clientservice.business.model.Client;
import com.springbootdemo.clientservice.persistence.dao.ClientDAO;

/**
 * 
 */
public class ClientServiceImplTest {
	
	private ClientService service;
	private ClientDAO mockDAO;
		
	@Before
	public void setUp() {
		mockDAO = EasyMock.createMock(ClientDAO.class);
		service = new ClientServiceImpl(mockDAO);
	}
	
	@Test
	public void testFindClientWithValidID() {
		
		Client client = new Client("1", "Wiggins", "Bob");
		EasyMock.expect(mockDAO.readClientById("1")).andReturn(client);
		EasyMock.replay(mockDAO);
		
		Client actual = service.findClient("1");
		
		EasyMock.verify(mockDAO);
		
		assertNotNull(actual);
		assertThat(actual.getId(), is("1"));
		assertThat(actual.getLastName(), is("Wiggins"));
		assertThat(actual.getFirstName(), is("Bob"));
	}
	
	@Test
	public void testFindClientWithInvalidID() {
	
		EasyMock.expect(mockDAO.readClientById("5")).andReturn(null);
		EasyMock.replay(mockDAO);
		
		Client actual = service.findClient("5");
		
		EasyMock.verify(mockDAO);
		
		assertNull(actual);
	}
	
	@Test
	public void testFindClientWithNullID() {
	
		EasyMock.expect(mockDAO.readClientById(null)).andReturn(null);
		EasyMock.replay(mockDAO);
		
		Client actual = service.findClient(null);
		
		EasyMock.verify(mockDAO);
		
		assertNull(actual);
	}
}
