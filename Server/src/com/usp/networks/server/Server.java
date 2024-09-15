package com.usp.networks.server;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.LinkedList;
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
	
	private ResultSet executeQuery(String sql) throws SQLException {
		Connection conn = connect();
		Statement stmt = conn.createStatement();
		return stmt.executeQuery(sql);
	}
	
	public List<User> getListUser(User owner) throws SQLException{
		ResultSet rs = executeQuery("SELECT * FROM Users WHERE login != " + owner.getLogin() + ";");
		List<User> list = new LinkedList<>();
		while(rs.next()) {
			int id = rs.getInt("id");
			String fname = rs.getString("fname");
			String lname = rs.getString("lname");
			String login = rs.getString("login");
			String password = rs.getString("password");
			Boolean admin = rs.getBoolean("admin");
			User u = new User(id, fname, lname, login, password, admin);
			list.add(u);
		}
		
		return list;
	}
		
	public boolean checkZone(XYUser u, Zone z) {
		double a = Math.pow((u.getX() - z.getX()), 2);
		double b = Math.pow((u.getY() - z.getY()), 2);
		return a + b <= Math.pow(z.getRadius(), 2);
	}
}
