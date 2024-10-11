package com.usp.networks.items;

import java.sql.*;

public class XYHandler implements Handler{
	public StringBuilder execute(String[] p) {
		Database db = Database.getDB(); //Abre conexão com o banco de dados
		ResultConnection rs = db.executeSelect("SELECT * FROM Users WHERE login = '" + p[1] +"';"); //Pesquisa pelo usuário
		ResultConnection rsT;
		StringBuilder msg = new StringBuilder("MSG;");
		try{
			while(rs.getResultSet().next()) {
				rsT = db.executeSelect("SELECT * FROM UserZoneMapping WHERE user_id = " + rs.getResultSet().getInt("id") +";"); //Pesquisa as associações do usuário
				
				while(rsT.getResultSet().next()) { 
					ResultConnection rt = db.executeSelect("SELECT * FROM Zone WHERE id = " + rsT.getResultSet().getString("zone_id") //Pesquisa as zonas associadas ao usuário
					+ ";");
					while(rt.getResultSet().next()) {
						double xZ = rt.getResultSet().getDouble("x");
						double yZ = rt.getResultSet().getDouble("y");
						double radiusZ = rt.getResultSet().getDouble("radius");
						double a = Math.pow((Double.parseDouble(p[2]) - xZ), 2);
						double b = Math.pow((Double.parseDouble(p[3]) - yZ), 2);
						if(a + b <= Math.pow(radiusZ, 2)) { //Verifica se o usuário entrou na zona
							System.out.println("User " + p[1] +" invaded non-permitted zone " + rt.getResultSet().getString("id"));
							db.executeSQL("INSERT INTO Notifications (user_id, sender, message) VALUES ('"+
									p[1] + "', 'admin@usp.br', 'Invaded a non-permitted zone(" + rt.getResultSet().getInt("id") + ")');"); //Cria notificação de invasão de zona
						}
					}
					rt.close();
				}
				rs.close();
				rsT.close();
				msg.append("\"Successfully verified\":");
				System.out.println("Successfully verified");
			}
			
		}
		catch(SQLException e) {
			StringBuilder msgException = new StringBuilder("MSG;");
			msgException.append("\"Unable to verify\":");
			return msgException;
		}
		
		return msg;
		
	}
}
