package com.gamelib.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import org.omg.CORBA.StringHolder;

import com.gamelib.client.GameClient;
import com.gamelib.helpers.LogHelper;
import com.gamelib.helpers.MethodHelper;

import GameServerIdl.GameServerInterfacePOA;

public class GameServerImplementation extends GameServerInterfacePOA {
	
	public Hashtable<String, ArrayList<GameClient>> hashData = new Hashtable<String, ArrayList<GameClient>>();
	
	GameClient _gClient;
	UDPServer _udpServer;
	
	public GameServerImplementation(int port){
		_udpServer = new UDPServer(port,this);
		_udpServer.start();
	}
	
	@Override
	public void checkMethod(String param1, StringHolder reply) {
		// TODO Auto-generated method stub
		reply.value = "from check method";
	}
	
	public void createPlayerAccount(String firstname, String lastname, int age,
			String username, String password, String ipaddress,
			StringHolder success_status) {

		boolean _flag = true;
		
		GameClient _gc = new GameClient();
		_gc._firstname = firstname;
		_gc._lastname = lastname;
		_gc._age = age;
		_gc._username = username;
		_gc._password = password;
		_gc._ipaddress = ipaddress;
		
		String _geolocation = new MethodHelper().fetchRegion(ipaddress);
		
		String _filename = _geolocation + "-server.log";
		
		String key = username.substring(0,1);
		if (hashData.containsKey(key)){
			if (hashData.get(key).size() > 0){
				for (int j = 0; j < hashData.get(key).size(); j++) {
					if (username.equalsIgnoreCase(hashData.get(key).get(j)._username)){
						_flag = false;
						break;
					}
				}
			}
		}
		
		ArrayList<GameClient> _list = new ArrayList<GameClient>();
		
		if (_flag){
			LogHelper.createLog(0, _filename,
					"Create account is requested from " + _gc._ipaddress);
			_gc._signinstatus = false;
			synchronized (hashData) {
				if (hashData.containsKey(key)){
					for (int j = 0; j < hashData.get(key).size(); j++) {
						_list.add(hashData.get(key).get(j));
					}
				}
				
				_list.add(_gc);
				hashData.put(key, _list);
				
				String clientfilename = _gc._username + "-" + _geolocation
						+ "-client.log";
				LogHelper.createLog(2, clientfilename,
						"Account successfully created. ");

				LogHelper.createLog(0, _filename,
						"Account successfully created with username : "
								+ _gc._username);
				
				success_status.value = "User added !";//+hashData.get("c").get(0)._username;

			}
		}else{
			LogHelper.createLog(0, _filename,
					"Create account is requested from " + _gc._ipaddress);
			// player.signInStatus = true;
			LogHelper.createLog(0, _filename, "Account is not created");

			success_status.value = "User not added";
		}
		
	}

	@Override
	public void playerSignIn(String username, String password,
			String ipaddress, StringHolder success_status) {
		// TODO Auto-generated method stub
		_gClient = new GameClient();
		String _geolocation = new MethodHelper().fetchRegion(ipaddress);
		
		String _filename = _geolocation + "-server.log";
		LogHelper.createLog(0, _filename, "Sign in request from " + ipaddress
				+ " with username : " + username);
		
		int _status = 0;
		String _key = username.substring(0,1);
		if (hashData.containsKey(_key)){
			for (int i = 0; i < hashData.get(_key).size(); i++) {
				GameClient tempGc= hashData.get(_key).get(i);
				if (username.equalsIgnoreCase(tempGc._username)){
					if (password.equalsIgnoreCase(tempGc._password)){
						_gClient = tempGc;
						synchronized (hashData) {
							if (!tempGc._signinstatus) { //sign in
								hashData.get(_key).get(i)._signinstatus = true;
								_status = 100;
								break;
							}else{ // already sign in
								_status = 101;
								break;
							}
						}
					}else{
						_status = 102; // invalid password
					}	
				}else{
					_status = 103; //invalid username
				}
			}
		}
		
		if (_status == 0) {
			LogHelper.createLog(0, _filename, "Username not found !");
			success_status.value = "User not found !";
		} else if (_status == 100) {

			String clientfilename = _gClient._username + "-" + _geolocation
					+ "-client.log";
			LogHelper.createLog(2, clientfilename, "Sign in successfully");

			LogHelper.createLog(0, _filename, "Sign in successfully");

			success_status.value = "You Are Signed In";
		} else if (_status == 101) {
			String clientfilename = _gClient._username + "-" + _geolocation
					+ "-client.log";
			LogHelper.createLog(2, clientfilename, "Already Sign In");

			LogHelper.createLog(0, _filename, "Already Sign In");

			success_status.value = "Already Signed In !";
		} else if (_status == 102) {
			String clientfilename = _gClient._username + "-" + _geolocation
					+ "-client.log";
			LogHelper.createLog(2, clientfilename, "Invalid Password");

			LogHelper.createLog(0, _filename, "Invalid Password");

			success_status.value =  "Invalid Password";
		} else if (_status == 103) {
			LogHelper.createLog(0, _filename, "Username not found !");

			success_status.value =  "Invalid Username";
		}
	}

	@Override
	public void playerSignOut(String username, String ipaddress,
			StringHolder success_status) {
		// TODO Auto-generated method stub
		String _geolocation = new MethodHelper().fetchRegion(ipaddress);
		
		String _filename = _geolocation + "-server.log";
		LogHelper.createLog(0, _filename, "Sign out request from " + ipaddress
				+ " with username : " + username);
		
		int _status = 0;
		String _key = username.substring(0,1);
		if (hashData.containsKey(_key)){
			for (int i = 0; i < hashData.get(_key).size(); i++) {
				GameClient tempGc= hashData.get(_key).get(i);
				if (username.equalsIgnoreCase(tempGc._username)){
						_gClient = tempGc;
						synchronized (hashData) {
							if (tempGc._signinstatus) { //sign out
								hashData.get(_key).get(i)._signinstatus = false;
								_status = 100;
								break;
							}else{ // already sign out
								_status = 101;
								break;
							}
						}
					
				}else{
					_status = 103; //invalid username
				}
			}
		}
		
		if (_status == 0) {
			LogHelper.createLog(0, _filename, "Username not found !");
			success_status.value =  "Invalid Username";
		} else if (_status == 100) {
			if (_gClient != null) {
				String clientfilename = _gClient._username + "-" + _geolocation
						+ "-client.log";
				LogHelper.createLog(2, clientfilename, "Sign Out successfully");

				LogHelper.createLog(0, _filename, "Sign Out successfully");

				success_status.value = "You Are Signed Out";				
			}
		} else if (_status == 101) {
			if (_gClient != null) {
				String clientfilename = _gClient._username + "-" + _geolocation
						+ "-client.log";
				LogHelper.createLog(2, clientfilename, "Already Sign Out");

				LogHelper.createLog(0, _filename, "Already Sign Out");

				success_status.value = "Already Signed Out !";				
			}
		} else if (_status == 103) {
			LogHelper.createLog(0, _filename, "Invalid Username !");
			success_status.value = "Invalid Username !";
		}
	}

	@Override
	public void getPlayerStatus(String username, String ipaddress,
			StringHolder success_status) {
		// TODO Auto-generated method stub
		String _geolocation = new MethodHelper().fetchRegion(ipaddress);
		
		String _filename = _geolocation + "-server.log";
		LogHelper.createLog(0, _filename, "getPlayerStatus request from " + ipaddress
				+ " with username : " + username);
		
		String _result = checkStatus();
		
		if (_geolocation.equalsIgnoreCase("na")) {
			String _resultEU = "\nEU :- " + getReplyFromUDP("getplayerstatus",1102);
			String _resultAS = "\nAS :- " + getReplyFromUDP("getplayerstatus",1103);
			_result = "\nNA:- " + _result + _resultEU + _resultAS;
		}else if (_geolocation.equalsIgnoreCase("eu")) {
			String _resultAS = "\nAS :- " + getReplyFromUDP("getplayerstatus",1103);
			String _resultNA = "\nNA :- " + getReplyFromUDP("getplayerstatus",1101);
			_result = _resultNA + "\nEU:- " +  _result + _resultAS ;
		}else if (_geolocation.equalsIgnoreCase("as")) {
			String _resultNA = "\nNA :- " + getReplyFromUDP("getplayerstatus",1101);
			String _resultEU = "\nES :- " + getReplyFromUDP("getplayerstatus",1102);
			_result = _resultNA + _resultEU + "\nAS:- " +_result;
		}	
		LogHelper.createLog(1, "admin.log", "From : " + _geolocation
				+ " region, result:" + _result);
		LogHelper.createLog(0, _filename, "result:" + _result);
		success_status.value = _result;
	}

	public String checkStatus(){
		int online = 0, offline = 0;

		Enumeration<ArrayList<GameClient>> e = hashData.elements();

		while (e.hasMoreElements()) {
			ArrayList<GameClient> ar = (ArrayList<GameClient>) e
					.nextElement();
			for (int i = 0; i < ar.size(); i++) {
				System.out.println(ar.get(i)._username);
				if (ar.get(i)._signinstatus) {
					online++;
				} else {
					offline++;
				}
			}
		}

		return online + " online, " + offline + " offline ";
	}

	public String getReplyFromUDP(String requestfor, int port){
		DatagramSocket aSocket = null;
		try {
			aSocket = new DatagramSocket();
			
			byte[] m = requestfor.getBytes();
			System.out.println("m.length : " + m.length);
			System.out.println("new String(m) : " + new String(m));
			InetAddress aHost = InetAddress.getByName("localhost");
			int serverPort = port;

			DatagramPacket request = new DatagramPacket(m,
					requestfor.length(), aHost, serverPort);
			aSocket.setBroadcast(true);
			aSocket.setSendBufferSize(10000);
			aSocket.send(request);

			byte[] buffer = new byte[10000];
			DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
			aSocket.receive(reply);
			
			return new String(reply.getData());
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


	@Override
	public void suspendAccount(String adminusername, String adminpassword,
			String adminipaddress, String usernametosuspend,
			StringHolder success_status) {
		// TODO Auto-generated method stub
		
		
		String _geolocation = new MethodHelper().fetchRegion(adminipaddress);
		
		String _filename = _geolocation + "-server.log";
		String _stringToLog = "suspendAccount request from " + adminipaddress
				+ " with username : " + adminusername + " for user with username : " + usernametosuspend;
		LogHelper.createLog(0, _filename, _stringToLog);
		
		int _status = 0;
		String _key = usernametosuspend.substring(0,1);
		if (hashData.containsKey(_key)){
			for (int i = 0; i < hashData.get(_key).size(); i++) {
				GameClient tempGc= hashData.get(_key).get(i);
				if (usernametosuspend.equalsIgnoreCase(tempGc._username)){
						_gClient = tempGc;
						synchronized (hashData) {
							_status = 100; //suspended
							hashData.get(_key).remove(i);
							break;
						}
						
				}else{
					_status = 102; //invalid username
				}
			}
		}
		
		if (_status == 0) {
			LogHelper.createLog(1, "admin.log", "Username not found !");
			success_status.value = "User not found !";
		}else if (_status == 100) {
			String clientfilename = _gClient._username + "-" + _geolocation
					+ "-client.log";
			LogHelper.createLog(2, clientfilename, "Account suspended with username : " + usernametosuspend);
			LogHelper.createLog(1, "admin.log", "Account suspended with username : " + usernametosuspend);
			LogHelper.createLog(0, _filename, "Account suspended with username : " + usernametosuspend);

			success_status.value =  "Account suspended";
		} else if (_status == 102) {
			LogHelper.createLog(1, "admin.log", "Username not found !");
			LogHelper.createLog(0, _filename, "Username not found !");

			success_status.value =  "Username not found !";
		}
		
	}

	@Override
	public void transferAccount(String username, String password,
			String oldipaddress, String newipaddress,
			StringHolder success_status) {
		// TODO Auto-generated method stub
		String _geolocation = new MethodHelper().fetchRegion(oldipaddress);
		String _filename = _geolocation + "-server.log";
		LogHelper.createLog(0, _filename, "transferAccount request from " + _geolocation
				+ " ( " + oldipaddress + " ) with username : " + username);
	
		
		if (isUserAvailable(username).equalsIgnoreCase("true")){
			
			String _newgeolocation = new MethodHelper().fetchRegion(newipaddress);
			String clientfilename = username + "-" + _geolocation
					+ "-client.log";
			String LogMsg = "";
			if (_newgeolocation.equalsIgnoreCase(_geolocation)) {
				success_status.value = "Invalid request ! You can't get transfer within same reqion";
			}else if (_newgeolocation.equalsIgnoreCase("eu")) {
				String _strToPass = getAllUserDetailsInOneString(username, newipaddress,oldipaddress);
				success_status.value = getReplyFromUDP(_strToPass,1102);
				suspendAccount("admin", "admin", oldipaddress, username, new StringHolder());
				LogMsg = "account transferred to " + _newgeolocation
						+ " ( " + newipaddress + " ) with username : " + username;
				
				LogHelper.createLog(0, _filename, LogMsg);
				LogHelper.createLog(2, clientfilename, LogMsg);
				
				
			}else if (_newgeolocation.equalsIgnoreCase("as")) {
				String _strToPass = getAllUserDetailsInOneString(username, newipaddress,oldipaddress);
				success_status.value = getReplyFromUDP(_strToPass,1103);
				suspendAccount("admin", "admin", oldipaddress, username, new StringHolder());
				
				LogMsg = "account transferred to " + _newgeolocation
						+ " ( " + newipaddress + " ) with username : " + username;
				
				LogHelper.createLog(0, _filename, LogMsg);
				LogHelper.createLog(2, clientfilename, LogMsg);
			}else if (_newgeolocation.equalsIgnoreCase("na")) {
				String _strToPass = getAllUserDetailsInOneString(username, newipaddress,oldipaddress);
				success_status.value = getReplyFromUDP(_strToPass,1101);
				suspendAccount("admin", "admin", oldipaddress, username, new StringHolder());
				
				LogMsg = "account transferred to " + _newgeolocation
						+ " ( " + newipaddress + " ) with username : " + username;
				
				LogHelper.createLog(0, _filename, LogMsg);
				LogHelper.createLog(2, clientfilename, LogMsg);
			}
		}else{
			LogHelper.createLog(0, _filename, "Username not found !");
			success_status.value =  "Invalid Username";
		}
		
	}


	public String isUserAvailable(String username){
		
		String key = username.substring(0,1);
		if (hashData.containsKey(key)){
			if (hashData.get(key).size() > 0){
				for (int j = 0; j < hashData.get(key).size(); j++) {
					if (username.equalsIgnoreCase(hashData.get(key).get(j)._username)){
						return "true";
					}
				}
			}
		}
		return "false";
	}

	public String getAllUserDetailsInOneString(String username, String newipaddress,String oldipaddress){
		String _result = "";
		String key = username.substring(0,1);
		if (hashData.containsKey(key)){
			if (hashData.get(key).size() > 0){
				for (int j = 0; j < hashData.get(key).size(); j++) {
					if (username.equalsIgnoreCase(hashData.get(key).get(j)._username)){
						_result = _result + hashData.get(key).get(j)._firstname + "-";	//0
						_result = _result + hashData.get(key).get(j)._lastname + "-"; 	//1
						_result = _result + hashData.get(key).get(j)._age + "-";		//2
						_result =_result + hashData.get(key).get(j)._username + "-";	//3
						_result = _result + hashData.get(key).get(j)._password + "-";	//4
						_result = _result + newipaddress;								//6
						return _result;
					}
				}
			}
		}
		return "no detail found";
	}

}
