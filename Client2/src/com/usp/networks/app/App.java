package com.usp.networks.app;

import com.usp.networks.screens.*;

public class App {
	public static void main(String[] args) { //Classe que contém o void main
		Screen login = new Login();
		login.showScreen(); //Invoca a página de login
	}
}
