package com.apress.prospring3.ch8.dao.jdbc.annotation;

import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import com.apress.prospring3.ch8.dao.ContactSfDao;

@Repository("contactSfDao")
public class JdbcContactSfDao implements ContactSfDao {

	private DataSource dataSource;

	private SfFirstNameById sfFirstNameById;

	@Override
	public String getFirstNameById(Long id) {
		List<String> result = sfFirstNameById.execute(id);

		return result.get(0);
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	@Resource(name = "dataSource")
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		sfFirstNameById = new SfFirstNameById(dataSource);
	}

}
