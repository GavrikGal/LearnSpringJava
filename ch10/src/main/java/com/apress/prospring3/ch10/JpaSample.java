package com.apress.prospring3.ch10;

import java.util.List;

import org.springframework.context.support.GenericXmlApplicationContext;

import com.apress.prospring3.ch10.domain.Contact;
import com.apress.prospring3.ch10.domain.ContactTelDetail;
import com.apress.prospring3.ch10.domain.Hobby;
import com.apress.prospring3.ch10.service.ContactService;

public class JpaSample {

	public static void main(String[] args) {

		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:app-context.xml");
		ctx.refresh();

		ContactService contactService = ctx.getBean("jpaContactService",
				ContactService.class);
		
		List<Contact> contacts = contactService.findAll();
		//listContacts(contacts);
		listContactWithDetail(contacts);
		
		
	}
	
	private static void listContacts(List<Contact> contacts) {
		System.out.println("");
		System.out.println("____________________Listing contacts without details____________________");
		for(Contact contact: contacts) {
			System.out.println(contact);
			System.out.println();
		}
		System.out.println("________________________________________________________________________");
	}
	
	private static void listContactWithDetail(List<Contact> contacts) {
		System.out.println("");
		System.out.println("_____________________Listing contacts with details______________________");
		for (Contact contact: contacts) {
			System.out.println(contact);
			if(contact.getContactTelDetails() != null) {
				for (ContactTelDetail contactTelDetail: contact.getContactTelDetails()) {
					System.out.println(contactTelDetail);
				}
			}
			if(contact.getHobbies() != null) {
				for (Hobby hobby: contact.getHobbies()) {
					System.out.println(hobby);
				}
			}
			System.out.println();
		}
		System.out.println("________________________________________________________________________");
	}

}



















