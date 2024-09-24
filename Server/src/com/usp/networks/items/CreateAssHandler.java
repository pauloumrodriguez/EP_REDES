package com.usp.networks.items;

public class CreateAssHandler implements HandlerR{
	public StringBuilder execute(String[] p) {
		Database db = Database.getDB();
		StringBuilder msg = new StringBuilder("MSG;");
		Boolean flag = db.executeSQL("INSERT INTO UserZoneMapping (user_id, zone_id) VALUES (" +
				p[1] + ", " + p[2] + ");");
		if(flag) msg.append("\"Association Zone-User created with sucess\":");
		else msg.append("Unable to register association zone-user\":");
		return msg;
	}
}
