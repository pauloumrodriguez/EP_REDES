package com.usp.networks.server;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import com.usp.networks.items.*;

public class Protocol {
	
	private HashMap<String, Type> mappingType;
	private HashMap<String, HandlerR> executeR;
	private HashMap<String, HandlerS> executeS;
	private static Protocol obj;
	
	private Protocol() {
		mappingType = new HashMap<>();
		initMappingType();
		initExecutingR();
		initExecutingS();
	}
	
	public static Protocol getInstance() {
		if(obj == null) {
			obj = new Protocol();
		}
		return obj;
	}
	
	private void initMappingType() {
		mappingType.put("LOGIN", Type.R);
		mappingType.put("CREATE-USER", Type.R);
		mappingType.put("CREATE-ZONE", Type.R);
		mappingType.put("CREATE-ASS", Type.R);
		mappingType.put("UPDATE-USER", Type.R);
		mappingType.put("UPDATE-ZONE", Type.R);
		mappingType.put("DELETE-USER", Type.R);
		mappingType.put("DELETE-ZONE", Type.R);
		mappingType.put("DELETE-ASS", Type.R);
		mappingType.put("XY", Type.S);
		mappingType.put("LIST-USER", Type.R);
		mappingType.put("LIST-ZONE", Type.R);
		mappingType.put("LIST-NOTIFY", Type.R);
		mappingType.put("SEND", Type.R);
	}
	
	private void initExecutingR() {
		executeR.put("LOGIN", new LoginHadler());
		executeR.put("CREATE-USER", new CreateUserHandler());
		executeR.put("CREATE-ZONE", new CreateZoneHandler());
		executeR.put("CREATE-ASS", new CreateAssHandler());
		executeR.put("UPDATE-USER", new UpdateUserHandler());
		executeR.put("UPDATE-ZONE", new UpdateZoneHandler());
		executeR.put("DELETE-USER", new DeleteUserHandler());
		executeR.put("DELETE-ZONE", new DeleteZoneHandler());
		executeR.put("DELETE-ASS", new DeleteAssHandler());
		executeR.put("LIST-USER", new ListUserHandler());
		executeR.put("LIST-ZONE", new ListZoneHandler());
		executeR.put("LIST-NOTIFY", new ListNotifyHandler());
		executeR.put("SEND", new SendHandler());
	}
	
	private void initExecutingS() {
		executeS.put("XY", new XYHandler());
	}
	
	private String[] decodingMSG(String msg) {
		String[] rs = msg.split("[;:]");
		return rs;
	}
	
	private String cleanString(String msg) {
		String rs = msg.replace("\"", "");
		return rs;
	}
	
	private Type getType(String cmd) {
		return mappingType.get(cmd);
	}
	
	public List<StringBuilder> execute(String msg){
		String[] dcd = decodingMSG(msg);
		List<StringBuilder> list = new ArrayList<>();
		for(int i = 0; i < dcd.length; i++) {
			dcd[i] = cleanString(dcd[i]);
		}
		Type typeCmd = getType(dcd[0]);
		if(typeCmd == Type.S) {
			HandlerS obj = executeS.get(dcd[0]);
			return obj.execute(dcd);
		}
		HandlerR obj = executeR.get(dcd[0]);
		list.add(obj.execute(dcd));
		return list;
		
	}
}
