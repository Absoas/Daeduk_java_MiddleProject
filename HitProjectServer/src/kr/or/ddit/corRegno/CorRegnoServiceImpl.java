package kr.or.ddit.corRegno;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;


public class CorRegnoServiceImpl extends UnicastRemoteObject implements ICorRegnoService{

	private static ICorRegnoService corRegnoService;
	private ICorRegnoDao corRegnoDao;
	public static ICorRegnoService getInstance() throws RemoteException {
		if (corRegnoService==null) {
			corRegnoService=new CorRegnoServiceImpl();
		}
		return corRegnoService;
	}
	private CorRegnoServiceImpl() throws RemoteException{
		corRegnoDao=CorRegnoDaoImpl.getInstance();
	}
	
	
	@Override
	public List<CorRegnoVO> selectCorRegno() {
		List<CorRegnoVO> result = null;
		try {
			result= corRegnoDao.selectCorRegno();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	@Override
	public List<CorRegnoVO> searchCorRegno(HashMap<String, String> corRegnoMap) throws RemoteException {
		List<CorRegnoVO> result = null;
		try {
			result= corRegnoDao.searchCorRegno(corRegnoMap);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	@Override
	public List<CorRegnoVO> searchCorName(HashMap<String, String> corRegnoMap) throws RemoteException {
		List<CorRegnoVO> result = null;
		try {
			result= corRegnoDao.searchCorName(corRegnoMap);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
