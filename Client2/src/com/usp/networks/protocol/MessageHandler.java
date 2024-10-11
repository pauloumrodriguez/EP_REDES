package com.usp.networks.protocol;

import java.util.List;
import java.util.ArrayList;

public class MessageHandler implements Handler { //Decodifica a resposta do servidor
	public List<StringBuilder> execute(String[] p) {
		List<StringBuilder> list = new ArrayList<>();
		StringBuilder msg = new StringBuilder(p[1]); //Conteúdo da resposta
		list.add(msg);
		return list;
	}
}
