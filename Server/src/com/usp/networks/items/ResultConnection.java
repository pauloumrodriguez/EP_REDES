package com.usp.networks.items;

import java.sql.*;

public class ResultConnection {
	 private ResultSet resultSet;
	 private Statement statement;
	 private Connection connection;
	 
	 public ResultConnection(ResultSet rs, Statement stmt, Connection conn) { //Objeto intermediário para os comandos SELECT
		 //Agrupa os items para permitir o fechamento da conexão com o banco de dados
		 this.resultSet = rs;
		 this.statement = stmt;
		 this.connection = conn;
	 }
	 
	 public ResultSet getResultSet() {
	      return resultSet;
	 }

	 public Statement getStatement() {
	      return statement;
	 }

	 public Connection getConnection() {
	      return connection;
	 }

	 public void close() {
	      try {
	         if (resultSet != null) {
	             resultSet.close();
	         }
	         if (statement != null) {
	             statement.close();
	         }
	         if (connection != null) {
	              connection.close();
	         }
	     } catch (SQLException e) {
	          System.out.println("Error closing resources: " + e.getMessage());
	      }
	 }
}
