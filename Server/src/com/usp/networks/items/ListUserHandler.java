package com.usp.networks.items;

import java.sql.*;
import com.usp.networks.server.User;

public class ListUserHandler implements Handler{
	public StringBuilder execute(String[] p) {
		Database db = Database.getDB();
		StringBuilder msg = new StringBuilder("PACK;");
		ResultConnection rs = db.executeSelect("SELECT * FROM Users WHERE login != 'admin@usp.br';");
		try {
			while(rs.getResultSet().next()) {
				User u = new User(rs.getResultSet().getInt("id"), rs.getResultSet().getString("fname"),
						rs.getResultSet().getString("lname"), rs.getResultSet().getString("login"), rs.getResultSet().getString("password"),
						rs.getResultSet().getBoolean("admin"));
				String a = "\"" + u.getID() + "," + u.getFName() + "," + u.getLName() + "," +
						u.getLogin() + "," + u.getPassword() + "," + u.getAdmin() + "\"";
				msg.append(a + ";");
			}
			msg.append(":");
			rs.close();
			System.out.println("user list successfully generated");
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
