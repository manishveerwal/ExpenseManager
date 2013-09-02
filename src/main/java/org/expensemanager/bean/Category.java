package org.expensemanager.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Category implements Serializable {
	
	private long categoryId;
	private String categoryName;
	private int categoryLevel;
	private long superCategoryId;
	private String categoryDefault;
	private List<Category> categories;

	public Category() {
		this.categories = new ArrayList<Category>();
	}
	
	public Category(String categoryName, int categoryLevel,
			long superCategoryId, String categoryDefault) {
		this.categoryName = categoryName;
		this.categoryLevel = categoryLevel;
		this.superCategoryId = superCategoryId;
		this.categoryDefault = categoryDefault;
		this.categories = new ArrayList<Category>();
	}

	public Category(long categoryId, String categoryName, int categoryLevel,
			long superCategoryId, String categoryDefault) {
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.categoryLevel = categoryLevel;
		this.superCategoryId = superCategoryId;
		this.categoryDefault = categoryDefault;
		this.categories = new ArrayList<Category>();
	}

	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public int getCategoryLevel() {
		return categoryLevel;
	}

	public void setCategoryLevel(int categoryLevel) {
		this.categoryLevel = categoryLevel;
	}

	public long getSuperCategoryId() {
		return superCategoryId;
	}

	public void setSuperCategoryId(long superCategoryId) {
		this.superCategoryId = superCategoryId;
	}

	public String getCategoryDefault() {
		return categoryDefault;
	}

	public void setCategoryDefault(String categoryDefault) {
		this.categoryDefault = categoryDefault;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
}
