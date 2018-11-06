package kr.or.ddit.myApply;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IMyApplyService extends Remote{
	/**
	 * 내가신청한 기업 조회
	 * @return
	 * @throws RemoteException
	 */
	public List<MyApplyVO> selectMyApply(String mem_id) throws RemoteException;
	
	public boolean insertApply(MyApplyVO vo) throws RemoteException;
	
	public int selectTestNo(MyApplyVO vo) throws RemoteException;

}
