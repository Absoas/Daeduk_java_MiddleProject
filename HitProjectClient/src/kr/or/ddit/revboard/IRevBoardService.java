package kr.or.ddit.revboard;

import java.rmi.RemoteException;
import java.util.List;




public interface IRevBoardService{
	public boolean insert(RevBoardVO rv);
	
	public List<RevBoardVO> selectAll();

	public int deleteBoard(int rev_board_no);
	
	public boolean updateBoard(RevBoardVO rev_board_no);
	
	//조회수
	public int clickBoard(RevBoardVO rev_board_no);
	
}
