package kr.or.ddit.myApply;

import java.sql.SQLException;
import java.util.List;

public interface IMyApplyDao {
	/**
	 * 내가신청한 기업조회
	 * @return
	 * @throws SQLException
	 */
	public List<MyApplyVO> selectMyApply(String mem_id) throws SQLException;
	public boolean insertApply(MyApplyVO vo) throws SQLException;
	public int selectTestNo(MyApplyVO vo)  throws SQLException;
}
