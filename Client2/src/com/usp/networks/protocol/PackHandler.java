package com.usp.networks.protocol;

import java.util.List;
import java.util.ArrayList;

public class PackHandler implements Handler{ //Decodifica a lista recebida
	public List<StringBuilder> execute(String[] p){
		List<StringBuilder> list = new ArrayList<>();
		for(int i = 1; i < p.length; i++) {
			StringBuilder msg = new StringBuilder(p[i]); //Armazena o registro
			list.add(msg);
		}
		
		return list;
	}
}
