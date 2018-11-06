package kr.or.ddit.jmemlist;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IJMemListService extends Remote {
	public List<JMemListVO> selectJMemList() throws RemoteException;
	public int updateJMemClick(JMemListVO jmListvo) throws RemoteException;
	public List<JMemListVO> selectCareerJMemList(String career) throws RemoteException;
	public List<JMemListVO> selectDeptJMemList(String dept) throws RemoteException;
	public List<JMemListVO> selectNameJMemList(String name) throws RemoteException;
}
