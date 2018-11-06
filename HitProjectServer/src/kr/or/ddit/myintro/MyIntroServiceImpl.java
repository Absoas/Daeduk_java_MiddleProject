package kr.or.ddit.myintro;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;


public class MyIntroServiceImpl extends UnicastRemoteObject implements IMyIntroService {
	private static IMyIntroService myIntroService;
	private IMyIntroDao myIntroDao;
	
	public static IMyIntroService getInstance() throws RemoteException {
		if (myIntroService==null) {
			myIntroService = new MyIntroServiceImpl();
		}
		return myIntroService;
	}
	private MyIntroServiceImpl() throws RemoteException{
		myIntroDao=MyIntroDaoImpl.getInstance();
	}
	@Override
	public MyIntroVO selectMyIntro(String mem_id) throws RemoteException {
		MyIntroVO result = null;
		try {
			result =  myIntroDao.selectMyIntro(mem_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	@Override
	public int insertMyIntro(MyIntroVO newIntro) throws RemoteException {
		int result = 0;
		try {
			result =  myIntroDao.insertMyIntro(newIntro);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	@Override
	public int updateMyIntro(MyIntroVO newIntro) throws RemoteException {
		int result = 0;
		try {
			result =  myIntroDao.updateMyIntro(newIntro);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}
