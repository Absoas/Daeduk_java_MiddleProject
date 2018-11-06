package kr.or.ddit.rmi.client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import kr.or.ddit.rmi.interf.IRemote;


public class ClientConnect {
	private static Registry reg=null;
	private static IRemote clientInf=null;
	
	public static IRemote getConnect() {
		try {
			reg=LocateRegistry.getRegistry("localhost",8887);
			clientInf=(IRemote) reg.lookup("HitServer");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clientInf;
	}
}
