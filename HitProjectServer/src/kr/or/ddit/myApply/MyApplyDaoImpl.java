package kr.or.ddit.myApply;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.ibatis.config.SqlMapClientFatory;

public class MyApplyDaoImpl implements IMyApplyDao {
	SqlMapClient smc;

	private static IMyApplyDao myapplydao;

	public static IMyApplyDao getInstance() {
		if (myapplydao == null) {
			myapplydao = new MyApplyDaoImpl();
		}
		return myapplydao;
	}

	private MyApplyDaoImpl() {
		smc = SqlMapClientFatory.getSqlMapClient();
	}

	@Override
	public List<MyApplyVO> selectMyApply(String mem_id) throws SQLException {
		return smc.queryForList("myApply.select", mem_id);
	}

	@Override
	public boolean insertApply(MyApplyVO vo) throws SQLException {
		boolean result = false;
		Object obj = smc.insert("myApply.insertApply", vo);
		if (obj == null) {
			result = true;
		}
		return result;
	}

	@Override
	public int selectTestNo(MyApplyVO vo) throws SQLException {
		return (int) smc.queryForObject("myApply.selectTest_no", vo);
	}
	
	

}
