package kr.or.ddit.rmi.server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import kr.or.ddit.rmi.interf.IRemote;
import kr.or.ddit.rmi.interf.RemoteImpl;

public class ServerMain{
	public static void main(String[] args) {
		try {
			IRemote inf= new RemoteImpl();
			Registry reg=LocateRegistry.createRegistry(8887);
			reg.rebind("HitServer",inf);
			System.out.println("서버 Start");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
