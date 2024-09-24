package com.usp.networks.items;

public class SendHandler implements HandlerR {
	public StringBuilder execute(String[] p) {
		StringBuilder msg = new StringBuilder("SEND;");
		String a = p[1] + "," + p[2] + "," + p[3];
		msg.append(String.join(";", a));
		return msg;
	}
}
