package com.usp.networks.items;

import java.sql.*;
import com.usp.networks.server.Zone;

public class ListZoneHandler implements Handler {
	public StringBuilder execute(String[] p) {
		Database db = Database.getDB();
		StringBuilder msg = new StringBuilder("PACK;");
		ResultConnection rs = db.executeSelect("SELECT * FROM Zone;");
		try {
			while(rs.getResultSet().next()) {
				Zone z = new Zone(rs.getResultSet().getInt("id"), rs.getResultSet().getDouble("x"), rs.getResultSet().getDouble("y"), rs.getResultSet().getDouble("radius"));
				String a = z.getID() + "," + z.getX() + "," + z.getY() + ","
						+ z.getRadius();
				msg.append(String.join(";", a));
			}
			rs.close();
			msg.append(":");
			System.out.println("zone list successfully generated");
		}
		catch(SQLException e) {
			StringBuilder msgException = new StringBuilder("MSG;");
			msgException.append("\"Database error\":");
			return msgException;
		}
		return msg;
	}
}
