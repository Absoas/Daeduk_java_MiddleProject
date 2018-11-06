package kr.or.ddit.passIntroBoard;

import java.util.List;

public interface IPassIntroBoardService {
	/**
	 * 전체게시판 목록을 가져오는 메서드
	 * @return PassboardVO타입인 ObservableList로 반환
	 */
	public List<PassIntroboardVO> selectpassboard();
	
	
	/**
	 * 조회수를 늘려주는 메서드 
	 * @param passVO
	 */
	public int countNotice(PassIntroboardVO  passVO);
	
	/**
	 * 글삭제메서드
	 * @param passno
	 * @return
	 */
	public int deletepassboard(int passno);
	
	/**
	 * 글추가
	 * @param passVO
	 * @return
	 */
	public int insertpassboard(PassIntroboardVO passVO);
	
	/**
	 * 글수정
	 * @param passVO
	 * @return
	 */
	public int updatepassboard(PassIntroboardVO passVO);

}
