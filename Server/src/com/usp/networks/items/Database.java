package com.usp.networks.items;

import java.sql.*;

public class Database {
	private String url;
	private static Database db;
	
	private Database() {
		url = "jdbc:sqlite:src/server/myDB.db";
	}
	
	public static Database getDB() {
		if(db == null) {
			db = new Database();
		}
		
		return db;
	}
	
	private Connection connect() throws SQLException{
		return DriverManager.getConnection(url);
	}
	
	public void executeSQL(String sql){
		
		try {
			Connection conn = connect();
			Statement stmt = conn.createStatement();
			stmt.execute(sql);
			stmt.close();
			conn.close();
		}
		catch(SQLException e) {
			System.out.println("Error in SQL: " + e.getMessage());
		}
		
	}
	
	public ResultSet executeSelect(String sql) {
		ResultSet rs = null;
		try {
			Connection conn = connect();
			Statement stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			stmt.close();
			conn.close();
		}
		catch(SQLException e) {
			System.out.println("Error in SQL: " + e.getMessage());
		}
		
		return rs;
		
	}
}
