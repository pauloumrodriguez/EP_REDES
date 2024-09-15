package server;

import java.sql.*;

public class AppServer {
	public static void main(String[] args) {
		Server server = Server.getServer();
		try {
			String query = "SELECT * FROM Users;";
			ResultSet rs = server.executeQuery(query);
			while(rs.next()) {
				String name = rs.getString("fname");
				String login = rs.getString("login");
				System.out.println("name = " + name + "| login = " + login);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
