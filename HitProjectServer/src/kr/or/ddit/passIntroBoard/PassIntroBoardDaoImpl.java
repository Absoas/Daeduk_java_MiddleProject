package kr.or.ddit.passIntroBoard;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.ibatis.config.SqlMapClientFatory;

public class PassIntroBoardDaoImpl implements IPassIntroBoardDao{
	SqlMapClient smc;
	
	private static IPassIntroBoardDao passIntroDao;
	
	public static  IPassIntroBoardDao getInstance() {
		if(passIntroDao == null) {
			passIntroDao = new PassIntroBoardDaoImpl();
		}
		return passIntroDao;
	}
	
	private PassIntroBoardDaoImpl() {
		smc = SqlMapClientFatory.getSqlMapClient();
	}

	@Override
	public List<PassIntroboardVO> selectpassboard() throws SQLException {
		return smc.queryForList("passIntro.selectpassboard");
	}

	@Override
	public int countNotice(PassIntroboardVO passVO) throws SQLException {
		int cnt = 0;
		cnt = smc.update("passIntro.updatecount", passVO);
		return cnt;
	}

	@Override
	public int deletepassboard(int passno) throws SQLException {
		int cnt = 0;
		cnt = smc.delete("passIntro.deletepassboard", passno);
		return cnt;
	}

	@Override
	public int insertpassboard(PassIntroboardVO passVO) throws SQLException {
		int cnt = 0;
		Object obj = smc.insert("passIntro.insertpassboard", passVO);
		if (obj == null) {
			cnt = 1;
		}
		return cnt;
	}

	@Override
	public int updatepassboard(PassIntroboardVO passVO) throws SQLException {
		int cnt = 0;
		cnt = smc.update("passIntro.updatepassboard", passVO);
		return cnt;
	}
	
	

}
