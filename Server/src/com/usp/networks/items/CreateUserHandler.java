package com.usp.networks.items;

public class CreateUserHandler implements Handler {
	public StringBuilder execute(String[] p) {
		Database db = Database.getDB();//Abre conexão com o banco de dados
		StringBuilder msg = new StringBuilder("MSG;");
		Boolean flag = db.executeSQL("INSERT INTO Users (fname, lname, login, password, admin) VALUES ('"+
		p[1]+"','"+p[2]+"','"+p[3]+"','"+p[4]+"',"+p[5]+");"); //Cria um novo usuário
		if(flag) {
			msg.append("\"User created with success\":");
			System.out.println("User created with success");
		}
		else {
			msg.append("\"Unable to register user\":");
			System.out.println("Unable to register user");
		}
		return msg;
	}
}
