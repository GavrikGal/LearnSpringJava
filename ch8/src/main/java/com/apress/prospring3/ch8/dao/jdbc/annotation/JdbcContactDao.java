package com.apress.prospring3.ch8.dao.jdbc.annotation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;


import com.apress.prospring3.ch8.dao.ContactDao;
import com.apress.prospring3.ch8.domain.Contact;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

@Repository("contactDao")
public class JdbcContactDao implements ContactDao {
	
	private Log log = LogFactory.getLog(JdbcContactDao.class);
	private DataSource dataSource;
	private SelectAllContacts selectAllContacts;
	private SelectContactByFirstName selectContactByFirstName;
	
	
	@Resource(name="dataSource")
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;		
		selectAllContacts = new SelectAllContacts(dataSource);
		selectContactByFirstName = new SelectContactByFirstName(dataSource);
	}
	
	public DataSource getDataSource() {
		return dataSource;
	}

	@Override
	public String findFirstNameById(Long id) {
		// TODO Автоматически созданная заглушка метода
		return null;
	}

	@Override
	public String findLastNameById(Long id) {
		// TODO Автоматически созданная заглушка метода
		return null;
	}

	@Override
	public List<Contact> findAllWithDetail() {
		// TODO Автоматически созданная заглушка метода
		return null;
	}

	@Override
	public List<Contact> findAll() {
		return selectAllContacts.execute();
	}

	@Override
	public List<Contact> findByFirstName(String firstName) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("first_name", firstName);
		return selectContactByFirstName.executeByNamedParam(paramMap);
		
	}

	@Override
	public void insert(Contact contact) {
		// TODO Автоматически созданная заглушка метода

	}

	@Override
	public void update(Contact contact) {
		// TODO Автоматически созданная заглушка метода

	}

	@Override
	public void delete(Long contactId) {
		// TODO Автоматически созданная заглушка метода

	}

}
