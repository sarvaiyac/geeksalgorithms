package com.gamelib.helpers;

import java.util.ArrayList;
import java.util.Hashtable;

import com.gamelib.client.PlayerClient;

public class HashtableData {
	public Hashtable<String, ArrayList<PlayerClient>> dataNA = new Hashtable<String, ArrayList<PlayerClient>>();
	public Hashtable<String, ArrayList<PlayerClient>> dataEU = new Hashtable<String, ArrayList<PlayerClient>>();
	public Hashtable<String, ArrayList<PlayerClient>> dataAS = new Hashtable<String, ArrayList<PlayerClient>>();
}
