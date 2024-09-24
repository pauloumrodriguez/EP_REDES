package com.usp.networks.items;

import java.sql.*;
import com.usp.networks.server.User;

public class ListUserHandler implements HandlerR{
	public StringBuilder execute(String[] p) {
		Database db = Database.getDB();
		StringBuilder msg = new StringBuilder("PACK;");
		ResultSet rs = db.executeSelect("SELECT * FROM Users WHERE login != admin@usp.br;");
		try {
			while(rs.next()) {
				User u = new User(rs.getInt("id"), rs.getString("fname"),
						rs.getString("lname"), rs.getString("login"), rs.getString("password"),
						rs.getBoolean("admin"));
				String a = u.getID() + "," + u.getFName() + "," + u.getLName() + "," +
						u.getLogin() + "," + u.getPassword() + "," + u.getAdmin();
				msg.append(String.join(";", a));
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
