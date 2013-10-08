package com.apress.prospring3.ch10.service;

import java.util.List;

import com.apress.prospring3.ch10.domain.Contact;

public interface ContactService {
	
	public List<Contact> findAll();
	
	public List<Contact> findAllWithDetail();
	
	public Contact findById(Long id);
	
	public Contact save(Contact contact);
	
	public void delete(Contact contact);

}
