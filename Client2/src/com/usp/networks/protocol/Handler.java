package com.usp.networks.protocol;

import java.util.List;

public interface Handler { //Implementa o Design Patterns Strategy
	public List<StringBuilder> execute(String[] p); //Executa a mensagem recebida do servidor
}
