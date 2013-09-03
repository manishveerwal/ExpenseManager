package org.expensemanager.service;

import java.util.List;

import org.expensemanager.bean.Account;

public interface AccountService {
	public List<Account> getAccountByUser(long userId);
	public boolean updateAccount(String account, int accountId, long userId);
	public boolean addAccount(String account, long userId);
	public boolean deleteAccount(int accountId, long userId);
}
