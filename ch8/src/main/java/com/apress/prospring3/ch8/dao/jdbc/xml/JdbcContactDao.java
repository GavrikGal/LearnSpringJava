package com.apress.prospring3.ch8.dao.jdbc.xml;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.apress.prospring3.ch8.dao.ContactDao;
import com.apress.prospring3.ch8.domain.Contact;

public class JdbcContactDao implements ContactDao, InitializingBean {
	
	private JdbcTemplate jdbcTemplate;

	private DataSource dataSource;
	
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	public void afterPropertiesSet() throws Exception {
		if (dataSource == null) {
			throw new BeanCreationException("Must set dataSource on ContactDao");
		}
	}

	@Override
	public String findFirstNameById(Long id) {
		String firstName = jdbcTemplate.queryForObject(
				"select first_name from contact where id = ?",
				new Object[] { id }, String.class);
		return firstName;
	}

	@Override
	public List<Contact> findAll() {
		String sql = "select id, first_name, last_name, birth_date from contact";
		
		return jdbcTemplate.query(sql, new ContactMapper());
	}
	
	private static final class ContactMapper implements RowMapper<Contact> {
		public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
			Contact contact = new Contact();
			contact.setId(rs.getLong("id"));
			contact.setFirstName(rs.getString("first_name"));
			contact.setLastName(rs.getString("last_name"));
			contact.setBirthDate(rs.getDate("birth_date"));
			return contact;
		}
	}

	@Override
	public List<Contact> findByFirstName(String firstName) {
		// TODO Автоматически созданная заглушка метода
		return null;
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

	@Override
	public String findLastNameById(Long id) {
		String sql = "select last_name from contact where id = :contactId";
		SqlParameterSource namedParameters = new MapSqlParameterSource("contactId",id);
		
		return namedParameterJdbcTemplate.queryForObject(sql, namedParameters, String.class);
	}
}
