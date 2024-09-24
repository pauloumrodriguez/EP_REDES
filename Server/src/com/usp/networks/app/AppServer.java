package com.usp.networks.app;

import java.io.*;
import java.net.*;

public class AppServer {
	public static void main(String[] args) {
		try(ServerSocket serverSocket = new ServerSocket(12345)){
			System.out.println("Server started and waiting for connections...");
			while(true) {
				Socket clientSocket = serverSocket.accept();
				System.out.println("Connected Client: " + clientSocket.getInetAddress().getHostAddress());
				ThreadClient client = new ThreadClient(clientSocket);
				Thread thread = new Thread(client);
				thread.start();
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
	}
}
