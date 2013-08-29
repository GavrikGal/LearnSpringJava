package com.apress.prospring3.ch8;

import java.util.List;

import org.springframework.context.support.GenericXmlApplicationContext;

import com.apress.prospring3.ch8.dao.ContactDao;
import com.apress.prospring3.ch8.domain.Contact;
import com.apress.prospring3.ch8.domain.ContactTelDetail;

public class JdbcContactDaosample {

	public static void main(String[] args) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:app-context-xml.xml");
		ctx.refresh();

		ContactDao contactDao = ctx.getBean("contactDao", ContactDao.class);

		System.out.println("First name for contact id 1 is: "
				+ contactDao.findFirstNameById(1l));
		System.out.println("Last name for contact id 1 is: "
				+ contactDao.findLastNameById(1l));
		
		List<Contact> contactsWithDetail = contactDao.findAllWithDetail();
		for (Contact contact: contactsWithDetail) {
			System.out.println(contact);
			if (contact.getContactTelDetails() != null) {
				for (ContactTelDetail contactTelDetail:
					contact.getContactTelDetails()) {
					System.out.println("---" + contactTelDetail);
				}
			}
			System.out.println();
		}
		
		ctx.close();
	}

}
