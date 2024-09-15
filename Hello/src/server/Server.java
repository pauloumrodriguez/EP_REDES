package server;

import java.sql.*;
import java.util.HashMap;
public class Server {
	
	private static Server obj;
	private HashMap<String, Cmd> table;
	
	private Server() {
		table = new HashMap<>();
		settingsTable();
	}
	
	public static Server getServer() { //Design Patterns Singleton
		if(obj == null) {
			obj = new Server();
		}
		return obj;
	}
	
	private void settingsTable() {
		table.put("SELECT", Cmd.SELECT);
		table.put("CREATE", Cmd.CREATE);
		table.put("SEND", Cmd.SEND);
		table.put("UPDATE", Cmd.UPDATE);
	}
	
	private Connection connect() throws SQLException {
		String url = "jdbc:sqlite:src/server/myDB.db";
		return DriverManager.getConnection(url);
	}
	
	public ResultSet executeQuery(String sql) throws SQLException {
		Connection conn = connect();
		Statement stmt = conn.createStatement();
		return stmt.executeQuery(sql);
	}
	
	public boolean checkZone(User u, Zone z) {
		double a = Math.pow((u.getX() - z.getX()), 2);
		double b = Math.pow((u.getY() - z.getY()), 2);
		return a + b <= Math.pow(z.getRadius(), 2);
	}
}
