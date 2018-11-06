package kr.or.ddit.test;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ITestService extends Remote{
	/**
	 * 전체코딩문제 조회
	 * @return
	 * @throws RemoteException
	 */
	public List<TestVO> selectCoding() throws RemoteException;
	/**
	 * 콤보박스조회
	 * @return
	 * @throws RemoteException
	 */
	
	public List<TestVO> selectCombo(int num) throws RemoteException;
	
	/**
	 * 코딩문제추가
	 * @param testvo
	 * @return
	 * @throws RemoteException
	 */
	public int inserttest (TestVO testvo) throws RemoteException;
	/**
	 * 선택글 삭제
	 * @param testvo
	 * @return
	 * @throws RemoteException
	 */
	
	public int deletetest (int testno) throws RemoteException;
	
	/**
	 * 선택글 수정
	 * @param testvo
	 * @return
	 * @throws RemoteException
	 */
	public int updatetest (TestVO testvo) throws RemoteException;
	
	
	public TestVO selectTestInfo(int test_no) throws RemoteException;

}
