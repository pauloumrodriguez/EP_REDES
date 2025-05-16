package com.usp.networks.protocol;

import java.util.HashMap;
import java.util.List;

public class Protocol {
	private HashMap<String, Handler> cmds; //Mapeia os comandos para as ações
	private static Protocol obj;
	
	private Protocol() { //Protocolo da aplicação utilizada pelo cliente
		cmds = new HashMap<>();
		initMap();
	}
	
	private void initMap() { //Mensagens que um cliente pode receber
		cmds.put("MSG", new MessageHandler());
		cmds.put("PACK", new PackHandler());
	}
	
	public static Protocol getInstance(){ //Design Patterns Singleton
		if(obj == null) {
			obj = new Protocol();
		}
		return obj;
	}
	
	public List<StringBuilder> execute(String cmd) { //Executa o comando
		cleanString(cmd);
		String[] rs = decodingMSG(cmd);
		return cmds.get(rs[0]).execute(rs);
	}
	
	private String[] decodingMSG(String msg) { //Decodifica os campos
		String[] rs = msg.split("[;:]");
		return rs;
	}
	
	private String cleanString(String msg) { //Limpa os campos
		String rs = msg.replace("\"", "").replace("\n", "");
		return rs;
	}
}
