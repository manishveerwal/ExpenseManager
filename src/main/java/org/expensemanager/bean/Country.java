package org.expensemanager.bean;

import java.io.Serializable;

public class Country implements Serializable {
	
	private int countryId;
	private String name;
	
	public Country() {
	}

	public Country(int countryId, String name) {
		this.countryId = countryId;
		this.name = name;
	}

	public Country(String name) {
		this.name = name;
	}

	public int getCountryId() {
		return countryId;
	}

	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
