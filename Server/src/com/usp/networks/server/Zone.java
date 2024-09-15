package com.usp.networks.server;

public class Zone {
	private double x;
	private double y;
	private double radius;
	
	public Zone(double x, double y, double radius) {
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
}
