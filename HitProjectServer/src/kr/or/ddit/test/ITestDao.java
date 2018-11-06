package kr.or.ddit.test;

import java.sql.SQLException;
import java.util.List;

public interface ITestDao {
	/**
	 * 전체 코딩문제 조회
	 * @return
	 * @throws SQLException
	 */
	public List<TestVO> selectCoding()throws SQLException;
	
	/**
	 * 콤보박스 조회
	 * @return
	 * @throws SQLException
	 */
	public List<TestVO> selectCombo(int num) throws SQLException;
	
	/**
	 * 코딩문제추가
	 * @param testvo
	 * @return
	 * @throws SQLException
	 */
	public int inserttest(TestVO testvo) throws SQLException;
	
	/**
	 * 선택글삭제
	 * @param testvo
	 * @return
	 * @throws SQLException
	 */
	public int deletetest (int testno)throws SQLException;
	
	/**
	 * 선택글 수정
	 * @param testvo
	 * @return
	 * @throws SQLException
	 */
	public int updatetest (TestVO testvo) throws SQLException;
	
	public TestVO selectTestInfo(int test_no) throws SQLException;

}
