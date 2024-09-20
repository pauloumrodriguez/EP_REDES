package com.usp.networks.server;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.LinkedList;
public class Server {
	
	private static Server obj;
	private Protocol protocol;
	private HashMap<String, Cmd> table;
	
	private Server() {
		table = new HashMap<>();
		settingsTable();
		protocol = Protocol.getInstance();
	}
	
	public static Server getServer() { //Design Patterns Singleton
		if(obj == null) {
			obj = new Server();
		}
		return obj;
	}
	
	public String[] decoding(String msg) {
		return protocol.decodingMSG(msg);
	}
	
	public String[] attributes(String attr) {
		return protocol.attributeQuery(attr);
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
	
	public List<Zone> getListZone(Zone zone) throws SQLException{
		ResultSet rs = executeQuery("SELECT * FROM Zone;");
		List<Zone> list = new LinkedList<>();
		while(rs.next()) {
			int id = rs.getInt("id");
			double x = rs.getDouble("x");
			double y = rs.getDouble("y");
			double radius = rs.getDouble("radius");
			Zone z = new Zone(id, x, y, radius);
			list.add(z);
		}
		
		return list;
	}
	
	private Statement execute() throws SQLException{
		Connection conn = connect();
		Statement stmt = conn.createStatement();
		return stmt;
	}
	
	public void createUser(User u) throws SQLException{
		Statement stmt = execute();
		int rows = stmt.executeUpdate("INSERT INTO Users (fname, lname,"
				+ " login, password, admin) VALUES (" + u.getFName() + ", " + u.getLName() + ", " + u.getLogin() +
				", " + u.getPassword() + ", " + u.getAdmin() + ");" );
		if(rows > 0) System.out.println("inserido com sucesso!");
		stmt.close();
	}
	
	public void createZone(Zone z) throws SQLException{
		Statement stmt = execute();
		int rows = stmt.executeUpdate("INSERT INTO Zone (x, y, radius) VALUES ( " + z.getX() +
				", " + z.getY() + ", " + z.getRadius() + ");");
		if(rows > 0) System.out.println("Inserido com sucesso!");
		stmt.close();
	}
		
	public boolean checkZone(XYUser u, Zone z) {
		double a = Math.pow((u.getX() - z.getX()), 2);
		double b = Math.pow((u.getY() - z.getY()), 2);
		return a + b <= Math.pow(z.getRadius(), 2);
	}
}
