package com.usp.networks.items;

import java.sql.*;

public class LoginHadler implements HandlerR{
	public StringBuilder execute(String[] p) {
		Database db = Database.getDB();
		ResultSet rs = db.executeSelect("SELECT * FROM Users WHERE login = " + p[1] + ";");
		StringBuilder msg = new StringBuilder("MSG;");
		try{
			while(rs.next()) {
				String login = rs.getString("login");
				if(login.compareTo(p[1]) == 0) {
					String password = rs.getString("password");
					if(password.compareTo(p[2]) == 0) msg.append("\"Connect with sucess\":");
					else msg.append("\"Incorrect password\":");
					break;
				}
				else {
					msg.append("\"User not found\":");
				}
			}
		}
	    catch(SQLException e) {
	    	StringBuilder msgException = new StringBuilder("MSG;");
	    	msgException.append("\"Database error\":");
	    	return msgException;
	    }
		
		return msg;
	}
}
