package com.gamelib.servers;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.gamelib.helpers.LogHelper;
import com.gamelib.helpers.MethodHelper;

public class ServerAS extends ServerParent {
//	final String serverAS_filename = "/Users/chintansarvaiya/Documents/Workspace_kepler/DBAssignment3/server-AS-data.txt";
	final String serverAS_filename = "server-AS-data.txt";
	public String createPlayerAccount(String firstname, String lastname,
			int age, String username, String password, String ipaddress)
			throws IOException {
		String msg = "";
		String _geolocation = new MethodHelper().fetchRegion(ipaddress);

		String _filename = _geolocation + "-server.log";

		msg = "Create account is requested from " + ipaddress
				+ " with username : " + username;
		LogHelper.createLog(0, _filename, msg);

		try {

			if (MethodHelper.isValid(username, password)) { // check if given username and password are valid
				boolean isRegistered = isUserRegistered(username);

				if (isRegistered) { // check if registered
					msg = "Username already registered";
				} else { // if not then register
					String dataString = firstname + "-";
					dataString = dataString + lastname + "-";
					dataString = dataString + age + "-";
					dataString = dataString + username + "-";
					dataString = dataString + password + "-";
					dataString = dataString + "false";

					FileWriter fwriter = new FileWriter(serverAS_filename, true);
					PrintWriter writer = new PrintWriter(fwriter);
					writer.println(dataString);
					writer.close();

					msg = "Account successfully created with username : "
							+ username;

					String clientfilename = username + "-" + _geolocation
							+ "-client.log";
					LogHelper.createLog(2, clientfilename, msg);

				}

			}else{
				msg = "Invalid username or password";
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		LogHelper.createLog(0, _filename, msg);

		return msg;

	}

	public String playerSignIn(String username, String password,
			String ipaddress) throws IOException {
		// TODO Auto-generated method stub
		String msg = "";

		String _geolocation = new MethodHelper().fetchRegion(ipaddress);

		String _filename = _geolocation + "-server.log";
		msg = "Sign In requested from " + ipaddress + " with username : "
				+ username;
		LogHelper.createLog(0, _filename, msg);

		boolean isRegistered = isUserRegistered(username);
		if (isRegistered) { // check if user is registered
			try {
				FileInputStream fInStream = new FileInputStream(
						serverAS_filename);
				DataInputStream in = new DataInputStream(fInStream);
				BufferedReader br = new BufferedReader(
						new InputStreamReader(in));

				String strline;
				String input = "";
				msg = "nil";
				while ((strline = br.readLine()) != null) {
					String arr[] = strline.split("-");
					if (arr[3].toLowerCase().equalsIgnoreCase(
							username.toLowerCase())
							&& arr[4].toLowerCase().equalsIgnoreCase(
									password.toLowerCase())) { // check if record has same username and password given by user

						if (arr[5].equalsIgnoreCase("false")) {// then make singInStatus as true and concate record into input String
							String tempData = arr[0] + "-";
							tempData = tempData + arr[1] + "-";
							tempData = tempData + arr[2] + "-";
							tempData = tempData + arr[3] + "-";
							tempData = tempData + arr[4] + "-";
							tempData = tempData + "true";

							input += tempData + "\n";

							msg = "Sign In Successfully";
						} else { // else put that record into input String
							input += strline + "\n";
							msg = "Already Sign In";
						}

					} else { // if not same username and password then concate record into input String and give message with msg
						input += strline + "\n";
						if (msg.equalsIgnoreCase("nil")) {
							msg = "Invalid password";
						}
					}
				}

				br.close();
				FileOutputStream file = new FileOutputStream(serverAS_filename);
				file.write(input.getBytes());// write modified data to file
				file.close();

				String clientfilename = username + "-" + _geolocation
						+ "-client.log";
				LogHelper.createLog(2, clientfilename, msg);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			msg = "Username does not exist";
		}
		LogHelper.createLog(0, _filename, msg);

		return msg;

	}

	public String playerSignOut(String username, String ipaddress)
			throws IOException {
		// TODO Auto-generated method stub
		String msg = "";

		String _geolocation = new MethodHelper().fetchRegion(ipaddress);

		String _filename = _geolocation + "-server.log";
		msg = "Sign Out request from " + ipaddress + " with username : "
				+ username;
		LogHelper.createLog(0, _filename, msg);

		boolean isRegistered = isUserRegistered(username);
		if (isRegistered) { // check if registered then go further
			try {
				FileInputStream fInStream = new FileInputStream(
						serverAS_filename);
				DataInputStream in = new DataInputStream(fInStream);
				BufferedReader br = new BufferedReader(
						new InputStreamReader(in));

				String strline;
				String input = "";
				msg = "nil";
				while ((strline = br.readLine()) != null) {
					String arr[] = strline.split("-");
					if (arr[3].toLowerCase().equalsIgnoreCase(
							username.toLowerCase())) {// if record with same username found

						if (arr[5].equalsIgnoreCase("true")) {// and if this record has signInStatus true.. make it false and concate that record into input String
							String tempData = arr[0] + "-";
							tempData = tempData + arr[1] + "-";
							tempData = tempData + arr[2] + "-";
							tempData = tempData + arr[3] + "-";
							tempData = tempData + arr[4] + "-";
							tempData = tempData + "false";

							input += tempData + "\n";

							msg = "Sign Out Successfully";
						}

					} else {// else put that record into input String
						input += strline + "\n";
						if (msg.equalsIgnoreCase("nil")) {
							msg = "You are not signed in";
						}
					}
				}

				br.close();
				FileOutputStream file = new FileOutputStream(serverAS_filename);
				file.write(input.getBytes());
				file.close();

				String clientfilename = username + "-" + _geolocation
						+ "-client.log";
				LogHelper.createLog(2, clientfilename, msg);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			msg = "Username does not exist";
		}
		LogHelper.createLog(0, _filename, msg);

		return msg;

	}

	public String getPlayerStatus(String username,String password, String ipaddress) {
		// TODO Auto-generated method stub
		String msg = "";
		String _geolocation = new MethodHelper().fetchRegion(ipaddress);

		String _filename = _geolocation + "-server.log";

		msg = "getPlayerStatus request from " + ipaddress + " with username : "
				+ username;
		LogHelper.createLog(0, _filename, msg);

		msg = "Invalid username";
		// if username and password == admin then find status of all players in local region
		// concate that result with another two regions' results ( which are requested through UDP )

		if (username.equalsIgnoreCase("admin") && password.equalsIgnoreCase("admin")) {

			String result1 = "NA : " + getReplyFromUDP("getplayerstatus", 1101)
					+ "\n";
			String result2 = "EU : " + getReplyFromUDP("getplayerstatus", 1102)
					+ "\n";
			String result3 = "AS : " + getStatus() + "\n";

			msg = result1 + result2 + result3;

			LogHelper.createLog(1, "admin.log", "From : " + _geolocation
					+ " region, result:" + msg);

		}else{

			msg = "Invalid username or password";
		}


		LogHelper.createLog(0, _filename, "result:" + msg);

		return msg;
	}

	public String suspendAccount(String adminusername, String adminpassword,
			String adminipaddress, String usernametosuspend) {
		// TODO Auto-generated method stub

		String msg = "";
		String _geolocation = new MethodHelper().fetchRegion(adminipaddress);

		String _filename = _geolocation + "-server.log";
		msg = "suspendAccount request from " + adminipaddress
				+ " with username : " + adminusername
				+ " for user with username : " + usernametosuspend;
		LogHelper.createLog(0, _filename, msg);

		msg = "Invalid Username and Password";

		if (adminusername.equalsIgnoreCase("admin")
				&& adminpassword.equalsIgnoreCase("admin")) { // check if username and password are admin
			try {
				FileInputStream fInStream = new FileInputStream(
						serverAS_filename);
				DataInputStream in = new DataInputStream(fInStream);
				BufferedReader br = new BufferedReader(
						new InputStreamReader(in));
				String strline;
				String input = "";

				boolean isUserRegistered = false;
				while ((strline = br.readLine()) != null) {
					if (strline.split("-")[3].toLowerCase().equalsIgnoreCase(
							usernametosuspend.toLowerCase())) {
						isUserRegistered = true;
					} else {
						input += strline + "\n";
					}
				}
				if (isUserRegistered) {// check if user registere then delete that record

					FileOutputStream file = new FileOutputStream(
							serverAS_filename);
					file.write(input.getBytes());

					msg = "Account with username : " + usernametosuspend
							+ " Suspend Successfully";
				} else {// if not registered then give response with proper msg
					msg = "User not found";
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String clientfilename = usernametosuspend + "-" + _geolocation
					+ "-client.log";
			LogHelper.createLog(2, clientfilename, msg);
		}

		LogHelper.createLog(1, "admin.log", msg);
		LogHelper.createLog(0, _filename, msg);

		return msg;

	}

	public String transferAccount(String username, String password,
			String oldipaddress, String newipaddress) throws IOException {
		// TODO Auto-generated method stub
		String msg = "";
		String _geolocation = new MethodHelper().fetchRegion(oldipaddress);
		String _filename = _geolocation + "-server.log";
		msg = "transferAccount request from " + _geolocation + " ( "
				+ oldipaddress + " ) with username : " + username;
		LogHelper.createLog(0, _filename, msg);

		boolean isRegistered = isUserRegistered(username);
		if (isRegistered) { // if user is registered then transfer user to appropriate region using udp

			String _newgeolocation = new MethodHelper()
					.fetchRegion(newipaddress);
			String clientfilename = username + "-" + _geolocation
					+ "-client.log";

			if (_newgeolocation.equalsIgnoreCase(_geolocation)) {
				msg = "Invalid request ! You can't get transfer within same reqion";
			} else if (_newgeolocation.equalsIgnoreCase("eu")) {
				String _strToPass = getAllUserDetailsInOneStringToTransfer(username,newipaddress);
				msg = getReplyFromUDP(_strToPass, 1102);
				suspendAccount("admin", "admin", oldipaddress, username);
				msg = "account transferred to " + _newgeolocation + " ( "
						+ newipaddress + " ) with username : " + username;

				LogHelper.createLog(0, _filename, msg);
			} else if (_newgeolocation.equalsIgnoreCase("na")) {
				String _strToPass = getAllUserDetailsInOneStringToTransfer(username,newipaddress);
				msg = getReplyFromUDP(_strToPass, 1101);
				suspendAccount("admin", "admin", oldipaddress, username);
				msg = "account transferred to " + _newgeolocation + " ( "
						+ newipaddress + " ) with username : " + username;

				LogHelper.createLog(0, _filename, msg);
			}
			LogHelper.createLog(2, clientfilename, msg);
		} else {

			msg = "Invalid Username";
		}
		LogHelper.createLog(0, _filename, msg);
		return msg;

	}

	public boolean isUserRegistered(String username) {
		try {
			FileInputStream fInStream = new FileInputStream(serverAS_filename);
			DataInputStream in = new DataInputStream(fInStream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strline;

			while ((strline = br.readLine()) != null) {
				if (strline.split("-")[3].toLowerCase().equalsIgnoreCase(
						username.toLowerCase())) {
					return true;
				}
			}

			br.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;

	}

	public String getAllUserDetailsInOneStringToTransfer(String username,String newipaddress) {

		String _result = "";
		try {
			FileInputStream fInStream = new FileInputStream(serverAS_filename);
			DataInputStream in = new DataInputStream(fInStream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));

			String strline;

			while ((strline = br.readLine()) != null) {
				String arr[] = strline.split("-");
				if (arr[3].toLowerCase().equalsIgnoreCase(
						username.toLowerCase())) {
					_result = arr[0] + "-";
					_result = _result + arr[1] + "-";
					_result = _result + Integer.parseInt(arr[2]) + "-";
					_result = _result + arr[3] + "-";
					_result = _result + arr[4] + "-";
					_result = _result + newipaddress;

					return _result;
				}
			}

			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return _result;
	}
	
	public String getAllUserDetailsInOneString(String username) {

		String _result = "";
		try {
			FileInputStream fInStream = new FileInputStream(serverAS_filename);
			DataInputStream in = new DataInputStream(fInStream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));

			String strline;

			while ((strline = br.readLine()) != null) {
				String arr[] = strline.split("-");
				if (arr[3].toLowerCase().equalsIgnoreCase(
						username.toLowerCase())) {
					_result = arr[0] + "-";
					_result = _result + arr[1] + "-";
					_result = _result + Integer.parseInt(arr[2]) + "-";
					_result = _result + arr[3] + "-";
					_result = _result + arr[4] + "-";
					_result = _result + "false";

					return _result;
				}
			}

			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return _result;
	}

	public String getReplyFromUDP(String requestfor, int port) {
		DatagramSocket aSocket = null;
		try {
			aSocket = new DatagramSocket();

			byte[] m = requestfor.getBytes();
			InetAddress aHost = InetAddress.getByName("localhost");
			int serverPort = port;

			DatagramPacket request = new DatagramPacket(m, requestfor.length(),
					aHost, serverPort);
			aSocket.setBroadcast(true);
			aSocket.setSendBufferSize(10000);
			aSocket.send(request);

			byte[] buffer = new byte[10000];
			DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
			aSocket.receive(reply);

			String dirtyXMLString = new String(reply.getData());
			String cleanXMLString = null;
			Pattern pattern = null;
			Matcher matcher = null;
			pattern = Pattern.compile("[\\000]*");
			matcher = pattern.matcher(dirtyXMLString);
			if (matcher.find()) {
				cleanXMLString = matcher.replaceAll("");
			}

			return cleanXMLString;
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

		return null;
	}

	public String getStatus() {
		// TODO Auto-generated method stub

		int online = 0, offline = 0;
		try {
			FileInputStream fInStream = new FileInputStream(serverAS_filename);
			DataInputStream in = new DataInputStream(fInStream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strline;

			while ((strline = br.readLine()) != null) {
				if (strline.split("-")[5].toLowerCase()
						.equalsIgnoreCase("true")) {
					online++;
				} else {
					offline++;
				}
			}

			br.close();

			return online + " online, " + offline + " offline";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

}
