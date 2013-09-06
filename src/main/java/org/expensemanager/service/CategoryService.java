package org.expensemanager.service;

import java.util.List;

import org.expensemanager.bean.Category;

public interface CategoryService {
	public int getCategoryID(String category);
	public List<Category> getSubCategories(long categoryId);
	public List<Category> getExpenseCategories(long userId);
	public List<Category> getIncomeCategories(long userId);
	public boolean deleteIncomeCategory(long categoryId);
	public boolean deleteExpenseCategory(long categoryId, int level);
	public boolean insertIncomeCategory(String incomeCategory, String defaultValue);
	public boolean insertExpenseMainCategory(String expenseCategory, String defaultValue);
	public boolean insertChildCategory(String categoryName, long superCategoryId, String defaultValue);
}
