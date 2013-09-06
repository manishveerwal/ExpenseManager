package org.expensemanager.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.expensemanager.bean.Category;
import org.expensemanager.util.Constants;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.PlatformTransactionManager;

public class CategoryServiceImpl implements CategoryService {
	
	protected static final String EMPTY_STRING = "";
	protected static final String CHILD_NAME = "child_name";
	protected static final String CHILD_CATEGORY_ID = "child_category_id";
	protected static final String PARENT_NAME = "parent_name";
	protected static final String PARENT_CATEGORY_ID = "parent_category_id";
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
	private static final String DELETE_CATEGORY = "delete from category where category_id=? and category_level <> '0'";
	private static final String DELETE_SUB_CATEGORY = "delete from category where super_category_id=? and category_level = '2'";
	
	private static final String GET_ROOT_CATEGORY = "select c1.category_name from category c1 left join category c2 on " +
			"c2.super_category_id = c1.category_id left join category c3 on " +
			"c3.super_category_id = c2.category_id where c3.category_id=?";
	
	// Insert Queries
	private static final String INSERT_CATEGORY = "insert into category (category_name, category_level, super_category_id," +
			" category_default) values (?, ?, ?, ?)";
	private static final String INSERT_INCOME_CATEGORY = "insert into category (category_name, category_level, super_category_id," +
			" category_default) values (?, ?, (select category_id from category where category_name='Income'), ?)";
	private static final String INSERT_EXPENSE_CATEGORY = "insert into category (category_name, category_level, super_category_id," +
			" category_default) values (?, ?, (select category_id from category where category_name='Expense'), ?)";
	
	// Select Queries
	private static final String GET_INCOME_CATEGORIES_USER = "select c.category_id, c.category_name, c.category_level, c.super_category_id, c.category_default from " +
			"category c, user_category uc, category c2 where" +
			" uc.category_id = c.category_id and uc.user_id = ? and c.category_level=1 and " +
			"c.super_category_id = c2.category_id and c2.category_name = 'Income'";
	private static final String GET_EXPENSE_CATEGORIES_USER = "select c2.category_id as parent_category_id, c2.category_name as parent_name, " +
			"c3.category_id as child_category_id, c3.category_name as child_name from " +
			"category c1 left join category c2 on c2.super_category_id = c1.category_id left join category c3 on c3.super_category_id = c2.category_id inner join " +
			"user_category uc on uc.category_id=c2.category_id where c1.category_name = 'Expense' and uc.user_id=? order by c2.category_name";
	
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
		List<Category> categories = getCategories(GET_SUB_CATEGORY, categoryId);
		
		if (categories.size() != 0 && categories.get(0).getCategoryLevel() < 2 ) {
			for(Category category : categories){
				category.setCategories(getCategories(GET_SUB_CATEGORY, categoryId));
			}
		}
		return categories;
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
	
	public boolean insertIncomeCategory(String incomeCategory, String defaultValue){
		int rows = jdbcTemplate.update(INSERT_INCOME_CATEGORY, incomeCategory, CATEGORY_LEVEL_1, defaultValue);
		if (rows == 0) {
			return false;
		} else {
			return true;
		}
	}
	
	public boolean insertExpenseMainCategory(String expenseCategory, String defaultValue){
		int rows = jdbcTemplate.update(INSERT_EXPENSE_CATEGORY, expenseCategory, CATEGORY_LEVEL_1, defaultValue);
		if (rows == 0) {
			return false;
		} else {
			return true;
		}
	}
	
	public boolean insertChildCategory(String categoryName, long superCategoryId, String defaultValue){
		int rows = jdbcTemplate.update(INSERT_CATEGORY, categoryName, CATEGORY_LEVEL_2, superCategoryId, defaultValue);
		if (rows == 0) {
			return false;
		} else {
			return true;
		}
	}
	
	public List<Category> getCategories(String query, long id) {
		return jdbcTemplate.query(query, new RowMapper<Category>() {
			@Override
			public Category mapRow(ResultSet rs, int i) throws SQLException {
				return new Category(rs.getLong(CATEGORY_ID), 
						rs.getString(CATEGORY_NAME), 
						rs.getInt(CATEGORY_LEVEL), 
						rs.getLong(SUPER_CATEGORY_ID), 
						rs.getString(CATEGORY_DEFAULT));
			}
		}, id);
	}
	
	public List<Category> getIncomeCategories(long userId){
		return getCategories(GET_INCOME_CATEGORIES_USER, userId);
	}
	
	public List<Category> getExpenseCategories(long userId){
		List<Category> categoryList = jdbcTemplate.query(GET_EXPENSE_CATEGORIES_USER, new RowMapper<Category>() {
			@Override
			public Category mapRow(ResultSet rs, int i) throws SQLException {
				Category category = new Category(rs.getInt(PARENT_CATEGORY_ID), rs.getString(PARENT_NAME), 0, 0, EMPTY_STRING);
				category.getCategories().add(new Category(rs.getInt(CHILD_CATEGORY_ID), rs.getString(CHILD_NAME), 0, 0, EMPTY_STRING));
				return category;
			}
		}, userId);
		
		int j = 0;
		for (int i = 1; i < categoryList.size();) {
			if (categoryList.get(i).getCategoryId() == categoryList.get(j).getCategoryId()) {
				categoryList.get(j).getCategories().add(categoryList.get(i).getCategories().get(0));
				categoryList.remove(i);
			} else {
				j = i;
				i++;
			}
		}
		if (log.isDebugEnabled()) {
			log.debug("Categories after: " + categoryList);
		}
		return categoryList;
	}
}
