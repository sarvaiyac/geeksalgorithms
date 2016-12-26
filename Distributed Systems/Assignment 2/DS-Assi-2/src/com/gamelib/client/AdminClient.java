package com.gamelib.client;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import org.omg.CORBA.ORB;
import org.omg.CORBA.StringHolder;

import com.gamelib.helpers.MethodHelper;

import GameServerIdl.GameServerInterface;
import GameServerIdl.GameServerInterfaceHelper;

public class AdminClient {

	/**
	 * @param args
	 */
	
	GameServerInterface iServer_NA, iServer_EU, iServer_AS;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		AdminClient admClient = new AdminClient();
		admClient.initServers(args);
		
		Scanner in = new Scanner(System.in);
		try {
			
			String _val = "y";
			while (_val.equalsIgnoreCase("y")) {
				System.out.println("==== Choose any region ====\n1. NA\n2. EU\n3. AS");
				int i = in.nextInt();
				System.out.println("==== Choose any option ====");
				System.out.println("1. getStatus");
				System.out.println("2. suspendAccount");
				int choice = in.nextInt();
				if (choice==1) {
					admClient.getStatus(i);
				}else if (choice == 2){
					admClient.suspendAccount(i);
				}
				
				System.out.println("==== Want to continue ? (y/n)");
				_val = in.next();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	private void suspendAccount(int i) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		System.out.println("Enter Username :");
		String username = in.next();
		while (!username.equalsIgnoreCase("admin")) {
			System.out.println("Invalid Username. Enter Username:");
			username = in.next();
		}
		System.out.println("Enter Password :");
		String password = in.next();
		while (!password.equalsIgnoreCase("admin")) {
			System.out.println("Invalid password. Enter Password:");
			password = in.next();
		}
		
		System.out.println("Enter Username (between 6 to 15 characters) :");
		String usernametosuspend = in.next();
		while (!(usernametosuspend.length() >= 6 && usernametosuspend.length() <= 15)) {
			System.out
					.println("Username is not proper. Enter Username (between 6 to 15 characters) :");
			usernametosuspend = in.next();
		}
		
		String ipaddress = new MethodHelper().createIp(i);
		
		StringHolder reply = new StringHolder();
		if (i == 1) { //NA
			iServer_NA.suspendAccount(username, password, ipaddress, usernametosuspend, reply);
			reply.value = "NA :- " + reply.value;
		}else if (i == 2) { //EU
			iServer_EU.suspendAccount(username, password, ipaddress, usernametosuspend, reply);
			reply.value = "EU :- " + reply.value;
		}else if (i == 3) { //AS
			iServer_AS.suspendAccount(username, password, ipaddress, usernametosuspend, reply);
			reply.value = "AS :- " + reply.value;
		}
		System.out.println(reply.value);
	}

	private void getStatus(int i) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		System.out.println("Enter Username :");
		String username = in.next();
		while (!username.equalsIgnoreCase("admin")) {
			System.out.println("Invalid Username. Enter Username:");
			username = in.next();
		}
		System.out.println("Enter Password :");
		String password = in.next();
		while (!password.equalsIgnoreCase("admin")) {
			System.out.println("Invalid password. Enter Password:");
			password = in.next();
		}
		
		String ipaddress = new MethodHelper().createIp(i);
		
		StringHolder reply = new StringHolder();
		if (i == 1) { //NA
			iServer_NA.getPlayerStatus(username, ipaddress, reply);
		}else if (i == 2) { //EU
			iServer_EU.getPlayerStatus(username, ipaddress, reply);
		}else if (i == 3) { //AS
			iServer_AS.getPlayerStatus(username, ipaddress, reply);
		}
		System.out.println(reply.value);
	}
	
	private void initServers(String[] args) throws IOException{
		ORB orb_NA = ORB.init(args, null);
		ORB orb_EU = ORB.init(args, null);
		ORB orb_AS = ORB.init(args, null);
		
		String ior_NA =  getIorFromFile("ior_NA.txt"); 
		String ior_EU = getIorFromFile("ior_EU.txt"); 
		String ior_AS = getIorFromFile("ior_AS.txt"); 
		
		org.omg.CORBA.Object o_NA = orb_NA.string_to_object(ior_NA);
		org.omg.CORBA.Object o_EU = orb_EU.string_to_object(ior_EU);
		org.omg.CORBA.Object o_AS = orb_AS.string_to_object(ior_AS);
		
		iServer_NA = GameServerInterfaceHelper.narrow(o_NA);
		iServer_EU = GameServerInterfaceHelper.narrow(o_EU);
		iServer_AS = GameServerInterfaceHelper.narrow(o_AS);
	}
	
	private String getIorFromFile(String string) throws IOException {
		// TODO Auto-generated method stub
		String reply = "";
		
		BufferedReader br = new BufferedReader(new FileReader(string));
		reply = br.readLine();
		br.close();
		
		return reply;
	}
}
