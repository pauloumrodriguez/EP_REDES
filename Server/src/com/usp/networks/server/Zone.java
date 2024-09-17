package com.usp.networks.server;

public class Zone {
	private int id;
	private double x;
	private double y;
	private double radius;
	
	public Zone(int id, double x, double y, double radius) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.radius = radius;
	}
	
	public double getX() {
		return this.x;
	}
	
	public double getY() {
		return this.y;
	}
	
	public double getRadius() {
		return this.radius;
	}
	
	public int getID() {
		return this.id;
	}
}
