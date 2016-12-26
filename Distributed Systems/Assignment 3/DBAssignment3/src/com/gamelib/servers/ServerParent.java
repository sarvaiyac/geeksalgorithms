package com.gamelib.servers;

import java.io.IOException;


public abstract class ServerParent {

	/*
	 * It creates three UDP servers on diffferent ports
	 */
	public static void main (String[] args){
		UDPServer _udpServerNA = new UDPServer(1101, new ServerNA());
		_udpServerNA.start();
		
		UDPServer _udpServerEU = new UDPServer(1102,  new ServerEU());
		_udpServerEU.start();
		
		UDPServer _udpServerAS = new UDPServer(1103,  new ServerAS());
		_udpServerAS.start();
		
		System.out.println("UDP servers are running");
	}

	/*
	 * These are lists of necessary methods which every child of ServerParent class must implement
	 * These abstract methods are created by assuming these methods are final structure for system.
	 */
	
	// It gives getPlayerStatus of current region
	public abstract String getStatus();

	// Check if user is already registered or not
	public abstract boolean isUserRegistered(String username);
	
	// it creates player on local region
	public abstract String createPlayerAccount(String firstname, String lastname, int age,
			String username, String password, String ipaddress)  throws IOException;
	
	// it allows player to sign in onto local region
	public abstract String playerSignIn(String username, String password,
			String ipaddress) throws IOException;
	
	// it allows player to sign out from local region
	public abstract String playerSignOut(String username,
			String ipaddress) throws IOException;
	
	// it allows administrator to get status of all the players in all over system ( region wise )
	public abstract String getPlayerStatus(String username,String password, String ipaddress);
	
	// allows player to suspend account
	public abstract String suspendAccount(String adminusername, String adminpassword,
			String adminipaddress, String usernametosuspend);
	
	// allows player to transfer account from current region to another region
	public abstract String transferAccount(String username, String password,
			String oldipaddress, String newipaddress) throws IOException;
}
