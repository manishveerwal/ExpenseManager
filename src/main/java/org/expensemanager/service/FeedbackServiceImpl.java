package org.expensemanager.service;

import javax.sql.DataSource;

import org.expensemanager.bean.Feedback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;

public class FeedbackServiceImpl implements FeedbackService{

	private JdbcTemplate jdbcTemplate;
	
	private static final String POST_FEEDBACK = "insert into feedback (name, email, feedback_comment) values(?, ?, ?)";
	
	public FeedbackServiceImpl() {
	}

	public FeedbackServiceImpl(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public boolean postFeedback(Feedback feedback){
		int rows = jdbcTemplate.update(POST_FEEDBACK, feedback.getName(), feedback.getEmail(), feedback.getComment());
		if (rows == 0) {
			return false;
		}
		return true;
	}
}
