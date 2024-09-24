package com.usp.networks.items;

public class CreateZoneHandler implements HandlerR {
	public StringBuilder execute(String[] p) {
		Database db = Database.getDB();
		StringBuilder msg = new StringBuilder("MSG;");
		Boolean flag = db.executeSQL("INSERT INTO Zone (x, y, radius) VALUES ("+
		p[1]+","+p[2]+");");
		if(flag) msg.append("\"Zone created with sucess\":");
		else msg.append("\"Unable to register zone\":");
		return msg;
	}
}