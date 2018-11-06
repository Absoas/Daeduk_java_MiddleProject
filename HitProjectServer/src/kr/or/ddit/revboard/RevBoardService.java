package kr.or.ddit.revboard;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import javafx.collections.ObservableList;



public class RevBoardService extends UnicastRemoteObject implements IRevBoardService{
	private IRevBoardDao dao;
	
	private static IRevBoardService bservice;
	
	
	public static IRevBoardService getServerInstance() throws RemoteException {
		if (bservice==null) {
			bservice=new RevBoardService();
		}
		return bservice;
	}
	
	private RevBoardService() throws RemoteException{
		dao = RevBoardDaoImpl.getInstance();
	}
	
	@Override
	public boolean insert(RevBoardVO rv) throws RemoteException{
		return dao.insert(rv);
	}

	@Override
	public List<RevBoardVO> selectAll() throws RemoteException {
		return dao.selectAll();
	}

	@Override
	public int deleteBoard(int rev_board_no) throws RemoteException{
		return dao.deleteBoard(rev_board_no);
	}

	@Override
	public boolean updateBoard(RevBoardVO rev_board_no) throws RemoteException{
		return dao.updateBoard(rev_board_no);
	}

	@Override
	public int clickBoard(RevBoardVO rev_board_no) throws RemoteException {
		int cnt = 0;
		return cnt = dao.clickBoard(rev_board_no);
	}



}
