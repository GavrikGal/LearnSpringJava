package com.apress.prospring3.ch9;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.context.support.GenericXmlApplicationContext;

import com.apress.prospring3.ch9.dao.ContactDao;
import com.apress.prospring3.ch9.domain.Contact;
import com.apress.prospring3.ch9.domain.ContactTelDetail;
import com.apress.prospring3.ch9.domain.Hobby;

public class SpringHibernateSample {

	public static void main(String[] args) {

		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:app-context.xml");
		ctx.refresh();

		ContactDao contactDao = ctx.getBean("contactDao", ContactDao.class);
		List<Contact> contacts = contactDao.findAllWithDetail();
		listContactsWithDetail(contacts);
		System.out.println("---------------findById-------------");
		Contact contact;
		contact = contactDao.findById(1l);
		System.out.println("");
		System.out.println("Contact with id: 1:" + contact);
		System.out.println("");
		System.out.println("-----------------Save---------------");
		contact = new Contact();
		contact.setFirstName("Michael");
		contact.setLastName("Jackson");
		contact.setBurthDate(new Date());
		ContactTelDetail contactTelDetail = new ContactTelDetail("Home",
				"111111111");
		contact.addContactTelDetail(contactTelDetail);
		contactTelDetail = new ContactTelDetail("Mobile", "2222222222");
		contact.addContactTelDetail(contactTelDetail);
		contactDao.save(contact);
		contacts = contactDao.findAllWithDetail();
		listContactsWithDetail(contacts);
		System.out.println("---------------Update---------------");
		contact = contactDao.findById(1l);
		System.out.println("Contact with id: 1:" + contact);
		contact.setFirstName("Kim Fung");
		System.out.println("Contact with id: " + contact.getId()
				+ " Renamed to: " + contact.getFirstName());
		Set<ContactTelDetail> contactTels = contact.getContactTelDetails();
		ContactTelDetail toDeleteContactTel = null;
		for (ContactTelDetail contactTel : contactTels) {
			if (contactTel.getTelType().equals("Home")) {
				toDeleteContactTel = contactTel;
			}
		}
		contact.removeContacttelDetail(toDeleteContactTel);
		contactDao.save(contact);
		contacts = contactDao.findAllWithDetail();
		listContactsWithDetail(contacts);
		System.out.println("-------------delete----------------");
		contact = contactDao.findById(1l);
		contactDao.delete(contact);
		contacts = contactDao.findAllWithDetail();
		listContactsWithDetail(contacts);
		ctx.close();
	}

	private static void listContacts(List<Contact> contacts) {
		System.out.println("");
		System.out.println("Listing contacts without details:");
		for (Contact contact : contacts) {
			System.out.println(contact);
			System.out.println();
		}
	}

	private static void listContactsWithDetail(List<Contact> contacts) {
		System.out.println("");
		System.out.println("Listing contacts with details:");
		for (Contact contact : contacts) {
			System.out.println(contact);
			if (contact.getContactTelDetails() != null) {
				for (ContactTelDetail contactTelDetail : contact
						.getContactTelDetails()) {
					System.out.println(contactTelDetail);
				}
			}
			if (contact.getHobbies() != null) {
				for (Hobby hobby : contact.getHobbies()) {
					System.out.println(hobby);
				}
			}
			System.out.println();
		}
	}

}
