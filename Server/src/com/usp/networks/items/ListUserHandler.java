package com.usp.networks.items;

import java.sql.*;
import com.usp.networks.server.User;

public class ListUserHandler implements Handler{
	public StringBuilder execute(String[] p) {
		Database db = Database.getDB();//abre conexão com banco de dados
		StringBuilder msg = new StringBuilder("PACK;");
		ResultConnection rs = db.executeSelect("SELECT * FROM Users WHERE login != 'admin@usp.br';"); //Gera a lista de usuários
		try {
			while(rs.getResultSet().next()) {
				User u = new User(rs.getResultSet().getInt("id"), rs.getResultSet().getString("fname"),
						rs.getResultSet().getString("lname"), rs.getResultSet().getString("login"), rs.getResultSet().getString("password"),
						rs.getResultSet().getBoolean("admin"));
				String a = "\"" + u.getID() + "," + u.getFName() + "," + u.getLName() + "," +
						u.getLogin() + "," + u.getPassword() + "," + u.getAdmin() + "\""; //Gera o registro do usuário e insere na lista
				msg.append(a + ";");
			}
			msg.append(":"); //Encerra a lista
			rs.close();
			System.out.println("user list successfully generated");
		}
		catch(SQLException e) {
			StringBuilder msgException = new StringBuilder("MSG;");
			msgException.append("\"Database error\":");
			System.out.println("Error in Database");
			return msgException;
		}
		return msg;
	}
}
