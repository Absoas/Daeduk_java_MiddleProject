package kr.or.ddit.mylicense;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.List;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import kr.or.ddit.ibatis.config.SqlMapClientFatory;

public class MylicDaoImpl implements IMylicDao{
	SqlMapClient smc;
	private static IMylicDao MylicDao;
	
	public static IMylicDao getInstance() {
		if(MylicDao == null) {
			MylicDao = new MylicDaoImpl();
		}
		return MylicDao;
	}
	
	private MylicDaoImpl() {
		smc = SqlMapClientFatory.getSqlMapClient();
	}

	@Override
	public List<MylicVO> selectMylic(String mem_id) throws SQLException{
		List<MylicVO> myliclist = null; 
		myliclist = smc.queryForList("Mylic.selectMylic", mem_id);
		return myliclist;
	}

	
	@Override
	public int insertLic(MylicVO mylicvo) throws SQLException {
		int cnt = 0;
		Object obj = smc.insert("Mylic.insertMylic", mylicvo);
		if(obj == null) {
			cnt = 1;
		}
		return cnt;
	}

	@Override
	public int deleteLic(MylicVO mylicvo) throws SQLException {
		int cnt = 0;
		cnt = smc.delete("Mylic.deleteMylic", mylicvo);
		return cnt;
	}


}
