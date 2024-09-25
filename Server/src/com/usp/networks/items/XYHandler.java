package com.usp.networks.items;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class XYHandler implements HandlerS{
	public List<StringBuilder> execute(String[] p) {
		Database db = Database.getDB();
		List<StringBuilder> list = new ArrayList<>();
		ResultConnection rs = db.executeSelect("SELECT * FROM UserZoneMapping WHERE user_id = " + p[1] +";");
		try{
			while(rs.getResultSet().next()) {
				StringBuilder msg = new StringBuilder("NOTIFY;");
				ResultConnection rt = db.executeSelect("SELECT * FROM Zone WHERE id = " + rs.getResultSet().getString("zone_id")
				+ ";");
				while(rt.getResultSet().next()) {
					double xZ = rt.getResultSet().getDouble("x");
					double yZ = rt.getResultSet().getDouble("y");
					double radiusZ = rt.getResultSet().getDouble("radius");
					double a = Math.pow((Double.parseDouble(p[1]) - xZ), 2);
					double b = Math.pow((Double.parseDouble(p[2]) - yZ), 2);
					if(a + b <= Math.pow(radiusZ, 2)) {
						msg.append("\"Invaded a non-permitted zone(" + rt.getResultSet().getInt("id") + ")\":");
						list.add(msg);
					}
				}
				rt.close();
			}
			rs.close();
		}
		catch(SQLException e) {
			return null;
		}
		return list;
	}
}
