package kr.or.ddit.test;

import java.util.List;

public interface ITestService {
	/**
	 * 전체코딩문제조회
	 * @return
	 */
	public List<TestVO> selectCoding();
	
	/**
	 * 콤보박스조회
	 * @param num
	 * @return
	 */
	public List<TestVO> selectCombo(int num);
	
	/**
	 * 코딩문제추가
	 * @param testvo
	 * @return
	 */
	public int inserttest (TestVO testvo);
	
	/**
	 * 선택글삭제
	 * @param testno
	 * @return
	 */
	public int deletetest (int testno);
	
	/**
	 * 선택글 수정
	 * @param testvo
	 * @return
	 */
	public int updatetest (TestVO testvo);
	
	public TestVO selectTestInfo(int test_no);

}
