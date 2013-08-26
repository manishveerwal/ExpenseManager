package org.expensemanager.dao;

import org.expensemanager.bean.Category;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class CategoryDAO extends JdbcDaoSupport implements DAOInterface {

	public CategoryDAO() {
	}

	@Override
	public Category findById(long id) {
		return null;
	}

	@Override
	public int insert(Object category) {
		return 0;
	}

	@Override
	public boolean delete(long id) {
		return false;
	}

	@Override
	public boolean update(Object category) {
		return false;
	}
}
