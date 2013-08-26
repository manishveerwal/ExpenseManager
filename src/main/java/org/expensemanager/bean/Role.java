package org.expensemanager.bean;

import java.io.Serializable;

public class Role implements Serializable {
	
	private int roleId;
	private String role;

	public Role() {
	}
	
	public Role(int roleId, String role) {
		this.roleId = roleId;
		this.role = role;
	}

	public Role(String role) {
		this.role = role;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
