package com.apress.prospring3.ch8;

import java.util.List;

import org.springframework.context.support.GenericXmlApplicationContext;

import com.apress.prospring3.ch8.dao.ContactDao;
import com.apress.prospring3.ch8.domain.Contact;
import com.apress.prospring3.ch8.domain.ContactTelDetail;

public class AnnotationJdbcDaoSample {

	public static void main(String[] args) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:app-context-annotation.xml");
		ctx.refresh();

		ContactDao contactDao = ctx.getBean("contactDao", ContactDao.class);
		
		List<Contact> contacts = contactDao.findAll();
		listContacts(contacts);
		System.out.println("findByFirstName: ");
		contacts = contactDao.findByFirstName("Clarence");
		listContacts(contacts);
		ctx.close();

	}

	private static void listContacts(List<Contact> contacts) {
		for (Contact contact : contacts) {
			System.out.println(contact);
			if (contact.getContactTelDetails() != null) {
				for (ContactTelDetail contactTelDetail : contact
						.getContactTelDetails()) {
					System.out.println("---" + contactTelDetail);
				}
			}
			System.out.println();
		}
	}
}
