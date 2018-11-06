package kr.or.ddit.revboard;

import java.util.List;

import javafx.collections.ObservableList;

public interface IRevBoardDao {
	public boolean insert(RevBoardVO rv);
	
	public List<RevBoardVO> selectAll();
	
	public int deleteBoard(int rev_board_no);
	
	public boolean updateBoard(RevBoardVO rev_board_no);
	
	//조회수 증가
	public int clickBoard(RevBoardVO rev_board_no);
	
	
}
