package com.usp.networks.items;

public class SendHandler implements Handler {
	public StringBuilder execute(String[] p) {
		Database db = Database.getDB(); //Abre a conexão com o banco de dados
		Boolean flag = db.executeSQL("INSERT INTO Notifications (user_id, sender, message) VALUES ('"+
		p[1] + "', '"+ p[2] + "', '" + p[3] + "');"); //Envia a mensagem ao destinatário
		StringBuilder msg = new StringBuilder("MSG;");
		if(flag) {
			msg.append("\"Sent with success\":");
			System.out.println("Message sent successfully");
		}
		else {
			msg.append("\"Unable to sent message\":");
			System.out.println("Unable to sent message");
		}
		return msg;
	}
}
