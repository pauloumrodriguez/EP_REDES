package com.usp.networks.items;

public class DeleteNotifyHandler implements Handler{
	public StringBuilder execute(String[] p) {
		Database db = Database.getDB();//abre conexão com banco de dados
		StringBuilder msg = new StringBuilder("MSG;");
		Boolean flag = db.executeSQL("DELETE FROM Notifications WHERE id = " + p[1] + ";"); //Deleta notificação
		if(flag) {
			msg.append("\"Notification deleted with success\":");
			System.out.println("Notification deleted with success");
		}
		else {
			msg.append("\"Unable to delete user\":");
			System.out.println("Unable to delete Notification");
		}
		return msg;
	}
}
