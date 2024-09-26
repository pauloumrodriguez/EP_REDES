package com.usp.networks.items;

public class CreateAssHandler implements Handler{
	public StringBuilder execute(String[] p) {
		Database db = Database.getDB();
		StringBuilder msg = new StringBuilder("MSG;");
		Boolean flag = db.executeSQL("INSERT INTO UserZoneMapping (user_id, zone_id) VALUES (" +
				p[1] + ", " + p[2] + ");");
		if(flag) {
			msg.append("\"Association Zone-User created with sucess\":");
			System.out.println("Association Zone-User created with sucess");
		}
		else {
			msg.append("\"Unable to register association zone-user\":");
			System.out.println("Unable to register association zone-user");
		}
		return msg;
	}
}
