package org.expensemanager.dao;

import org.expensemanager.bean.User;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class UserDAO extends JdbcDaoSupport implements DAOInterface {
	
	public UserDAO() {
	}
	
	@Override
	public Object findById(long id) {
		return null;
	}

	@Override
	public int insert(Object object) {
		User user = (User) object;
		return 0;
	}

	@Override
	public boolean delete(long id) {
		return false;
	}

	@Override
	public boolean update(Object object) {
		return false;
	}
}
