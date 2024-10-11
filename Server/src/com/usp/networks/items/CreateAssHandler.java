package com.usp.networks.items;

public class CreateAssHandler implements Handler{
	public StringBuilder execute(String[] p) {
		Database db = Database.getDB(); //Abre conexão com o banco de dados
		StringBuilder msg = new StringBuilder("MSG;");
		Boolean flag = db.executeSQL("INSERT INTO UserZoneMapping (user_id, zone_id) VALUES (" +
				p[1] + ", " + p[2] + ");"); //Cria uma associação entre zona e usuário
		if(flag) { //Sucesso
			msg.append("\"Association Zone-User created with success\":");
			System.out.println("Association Zone-User created with success");
		}
		else { //Não foi possível criar associação zone-user
			msg.append("\"Unable to register association zone-user\":");
			System.out.println("Unable to register association zone-user");
		}
		return msg;
	}
}
