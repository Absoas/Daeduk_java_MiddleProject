package kr.or.ddit.mylicense;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IMylicService extends Remote{
	public List<MylicVO> selectMylic(String mem_id) throws RemoteException;
	
	public int insertLic(MylicVO mylicvo) throws RemoteException;
	
	public int deleteLic(MylicVO mylicvo) throws RemoteException;
	

}
