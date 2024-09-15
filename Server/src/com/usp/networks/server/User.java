package com.usp.networks.server;

public class User {
	
	private int id;
	private String fname;
	private String lname;
	private String login;
	private String password;
	private Boolean admin;
	
	public User(int id, String fname, String lname, String login, String password, Boolean admin) {
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.login = login;
		this.password = password;
		this.admin = admin;
	}
	
	public int getID() {
		return id;
	}
	
	public String getFName() {
		return fname;
	}
	
	public String getLName() {
		return lname;
	}
	
	public String getLogin() {
		return login;
	}
	
	public String getPassword() {
		return password;
	}
	
	public Boolean getAdmin() {
		return admin;
	}
}
