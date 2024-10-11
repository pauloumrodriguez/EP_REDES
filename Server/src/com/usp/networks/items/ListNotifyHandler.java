package com.usp.networks.items;

import java.sql.SQLException;

public class ListNotifyHandler implements Handler {
	public StringBuilder execute(String[] p) {
		Database db = Database.getDB();
		ResultConnection rs;
		if(p[2].equals("true")) {
			rs = db.executeSelect("SELECT * FROM Notifications WHERE sender = \'admin@usp.br\' OR sender = \'" + p[1] + "\';");
		}
		else rs = db.executeSelect("SELECT * FROM Notifications WHERE user_id = \'" + p[1] + "\';");
		StringBuilder msg = new StringBuilder("PACK;");
		try {
			while(rs.getResultSet().next()) {
				String a = "\"" + rs.getResultSet().getInt("id") + ","+ rs.getResultSet().getString("user_id") + "," + rs.getResultSet().getString("sender") + "," + rs.getResultSet().getString("message") + "\"";
				msg.append(a + ";");
			}
			rs.close();
			msg.append(":");
			System.out.println("notification list successfully generated");
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
