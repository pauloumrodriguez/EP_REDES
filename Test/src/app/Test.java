package app;

import protocol.*;
import java.net.*;
import java.io.*;
import java.util.List;

public class Test {
	public static void main(String[] args) {
		String ipHost = "26.163.176.8";
		Protocol p = Protocol.getInstance();
		try(Socket client = new Socket(ipHost, 12345)){
			PrintWriter out = new PrintWriter(client.getOutputStream(), true);
//			String msg = "SEND;\"3\";\"dilma_rousseff@usp.br\";\"Estocar vento!\":";
			String msg = "LIST-USER;\":";
			out.println(msg);
			BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			String response = in.readLine();
			List<StringBuilder> list = p.execute(response);
			for(StringBuilder value: list) {
				System.out.println(value);
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
	}
}
