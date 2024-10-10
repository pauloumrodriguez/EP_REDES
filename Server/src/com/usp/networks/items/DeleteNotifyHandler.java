package com.usp.networks.items;

public class DeleteNotifyHandler implements Handler{
	public StringBuilder execute(String[] p) {
		Database db = Database.getDB();
		StringBuilder msg = new StringBuilder("MSG;");
		Boolean flag = db.executeSQL("DELETE FROM Notifications WHERE id = " + p[1] + ";");
		if(flag) {
			msg.append("\"Notification deleted with sucess\":");
			System.out.println("Notification deleted with sucess");
		}
		else {
			msg.append("\"Unable to delete user\":");
			System.out.println("Unable to delete Notification");
		}
		return msg;
	}
}
