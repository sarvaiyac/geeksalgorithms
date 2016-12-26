package com.gamelib.server;

import java.net.*;
import java.io.*;

public class UDPServer {

	/**
	 * @param args
	 */
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DatagramSocket aSocket = null;
		try {
			
			aSocket = new DatagramSocket(6789);
			byte[] buffer = new byte[10000];
			
			while(true){
				DatagramPacket request = new DatagramPacket(buffer,buffer.length);
				aSocket.receive(request);
				
				DatagramPacket reply = new DatagramPacket(request.getData(), request.getLength(), request.getAddress(), request.getPort());
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
