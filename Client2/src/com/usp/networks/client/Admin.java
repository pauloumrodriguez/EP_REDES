package com.usp.networks.client;

import com.usp.networks.screens.*;

public class Admin { //Responsável por conter a tela inicial do Admin
	private Screen admin;
	private static Admin obj;
	private Admin() {
		admin = new AdmScreen(); //Tela inicial do Admin
	}
	
	public void show() { //Mostra tela
		admin.showScreen();
	}
	
	public static Admin getInstance() { //Design Patterns Singleton
		if (obj == null) {
			obj = new Admin();
		}
		return obj;
	}
	
}