package com.usp.networks.items;

public class UpdateZoneHandler implements Handler{
	public StringBuilder execute(String[] p) {
		Database db = Database.getDB();
		StringBuilder msg = new StringBuilder("MSG;");
		if(p[4].charAt(0) == '-') {
			msg.append("\"there is no zone with a negative radius\":");
			System.out.println("There is no zone with a negative radius");
			return msg;
		}
		Boolean flag = db.executeSQL("UPDATE Zone SET x = " + p[2] + ", y = " + p[3] + ", radius = " + p[4]
				+ " WHERE id = " + p[1] + ";");
		if(flag) {
			msg.append("\"Zone updated with sucess\":");
			System.out.println("Zone updated with sucess");
		}
		else {
			msg.append("\"Unable to updated zone\":");
			System.out.println("Unable to updated zone");
		}
		return msg;
	}
}
