package com.usp.networks.items;

import java.sql.*;
import com.usp.networks.server.Zone;

public class ListZoneHandler implements Handler {
	public StringBuilder execute(String[] p) {
		Database db = Database.getDB();//Abre a conexão com banco de dados
		StringBuilder msg = new StringBuilder("PACK;");
		ResultConnection rs = db.executeSelect("SELECT * FROM Zone;"); //Gera a lista de zonas
		try {
			while(rs.getResultSet().next()) {
				Zone z = new Zone(rs.getResultSet().getInt("id"), rs.getResultSet().getDouble("x"), rs.getResultSet().getDouble("y"), rs.getResultSet().getDouble("radius"));
				String a = "\"" + z.getID() + "," + z.getX() + "," + z.getY() + ","
						+ z.getRadius() + "\""; //Gera o registro da zona e insere na lista
				msg.append(a + ";");
			}
			rs.close();
			msg.append(":"); //Encerra a lista
			System.out.println("zone list successfully generated");
		}
		catch(SQLException e) {
			StringBuilder msgException = new StringBuilder("MSG;");
			msgException.append("\"Database error\":");
			return msgException;
		}
		return msg;
	}
}
