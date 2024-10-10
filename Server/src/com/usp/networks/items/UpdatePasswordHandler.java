package com.usp.networks.items;

import java.sql.SQLException;

public class UpdatePasswordHandler implements Handler {
	public StringBuilder execute(String[] p) {
		Database db = Database.getDB();
		StringBuilder msg = new StringBuilder("MSG;");
		ResultConnection rs = db.executeSelect("SELECT * FROM Users WHERE login = '" + p[1] + "';");
		try {
			while(rs.getResultSet().next()) {
				String login = rs.getResultSet().getString("login");
				if(login.compareTo("admin@usp.br") == 0) {
					msg.append("\"cannot update standard user\":");
					System.out.println("Cannot update standard user");
					return msg;
				}
				else {
					if(p[2].equals(p[3])) {
						Boolean flag = db.executeSQL("UPDATE Users SET password = '" + p[2] +"' WHERE id = " + rs.getResultSet().getInt("id") +";");
						if(flag) {
							msg.append("\"Updated with sucess\":");
							System.out.println("Updated with sucess");
							return msg;
						}
						msg.append("\"Unable updated\":");
						System.out.println("Updated with sucess");
						return msg;
					}
					else {
						msg.append("\"This password is not correct\":");
						System.out.println("This password is not correct");
						return msg;
					}
				}
			}
			msg.append("\"User not found\":");
			System.out.println("User not found");
			return msg;
		}
		catch(SQLException e) {
			StringBuilder msgException = new StringBuilder("MSG;");
	    	msgException.append("\"Database error\":");
	    	System.out.println("Error in Database");
	    	return msgException;
		}
	}
}
