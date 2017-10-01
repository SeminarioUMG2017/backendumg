package com.seminarioUMG.seminario.resultmodels;

public class Password {
	
	
	private String actualpass;
	private String user;
	private String newpass;
	private String confirmpass;
	
	
	public String getNewpass() {
		return newpass;
	}
	public void setNewpass(String newpass) {
		this.newpass = newpass;
	}
	public String getConfirmpass() {
		return confirmpass;
	}
	public void setConfirmpass(String confirmpass) {
		this.confirmpass = confirmpass;
	}
	public String getActualpass() {
		return actualpass;
	}
	public void setActualpass(String actualpass) {
		this.actualpass = actualpass;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	
}
