package com.usp.networks.client;

import com.usp.networks.screens.*;

public class Admin {
	private Screen admin;
	private static Admin obj;
	private Admin() {
		admin = new AdmScreen();
	}
	
	public void show() {
		admin.showScreen();
	}
	
	public static Admin getInstance() {
		if (obj == null) {
			obj = new Admin();
		}
		return obj;
	}
	
}