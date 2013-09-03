package org.expensemanager.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.expensemanager.bean.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

public class UserServiceImpl implements UserService {
	
	private Logger log = Logger.getLogger(getClass());
	
	private PlatformTransactionManager transactionManager;
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	private static final String INSERT_USER_CREDENTIAL = "INSERT INTO USER_CREDENTIAL (EMAIL, PASSWORD, ROLE_ID) VALUES (?, ?, ?)";
	private static final String INSERT_USER_DETAILS = "INSERT INTO USER_DETAILS (USER_ID, FIRST_NAME, LAST_NAME, GENDER, COUNTRY_ID) VALUES (?, ?, ?, ?, ?)";
	private static final String GET_ROLE_ID = "SELECT ROLE_ID FROM ROLE WHERE ROLE=?";
	private static final String GET_COUNTRY_ID = "SELECT COUNTRY_ID FROM COUNTRY WHERE NAME=?";
	private static final String GET_USER_ID = "SELECT USER_ID FROM USER_CREDENTIAL WHERE EMAIL=?";
	private static final String GET_PASSWORD_BY_USER = "SELECT PASSWORD FROM USER_CREDENTIAL WHERE EMAIL=?";
	private static final String IS_USER_EXIST = "SELECT COUNT(*) FROM USER_CREDENTIAL WHERE EMAIL=?";
	private static final String GET_COUNTRY_LIST = "SELECT NAME FROM COUNTRY";
	private static final String GET_USER_BY_EMAIL = "select ud.user_id, ud.first_name, ud.last_name, ud.gender, c.currency_name from user_details ud, user_credential uc, currency c where ud.user_id = uc.user_id and c.country_id=c.country_id and uc.email = ?";
	private static final String GET_ALL_CATEGORIES = "select category_id from category where category_level > 0 and category_default = 'Y'";
	private static final String INSERT_USER_CATEGORY = "insert into user_category (user_id, category_id) values(?, ?)";
	private static final String GET_DEFAULT_ACCOUNT = "select account_id from account where account_default = 'Y'";
	private static final String INSERT_USER_ACCOUNT = "insert into user_account (user_id, account_id) values(?, ?)";
	
	public UserServiceImpl() {
		
	}
	
	public UserServiceImpl(PlatformTransactionManager transactionManager, DataSource dataSource) {
		this.transactionManager = transactionManager;
		this.dataSource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void create(User user) {
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		
		try {
			int roleId = jdbcTemplate.queryForInt(GET_ROLE_ID, user.getRole());
			int countryId = jdbcTemplate.queryForInt(GET_COUNTRY_ID, user.getCountry());
			jdbcTemplate.update(INSERT_USER_CREDENTIAL, user.getEmail(), user.getPassword(), roleId);
			final long userId = jdbcTemplate.queryForLong(GET_USER_ID, user.getEmail());
			jdbcTemplate.update(INSERT_USER_DETAILS, userId, user.getFirstName(), user.getLastName(), user.getGender(), countryId);
			
			final List<Integer> categoryIds = jdbcTemplate.queryForList(GET_ALL_CATEGORIES, Integer.class);
			jdbcTemplate.batchUpdate(INSERT_USER_CATEGORY, new BatchPreparedStatementSetter() {
				
				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					ps.setLong(1, userId);
					ps.setInt(2, categoryIds.get(i));
				}
				
				@Override
				public int getBatchSize() {
					return categoryIds.size();
				}
			});
			
			int accountId = jdbcTemplate.queryForInt(GET_DEFAULT_ACCOUNT);
			jdbcTemplate.update(INSERT_USER_ACCOUNT, userId, accountId);
			
			transactionManager.commit(status);
		} catch (DataAccessException e) {
			log.error("Error while Registering User.", e);
			transactionManager.rollback(status);
		}
	}
	
	@Override
	public long getUserId(String username) {
		return jdbcTemplate.queryForLong(GET_USER_ID, username);
	}
	
	public User getUser(String username){
		return jdbcTemplate.queryForObject(GET_USER_BY_EMAIL, new Object[] {username}, new RowMapper<User>() {
			@Override
			public User mapRow(ResultSet resultSet, int i) throws SQLException {
				User user = new User();
				user.setUserId(resultSet.getLong("user_id"));
				user.setFirstName(resultSet.getString("first_name"));
				user.setLastName(resultSet.getString("last_name"));
				user.setGender(resultSet.getString("gender"));
				user.setCurrency(resultSet.getString("currency_name"));
				return user;
			}
		});
	}
	
	@Override
	public boolean checkUserPassword(String user, String password) {
		String passowrdDB = jdbcTemplate.queryForObject(GET_PASSWORD_BY_USER, String.class, user);
		if (passowrdDB.equals(password)) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isUserExist(String user){
		int count = jdbcTemplate.queryForInt(IS_USER_EXIST, user);
		log.debug("User count: " + count);
		if (count == 0) {
			return false;
		} else {
			return true;
		}
	}
	
	public List<String> listCountries() {
		return jdbcTemplate.queryForList(GET_COUNTRY_LIST, String.class);
	}
}
