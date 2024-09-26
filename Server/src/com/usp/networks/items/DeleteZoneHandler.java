package com.usp.networks.items;

public class DeleteZoneHandler implements Handler {
	public StringBuilder execute(String[] p) {
		Database db = Database.getDB();
		StringBuilder msg = new StringBuilder("MSG;");
		Boolean flag = db.executeSQL("DELETE FROM Zone WHERE id = " + p[1] + ";");
		if(flag) {
			msg.append("\"Zone deleted with sucess\":");
			System.out.println("Zone deleted with sucess");
		}
		else {
			msg.append("\"Unable to delete zone\":");
			System.out.println("Unable to delete zone");		
		}
		return msg;
	}
}
