package com.usp.networks.items;

import java.sql.*;

public class DeleteUserHandler implements Handler{
	public StringBuilder execute(String[] p) {
		Database db = Database.getDB();//Abre conexão com o banco de dados
		StringBuilder msg = new StringBuilder("MSG;");
		ResultConnection rs = db.executeSelect("SELECT * FROM Users WHERE id = '" + p[1] + "';"); //Busca o usuário
		try {
			while(rs.getResultSet().next()) {
				String login = rs.getResultSet().getString("login");
				if(login.compareTo("admin@usp.br") == 0) { //Não permite excluir o usuário padrão
					msg.append("\"cannot delete standard user\":");
					System.out.println("Cannot delete standard user");
					return msg;
				}
			}
		}
		catch(SQLException e) {
			StringBuilder msgException = new StringBuilder("MSG;");
	    	msgException.append("\"Database error\":");
	    	System.out.println("Error in Database");
	    	return msgException;
		}
		Boolean flag = db.executeSQL("DELETE FROM Users WHERE id = " + p[1] + ";"); //Deleta o usuário
		if(flag) {
			msg.append("\"User deleted with success\":");
			System.out.println("User deleted with success");
		}
		else {
			msg.append("\"Unable to delete user\":");
			System.out.println("Unable to delete user");
		}
		return msg;
	}
}
