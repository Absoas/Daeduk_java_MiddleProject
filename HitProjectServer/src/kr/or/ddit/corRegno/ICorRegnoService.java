package kr.or.ddit.corRegno;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;

public interface ICorRegnoService extends Remote{
	public List<CorRegnoVO> selectCorRegno() throws RemoteException;
	public List<CorRegnoVO> searchCorRegno(HashMap<String, String> corRegnoMap) throws RemoteException;
	public List<CorRegnoVO> searchCorName(HashMap<String, String> corRegnoMap) throws RemoteException;
	
}
