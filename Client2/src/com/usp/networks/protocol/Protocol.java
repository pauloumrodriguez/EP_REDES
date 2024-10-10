package com.usp.networks.protocol;

import java.util.HashMap;
import java.util.List;

public class Protocol {
	private HashMap<String, Handler> cmds;
	private static Protocol obj;
	
	private Protocol() {
		cmds = new HashMap<>();
		initMap();
	}
	
	private void initMap() {
		cmds.put("MSG", new MessageHandler());
		cmds.put("PACK", new PackHandler());
		cmds.put("NOTIFY", new NotifyHandler());
	}
	
	public static Protocol getInstance(){
		if(obj == null) {
			obj = new Protocol();
		}
		return obj;
	}
	
	public List<StringBuilder> execute(String cmd) {
		cleanString(cmd);
		String[] rs = decodingMSG(cmd);
		return cmds.get(rs[0]).execute(rs);
	}
	
	private String[] decodingMSG(String msg) {
		String[] rs = msg.split("[;:]");
		return rs;
	}
	
	private String cleanString(String msg) {
		String rs = msg.replace("\"", "").replace("\n", "");
		return rs;
	}
}
