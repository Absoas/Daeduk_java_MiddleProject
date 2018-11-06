package kr.or.ddit.corApply;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.ibatis.config.SqlMapClientFatory;

public class CorApplyDaoImpl implements ICorApplyDao{
	private static ICorApplyDao dao;
	private SqlMapClient smc;
	
	private CorApplyDaoImpl() {
		smc = SqlMapClientFatory.getSqlMapClient();
	}
	
	public static ICorApplyDao getInstance() {
		if(dao == null) {
			dao = new CorApplyDaoImpl();
		}
		return dao;
	}

	@Override
	public List<CorApplyVO> selectCorApplyInfo(String corId) throws SQLException {
		return smc.queryForList("corApply.selectCorApplyInfo", corId);
	}

	@Override
	public List<CorApplyVO> selectComboCorApply(CorApplyVO corAppVO) throws SQLException {
		return smc.queryForList("corApply.selectComboCorApply", corAppVO);
	}

	@Override
	public int updateComboCorApply(CorApplyVO corAppVO) throws SQLException {
		return smc.update("corApply.updateComboCorApply", corAppVO);
	}
	
	@Override
	public List<CorDetailVO> selectDetilList() throws SQLException {
		return smc.queryForList("corApply.selectDetailList");
	}
}
