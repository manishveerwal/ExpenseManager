package org.expensemanager.service;

import org.expensemanager.bean.User;

public interface UserService {
	public void create(User user);
	public boolean checkUserPassword(String user, String password);
	public boolean isUserExist(String user);
}
