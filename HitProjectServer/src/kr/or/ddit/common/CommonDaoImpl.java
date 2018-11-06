package kr.or.ddit.common;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.List;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import kr.or.ddit.ibatis.config.SqlMapClientFatory;


public class CommonDaoImpl implements ICommonDao {
	
	private static ICommonDao comDao;
	private SqlMapClient smc;
	
	private CommonDaoImpl() {
		smc = SqlMapClientFatory.getSqlMapClient();
	}
	
	public static ICommonDao getInstance() {
		if(comDao == null) {
			comDao = new CommonDaoImpl();
		}
		return comDao;
	}

	@Override
	public List<ZipVO> searchZip(String zip) throws SQLException {
		return smc.queryForList("zipTest.getAddrZip",zip);
	}

	@Override
	public List<ZipVO> searchDong(String dong) throws SQLException {
		return smc.queryForList("zipTest.getAddrDong",dong);
	}
	
	
}	
