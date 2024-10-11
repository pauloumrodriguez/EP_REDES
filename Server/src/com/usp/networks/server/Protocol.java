package com.usp.networks.server;

import java.util.HashMap;
import com.usp.networks.items.*;

public class Protocol { //Classe responsável pela implementação do protocolo da aplicação
	
	private HashMap<String, Handler> execute; //Mapeia os comandos do protocolo para as ações
	private static Protocol obj;
	
	private Protocol() {
		execute = new HashMap<>();
		initExecuting();
	}
	
	public static Protocol getInstance() { //Design Patterns Singleton
		if(obj == null) {
			obj = new Protocol();
		}
		return obj;
	}
	
	private void initExecuting() {
		execute.put("LOGIN", new LoginHadler());
		execute.put("CREATE-USER", new CreateUserHandler());
		execute.put("CREATE-ZONE", new CreateZoneHandler());
		execute.put("CREATE-ASS", new CreateAssHandler());
		execute.put("UPDATE-USER", new UpdateUserHandler());
		execute.put("UPDATE-ZONE", new UpdateZoneHandler());
		execute.put("DELETE-USER", new DeleteUserHandler());
		execute.put("DELETE-ZONE", new DeleteZoneHandler());
		execute.put("DELETE-ASS", new DeleteAssHandler());
		execute.put("LIST-USER", new ListUserHandler());
		execute.put("LIST-ZONE", new ListZoneHandler());
		execute.put("LIST-NOTIFY", new ListNotifyHandler());
		execute.put("SEND", new SendHandler());
		execute.put("XY", new XYHandler());
		execute.put("UPDATE-PASSWORD", new UpdatePasswordHandler());
		execute.put("DELETE-NOTIFY", new DeleteNotifyHandler());
	}
		
	private String[] decodingMSG(String msg) {
		String[] rs = msg.split("[;:]"); //Decodifica os campos
		return rs;
	}
	
	private String cleanString(String msg) {
		String rs = msg.replace("\"", "").replace("\n", ""); //Limpa os campos
		return rs;
	}
	
	public StringBuilder execute(String msg){
		String[] dcd = decodingMSG(msg);
		for(int i = 0; i < dcd.length; i++) {
			dcd[i] = cleanString(dcd[i]);
		}
		
		if(execute.get(dcd[0]) == null) {
			StringBuilder msgException = new StringBuilder("MSG;\"I don't know this command\":"); //Não conhece o comando
			return msgException;
		}
		return execute.get(dcd[0]).execute(dcd); //Executa a requisição
		
	}
}
