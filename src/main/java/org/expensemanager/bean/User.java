package org.expensemanager.bean;

import java.io.Serializable;

public class User implements Serializable {
	
	private long userId;
	private String email;
	private String password;
	private String role;
	private String firstName;
	private String lastName;
	private String gender;
	private String country;

	public User() {
	}

	public User(long userId, String email, String password, String role,
			String firstName, String lastName, String gender, String country) {
		this.userId = userId;
		this.email = email;
		this.password = password;
		this.role = role;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.country = country;
	}

	public User(String email, String password, String role, String firstName,
			String lastName, String gender, String country) {
		this.email = email;
		this.password = password;
		this.role = role;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.country = country;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
}
