package com.usp.networks.server;

public class Server {
	
	private static Server obj;
	private Protocol protocol;
	
	private Server() {
		protocol = Protocol.getInstance();
	}
	
	public static Server getServer() { //Design Patterns Singleton
		if(obj == null) {
			obj = new Server();
		}
		return obj;
	}
	
	public StringBuilder execute(String msg){
		return protocol.execute(msg);
	}
}
