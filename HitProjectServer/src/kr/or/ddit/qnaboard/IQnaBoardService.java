package kr.or.ddit.qnaboard;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;


public interface IQnaBoardService extends Remote{
	public boolean insert(QnaBoardVO qv) throws RemoteException;
	
	public List<QnaBoardVO> selectAll() throws RemoteException;

	public int deleteBoard(int qna_board_no) throws RemoteException;
	
	public boolean updateBoard(QnaBoardVO qna_board_no) throws RemoteException;

	public int clickBoard(QnaBoardVO qna_board_no) throws RemoteException;
}
