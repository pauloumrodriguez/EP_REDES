package com.usp.networks.items;

import java.sql.*;
import com.usp.networks.server.Zone;

public class ListZoneHandler implements HandlerR {
	public StringBuilder execute(String[] p) {
		Database db = Database.getDB();
		StringBuilder msg = new StringBuilder("PACK;");
		ResultSet rs = db.executeSelect("SELECT * FROM Zone;");
		try {
			while(rs.next()) {
				Zone z = new Zone(rs.getInt("id"), rs.getDouble("x"), rs.getDouble("y"), rs.getDouble("radius"));
				String a = z.getID() + "," + z.getX() + "," + z.getY() + ","
						+ z.getRadius();
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
