package kr.or.ddit.revboard;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;



public interface IRevBoardService extends Remote{
	public boolean insert(RevBoardVO rv) throws RemoteException;
	
	public List<RevBoardVO> selectAll() throws RemoteException;

	public int deleteBoard(int rev_board_no) throws RemoteException;
	
	public boolean updateBoard(RevBoardVO rev_board_no) throws RemoteException;
	
	//조회수
	public int clickBoard(RevBoardVO rev_board_no) throws RemoteException;
	
}
