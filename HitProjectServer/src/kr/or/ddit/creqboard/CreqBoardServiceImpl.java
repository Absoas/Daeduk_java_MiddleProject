package kr.or.ddit.creqboard;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.List;

public class CreqBoardServiceImpl  extends UnicastRemoteObject implements ICreqBoardService {

	private static ICreqBoardService creqService;
	private ICreqBoardDao creqDao;

	public static ICreqBoardService getInstance() throws RemoteException {
		if (creqService == null) {
			creqService = new CreqBoardServiceImpl();
		}
		return creqService;
	}

	private CreqBoardServiceImpl() throws RemoteException {
		creqDao = CreqBoardDaoImpl.getInstance();
	}
	
	@Override
	public List<CReqBoardVO> selectCReqBoard() throws RemoteException {
		List<CReqBoardVO> list = null;
		try {
			list = creqDao.selectCReqBoard();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public boolean insertCreqBoard(CReqBoardVO creqVO) throws RemoteException {
		boolean result = false;
		try {
			result = creqDao.insertCreqBoard(creqVO);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int deleteCreqBoard(String corid) throws RemoteException {
		int cnt = 0;
		try {
			cnt = creqDao.deleteCreqBoard(corid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int updateCreqBoard(CReqBoardVO creqVO) throws RemoteException {
		int cnt = 0;
		try {
			cnt = creqDao.updateCreqBoard(creqVO);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}
	
	@Override
	public boolean insertApply_board(String mem_id) throws RemoteException {
		boolean result = false;
		try {
			result = creqDao.insertApply_board(mem_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	
	@Override
	public List<String> selectCReqName() throws RemoteException {
		List<String> list = null;
		try {
			list = creqDao.selectCReqName();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public CReqBoardVO selectCreqBoardInfo(String corName) throws RemoteException {
		CReqBoardVO vo = null;
		try {
			vo = creqDao.selectCreqBoardInfo(corName);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;
	}

	@Override
	public int updateCreqClick(CReqBoardVO creqVO) throws RemoteException {
		int cnt = 0;
		try {
			cnt = creqDao.updateCreqClick(creqVO);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public List<String> selectCreqArea(String corArea) throws RemoteException {
		List<String> list = null;
		try {
			list = creqDao.selectCreqArea(corArea);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<String> selectCreqCorName(String corName) throws RemoteException {
		List<String> list = null;
		try {
			list = creqDao.selectCreqCorName(corName);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<String> selectCreqSal(String creqSalary) throws RemoteException {
		List<String> list = null;
		try {
			list = creqDao.selectCreqSal(creqSalary);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}	
	
	@Override
	public List<String> setRanckView() throws RemoteException {
		List<String> list = null;
		try {
			list = creqDao.setRanckView();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	
	
}
