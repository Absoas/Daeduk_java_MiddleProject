package kr.or.ddit.test;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.List;

public class TestServiceImpl extends UnicastRemoteObject implements ITestService {
	private ITestDao testdao; 
	private static ITestService testservice;
	
	public static ITestService getInstance() throws RemoteException{
		if(testservice == null) {
			testservice =  new TestServiceImpl();
		}
		return testservice;
	}
	private TestServiceImpl() throws RemoteException {
		testdao = TestDaoImpl.getInstance();
	}
	
	@Override
	public List<TestVO> selectCoding() throws RemoteException {
		List<TestVO> selectCodingList = null;
		try {
			selectCodingList = testdao.selectCoding();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return selectCodingList;
		
	}
	@Override
	public List<TestVO> selectCombo(int num) throws RemoteException {
		List<TestVO> selectComboList = null;
		try {
			selectComboList = testdao.selectCombo(num);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return selectComboList;
	}
	@Override
	public int inserttest(TestVO testvo) throws RemoteException {
		int cnt = 0;
		try {
			cnt = testdao.inserttest(testvo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}
	@Override
	public int deletetest(int testno) throws RemoteException {
		int cnt = 0;
		try {
			cnt = testdao.deletetest(testno);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}
	@Override
	public int updatetest(TestVO testvo) throws RemoteException {
		int cnt = 0;
		try {
			cnt = testdao.updatetest(testvo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}
	@Override
	public TestVO selectTestInfo(int test_no) throws RemoteException {
		TestVO result = null;
		try {
			result = testdao.selectTestInfo(test_no);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

}
