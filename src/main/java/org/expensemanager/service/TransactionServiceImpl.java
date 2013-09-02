package org.expensemanager.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.sql.DataSource;

import oracle.sql.DATE;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.PlatformTransactionManager;

public class TransactionServiceImpl implements TransactionService {

	public static final String AMOUNT = "amount";

	public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

	private Logger log = Logger.getLogger(getClass());

	private PlatformTransactionManager transactionManager;
	private JdbcTemplate jdbcTemplate;

	private static final String TOTAL_EXPENSE = "select sum(transaction_amount) as EXPENSE from transaction_log where " +
			"category_id=(select category_id from category where category_name='Expense') and user_id=?";
	private static final String TOTAL_INCOME = "select sum(transaction_amount) as INCOME from transaction_log where " +
			"category_id=(select category_id from category where category_name='Income') and user_id=?";
	private static final String SAVE_TRANSACTION = "insert into transaction_log (transaction_date, transaction_amount, " +
			"description, category_id, user_id, account_id) " +
			"values (?, ?, ?, ?, ?, ?)";
	private static final String GET_TODAY_TRANSACTION = "select sum(transaction_amount) as amount from transaction_log where " +
			"transaction_date = to_date(?, 'YYYY-MM-DD') and category_id = ?  and user_id = ?";
	private static final String GET_THIS_WEEK_TRANSACTION = "select sum(transaction_amount) as amount from transaction_log where " +
			"transaction_date <= to_date(?, 'YYYY-MM-DD') and " +		//Today's date
			"transaction_date >= to_date(?, 'YYYY-MM-DD') and " +		//Monday's date
			"category_id = ? and " +
			"user_id = ?";
	
	private static final int ACCOUNT_ID = 1;

	public TransactionServiceImpl(PlatformTransactionManager transactionManager, DataSource dataSource) {
		this.transactionManager = transactionManager;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public long getTotalExpense(long userId) {
		return (Long) jdbcTemplate.query(TOTAL_EXPENSE, new RowMapper() {
			@Override
			public Object mapRow(ResultSet rs, int i) throws SQLException {
				if (rs == null) {
					return 0;
				} else{
					return rs.getLong("EXPENSE");
				}
			}
		} ,userId).get(0);
	}

	@Override
	public long getTotalIncome(long userId) {
		return (Long) jdbcTemplate.query(TOTAL_INCOME, new RowMapper() {
			@Override
			public Object mapRow(ResultSet rs, int i) throws SQLException {
				if (rs == null) {
					return 0;
				} else{
					return rs.getLong("Income");
				}
			}
		} ,userId).get(0);
	}

	@Override
	public boolean saveTransaction(int category, String amount, String date,
			String description, long userId) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		int rows = 0;
		try {
			rows = jdbcTemplate.update(SAVE_TRANSACTION, new java.sql.Date(dateFormat.parse(date).getTime()),
					amount, description,
					category, userId, ACCOUNT_ID);
		} catch (ParseException e) {
			log.error("Error parsing date " + date, e);
		}

		if (rows == 0) {
			return false;
		} else {
			return true;
		}
	}
	
	@Override
	public long getTodayTransaction(long userId, int category_id) {
		return jdbcTemplate.query(GET_TODAY_TRANSACTION, new RowMapper<Long>() {
			@Override
			public Long mapRow(ResultSet rs, int i) throws SQLException {
				if (rs == null) {
					return 0L;
				} else{
					return rs.getLong(AMOUNT);
				}
			}
		} , new java.sql.Date(new Date().getTime()).toString(), 
		category_id, 
		userId).get(0);
	}
	
	@Override
	public long getThisWeekTransaction(long userId, int category_id) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		Date today = new Date();
		if (c.getTime().getTime() == today.getTime()) {
			return getTodayTransaction(userId, category_id);
		} else {
			return jdbcTemplate.query(GET_THIS_WEEK_TRANSACTION, new RowMapper<Long>() {
				@Override
				public Long mapRow(ResultSet rs, int i) throws SQLException {
					if (rs == null) {
						return 0L;
					} else{
						return rs.getLong(AMOUNT);
					}
				}
			} , new java.sql.Date(new Date().getTime()).toString(), 
			new java.sql.Date(c.getTime().getTime()).toString(), 
			category_id, 
			userId).get(0);
		}
	}
	
	@Override
	public long getThisMonthTransaction(long userId, int category_id) {
		return 0;
	}
	
	@Override
	public long getThisYearTransaction(long userId, int category_id) {
		return 0;
	}
}
