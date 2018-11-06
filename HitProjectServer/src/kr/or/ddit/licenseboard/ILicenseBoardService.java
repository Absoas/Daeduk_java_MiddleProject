package kr.or.ddit.licenseboard;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;


public interface ILicenseBoardService extends Remote{
	public int insertBoard(LicenseBoardVO licensevo) throws RemoteException;
	public List<LicenseBoardVO> getAllBoard() throws RemoteException;
	public int deleteBoard(String liccode) throws RemoteException;
	public int updateBoard(LicenseBoardVO licensevo) throws RemoteException;
	
}
