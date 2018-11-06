package kr.or.ddit.rmi.client;

import kr.or.ddit.rmi.interf.IRemote;

public class ClientConnectFactory {
	private static ClientConnect cc;
	private static IRemote conn;
	
	static {
		cc = new ClientConnect();
		conn = cc.getConnect();
	}
	
	public static IRemote getClientConnect(){
		return conn;
	}
}
