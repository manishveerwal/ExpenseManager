package org.expensemanager.dao;

public interface DAOInterface {
	public Object findById(long id);
	public int insert(Object object);
	public boolean delete(long id);
	public boolean update(Object object);
}
