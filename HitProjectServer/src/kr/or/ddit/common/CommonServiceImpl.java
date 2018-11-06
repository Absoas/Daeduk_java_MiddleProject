package kr.or.ddit.common;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.List;


public class CommonServiceImpl extends UnicastRemoteObject implements ICommonService{

	
	private static ICommonService comService;
	private ICommonDao comDao;
	
	public static ICommonService getInstance() throws RemoteException{
		if(comService == null) {
			comService = new CommonServiceImpl();
		}
		return comService;
	}
	
	private CommonServiceImpl() throws RemoteException {
		comDao = CommonDaoImpl.getInstance();
	}
	
	@Override
	public List<ZipVO> searchZip(String zip) throws RemoteException {
		List<ZipVO>  list  = null;
		try {
			list = comDao.searchZip(zip);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<ZipVO> searchDong(String dong) throws RemoteException {
		List<ZipVO>  list  = null;
		try {
			list = comDao.searchDong(dong);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
}
