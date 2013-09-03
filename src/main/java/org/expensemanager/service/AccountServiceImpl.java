package org.expensemanager.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.expensemanager.bean.Account;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

public class AccountServiceImpl implements AccountService {
	
	private Logger log = Logger.getLogger(getClass());

	private PlatformTransactionManager transactionManager;
	private JdbcTemplate jdbcTemplate;
	
	public static final String ACCOUNT_ID = "account_id";
	public static final String ACCOUNT_NAME = "account_name";
	public static final String ACCOUNT_DEFAULT = "account_default";
	
	private static final String ACCOUNT_BY_USER = "select account_id, account_name, account_default from account where account_id in (select account_id from user_account where user_id = ?)";
	private static final String ADD_ACCOUNT = "insert into account (account_name, account_default) values (?, ?)";
	private static final String ADD_ACCOUNT_TO_USER = "insert into user_account (user_id, account_id) values (?, ?)";
	private static final String GET_ACCOUNTID_BY_NAME = "select account_id from account where account_name=? and rownum < 2 order by account_id desc";
	private static final String DELETE_ACCOUNT_USER = "delete from user_account where account_id=? and user_id=?";
	private static final String DELETE_ACCOUNT = "delete from account where account_id=? and account_default='N'";
	
	public AccountServiceImpl() {
	}

	public AccountServiceImpl(PlatformTransactionManager transactionManager,
			DataSource dataSource) {
		this.transactionManager = transactionManager;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public List<Account> getAccountByUser(long userId){
		return jdbcTemplate.query(ACCOUNT_BY_USER, new RowMapper<Account>() {
			@Override
			public Account mapRow(ResultSet rs, int i) throws SQLException {
				return new Account(rs.getInt(ACCOUNT_ID), rs.getString(ACCOUNT_NAME), rs.getString(ACCOUNT_DEFAULT));
			}
		}, userId);
	}
	
	public boolean updateAccount(String account, int accountId, long userId){
		if (!deleteAccount(accountId, userId)) {
			return false;
		}
		return addAccount(account, userId);
	}

	public boolean deleteAccount(int accountId, long userId) {
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		try {
			jdbcTemplate.update(DELETE_ACCOUNT_USER, accountId, userId);
			jdbcTemplate.update(DELETE_ACCOUNT, accountId);
			transactionManager.commit(status);
			return true;
		} catch (DataAccessException e) {
			log.error("Error while Registering User.", e);
			transactionManager.rollback(status);
			return false;
		}
	}
	
	public boolean addAccount(String account, long userId){
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		try {
			jdbcTemplate.update(ADD_ACCOUNT, account, "N");
			jdbcTemplate.update(ADD_ACCOUNT_TO_USER, userId, jdbcTemplate.queryForInt(GET_ACCOUNTID_BY_NAME, account));
			transactionManager.commit(status);
			return true;
		} catch (DataAccessException e) {
			log.error("Error while Registering User.", e);
			transactionManager.rollback(status);
			return false;
		}
	}
}
