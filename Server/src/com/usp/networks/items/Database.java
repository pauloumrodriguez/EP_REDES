package com.usp.networks.items;

import java.sql.*;

public class Database {
	private String url;
	private static Database db;
	
	private Database() {
		url = "jdbc:sqlite:src/com/usp/networks/server/myDB.db";
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
	
	public synchronized Boolean executeSQL(String sql){
		boolean flag = false;
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = connect();
			stmt = conn.createStatement();
			stmt.execute("PRAGMA journal_mode=WAL;");
			stmt.execute("PRAGMA foreign_keys = ON;");
            System.out.println("Modo WAL ativado.");
			stmt.execute(sql);
			stmt.close();
			conn.close();
			flag = true;
		}
		catch(SQLException e) {
			System.out.println("Error in SQL: " + e.getMessage());
		}
		finally {
	        try {
	            if (stmt != null) stmt.close();
	            if (conn != null) conn.close();
	        } catch (SQLException e) {
	            System.out.println("Error closing resources: " + e.getMessage());
	        }
	    }
		return flag;
	}
	
	public ResultConnection executeSelect(String sql) {
		ResultSet rs = null;
		Connection conn = null;
		Statement stmt = null;
		ResultConnection resultSet = null;
		try {
			conn = connect();
			stmt = conn.createStatement();
			stmt.execute("PRAGMA journal_mode=WAL;");
			stmt.execute("PRAGMA foreign_keys = ON;");
            System.out.println("Modo WAL ativado.");
			rs = stmt.executeQuery(sql);
			resultSet = new ResultConnection(rs, stmt, conn);
		}
		catch(SQLException e) {
			System.out.println("Error in SQL: " + e.getMessage());
			try {
	            if (stmt != null) stmt.close();
	            if (conn != null) conn.close();
	        } catch (SQLException exception) {
	            System.out.println("Error closing resources: " + exception.getMessage());
	        }

		}
		
		return resultSet;
		
	}
}
