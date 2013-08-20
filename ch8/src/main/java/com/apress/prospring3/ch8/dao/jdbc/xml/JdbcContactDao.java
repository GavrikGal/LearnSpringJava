package com.apress.prospring3.ch8.dao.jdbc.xml;

import javax.sql.DataSource;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;

import com.apress.prospring3.ch8.dao.ContactDao;

public class JdbcContactDao implements ContactDao, InitializingBean {

	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public void afterPropertiesSet() throws Exception {
		if (dataSource == null) {
			throw new BeanCreationException("Must set dataSource on ContactDao");
		}
	}
}
