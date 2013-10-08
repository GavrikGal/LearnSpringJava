package com.apress.prospring3.ch10.service.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apress.prospring3.ch10.domain.Contact;
import com.apress.prospring3.ch10.service.ContactService;

@Service("jpaContactService")
@Repository
@Transactional
public class ContactServiceImpl implements ContactService {

	private Log log = LogFactory.getLog(ContactServiceImpl.class);

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Contact> findAll() {
		List<Contact> contacts = em.createNamedQuery("Contact.findAll",
				Contact.class).getResultList();
		return contacts;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Contact> findAllWithDetail() {
		List<Contact> contacts = em.createNamedQuery(
				"Contact.findAllWihDetail", Contact.class).getResultList();
		return contacts;
	}

	@Override
	public Contact findById(Long id) {
		// TODO Автоматически созданная заглушка метода
		return null;
	}

	@Override
	public Contact save(Contact contact) {
		// TODO Автоматически созданная заглушка метода
		return null;
	}

	@Override
	public void delete(Contact contact) {
		// TODO Автоматически созданная заглушка метода

	}

}
