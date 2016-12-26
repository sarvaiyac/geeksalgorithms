package com.gamelib.client;

import java.rmi.Naming;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;
import java.util.Scanner;

import com.gamelib.helpers.MethodHelper;
import com.gamelib.server.GameServerInterface;

public class AdministratorClient {
	transient GameServerInterface server;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// Admin would choose region first
		// Another requests would be made by chosen region server
		// And final result would be given by UDP server
		//
		Scanner keyboard = new Scanner(System.in);
		try {
			System.out
					.println("Select any region?\n1-NA ( North America )\n2-EU (Europe)\n3-AS (Asia)");
			int i = keyboard.nextInt();
			System.out.println("Enter Username :");
			String tempString = keyboard.next();
			while (!tempString.equalsIgnoreCase("admin")) {
				System.out.println("Invalid Username. Enter Username:");
				tempString = keyboard.next();
			}
			System.out.println("Enter Password :");
			tempString = keyboard.next();
			while (!tempString.equalsIgnoreCase("admin")) {
				System.out.println("Invalid Password. Enter password:");
				tempString = keyboard.next();
			}
			String ipAddress = new MethodHelper().createIp(i);
			new AdministratorClient().selectServer(ipAddress);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void selectServer(String ip) {
		String ans = new MethodHelper().fetchRegion(ip);
		try {
			server = (GameServerInterface) Naming
					.lookup("rmi://localhost:3456/" + ans);
			System.out.println(server.getPlayerStatus(ip));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
