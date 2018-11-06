package kr.or.ddit.jmemlist;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.ibatis.config.SqlMapClientFatory;


public class JMemListDaoImpl implements IJMemListDao{
	private static IJMemListDao jmemListDao;
	
	private SqlMapClient smc;
	
	private JMemListDaoImpl() {
		smc = SqlMapClientFatory.getSqlMapClient();
	}
	
	public static IJMemListDao getInstance() {
		if(jmemListDao == null) {
			jmemListDao = new JMemListDaoImpl();
		}
		return jmemListDao;
	}

	@Override
	public List<JMemListVO> selectJMemList() throws SQLException {
		return smc.queryForList("jmemList.selectJMemList");
	}

	@Override
	public int updateJMemClick(JMemListVO jmListvo) throws SQLException {
		return smc.update("jmemList.updateJMemList",jmListvo);
	}

	@Override
	public List<JMemListVO> selectCareerJMemList(String career) throws SQLException {
		return smc.queryForList("jmemList.selectCareerJMemList", career);
	}

	@Override
	public List<JMemListVO> selectDeptJMemList(String dept) throws SQLException {
		return smc.queryForList("jmemList.selectDeptJMemList" ,dept);
	}

	@Override
	public List<JMemListVO> selectNameJMemList(String name) throws SQLException {
		return smc.queryForList("jmemList.selectNameJMemList" ,name);
	}
	
	
	
	
	
	
}
