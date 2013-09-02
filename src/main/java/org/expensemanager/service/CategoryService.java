package org.expensemanager.service;

import java.util.List;

import org.expensemanager.bean.Category;

public interface CategoryService {
	public int getCategoryID(String category);
	public List<Category> getSubCategories(long categoryId);
	public List<Category> getExpenseCategories();
	public List<Category> getIncomeCategories();
	public boolean createIncomeCategory(String incomeCategoryFieldValue);
	public boolean deleteIncomeCategory(long categoryId);
	public boolean createMainExpenseCategory(String expenseCategoryFieldValue);
	public boolean createSubExpenseCategory(String expenseCategoryFieldValue, long superCategoryId);
	public boolean createExpenseCategory(String expenseCategoryFieldValue, long superCategoryId, int level);
	public boolean deleteExpenseCategory(long categoryId, int level);
}
