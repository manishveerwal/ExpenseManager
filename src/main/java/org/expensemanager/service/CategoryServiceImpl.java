package org.expensemanager.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.expensemanager.bean.Category;
import org.expensemanager.util.Constants;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.PlatformTransactionManager;

public class CategoryServiceImpl implements CategoryService {
	
	public static final String CATEGORY_ID = "category_id";
	public static final String CATEGORY_LEVEL = "category_level";
	public static final String CATEGORY_NAME = "category_name";
	public static final String SUPER_CATEGORY_ID = "super_category_id";
	public static final String CATEGORY_DEFAULT = "category_default";
	
	private static final String CATEGORY_LEVEL_1 = "1";
	private static final String CATEGORY_LEVEL_2 = "2";
	
	private Logger log = Logger.getLogger(getClass());

	private PlatformTransactionManager transactionManager;
	private JdbcTemplate jdbcTemplate;
	
	private static final String GET_CATEGORY_ID = "select category_id from category where category_name=? ";
	private static final String GET_SUB_CATEGORY = "select category_id, category_level, category_name, super_category_id, " +
			"category_default from category where super_category_id=?";
	private static final String INSERT_INCOME_CATEGORY = "insert into category (category_level, category_name, super_category_id," +
			" category_default) values (?, ?, ?, ?)";
	private static final String DELETE_CATEGORY = "delete from category where category_id=? and category_level <> '0'";
	private static final String DELETE_SUB_CATEGORY = "delete from category where super_category_id=? and category_level = '2'";
	private static final String INSERT_EXPENSE_CATEGORY = "insert into category (category_level, category_name, super_category_id," +
			" category_default) values (?, ?, ?, ?)";
	
	public CategoryServiceImpl() {
	}
	
	public CategoryServiceImpl(PlatformTransactionManager transactionManager, DataSource dataSource) {
		this.transactionManager = transactionManager;
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public int getCategoryID(String category) {
		return getCategoryID(category, this.jdbcTemplate);
	}
	
	public static int getCategoryID(String category, JdbcTemplate jdbcTemplate) {
		return jdbcTemplate.queryForInt(GET_CATEGORY_ID, category);
	}
	
	public List<Category> getSubCategories(long categoryId){
		List<Category> categories = jdbcTemplate.query(GET_SUB_CATEGORY, new RowMapper<Category>() {
			@Override
			public Category mapRow(ResultSet rs, int i) throws SQLException {
				return new Category(rs.getLong(CATEGORY_ID), 
						rs.getString(CATEGORY_NAME), 
						rs.getInt(CATEGORY_LEVEL), 
						rs.getLong(SUPER_CATEGORY_ID), 
						rs.getString(CATEGORY_DEFAULT));
			}
		}, categoryId);
		
		if (categories.size() != 0 && categories.get(0).getCategoryLevel() < 2 ) {
			for(Category category : categories){
				category.setCategories(getSubCategories(category.getCategoryId()));
			}
		}
		return categories;
	}
	
	public List<Category> getExpenseCategories(){
		return getSubCategories(getCategoryID(Constants.EXPENSE));
	}
	
	public List<Category> getIncomeCategories(){
		return getSubCategories(getCategoryID(Constants.INCOME));
	}
	
	public boolean createIncomeCategory(String incomeCategoryFieldValue) {
		int rows = jdbcTemplate.update(INSERT_INCOME_CATEGORY, 1, incomeCategoryFieldValue, getCategoryID(Constants.INCOME), "Y");
		if (rows == 0) {
			return false;
		} else {
			return true;
		}
	}
	
	public boolean deleteIncomeCategory(long categoryId) {
		int rows = jdbcTemplate.update(DELETE_CATEGORY, categoryId);
		if (rows == 0) {
			return false;
		} else {
			return true;
		}
	}
	
	public boolean deleteExpenseCategory(long categoryId, int level){
		if (level == 1) {
			jdbcTemplate.update(DELETE_SUB_CATEGORY, categoryId);
		}
		int rows = jdbcTemplate.update(DELETE_CATEGORY, categoryId);
		if (rows == 0) {
			return false;
		} else {
			return true;
		}
	}
	
	public boolean createMainExpenseCategory(String expenseCategoryFieldValue) {
		return createExpenseCategory(expenseCategoryFieldValue, getCategoryID(Constants.EXPENSE), 1);
	}
	
	public boolean createSubExpenseCategory(String expenseCategoryFieldValue, long superCategoryId) {
		return createExpenseCategory(expenseCategoryFieldValue, superCategoryId, 2);
	}
	
	public boolean createExpenseCategory(String expenseCategoryFieldValue, long superCategoryId, int level) {
		int rows = jdbcTemplate.update(INSERT_EXPENSE_CATEGORY, level, expenseCategoryFieldValue, superCategoryId, "Y");
		if (rows == 0) {
			return false;
		} else {
			return true;
		}
	}
}
