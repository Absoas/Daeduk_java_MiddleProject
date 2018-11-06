package kr.or.ddit.corRegno;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import kr.or.ddit.ibatis.config.SqlMapClientFatory;

public class CorRegnoDaoImpl implements ICorRegnoDao{

	private static ICorRegnoDao corRegnoDao;
	private SqlMapClient smc;
	
	private CorRegnoDaoImpl() {
		smc = SqlMapClientFatory.getSqlMapClient();
	}
	
	
	public static ICorRegnoDao getInstance() {
		if(corRegnoDao==null) {
			corRegnoDao = new CorRegnoDaoImpl();
		}
		return corRegnoDao;
	}
	
	
	@Override
	public List<CorRegnoVO> selectCorRegno() throws SQLException {
		return smc.queryForList("corRegno.selectCorRegno");
	}


	@Override
	public List<CorRegnoVO> searchCorRegno(HashMap<String, String> corRegnoMap) throws SQLException {
		return smc.queryForList("corRegno.searchCorRegno",corRegnoMap);
	}


	@Override
	public List<CorRegnoVO> searchCorName(HashMap<String, String> corRegnoMap) throws SQLException {
		return smc.queryForList("corRegno.searchCorName",corRegnoMap);
	}

}
