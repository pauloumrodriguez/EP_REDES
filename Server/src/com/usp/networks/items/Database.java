package com.usp.networks.items;

import java.sql.*;

public class Database {
	private String url;
	private static Database db;
	
	private Database() {
		url = "jdbc:sqlite:src/com/usp/networks/server/myDB.db"; //Banco de dados SQLite3
	}
	
	public static Database getDB() { //Design Patterns Singleton
		if(db == null) {
			db = new Database();
		}
		
		return db;
	}
	
	private Connection connect() throws SQLException{
		return DriverManager.getConnection(url); //Cria uma conexão com banco de dados
	}
	
	public synchronized Boolean executeSQL(String sql){ //executa comandos sql diferentes de SELECT
		//apenas uma thread executa por vez esse método
		boolean flag = false;
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = connect();
			stmt = conn.createStatement();
			stmt.execute("PRAGMA journal_mode=WAL;"); //Garante a exclusão mútua das threads
			stmt.execute("PRAGMA foreign_keys = ON;"); //Habilita a restrição de chave estrangeira
            System.out.println("Modo WAL ativado.");
			stmt.execute(sql); //executa o comando SQL
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
	
	public ResultConnection executeSelect(String sql) { //executa SELECT
		ResultSet rs = null;
		Connection conn = null;
		Statement stmt = null;
		ResultConnection resultSet = null;
		try {
			conn = connect();
			stmt = conn.createStatement();
			stmt.execute("PRAGMA journal_mode=WAL;"); //Garante a exclusão mútua das threads
			stmt.execute("PRAGMA foreign_keys = ON;"); //Habilita a restrição de chave estrangeira
            System.out.println("Modo WAL ativado.");
			rs = stmt.executeQuery(sql); //executa select
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
