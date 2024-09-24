package com.usp.networks.items;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class XYHandler implements HandlerS{
	public List<StringBuilder> execute(String[] p) {
		Database db = Database.getDB();
		List<StringBuilder> list = new ArrayList<>();
		ResultSet rs = db.executeSelect("SELECT * FROM UserZoneMapping WHERE user_id = " + p[1] +";");
		try{
			while(rs.next()) {
				StringBuilder msg = new StringBuilder("NOTIFY;");
				ResultSet rt = db.executeSelect("SELECT * FROM Zone WHERE id = " + rs.getString("zone_id")
				+ ";");
				while(rt.next()) {
					double xZ = rt.getDouble("x");
					double yZ = rt.getDouble("y");
					double radiusZ = rt.getDouble("radius");
					double a = Math.pow((Double.parseDouble(p[1]) - xZ), 2);
					double b = Math.pow((Double.parseDouble(p[2]) - yZ), 2);
					if(a + b <= Math.pow(radiusZ, 2)) {
						msg.append("\"Invaded a non-permitted zone(" + rt.getInt("id") + ")\":");
						list.add(msg);
					}
				}
			}
		}
		catch(SQLException e) {
			return null;
		}
		return list;
	}
}
