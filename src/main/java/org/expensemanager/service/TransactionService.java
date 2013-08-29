package org.expensemanager.service;


public interface TransactionService {
	public long getTotalExpense(long userId);
	public long getTotalIncome(long userId);
	public boolean saveTransaction( String category,  String amount, 
			 String date,  String description, long userId);
	public long getTodayTransaction(long userId, int category_id);
	public long getThisWeekTransaction(long userId, int category_id);
	public long getThisMonthTransaction(long userId, int category_id);
	public long getThisYearTransaction(long userId, int category_id);
	public int getCategoryID(String category);
}
