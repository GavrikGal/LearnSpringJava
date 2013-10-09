/**
 * Created on Dec 21, 2011
 */
package com.apress.prospring3.ch17.service;

import java.util.List;

import com.apress.prospring3.ch17.domain.Contact;

/**
 * @author Clarence
 *
 */
public interface ContactService {

	public List<Contact> findAll();
	
	public Contact findById(Long id);
	
	public Contact save(Contact contact);	
	
}
