package kr.or.ddit.jmemlist;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.List;


public class JMemListServiceImpl extends UnicastRemoteObject implements IJMemListService {
	
	private IJMemListDao jmemListDao;
	private static IJMemListService jmemListService;
	
	private JMemListServiceImpl() throws RemoteException {
		jmemListDao = JMemListDaoImpl.getInstance();
	}
	
	public static IJMemListService getInstance() throws RemoteException {
		if(jmemListService==null) {
			jmemListService = new JMemListServiceImpl();
		}
		return jmemListService;
	}

	@Override
	public List<JMemListVO> selectJMemList() throws RemoteException {
		List<JMemListVO> result = null;
		try {
			result = jmemListDao.selectJMemList();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int updateJMemClick(JMemListVO jmListvo) throws RemoteException {
		int result = 0;
		try {
			result = jmemListDao.updateJMemClick(jmListvo);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<JMemListVO> selectCareerJMemList(String career) throws RemoteException {
		List<JMemListVO> result = null;
		try {
			result = jmemListDao.selectCareerJMemList(career);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<JMemListVO> selectDeptJMemList(String dept) throws RemoteException {
		List<JMemListVO> result = null;
		try {
			result = jmemListDao.selectDeptJMemList(dept);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<JMemListVO> selectNameJMemList(String name) throws RemoteException {
		List<JMemListVO> result = null;
		try {
			result = jmemListDao.selectNameJMemList(name);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}
