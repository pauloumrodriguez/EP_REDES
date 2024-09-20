package com.usp.networks.items;

public class CreateUserHandler implements Handler {
	public Boolean execute(String[] p) {
		Database db = Database.getDB();
		db.executeSQL("INSERT INTO Users (fname, lname, login, password, admin) VALUES ('"+
		p[0]+"','"+p[1]+"','"+p[2]+"','"+p[3]+"',"+p[4]+");");
		return true;
	}
}
