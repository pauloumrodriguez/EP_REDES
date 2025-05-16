package com.usp.networks.items;

import java.sql.*;

public class LoginHadler implements Handler{
	public StringBuilder execute(String[] p) {
		Database db = Database.getDB(); //Abre conexão com banco de dados
		ResultConnection rs = db.executeSelect("SELECT * FROM Users WHERE login = '" + p[1] + "';"); //Pesquisa pelo usuário
		StringBuilder msg = new StringBuilder("MSG;");
		try{
			if(!rs.getResultSet().next()) { //Usuário não encontrado
				msg.append("\"User not found\":");
				System.out.println("User not found");
			}
			else {
				String password = rs.getResultSet().getString("password");
				if(password.compareTo(p[2]) == 0) { //Verifica se a senha está correta
					msg.append("\"Connect with success\"|" + rs.getResultSet().getBoolean("admin") + "|" + rs.getResultSet().getInt("id") + "|:");
					System.out.println("Connect with success"); //Permite a conexão
				}
				else {
					msg.append("\"Incorrect password\":"); //Senha incorreta
					System.out.println("Incorrect password");
				}
			}
			rs.close();
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
