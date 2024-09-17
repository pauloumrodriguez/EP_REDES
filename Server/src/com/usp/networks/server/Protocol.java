package com.usp.networks.server;

import java.util.HashMap;

public class Protocol {
	
	private HashMap<String, Type> mappingType;
	private HashMap<String, Entity> mappingEntity;
	
	public Protocol() {
		mappingType = new HashMap<>();
		mappingEntity = new HashMap<>();
		initMappingType();
		initMappingEntity();
	}
	
	private void initMappingType() {
		mappingType.put("LOGIN", Type.R);
		mappingType.put("CREATE", Type.S);
		mappingType.put("UPDATE", Type.S);
		mappingType.put("DELETE", Type.S);
		mappingType.put("MSG", Type.R);
		mappingType.put("XY", Type.R);
		mappingType.put("LIST", Type.S);
		mappingType.put("SEND", Type.R);
		mappingType.put("KEEP", Type.R);
		mappingType.put("PACK", Type.R);
	}
	
	private void initMappingEntity() {
		mappingEntity.put("ZONE", Entity.ZONE);
		mappingEntity.put("USER", Entity.USER);
		mappingEntity.put("ASS", Entity.ASS);
	}
	
	public String[] decodingMSG(String msg) {
		String[] rs = msg.split("[;:]");
		return rs;
	}
	
	public Type mapType(String cmd) {
		return mappingType.get(cmd);
	}
	
	public Entity mapEntity(String entity) {
		return mappingEntity.get(entity);
	}
	
	public Zone getZone(int id, double x, double y, double radius) {
		return new Zone(id, x, y, radius);
	}
	
	public User getUser(int id, String fname, String lname, String login, String password, Boolean admin) {
		return new User(id, fname, lname, login, password, admin);
	}
	
	public XYUser getXYUser(int id, double x, double y) {
		return new XYUser(id, x, y);
	}
	
}
