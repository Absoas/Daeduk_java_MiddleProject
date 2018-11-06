package kr.or.ddit.cormem;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.ibatis.config.SqlMapClientFatory;
import kr.or.ddit.jobmem.JobMemberVO;

public class CorMemberDaoImpl implements ICorMemberDao {

	private static ICorMemberDao corDao;
	private CorMemberVO corVO;
	private SqlMapClient smc;

	private CorMemberDaoImpl() {
		smc = SqlMapClientFatory.getSqlMapClient();
	}

	public static ICorMemberDao getInstance() {
		if (corDao == null) {
			corDao = new CorMemberDaoImpl();
		}
		return corDao;
	}

	// id중복체크
	@Override
	public boolean idCheck(String colId) throws SQLException {
		boolean result = false;
		try {
			result = corDao.idCheck(colId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;

	}

	@Override
	public CorMemberVO Login_Check(CorMemberVO loginMember) throws SQLException {
		return (CorMemberVO) smc.queryForObject("corMember.loginCheck", loginMember);
	}

	@Override
	public List<CorMemberVO> selectCorMemberInfo() throws SQLException {
		return smc.queryForList("corMember.selectCorMemberInfo");
	}

	@Override
	public int deleteCorMember(String memId) throws SQLException {
		int result = 0;
		
		int cnt1 = smc.delete("corMember.deleteCorMember", memId);
		int cnt2 = smc.delete("corMember.deleteCorMember2", memId);
		
		if(cnt1==1 && cnt2==2) {
			result = 1;
		}
		return result;
	}

	@Override
	public int updateCorMember(CorMemberVO corMemVO) throws SQLException {
		return smc.update("corMember.updateCorMember", corMemVO);
	}

	// 회원가입
	@Override
	public boolean signupMain(CorMemberVO signup) throws SQLException {
		boolean result = false;
		System.out.println(signup.getCor_id());
		Object obj = smc.insert("corMember.insertCorpMain", signup);
		if (obj == null) {
			result = true;
		}
		return result;
	}

	@Override
	public boolean signupSub(CorMemberVO signup) throws SQLException {
		boolean result = false;
		Object obj = smc.insert("corMember.insertCorpSub", signup);
		if (obj == null) {
			result = true;
		}
		return result;
	}

	@Override
	public int updateMyInfo(CorMemberVO cmemVO) throws SQLException {
		return smc.update("corMember.updateMyInfo", cmemVO);
	}

	@Override
	public List<CorMemberVO> selectCorConId(String memId) throws SQLException {
		return smc.queryForList("corMember.selectCorConId", memId);
	}

	@Override
	public List<CorMemberVO> selectCorConSta(String cState) throws SQLException {
		return smc.queryForList("corMember.selectCorConSta", cState);
	}

	@Override
	public String CormemFindID(CorMemberVO corIDFinder) throws SQLException {
		return (String) smc.queryForObject("FindID.corFindID", corIDFinder);
	}
	
	@Override
	public String cormemFindPw(CorMemberVO corPwFinder) throws SQLException {
		return (String) smc.queryForObject("FindPW.corFindPW", corPwFinder);
	}

	@Override
	public int updateProfileImg(CorMemberVO sessionMember) throws SQLException {
		return smc.update("corMember.updateProfileImg",sessionMember);
	}
}