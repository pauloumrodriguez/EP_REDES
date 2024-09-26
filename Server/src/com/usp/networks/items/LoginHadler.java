package com.usp.networks.items;

import java.sql.*;

public class LoginHadler implements Handler{
	public StringBuilder execute(String[] p) {
		Database db = Database.getDB();
		ResultConnection rs = db.executeSelect("SELECT * FROM Users WHERE login = '" + p[1] + "';");
		StringBuilder msg = new StringBuilder("MSG;");
		try{
			if(!rs.getResultSet().next()) {
				msg.append("\"User not found\":");
				System.out.println("User not found");
			}
			else {
				String password = rs.getResultSet().getString("password");
				if(password.compareTo(p[2]) == 0) {
					msg.append("\"Connect with sucess\"|" + rs.getResultSet().getBoolean("admin") + "|:");
					System.out.println("Connect with sucess");
				}
				else {
					msg.append("\"Incorrect password\":");
					System.out.println("Incorrect password");
				}
			}
			rs.close();
		}
	    catch(SQLException e) {
	    	StringBuilder msgException = new StringBuilder("MSG;");
	    	msgException.append("\"Database error\":");
	    	System.out.println("Error in Database");
	    	return msgException;
	    }
		
		return msg;
	}
}
