package kr.or.ddit.mylicense;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.List;

import kr.or.ddit.mylicense.MylicServiceImpl;

public class MylicServiceImpl extends UnicastRemoteObject implements IMylicService{
	private IMylicDao MylicDao;
	private static IMylicService MylicService;
	
	public static IMylicService getInstance() throws RemoteException{
		if(MylicService == null) {
			MylicService = new MylicServiceImpl();
		}
		return MylicService;
	}
	
	private MylicServiceImpl() throws RemoteException{
		MylicDao = MylicDaoImpl.getInstance();
	}

	@Override
	public List<MylicVO> selectMylic(String mem_id) throws RemoteException{
		List<MylicVO> myliclist = null;
		try {
			myliclist = MylicDao.selectMylic(mem_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return myliclist;
	}

	
	@Override
	public int insertLic(MylicVO mylicvo) throws RemoteException {
		int cnt = 0;
		try {
			cnt = MylicDao.insertLic(mylicvo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int deleteLic(MylicVO mylicvo) throws RemoteException {
		int cnt = 0;
		try {
			cnt = MylicDao.deleteLic(mylicvo);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

}
