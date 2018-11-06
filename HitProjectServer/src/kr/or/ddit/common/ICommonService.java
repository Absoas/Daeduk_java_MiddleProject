package kr.or.ddit.common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ICommonService extends Remote {
	public List<ZipVO> searchZip(String zip) throws RemoteException;
	public List<ZipVO> searchDong(String dong) throws RemoteException;
}
