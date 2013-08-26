package org.expensemanager.service;

import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;

public class SignUpImpl implements SignUpService {
	
private Logger log = Logger.getLogger(getClass());
	
	private PlatformTransactionManager transactionManager;
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	private static final String GET_COUNTRY_LIST = "SELECT NAME FROM COUNTRY";
	
	public SignUpImpl() {
	}
	
	public SignUpImpl(PlatformTransactionManager transactionManager,
			DataSource dataSource) {
		this.transactionManager = transactionManager;
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}



	@Override
	public List<String> listCountries() {
		return jdbcTemplate.queryForList(GET_COUNTRY_LIST, String.class);
	}
}
