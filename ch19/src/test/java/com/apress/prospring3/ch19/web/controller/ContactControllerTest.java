package com.apress.prospring3.ch19.web.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.apress.prospring3.ch19.domain.Contact;
import com.apress.prospring3.ch19.service.ContactService;

public class ContactControllerTest extends AbstractControllerTest {
	
	private final List<Contact> contacts = new ArrayList<Contact>();
	private ContactService contactService;
	
	@Before
	public void initContacts() {
		Contact contact = new Contact();
		contact.setId(1l);
		contact.setFirstName("Clara");
		contact.setLastName("Ho");
		contacts.add(contact);
	}
	
	@Test
	public void testList() throws Exception {
		contactService = mock(ContactService.class);
		when (contactService.findAll()).thenReturn(contacts);
		
	}
}
