package com.usp.networks.items;

import java.sql.SQLException;

public class ListNotifyHandler implements HandlerR {
	public StringBuilder execute(String[] p) {
		Database db = Database.getDB();
		ResultConnection rs = db.executeSelect("SELECT * FROM Notifications;");
		StringBuilder msg = new StringBuilder("PACK;");
		try {
			while(rs.getResultSet().next()) {
				String a = rs.getResultSet().getInt("user_id") + "," + rs.getResultSet().getString("sender") + "," + rs.getResultSet().getString("message");
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
