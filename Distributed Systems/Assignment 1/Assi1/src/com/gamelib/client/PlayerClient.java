package com.gamelib.client;


import java.io.Serializable;
import java.rmi.Naming;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;
import java.util.Scanner;

import com.gamelib.helpers.MethodHelper;
import com.gamelib.server.GameServerInterface;

public class PlayerClient implements Serializable,Runnable {

	public String userName, firstName, lastName, password;
	public int age;
	public boolean signInStatus = false;
	public String ipAdd;
	transient GameServerInterface server;

	// Client programm starts. You would be given 5 options
	// 1. Sign Up ( Create Account )
	// 2. Sign In ( Log In With Your Account )
	// 3. Sign Out ( Log Out with logged in account)
	// 4. Multithreaded Demo ( Demo for multi thread program )
	// 5. Exit current program
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		try {
			System.out.println("-- Client Started --\n");
			String val = "y";
			while (val.equalsIgnoreCase("y")) {

				menu();
				int i = in.nextInt();
				if (i == 1) {
					(new PlayerClient()).playerSignUp();
				}else if (i == 2) {
					new PlayerClient().playerSignIn();
				}else if (i == 3) {
					new PlayerClient().playerSignOut();
				}else if (i == 4){
					new PlayerClient().multithreadDemo();
				}else if (i == 5){
					break;
				}
				System.out.println("Do you want to continue ? y/n");
				val = in.next();
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// Multi thread Demo
	// 5 users would be added by thread
	
	public void multithreadDemo(){
		Runnable run=new PlayerClient();
		Thread t1=new Thread(run);
		Thread t2=new Thread(run);
		Thread t3=new Thread(run);
		Thread t4=new Thread(run);
		Thread t5=new Thread(run);
		Thread t6=new Thread(run);
		Thread t7=new Thread(run);
		Thread t8=new Thread(run);
		Thread t9=new Thread(run);
		Thread t10=new Thread(run);
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		t6.start();
		t7.start();
		t8.start();
		t9.start();
		t10.start();
	}
	
	public void run(){
		PlayerClient temp=new PlayerClient();
		temp.userName=Thread.currentThread().getName();
		temp.password= "password";
		temp.firstName= "firstname";
		temp.lastName= "lastname";
		temp.signInStatus=false;
		temp.ipAdd=new MethodHelper().createIp(1);
		try {
			Hashtable<String, ArrayList<PlayerClient>> datatemp=null;
			server=(GameServerInterface)Naming.lookup("rmi://localhost:3456/NA");
			datatemp=server.createPlayerAccount("NA", temp);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public static void menu() {
		System.out.println("\n-- Select any option --\n");
		System.out.println("1. Sign Up");
		System.out.println("2. Sign In");
		System.out.println("3. Sign Out");
		System.out.println("4. Demo with Multithread");
		System.out.println("5. Exit");
	}

	// Sign Up Method
	// Choose region, enter username , password and other details
	// Account would be created on particular server of selected region
	
	public void playerSignUp() {
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Select any region?\n1-NA ( North America )\n2-EU (Europe)\n3-AS (Asia)");
		int i = keyboard.nextInt();
		this.ipAdd = new MethodHelper().createIp(i);
		
		System.out.println("Enter Username (between 6 to 15 characters) :");
		String tempString = keyboard.next();
		while (!(tempString.length() >= 6 && tempString.length() <= 15)) {
			System.out
					.println("Username is not proper. Enter Username (between 6 to 15 characters) :");
			tempString = keyboard.next();
		}
		this.userName = tempString;
		System.out.println("Enter Password (atleast 6 characters) :");
		tempString = keyboard.next();
		while (!(tempString.length() >= 6)) {
			System.out
					.println("Password is not proper. Enter Password (atleast 6 characters) :");
			tempString = keyboard.next();
		}
		this.password = tempString;
		System.out.println("Enter Age :");
		this.age = keyboard.nextInt();
		System.out.println("Enter Firstname :");
		this.firstName = keyboard.next();
		System.out.println("Enter Lastname :");
		this.lastName = keyboard.next();

		try {

			selectServer(this.ipAdd);

			Hashtable<String, ArrayList<PlayerClient>> answer = null;
//			answer = server.createPlayerAccount(new MethodHelper().fetchRegion(this.ipAdd), this);
			answer = server.createPlayerAccount(this.firstName, this.lastName, this.age, this.userName, this.password, this.ipAdd);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	// Sign In Method
	// Choose region, enter username and password
	// local region server would check data and make its sign in status on
	
	public void playerSignIn() {
		Scanner in = new Scanner(System.in);
		System.out.println("Select any region?\n1-NA ( North America )\n2-EU (Europe)\n3-AS (Asia)");
		int i = in.nextInt();
		String ipAddress = new MethodHelper().createIp(i);
		selectServer(ipAddress);

		System.out.println("Enter Username :");
		String userName = in.next();
		System.out.println("Enter Password :");
		String password = in.next();
		try {
			String value = server.playerSignIn(userName, password, ipAddress);
			System.out.println(value);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}
	
	// Sign Out Method
	// Choose region, enter username
	// local region server would check data and make its sign in status off
	
	private void playerSignOut() {
		Scanner in = new Scanner(System.in);
		System.out.println("Select any region?\n1-NA ( North America )\n2-EU (Europe)\n3-AS (Asia)");
		int i = in.nextInt();
		String ipAddress = new MethodHelper().createIp(i);
		selectServer(ipAddress);

		System.out.println("Enter Username :");
		String userName = in.next();

		try {
			String value = server.playerSignOut(userName, ipAddress);
			System.out.println(value);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}

	// selectServer Method
	// Server is created from given Ip ( Ip would be created dynamically as per region selection )
	
	public void selectServer(String ip) {
		String ans = new MethodHelper().fetchRegion(ip);
		try {
			server = (GameServerInterface) Naming
					.lookup("rmi://localhost:3456/" + ans);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}


}
