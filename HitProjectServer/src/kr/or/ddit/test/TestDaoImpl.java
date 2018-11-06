package kr.or.ddit.test;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.ibatis.config.SqlMapClientFatory;

public class TestDaoImpl implements ITestDao {
	SqlMapClient smc;
	
	private static ITestDao testdao;
	
	public static ITestDao getInstance(){
		if(testdao == null) {
			testdao = new TestDaoImpl();
		}
		return testdao;
	}
	
	private TestDaoImpl(){
		smc = SqlMapClientFatory.getSqlMapClient();
	}
	

	@Override
	public List<TestVO> selectCoding() throws SQLException {
		return smc.queryForList("test.selecttest");
	}

	@Override
	public List<TestVO> selectCombo(int num) throws SQLException {
		return smc.queryForList("test.selecttype",num);
	}

	@Override
	public int inserttest(TestVO testvo) throws SQLException {
		int cnt = 0;
		Object obj = smc.insert("test.inserttest", testvo);
		if (obj == null) {
			cnt = 1;
		}
		return cnt;
	}

	@Override
	public int deletetest(int testno) throws SQLException {
		int cnt = 0;
		cnt = smc.delete("test.deletetest",testno);
		return cnt;
	}

	@Override
	public int updatetest(TestVO testvo) throws SQLException {
		int cnt = 0;
		cnt = smc.update("test.updatetest",testvo);
		return cnt;
	}

	@Override
	public TestVO selectTestInfo(int test_no) throws SQLException {
		return (TestVO) smc.queryForObject("test.selectTestInfo",test_no);
	}
	
	

}
