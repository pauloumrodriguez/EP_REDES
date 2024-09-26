package com.usp.networks.items;

import java.sql.*;

public class DeleteUserHandler implements Handler{
	public StringBuilder execute(String[] p) {
		Database db = Database.getDB();
		StringBuilder msg = new StringBuilder("MSG;");
		ResultConnection rs = db.executeSelect("SELECT * FROM Users WHERE id = '" + p[1] + "';");
		try {
			while(rs.getResultSet().next()) {
				String login = rs.getResultSet().getString("login");
				if(login.compareTo("admin@usp.br") == 0) {
					msg.append("\"cannot delete standard user\":");
					System.out.println("Cannot delete standard user");
					return msg;
				}
			}
		}
		catch(SQLException e) {
			StringBuilder msgException = new StringBuilder("MSG;");
	    	msgException.append("\"Database error\":");
	    	System.out.println("Error in Database");
	    	return msgException;
		}
		Boolean flag = db.executeSQL("DELETE FROM Users WHERE id = " + p[1] + ";");
		if(flag) {
			msg.append("\"User deleted with sucess\":");
			System.out.println("User deleted with sucess");
		}
		else {
			msg.append("\"Unable to delete user\":");
			System.out.println("Unable to delete user");
		}
		return msg;
	}
}
