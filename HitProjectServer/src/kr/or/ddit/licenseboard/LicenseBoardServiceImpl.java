package kr.or.ddit.licenseboard;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;


public class LicenseBoardServiceImpl extends UnicastRemoteObject implements ILicenseBoardService{
	
	private static ILicenseBoardService licService;
	private ILicenseBoardDao licDao;
	public static ILicenseBoardService getInstance() throws RemoteException {
		if(licService==null) {
			licService = new LicenseBoardServiceImpl();
		}
		return licService;
	}
	
	protected LicenseBoardServiceImpl() throws RemoteException {
		licDao=LicenseBoardDaoImpl.getInstance();
	}

	@Override
	public int insertBoard(LicenseBoardVO licensevo) throws RemoteException {
		return licDao.insertBoard(licensevo);
	}

	@Override
	public List<LicenseBoardVO> getAllBoard() throws RemoteException {
		return licDao.getAllBoard();
	}

	@Override
	public int deleteBoard(String liccode) throws RemoteException {
		return licDao.deleteBoard(liccode);
	}

	@Override
	public int updateBoard(LicenseBoardVO licensevo) throws RemoteException {
		return licDao.updateBoard(licensevo);
	}

}
