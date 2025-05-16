package com.usp.networks.client;

import com.usp.networks.screens.*;

public class Client { //Responsável por conter a tela inicial do Cliente
	private Screen xy;
	private static Client obj;
	
	private Client() {
		xy = new XYScreen(); //Tela inicial de Cliente
	}
	
	public static Client getInstance() { //Design Patterns Singleton
		if(obj == null) {
			obj = new Client();
		}
		return obj;
	}
	
	public void show() { //Mostra a tela
		xy.showScreen();
	}
}
