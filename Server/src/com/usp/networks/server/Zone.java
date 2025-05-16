package com.usp.networks.server;

public class Zone { //Representa a entidade zona
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
	
	public double getX() { //Devolve a coordenada X do ponto central
		return this.x;
	}
	
	public double getY() { //Devolve a coordenada Y do ponto central
		return this.y;
	}
	
	public double getRadius() { //Devolve o raio da zona
		return this.radius;
	}
	
	public int getID() { //Devolve o ID
		return this.id;
	}
}
