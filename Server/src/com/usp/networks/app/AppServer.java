package com.usp.networks.app;

import com.usp.networks.server.*;

public class AppServer {
	public static void main(String[] args) {
		Server server = Server.getServer();
		String msg = "XY;(id,x,y);1;50.5;76.4:";
		String[] rs = server.decoding(msg);
		String[] p = server.attributes(rs[1]);
		for(String n: p) {
			System.out.println(n);
		}
	}
}
