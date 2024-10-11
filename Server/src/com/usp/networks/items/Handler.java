package com.usp.networks.items;

public interface Handler { //Interface para implementar o Design Patterns Strategy
	public StringBuilder execute(String[] p); // executa a ação apropriada de acordo com a mensagem
}
