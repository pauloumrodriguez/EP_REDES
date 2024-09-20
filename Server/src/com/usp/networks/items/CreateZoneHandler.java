package com.usp.networks.items;

public class CreateZoneHandler implements Handler {
	public Boolean execute(String[] p) {
		Database db = Database.getDB();
		db.executeSQL("INSERT INTO Zone (x, y, radius) VALUES ("+
		p[0]+","+p[1]+");");
		return true;
	}
}