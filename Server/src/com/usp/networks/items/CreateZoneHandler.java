package com.usp.networks.items;

public class CreateZoneHandler implements Handler {
	public StringBuilder execute(String[] p) {
		Database db = Database.getDB();//Abre conexão com banco de dados
		StringBuilder msg = new StringBuilder("MSG;");
		if(p[3].charAt(0) == '-') { //Verifica se foi enviado uma zona com raio negativo
			msg.append("\"There is no zone with a negative radius\":"); // Não cadastra tal zona
			System.out.println("There is no zone with a negative radius");
			return msg;
		}
		Boolean flag = db.executeSQL("INSERT INTO Zone (x, y, radius) VALUES ("+
		p[1]+","+p[2]+"," + p[3] + ");"); //Cria uma nova zona
		if(flag) {
			msg.append("\"Zone created with success\":");
			System.out.println("Zone created with success");
		}
		else {
			msg.append("\"Unable to register zone\":");
			System.out.println("Unable to register zone");
		}
		return msg;
	}
}