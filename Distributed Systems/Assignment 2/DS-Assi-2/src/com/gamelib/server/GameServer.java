package com.gamelib.server;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Hashtable;

import org.omg.CORBA.ORB;
import org.omg.CORBA.Object;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import org.omg.PortableServer.POAManagerPackage.AdapterInactive;
import org.omg.PortableServer.POAPackage.ObjectNotActive;
import org.omg.PortableServer.POAPackage.ServantAlreadyActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

public class GameServer {


	
	/**
	 * @param args
	 * @throws InvalidName 
	 * @throws WrongPolicy 
	 * @throws ServantAlreadyActive 
	 * @throws ObjectNotActive 
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws InvalidName, ServantAlreadyActive, WrongPolicy, AdapterInactive,ObjectNotActive, FileNotFoundException {
		// TODO Auto-generated method stub
		ORB orb_NA = ORB.init(args, null);
		ORB orb_EU = ORB.init(args, null);
		ORB orb_AS = ORB.init(args, null);
		
		POA rootPOA_NA = POAHelper.narrow(orb_NA.resolve_initial_references("RootPOA"));
		POA rootPOA_EU = POAHelper.narrow(orb_NA.resolve_initial_references("RootPOA"));
		POA rootPOA_AS = POAHelper.narrow(orb_NA.resolve_initial_references("RootPOA"));
		
		/*
		GameServerImplementation gsImpl_NA = new GameServerImplementation();
		GameServerImplementation gsImpl_EU = new GameServerImplementation();
		GameServerImplementation gsImpl_AS = new GameServerImplementation();
		*/
		
		GameServerImplementation gsImpl_NA = new GameServerImplementation(1101);
		GameServerImplementation gsImpl_EU = new GameServerImplementation(1102);
		GameServerImplementation gsImpl_AS = new GameServerImplementation(1103);
		
		byte[] id_NA = rootPOA_NA.activate_object(gsImpl_NA);
		byte[] id_EU = rootPOA_EU.activate_object(gsImpl_EU);
		byte[] id_AS = rootPOA_AS.activate_object(gsImpl_AS);
		
		Object ref_NA = rootPOA_NA.id_to_reference(id_NA);
		Object ref_EU = rootPOA_EU.id_to_reference(id_EU);
		Object ref_AS = rootPOA_AS.id_to_reference(id_AS);
		
		String ior_NA = orb_NA.object_to_string(ref_NA);
		String ior_EU = orb_EU.object_to_string(ref_EU);
		String ior_AS = orb_AS.object_to_string(ref_AS);
		
		printFile("ior_NA.txt", ior_NA);
		printFile("ior_EU.txt", ior_EU);
		printFile("ior_AS.txt", ior_AS);
		
		rootPOA_NA.the_POAManager().activate();
		orb_NA.run();
		
		rootPOA_EU.the_POAManager().activate();
		orb_EU.run();
		
		rootPOA_AS.the_POAManager().activate();
		orb_AS.run();
	}

	
	public static void printFile(String filename, String line) throws FileNotFoundException{
		PrintWriter print = new PrintWriter(filename);
		print.println(line);
		print.close();
	}
}
