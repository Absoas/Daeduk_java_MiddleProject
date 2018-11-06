package kr.or.ddit.corApply;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.List;

public class CorApplyServiceImpl extends UnicastRemoteObject implements ICorApplyService{

	private static ICorApplyService applyService;
	private static ICorApplyDao applyDao;
	
	public static ICorApplyService getInstance() throws RemoteException{
		if(applyService == null) {
			applyService = new CorApplyServiceImpl();
		}
		return applyService;
	}
	private CorApplyServiceImpl() throws RemoteException {
		applyDao = CorApplyDaoImpl.getInstance();
	}
	
	@Override
	public List<CorApplyVO> selectCorApplyInfo(String corId) throws RemoteException {
		List<CorApplyVO> list = null;
		try {
			list = applyDao.selectCorApplyInfo(corId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public List<CorApplyVO> selectComboCorApply(CorApplyVO corAppVO) throws RemoteException {
		List<CorApplyVO> list = null;
		try {
			list = applyDao.selectComboCorApply(corAppVO);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public int updateComboCorApply(CorApplyVO corAppVO) throws RemoteException {
		int cnt = 0;
		try {
			cnt = applyDao.updateComboCorApply(corAppVO);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}
	@Override
	public List<CorDetailVO> selectDetilList() throws RemoteException {
		List<CorDetailVO> list = null;
		try {
			list = applyDao.selectDetilList();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	
	
	
}
