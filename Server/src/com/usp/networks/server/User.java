package com.usp.networks.server;

public class User { //Representa a entidade usuário
	
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
	
	public int getID() { //Devolve ID
		return id;
	}
	
	public String getFName() { //Devolve primeiro nome
		return fname;
	}
	
	public String getLName() { //Devolve último nome
		return lname;
	}
	
	public String getLogin() { //Devolve login
		return login;
	}
	
	public String getPassword() { //Devolve senha
		return password;
	}
	
	public Boolean getAdmin() { //Devolve se ele é admin
		return admin;
	}
}
