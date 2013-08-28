package org.expensemanager.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.sql.DataSource;

import oracle.sql.DATE;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.PlatformTransactionManager;

public class TransactionServiceImpl implements TransactionService {
	
public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

private Logger log = Logger.getLogger(getClass());
	
	private PlatformTransactionManager transactionManager;
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	private static final String TOTAL_EXPENSE = "select sum(transaction_amount) as EXPENSE from transaction_log where " +
			"category_id=(select category_id from category where category_name='Expense') and user_id=?";
	private static final String TOTAL_INCOME = "select sum(transaction_amount) as INCOME from transaction_log where " +
			"category_id=(select category_id from category where category_name='Income') and user_id=?";
	private static final String GET_CATEGORY_ID = "select category_id from category where category_name=? ";
	private static final String SAVE_TRANSACTION = "insert into transaction_log (transaction_date, transaction_amount, " +
			"description, category_id, user_id, account_id) " +
			"values (?, ?, ?, ?, ?, ?)";
	private static final int ACCOUNT_ID = 1;

	public TransactionServiceImpl(PlatformTransactionManager transactionManager, DataSource dataSource) {
		this.transactionManager = transactionManager;
		this.dataSource = dataSource;
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
					return rs.getLong("INCOME");
				}
			}
		} ,userId).get(0);
	}
	
	@Override
		public boolean saveTransaction(String category, String amount, String date,
				String description, long userId) {
//			SimpleDateFormat dateFormat = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			int rows = 0;
			try {
				rows = jdbcTemplate.update(SAVE_TRANSACTION, new java.sql.Date(dateFormat.parse(date).getTime()),
						amount, description,
						jdbcTemplate.queryForInt(GET_CATEGORY_ID, category), userId, ACCOUNT_ID);
			} catch (ParseException e) {
				log.error("Error parsing date " + date, e);
			}
			
			if (rows == 0) {
				return false;
			} else {
				return true;
			}
		}
}
