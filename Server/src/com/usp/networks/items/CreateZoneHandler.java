package com.usp.networks.items;

public class CreateZoneHandler implements Handler {
	public StringBuilder execute(String[] p) {
		Database db = Database.getDB();
		StringBuilder msg = new StringBuilder("MSG;");
		if(p[3].charAt(0) == '-') {
			msg.append("\"there is no zone with a negative radius\":");
			System.out.println("There is no zone with a negative radius");
			return msg;
		}
		Boolean flag = db.executeSQL("INSERT INTO Zone (x, y, radius) VALUES ("+
		p[1]+","+p[2]+"," + p[3] + ");");
		if(flag) {
			msg.append("\"Zone created with sucess\":");
			System.out.println("Zone created with sucess");
		}
		else {
			msg.append("\"Unable to register zone\":");
			System.out.println("Unable to register zone");
		}
		return msg;
	}
}