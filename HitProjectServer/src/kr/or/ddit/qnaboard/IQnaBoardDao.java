package kr.or.ddit.qnaboard;

import java.util.List;

import javafx.collections.ObservableList;

public interface IQnaBoardDao {
	public boolean insert(QnaBoardVO qv);
	
	public List<QnaBoardVO> selectAll();
	
	public int deleteBoard(int qna_board_no);
	
	public boolean updateBoard(QnaBoardVO qna_board_no);
	
	public int clickBoard(QnaBoardVO qna_board_no);
}
