package com.usp.networks.server;

public class XYUser {
	private int id;
	private double x;
	private double y;
	
	public XYUser(int id, double x, double y) {
		this.id = id;
		this.x = x;
		this.y = y;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public int getID() {
		return id;
	}
}
