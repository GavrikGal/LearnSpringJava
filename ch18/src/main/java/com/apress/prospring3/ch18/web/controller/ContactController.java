package com.apress.prospring3.ch18.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.apress.prospring3.ch18.service.ContactService;
import com.apress.prospring3.ch18.web.view.ContactListBean;

@Component("contactController")
public class ContactController {

	private static final Logger	logger	= LoggerFactory.getLogger(ContactController.class);

	@Autowired
	private ContactService		contactService;

	public ContactListBean newContactListBean() {
		ContactListBean contactListBean = new ContactListBean();
		contactListBean.setContacts(contactService.findAll());
		return contactListBean;
	}

}
