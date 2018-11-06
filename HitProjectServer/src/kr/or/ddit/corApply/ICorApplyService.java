package kr.or.ddit.corApply;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ICorApplyService extends Remote{
	public List<CorApplyVO> selectCorApplyInfo(String corId) throws RemoteException;
	public List<CorApplyVO> selectComboCorApply(CorApplyVO corAppVO) throws RemoteException;
	public int updateComboCorApply(CorApplyVO corAppVO) throws RemoteException;
	public List<CorDetailVO> selectDetilList() throws RemoteException;
}
