package com.usp.networks.items;

import java.sql.*;

public class LoginHadler implements Handler{
	public Boolean execute(String[] p) {
		Database db = Database.getDB();
		ResultSet rs = db.executeSelect("SELECT * FROM Users WHERE login = " + p[0]);
		Boolean flag = false;
		try{
			while(rs.next()) {
				String login = rs.getString("login");
				if(login.compareTo(p[0]) == 0) flag = true;
			}
		}
	    catch(SQLException e) {
	    	e.printStackTrace();
	    }
		return flag;
	}
}
