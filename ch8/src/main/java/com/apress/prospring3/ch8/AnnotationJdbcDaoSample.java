package com.apress.prospring3.ch8;

import java.sql.Date;
import java.util.ArrayList;
import java.util.GregorianCalendar;
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
		System.out.println("-----------------findByFirstName--------- ");
		contacts = contactDao.findByFirstName("Clarence");
		listContacts(contacts);
		System.out.println("--------------------update---------------");
		Contact contact;
		contact = new Contact();
		contact.setId(1l);
		contact.setFirstName("Clarence");
		contact.setLastName("Peter");
		contact.setBirthDate(new Date((new GregorianCalendar(1977, 10, 1))
				.getTime().getTime()));
		contactDao.update(contact);
		contacts = contactDao.findAll();
		listContacts(contacts);
		System.out.println("--------------------insert---------------");
		contact = new Contact();
		contact.setFirstName("Rod");
		contact.setLastName("Johnson");
		contact.setBirthDate(new Date((new GregorianCalendar(2001, 10, 1))
				.getTime().getTime()));
		contactDao.insert(contact);
		contacts = contactDao.findAll();
		listContacts(contacts);
		System.out.println("----------------insertWithDetail---------");
		contact = new Contact();
		contact.setFirstName("Michael");
		contact.setLastName("Jackson");
		contact.setBirthDate(new Date((new GregorianCalendar(1964, 10, 1))
				.getTime().getTime()));
		List<ContactTelDetail> contactTelDetails = new ArrayList<ContactTelDetail>();
		
		ContactTelDetail contactTelDetail = new ContactTelDetail();
		contactTelDetail.setTelType("Home");
		contactTelDetail.setTelNumber("11111111");
		contactTelDetails.add(contactTelDetail);
		contactTelDetail = new ContactTelDetail();
		contactTelDetail.setTelType("Mobile");
		contactTelDetail.setTelNumber("22222222");
		contactTelDetails.add(contactTelDetail);
		contact.setContactTelDetails(contactTelDetails);
		contactDao.insertWithDetail(contact);
		contacts = contactDao.findAllWithDetail();
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
