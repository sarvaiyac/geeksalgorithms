package com.gamelib.servers;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPServer extends Thread {

	final String serverNA_filename = "/Users/chintansarvaiya/Documents/Workspace_kepler/DBAssignment3/server-NA-data.txt";
	final String serverEU_filename = "/Users/chintansarvaiya/Documents/Workspace_kepler/DBAssignment3/server-EU-data.txt";
	final String serverAS_filename = "/Users/chintansarvaiya/Documents/Workspace_kepler/DBAssignment3/server-AS-data.txt";

	int port;
	ServerParent serverXX;

	public UDPServer(int port, ServerParent server) {
		// TODO Auto-generated constructor stub
		this.port = port;
		this.serverXX = server;
	}

	/**
	 * @param args
	 */

	@Override
	public void run() {
		// TODO Auto-generated method stub
		DatagramSocket aSocket = null;
		try {

			aSocket = new DatagramSocket(this.port);

			while (true) {
				byte[] buffer = new byte[10000];
				DatagramPacket request = new DatagramPacket(buffer,
						buffer.length);
				aSocket.receive(request);

				String requestedString = new String(request.getData());// buffer,
																		// 0,
																		// request.getLength());

				if (requestedString.contains("getplayerstatus")) {
					buffer = serverXX.getStatus().getBytes();
				} else {
					String _concatedClient = requestedString;

					String[] _arrClient = _concatedClient.split("-");

					buffer = _concatedClient.getBytes();
					
					String reply = serverXX.createPlayerAccount(_arrClient[0],
							_arrClient[1], Integer.parseInt(_arrClient[2]),
							_arrClient[3], _arrClient[4], _arrClient[5]);

					buffer = "User transferred".getBytes();

					if (reply.equalsIgnoreCase("Username already registered")) { // you
																					// can
																					// transfer
						buffer = "Can't transfer because some user with same username"
								.getBytes();
					}
					
				}

				DatagramPacket reply = new DatagramPacket(buffer,
						buffer.length, request.getAddress(), request.getPort());
				aSocket.send(reply);
			}
		} catch (SocketException e) {
			// TODO: handle exception\
			System.out.println("Error in SocketException : " + e.getMessage());
		} catch (IOException e) {
			// TODO: handle exception
			System.out.println("Error in IOException : " + e.getMessage());
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error in Exception : " + e.getMessage());
		}

	}

}
