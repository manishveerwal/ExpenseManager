package org.expensemanager.service;


public interface TransactionService {
	public long getTotalExpense(long userId);
	public long getTotalIncome(long userId);
	public boolean saveTransaction( String category,  String amount, 
			 String date,  String description, long userId);
}
