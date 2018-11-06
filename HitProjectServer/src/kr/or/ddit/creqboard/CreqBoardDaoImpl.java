package kr.or.ddit.creqboard;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.ibatis.config.SqlMapClientFatory;

public class CreqBoardDaoImpl implements ICreqBoardDao {

	private static ICreqBoardDao creqDao;
	private SqlMapClient smc;

	private CreqBoardDaoImpl() {
		smc = SqlMapClientFatory.getSqlMapClient();
	}

	public static ICreqBoardDao getInstance() {
		if (creqDao == null) {
			creqDao = new CreqBoardDaoImpl();
		}
		return creqDao;
	}

	@Override
	public List<CReqBoardVO> selectCReqBoard() throws SQLException {
	    return smc.queryForList("creq.selectCReqBoard");
	}

	@Override
	public boolean insertCreqBoard(CReqBoardVO creqVO) throws SQLException {
		boolean result = false;
		Object obj = smc.insert("creq.insertCreqBoard", creqVO);
		if (obj == null) {
			result = true;
		}
		return result;
	}

	@Override
	public int deleteCreqBoard(String corid) throws SQLException {
		return smc.delete("creq.deleteCreqBoard", corid);
	}

	@Override
	public int updateCreqBoard(CReqBoardVO creqVO) throws SQLException {
		return smc.update("creq.updateCreqBoard", creqVO);
	}
	
	@Override
	public boolean insertApply_board(String mem_id) throws SQLException {
		boolean result = false;
		Object obj = smc.insert("creq.insertApplyBoard", mem_id);
		if (obj == null) {
			result = true;
		}
		return result;
	}

	@Override
	public List<String> selectCReqName() throws SQLException {
		return smc.queryForList("creq.selectCReqName");
	}

	@Override
	public CReqBoardVO selectCreqBoardInfo(String corName) throws SQLException {
		return (CReqBoardVO) smc.queryForObject("creq.selectCreqBoardInfo", corName);
	}

	@Override
	public int updateCreqClick(CReqBoardVO creqVO) throws SQLException {
		return smc.update("creq.updateCreqClick", creqVO);
	}

	@Override
	public List<String> selectCreqArea(String corArea) throws SQLException {
		return smc.queryForList("creq.selectCreqArea", corArea);
	}

	@Override
	public List<String> selectCreqCorName(String corName) throws SQLException {
		return smc.queryForList("creq.selectCreqCorName", corName);
	}

	@Override
	public List<String> selectCreqSal(String creqSalary) throws SQLException {
		return smc.queryForList("creq.selectCreqSal", creqSalary);
	}
	
	@Override
	public List<String> setRanckView() throws SQLException {
		return smc.queryForList("creq.setRanckView");
	}

	
	
}