package kr.or.ddit.passIntroBoard;

import java.sql.SQLException;
import java.util.List;



public interface IPassIntroBoardDao {
	/**
	 * 전체게시판 목록을 가져오는 메서드
	 * @returnPassboardVO타입인 ObservableList로 반환
	 */
	public List<PassIntroboardVO> selectpassboard()throws SQLException;
	
	/**
	 * 조회수를 늘려주는 메서드
	 * @param passVO
	 * @return
	 */
	public int countNotice(PassIntroboardVO  passVO)throws SQLException;
	
	
	/**
	 * 글삭제
	 * @param passno
	 * @return
	 * @throws SQLException
	 */
	public int deletepassboard(int passno) throws SQLException;
	
	/**
	 * 글추가
	 * @param passVO
	 * @return
	 * @throws SQLException
	 */
	public int insertpassboard(PassIntroboardVO passVO) throws SQLException;
	
	/**
	 * 글수정
	 * @param passVO
	 * @return
	 * @throws SQLException
	 */
	public int updatepassboard(PassIntroboardVO passVO) throws SQLException;
	
	
}
