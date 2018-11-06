package kr.or.ddit.qnaboard;

import java.util.List;


public interface IQnaBoardService{
	public boolean insert(QnaBoardVO qv);
	
	public List<QnaBoardVO> selectAll();

	public int deleteBoard(int qna_board_no);
	
	public boolean updateBoard(QnaBoardVO qna_board_no);
	
	public int clickBoard(QnaBoardVO qna_board_no);
}
