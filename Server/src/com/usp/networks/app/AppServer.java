package com.usp.networks.app;

import java.io.*;
import java.net.*;

public class AppServer {
	//Classe principal do Servidor
	public static void main(String[] args) {
		try(ServerSocket serverSocket = new ServerSocket(12345)){ //Abre Socket TCP do Servidor pela porta "12345"
			System.out.println("Server started and waiting for connections...");
			while(true) {
				Socket clientSocket = serverSocket.accept(); //Aceita conexão com cliente
				System.out.println("Connected Client: " + clientSocket.getInetAddress().getHostAddress()); //Extrai o endereço IP do cliente
				ThreadClient client = new ThreadClient(clientSocket);
				Thread thread = new Thread(client);
				thread.start(); //Inicia o tratamento da requisição via Thread
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
	}
}
