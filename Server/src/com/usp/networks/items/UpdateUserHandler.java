package com.usp.networks.items;

public class UpdateUserHandler implements HandlerR{
	public StringBuilder execute(String[] p) {
		Database db = Database.getDB();
		StringBuilder msg = new StringBuilder("MSG;");
		Boolean flag = db.executeSQL("UPDATE Users SET fname = "+ p[2] +",lname = " + p[3] +
				", login = " + p[4] + ", password = " + p[5] + ", admin = "+ p[6] + " WHERE id = " + p[1] +";");
		if(flag) msg.append("\"User updated with sucess\":");
		else msg.append("\"Unable to updated user\":");
		return msg;
	}
}
