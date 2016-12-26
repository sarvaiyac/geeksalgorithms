package com.gamelib.server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Hashtable;

import com.gamelib.client.PlayerClient;

public interface GameServerInterface extends Remote {
	public Hashtable<String, ArrayList<PlayerClient>> createPlayerAccount(
			String region, PlayerClient client) throws RemoteException;

	public Hashtable<String, ArrayList<PlayerClient>> createPlayerAccount(
			String FirstName, String LastName, int Age, String Username,
			String Password, String IPAddress) throws RemoteException;

	public String playerSignIn(String userName, String password,
			String ipAddress) throws RemoteException;

	public String playerSignOut(String userName, String ipAddress)
			throws RemoteException;

	public String getPlayerStatus(String ipAddress) throws RemoteException;
}
