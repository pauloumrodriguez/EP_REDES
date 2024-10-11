package com.usp.networks.client;

import com.usp.networks.screens.*;

public class Client {
	private Screen xy;
	private static Client obj;
	
	private Client() {
		xy = new XYScreen();
	}
	
	public static Client getInstance() {
		if(obj == null) {
			obj = new Client();
		}
		return obj;
	}
	
	public void show() {
		xy.showScreen();
	}
}
