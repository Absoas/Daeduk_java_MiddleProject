package kr.or.ddit.passIntroBoard;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.List;

public class PassIntroBoardServiceImpl extends UnicastRemoteObject implements IPassIntroBoardService {
	private IPassIntroBoardDao passIntroDao;
	private static IPassIntroBoardService passIntroService;
	
	public static IPassIntroBoardService getInstance()throws RemoteException {
		if(passIntroService == null) {
			passIntroService = new PassIntroBoardServiceImpl();
		}
		return passIntroService;
	}
	
	private PassIntroBoardServiceImpl()throws RemoteException {
		passIntroDao = PassIntroBoardDaoImpl.getInstance();
	}

	@Override
	public List<PassIntroboardVO> selectpassboard() throws RemoteException {
		List<PassIntroboardVO> passlist = null;
		try {
			passlist = passIntroDao.selectpassboard();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return passlist;
	}

	@Override
	public int countNotice(PassIntroboardVO passVO)throws RemoteException {
		int cnt = 0;
		try {
			cnt = passIntroDao.countNotice(passVO);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int deletepassboard(int passno) throws RemoteException {
		int cnt = 0;
		try {
			cnt = passIntroDao.deletepassboard(passno);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int insertpassboard(PassIntroboardVO passVO) throws RemoteException {
		int cnt = 0;
		try {
			cnt = passIntroDao.insertpassboard(passVO);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int updatepassboard(PassIntroboardVO passVO) throws RemoteException {
		int cnt = 0;
		try {
			cnt = passIntroDao.updatepassboard(passVO);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	


}
