package com.usp.networks.items;

import java.sql.*;

public class LoginHadler implements HandlerR{
	public StringBuilder execute(String[] p) {
		Database db = Database.getDB();
		ResultConnection rs = db.executeSelect("SELECT * FROM Users WHERE login = " + p[1] + ";");
		StringBuilder msg = new StringBuilder("MSG;");
		try{
			while(rs.getResultSet().next()) {
				String login = rs.getResultSet().getString("login");
				if(login.compareTo(p[1]) == 0) {
					String password = rs.getResultSet().getString("password");
					if(password.compareTo(p[2]) == 0) msg.append("\"Connect with sucess\":");
					else msg.append("\"Incorrect password\":");
					break;
				}
				else {
					msg.append("\"User not found\":");
				}
			}
			rs.close();
		}
	    catch(SQLException e) {
	    	StringBuilder msgException = new StringBuilder("MSG;");
	    	msgException.append("\"Database error\":");
	    	return msgException;
	    }
		
		return msg;
	}
}
