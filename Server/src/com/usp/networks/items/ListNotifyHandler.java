package com.usp.networks.items;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ListNotifyHandler implements HandlerR {
	public StringBuilder execute(String[] p) {
		Database db = Database.getDB();
		ResultSet rs = db.executeSelect("SELECT * FROM Notifications;");
		StringBuilder msg = new StringBuilder("PACK;");
		try {
			while(rs.next()) {
				String a = rs.getInt("user_id") + "," + rs.getString("sender") + "," + rs.getString("message");
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
