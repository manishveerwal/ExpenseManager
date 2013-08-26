package org.expensemanager.bean;

public class EmailMsg {

	private String msg;
	private boolean error = false;
	
	public EmailMsg() {
	}

	public EmailMsg(String msg, boolean error) {
		this.msg = msg;
		this.error = error;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}
}
