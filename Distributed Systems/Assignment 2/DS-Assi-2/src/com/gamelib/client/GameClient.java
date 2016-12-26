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

public class GameClient {

	public String _username, _password, _firstname, _lastname, _ipaddress;
	public int _age;
	public boolean _signinstatus = false;

	GameServerInterface iServer_NA, iServer_EU, iServer_AS;

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		GameClient mainGameClient = new GameClient();
		mainGameClient.initServers(args);

		Scanner in = new Scanner(System.in);
		try {

			String _val = "y";
			while (_val.equalsIgnoreCase("y")) {
				System.out
						.println("==== Choose any region ====\n1. NA\n2. EU\n3. AS");
				int i = in.nextInt();

				System.out.println("==== Choose any option ====");
				System.out.println("1. Create Accounter");
				System.out.println("2. Sign In");
				System.out.println("3. Sign Out");
				System.out.println("4. Transfer Account");
				
				int choice = in.nextInt();
				if (choice == 1) {
					mainGameClient.createPlayerAccount(i);
				} else if (choice == 2) {
					mainGameClient.singIn(i);
				} else if (choice == 3) {
					mainGameClient.signOut(i);
				} else if (choice == 4) {
					mainGameClient.transferAccount(i);
				}

				System.out.println("==== Want to continue ? (y/n)");
				_val = in.next();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	private void createPlayerAccount(int i) {

		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		System.out.println("Enter Username (between 6 to 15 characters) :");
		String tempString = in.next();
		while (!(tempString.length() >= 6 && tempString.length() <= 15)) {
			System.out
					.println("Username is not proper. Enter Username (between 6 to 15 characters) :");
			tempString = in.next();
		}
		this._username = tempString;
		System.out.println("Enter Password (atleast 6 characters) :");
		tempString = in.next();
		while (!(tempString.length() >= 6)) {
			System.out
					.println("Password is not proper. Enter Password (atleast 6 characters) :");
			tempString = in.next();
		}
		this._password = tempString;
		System.out.println("Enter Age :");
		this._age = in.nextInt();
		System.out.println("Enter Firstname :");
		this._firstname = in.next();
		System.out.println("Enter Lastname :");
		this._lastname = in.next();

		String ipaddress = new MethodHelper().createIp(i);
		this._ipaddress = ipaddress;

		StringHolder reply = new StringHolder();
		if (i == 1) { // NA
			iServer_NA.createPlayerAccount(this._firstname, this._lastname,
					this._age, this._username, this._password, this._ipaddress,
					reply);
			reply.value = "NA :- " + reply.value;
		} else if (i == 2) { // EU
			iServer_EU.createPlayerAccount(this._firstname, this._lastname,
					this._age, this._username, this._password, this._ipaddress,
					reply);
			reply.value = "EU :- " + reply.value;
		} else if (i == 3) { // AS
			iServer_AS.createPlayerAccount(this._firstname, this._lastname,
					this._age, this._username, this._password, this._ipaddress,
					reply);
			reply.value = "AS :- " + reply.value;
		}
		System.out.println(reply.value);

	}

	private void singIn(int i) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		System.out.println("Enter Username :");
		String tempString = in.next();
		while (!(tempString.length() >= 6 && tempString.length() <= 15)) {
			System.out
					.println("Username is not proper. Enter Username (between 6 to 15 characters) :");
			tempString = in.next();
		}
		this._username = tempString;
		System.out.println("Enter Password :");
		tempString = in.next();
		while (!(tempString.length() >= 6)) {
			System.out
					.println("Password is not proper. Enter Password (atleast 6 characters) :");
			tempString = in.next();
		}
		this._password = tempString;

		String ipaddress = new MethodHelper().createIp(i);
		this._ipaddress = ipaddress;

		StringHolder reply = new StringHolder();
		if (i == 1) { // NA
			iServer_NA.playerSignIn(this._username, this._password,
					this._ipaddress, reply);
			reply.value = "NA :- " + reply.value;
		} else if (i == 2) { // EU
			iServer_EU.playerSignIn(this._username, this._password,
					this._ipaddress, reply);
			reply.value = "EU :- " + reply.value;
		} else if (i == 3) { // AS
			iServer_AS.playerSignIn(this._username, this._password,
					this._ipaddress, reply);
			reply.value = "AS :- " + reply.value;
		}
		System.out.println(reply.value);
	}

	private void signOut(int i) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		System.out.println("Enter Username :");
		String tempString = in.next();
		while (!(tempString.length() >= 6 && tempString.length() <= 15)) {
			System.out
					.println("Username is not proper. Enter Username (between 6 to 15 characters) :");
			tempString = in.next();
		}
		this._username = tempString;

		String ipaddress = new MethodHelper().createIp(i);
		this._ipaddress = ipaddress;

		StringHolder reply = new StringHolder();
		if (i == 1) { // NA
			iServer_NA.playerSignOut(this._username, this._ipaddress, reply);
			reply.value = "NA :- " + reply.value;
		} else if (i == 2) { // EU
			iServer_EU.playerSignOut(this._username, this._ipaddress, reply);
			reply.value = "EU :- " + reply.value;
		} else if (i == 3) { // AS
			iServer_AS.playerSignOut(this._username, this._ipaddress, reply);
			reply.value = "AS :- " + reply.value;
		}
		System.out.println(reply.value);
	}

	private void transferAccount(int i) {

		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		String oldipaddress = new MethodHelper().createIp(i);
		
		
		System.out.println("Enter Username (between 6 to 15 characters) :");
		String username = in.next();
		while (!(username.length() >= 6 && username.length() <= 15)) {
			System.out
					.println("Username is not proper. Enter Username (between 6 to 15 characters) :");
			username = in.next();
		}
		
		System.out.println("Enter Password (atleast 6 characters) :");
		String password = in.next();
		while (!(password.length() >= 6)) {
			System.out
					.println("Password is not proper. Enter Password (atleast 6 characters) :");
			password = in.next();
		}
		
		System.out.println("==== Choose region you want to transfer ====\n1. NA\n2. EU\n3. AS");
		int y = in.nextInt();
		String newipaddress = new MethodHelper().createIp(y);
		
		
		StringHolder reply = new StringHolder();
		if (i == 1) { // NA
			iServer_NA.transferAccount(username, password, oldipaddress, newipaddress, reply);
			reply.value = "NA :- " + reply.value;
		} else if (i == 2) { // EU
			iServer_EU.transferAccount(username, password, oldipaddress, newipaddress, reply);
			reply.value = "EU :- " + reply.value;
		} else if (i == 3) { // AS
			iServer_AS.transferAccount(username, password, oldipaddress, newipaddress, reply);
			reply.value = "AS :- " + reply.value;
		}
		System.out.println(reply.value);

	}

	private void initServers(String[] args) throws IOException {
		ORB orb_NA = ORB.init(args, null);
		ORB orb_EU = ORB.init(args, null);
		ORB orb_AS = ORB.init(args, null);

		String ior_NA = getIorFromFile("ior_NA.txt");
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
