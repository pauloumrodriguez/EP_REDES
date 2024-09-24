package com.usp.networks.items;

public class UpdateZoneHandler implements HandlerR{
	public StringBuilder execute(String[] p) {
		Database db = Database.getDB();
		StringBuilder msg = new StringBuilder("MSG;");
		Boolean flag = db.executeSQL("UPDATE Zone SET x = " + p[2] + ", y = " + p[3] + ", radius = " + p[4]
				+ " WHERE id = " + p[1] + ";");
		if(flag) msg.append("\"Zone updated with sucess\":");
		else msg.append("\"Unable to updated zone\":");
		return msg;
	}
}
