package com.usp.networks.items;

import java.sql.SQLException;

public class UpdatePasswordHandler implements Handler {
	public StringBuilder execute(String[] p) {
		Database db = Database.getDB(); //Abre a conexão com o banco de dados
		StringBuilder msg = new StringBuilder("MSG;");
		ResultConnection rs = db.executeSelect("SELECT * FROM Users WHERE login = '" + p[1] + "';"); //Pesquisa pelo usuário
		try {
			while(rs.getResultSet().next()) {
				String login = rs.getResultSet().getString("login");
				if(login.compareTo("admin@usp.br") == 0) {//Não permite atualizar o admin padrão
					msg.append("\"cannot update standard user\":");
					System.out.println("Cannot update standard user");
					return msg;
				}
				else {
					if(p[2].equals(p[3])) { //Verifica se os campos "senha" e "confirmar senha" são iguais
						//Atualiza o usuário
						Boolean flag = db.executeSQL("UPDATE Users SET password = '" + p[2] +"' WHERE id = " + rs.getResultSet().getInt("id") +";");
						if(flag) {
							msg.append("\"Updated with success\":");
							System.out.println("Updated with success");
							return msg;
						}
						msg.append("\"Unable updated\":");
						System.out.println("Unable updated");
						return msg;
					}
					else {
						msg.append("\"This password is not correct\":"); //As senhas passadas são distintas
						System.out.println("This password is not correct");
						return msg;
					}
				}
			}
			msg.append("\"User not found\":"); //Usuário não encontrado
			System.out.println("User not found");
			return msg;
		}
		catch(SQLException e) {
			StringBuilder msgException = new StringBuilder("MSG;");
	    	msgException.append("\"Database error\":");
	    	System.out.println("Error in Database");
	    	return msgException;
		}
	}
}
