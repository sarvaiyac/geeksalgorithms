package com.gamelib.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import com.gamelib.client.PlayerClient;
import com.gamelib.helpers.HashtableData;
import com.gamelib.helpers.LogHelper;
import com.gamelib.helpers.MethodHelper;

public class GameServer implements GameServerInterface {

	static HashtableData htData;
	PlayerClient pClient;

	public static void main(String[] args) {

		try {
			htData = new HashtableData();
			(new GameServer()).exportSever();
			System.out.println("-- Server Started --");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	// exportServer Method
	// It will create three RMI servers

	public void exportSever() throws Exception {
		
		GameServer serverNA = new GameServer();
		GameServer serverEU = new GameServer();
		GameServer serverAS = new GameServer();

		Remote objNA = UnicastRemoteObject.exportObject(serverNA, 3456);
		Remote objEU = UnicastRemoteObject.exportObject(serverEU, 3456);
		Remote objAS = UnicastRemoteObject.exportObject(serverAS, 3456);

		Registry r = LocateRegistry.createRegistry(3456);

		r.bind("NA", objNA);
		r.bind("EU", objEU);
		r.bind("AS", objAS);
	}

	// SignUp Method
	// It creates an account on particular region server based on chosen
	// geolocation
	// It stores data on Hashtable by taking first character of username as key
	// Server Log is written in log/server/ directory

	@Override
	public Hashtable<String, ArrayList<PlayerClient>> createPlayerAccount(
			String FirstName, String LastName, int Age, String Username,
			String Password, String IPAddress) throws RemoteException {
		// TODO Auto-generated method stub
		ArrayList<PlayerClient> list = new ArrayList<PlayerClient>();
		int i = 0, length = 0;
		boolean flag = true;
		String key = Username.substring(0, 1);
		PlayerClient player = new PlayerClient();
		player.userName = Username;
		player.firstName = FirstName;
		player.lastName = LastName;
		player.age = Age;
		player.password = Password;
		player.signInStatus = false;
		player.ipAdd = IPAddress;
		String geolocation = new MethodHelper().fetchRegion(IPAddress);
		
		Hashtable<String, ArrayList<PlayerClient>> data = new Hashtable<String, ArrayList<PlayerClient>>();
		if (geolocation.equalsIgnoreCase("NA")) {
			data = htData.dataNA;
		} else if (geolocation.equalsIgnoreCase("EU")) {
			data = htData.dataEU;
		} else if (geolocation.equalsIgnoreCase("AS")) {
			data = htData.dataAS;
		}
		String filename = geolocation + "-server.log";

		if (data.containsKey(key)) {
			length = data.get(key).size();
			if (length > 0) {
				for (i = 0; i < length; i++) {
					if (Username
							.equalsIgnoreCase(data.get(key).get(i).userName)) {
						flag = false;
						data.get(key).get(i).signInStatus = true;
						break;
					}
				}
			}
		}

		if (flag) {
			
			LogHelper.createLog(0, filename,
					"Create account is requested from " + player.ipAdd);

			player.signInStatus = false;
			synchronized (data) {

				if (data.containsKey(key)) {
					length = data.get(key).size();
					for (int j = 0; j < length; j++) {
						list.add(data.get(key).get(j));
					}
				}

				list.add(player);
				data.put(key, list);
				String clientfilename = player.userName + "-" + geolocation
						+ "-client.log";
				LogHelper.createLog(2, clientfilename,
						"Account successfully created. ");

				LogHelper.createLog(0, filename,
						"Account successfully created with username : "
								+ player.userName);
				return data;
			}

		} else {
			LogHelper.createLog(0, filename,
					"Create account is requested from " + player.ipAdd);
			// player.signInStatus = true;
			LogHelper.createLog(0, filename, "Account is not created");

			return data;
		}
	}

	public Hashtable<String, ArrayList<PlayerClient>> createPlayerAccount(
			String geolocation, PlayerClient player) {
		
		ArrayList<PlayerClient> list = new ArrayList<PlayerClient>();
		int i = 0, length = 0;
		boolean flag = true;
		String key = player.userName.substring(0, 1);

		Hashtable<String, ArrayList<PlayerClient>> data = new Hashtable<String, ArrayList<PlayerClient>>();
		if (geolocation.equalsIgnoreCase("NA")) {
			data = htData.dataNA;
		} else if (geolocation.equalsIgnoreCase("EU")) {
			data = htData.dataEU;
		} else if (geolocation.equalsIgnoreCase("AS")) {
			data = htData.dataAS;
		}
		String filename = geolocation + "-server.log";

		if (data.containsKey(key)) {
			length = data.get(key).size();
			if (length > 0) {
				for (i = 0; i < length; i++) {
					if (player.userName
							.equalsIgnoreCase(data.get(key).get(i).userName)) {
						flag = false;
						data.get(key).get(i).signInStatus = true;// it means
																	// already
																	// registered
						break;
					}
				}
			}
		}

		if (flag) {
			LogHelper.createLog(0, filename,
					"Create account is requested from " + player.ipAdd);

			player.signInStatus = false;
			synchronized (data) {

				if (data.containsKey(key)) {
					length = data.get(key).size();
					for (int j = 0; j < length; j++) {
						list.add(data.get(key).get(j));
					}
				}

				list.add(player);
				data.put(key, list);
//				htData.setHashtableValue(data, geolocation);
				String clientfilename = player.userName + "-" + geolocation
						+ "-client.log";
				LogHelper.createLog(2, clientfilename,
						"Account successfully created. ");

				LogHelper.createLog(0, filename,
						"Account successfully created with username : "
								+ player.userName);
				return data;
			}

		} else {
			LogHelper.createLog(0, filename,
					"Create account is requested from " + player.ipAdd);
			// player.signInStatus = true;
			LogHelper.createLog(0, filename, "Account is not created");

			return data;
		}

	}

	// SignUp Method
	// It creates an account on particular region server based on chosen
	// geolocation
	// It stores data on Hashtable by taking first character of username as key
	// Server Log is written in log/server/ directory
	// Cleint log is written in log/client/ directory

	public String playerSignIn(String userName, String password, String ipAddress) {
		Hashtable<String, ArrayList<PlayerClient>> data = new Hashtable<String, ArrayList<PlayerClient>>();
		pClient = new PlayerClient();
		String values = ipAddress.substring(0, ipAddress.indexOf("."));
		String geolocation = "";

		if (values.equals("132")) {
			data = htData.dataNA;
			geolocation = "NA";
		} else if (values.equals("93")) {
			data = htData.dataEU;
			geolocation = "EU";
		} else if (values.equals("182")) {
			data = htData.dataAS;
			geolocation = "AS";
		}
		String filename = geolocation + "-server.log";
		LogHelper.createLog(0, filename, "Sign in request from " + ipAddress
				+ " with username : " + userName);

		String key = userName.substring(0, 1);
		int status = 0;
		if (data.containsKey(key)) {

			for (int i = 0; i < data.get(key).size(); i++) {
				PlayerClient tempClient = data.get(key).get(i);

				if (userName.equalsIgnoreCase(tempClient.userName)) {
					if (password.equalsIgnoreCase(tempClient.password)) {
						pClient = tempClient;
						synchronized (data) {
							if (!tempClient.signInStatus) {

								data.get(key).get(i).signInStatus = true;

								// return "u got signed in!!";
								status = 100;
								break;
							} else {
								// return "already signed in!!";
								status = 101;
								break;
							}
						}

					} else {
						// return "invalid password";
						status = 102;
					}
				} else {
					// return "invalid username";
					status = 103;
				}
			}

		}

		if (status == 0) {
			LogHelper.createLog(0, filename, "Username not found !");
			return "User not found !";
		} else if (status == 100) {

			String clientfilename = pClient.userName + "-" + geolocation
					+ "-client.log";
			LogHelper.createLog(2, clientfilename, "Sign in successfully");

			LogHelper.createLog(0, filename, "Sign in successfully");

			return "You Are Signed In";
		} else if (status == 101) {
			String clientfilename = pClient.userName + "-" + geolocation
					+ "-client.log";
			LogHelper.createLog(2, clientfilename, "Already Sign In");

			LogHelper.createLog(0, filename, "Already Sign In");

			return "Already Signed In !";
		} else if (status == 102) {
			String clientfilename = pClient.userName + "-" + geolocation
					+ "-client.log";
			LogHelper.createLog(2, clientfilename, "Invalid Password");

			LogHelper.createLog(0, filename, "Invalid Password");

			return "Invalid Password";
		} else if (status == 103) {
			LogHelper.createLog(0, filename, "Username not found !");

			return "Invalid Username";
		}
		return "";

	}

	// SignOut Method
	// It checks whether user is exist & signed in, it will assign sign in
	// status to false
	// Server Log is written in log/server/ directory
	// Cleint log is written in log/client/ directory

	public String playerSignOut(String userName, String ipAddress) {
		Hashtable<String, ArrayList<PlayerClient>> data = new Hashtable<String, ArrayList<PlayerClient>>();
		String values = ipAddress.substring(0, ipAddress.indexOf("."));

		String geolocation = "";
		if (values.equals("132")) {
			data = htData.dataNA;
			geolocation = "NA";
		} else if (values.equals("93")) {
			data = htData.dataEU;
			geolocation = "EU";
		} else if (values.equals("182")) {
			data = htData.dataAS;
			geolocation = "AS";
		}
		String filename = geolocation + "-server.log";
		LogHelper.createLog(0, filename, "Sign out request from " + ipAddress
				+ " with username : " + userName);

		String key = userName.substring(0, 1);
		int status = 0;
		if (data.containsKey(key)) {

			for (int i = 0; i < data.get(key).size(); i++) {
				PlayerClient tempClient = data.get(key).get(i);

				if (userName.equalsIgnoreCase(tempClient.userName)) {
					synchronized (data) {
						if (tempClient.signInStatus) {
							data.get(key).get(i).signInStatus = false;
							// return "u got signed out!!";
							status = 100;
							break;
						} else {
							// return "already signed out!!";
							status = 101;
							break;
						}
					}
				} else {
					// return "invalid username";
					status = 103;
				}
			}

		}

		if (status == 0) {
			return "User is invalid";
		} else if (status == 100) {
			if (pClient != null) {
				String clientfilename = pClient.userName + "-" + geolocation
						+ "-client.log";
				LogHelper.createLog(2, clientfilename, "Sign out successfully");

				LogHelper.createLog(0, filename, "Sign out successfully");
			}

			return "You Are Signed Out";
		} else if (status == 101) {
			if (pClient != null) {
				String clientfilename = pClient.userName + "-" + geolocation
						+ "-client.log";
				LogHelper.createLog(2, clientfilename, "Already Sign out");

				LogHelper.createLog(0, filename, "Already Sign out");
			}

			return "Already Signed Out  !";
		} else if (status == 103) {
			return "Invalid Username";
		}
		return "";

	}

	// getPlayerStatus Method
	// It will create one server from ip address and generates result in form of
	// " xx Online, xx Offline"
	// That server requests to another two servers for online/offline data
	// Final result would be sent to UDP server
	// UDP server replies back with same data
	// Resultant data would be returned to administartor client
	// Server Log is written in log/server/ directory
	// Client log is written in log/client/ directory

	public String getPlayerStatus(String ip) {

		String values = ip.substring(0, ip.indexOf("."));

		String finalresult = "", resultforudp = "";
		String region = "";
		if (values.equals("132")) {
			region = "NA";
			finalresult = "\nNA : " + checkStatus(htData.dataNA);
			resultforudp = "\nAS : " + checkStatus(htData.dataEU) + "\nNA : "
					+ checkStatus(htData.dataAS);
		} else if (values.equals("93")) {
			region = "EU";
			finalresult = "\nEU : " + checkStatus(htData.dataEU);
			resultforudp = "\nAS : " + checkStatus(htData.dataAS) + "\nNA : "
					+ checkStatus(htData.dataNA);
		} else if (values.equals("182")) {
			region = "AS";
			finalresult = "\nAS : " + checkStatus(htData.dataAS);
			resultforudp = "\nNA : " + checkStatus(htData.dataNA) + "\nEU : "
					+ checkStatus(htData.dataEU);
		}

		String filename = region + "-server.log";
		LogHelper.createLog(0, filename,
				"Administartion sent player status request from : " + ip);

		DatagramSocket aSocket = null;
		try {
			aSocket = new DatagramSocket();
			Hashtable<String, ArrayList<PlayerClient>> data1;

			byte[] m = resultforudp.getBytes();
			InetAddress aHost = InetAddress.getByName("localhost");
			int serverPort = 6789;

			DatagramPacket request = new DatagramPacket(m,
					resultforudp.length(), aHost, serverPort);
			aSocket.send(request);

			byte[] buffer = new byte[10000];
			DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
			aSocket.receive(reply);
			System.out.println("-- Server gave reply --\n");
			String replyfromudp = new String(reply.getData());
			finalresult = finalresult + replyfromudp;

			filename = "admin.log";
			LogHelper.createLog(1, filename, "From : " + region
					+ " region, result:" + finalresult);
		} catch (SocketException e) {
			// TODO: handle exception\
			System.out.println("Error in SocketException : " + e.getMessage());
		} catch (IOException e) {
			// TODO: handle exception
			System.out.println("Error in IOException : " + e.getMessage());
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error in Exception : " + e.getMessage());
		} finally {

			if (aSocket != null)
				aSocket.close();

		}

		return "Reply : " + finalresult;

	}

	private String checkStatus(
			Hashtable<String, ArrayList<PlayerClient>> modifieddata) {
		int online = 0, offline = 0;

		Enumeration<ArrayList<PlayerClient>> e = modifieddata.elements();

		while (e.hasMoreElements()) {
			ArrayList<PlayerClient> ar = (ArrayList<PlayerClient>) e
					.nextElement();
			for (int i = 0; i < ar.size(); i++) {
				System.out.println(ar.get(i).userName);
				if (ar.get(i).signInStatus) {
					online++;
				} else {
					offline++;
				}
			}
		}

		return online + " online, " + offline + " offline";
	}
}
