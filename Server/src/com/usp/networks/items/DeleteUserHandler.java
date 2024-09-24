package com.usp.networks.items;

public class DeleteUserHandler implements HandlerR{
	public StringBuilder execute(String[] p) {
		Database db = Database.getDB();
		StringBuilder msg = new StringBuilder("MSG;");
		Boolean flag = db.executeSQL("DELETE FROM Users WHERE id = " + p[1] + ";");
		if(flag) msg.append("\"User deleted with sucess\":");
		else msg.append("\"Unable to delete user\":");
		return msg;
	}
}
