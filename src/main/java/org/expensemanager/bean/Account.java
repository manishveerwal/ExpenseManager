package org.expensemanager.bean;

public class Account {
	
	private int accountId;
	private String accountName;
	private String accountDefault;

	public Account() {
	}
	
	public Account(int accountId, String accountName, String accountDefault) {
		this.accountId = accountId;
		this.accountName = accountName;
		this.accountDefault = accountDefault;
	}

	public Account(String accountName, String accountDefault) {
		this.accountName = accountName;
		this.accountDefault = accountDefault;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountDefault() {
		return accountDefault;
	}

	public void setAccountDefault(String accountDefault) {
		this.accountDefault = accountDefault;
	}
}
