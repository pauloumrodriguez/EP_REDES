package com.usp.networks.app;

import java.io.*;
import java.net.*;

import com.usp.networks.server.Server;
import java.util.List;

public class ThreadClient implements Runnable{
	private Server server;
	private Socket client;
	
	public ThreadClient(Socket client) {
		this.client = client;
		server = Server.getServer();
	}
	
	@Override
	public void run() {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			PrintWriter out = new PrintWriter(client.getOutputStream(), true);
			String msg;
			List<StringBuilder> list;
			while((msg = in.readLine()) != null) {
				list = server.execute(msg);
				for(StringBuilder str: list) {
					out.print(str);
				}
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				client.close();
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
}
