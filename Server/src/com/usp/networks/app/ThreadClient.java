package com.usp.networks.app;

import java.io.*;
import java.net.*;
import com.usp.networks.server.Server;

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
			while((msg = in.readLine()) != null) {
				out.println(server.execute(msg));
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
