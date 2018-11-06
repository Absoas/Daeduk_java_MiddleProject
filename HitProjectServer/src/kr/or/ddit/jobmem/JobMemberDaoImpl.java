package kr.or.ddit.jobmem;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.cormem.CorMemberVO;
import kr.or.ddit.ibatis.config.SqlMapClientFatory;

public class JobMemberDaoImpl implements IJobMemberDao {
	private static IJobMemberDao jMemDao;
	JobMemberVO vo;

	private SqlMapClient smc;

	private JobMemberDaoImpl() {
		smc = SqlMapClientFatory.getSqlMapClient();
	}

	public static IJobMemberDao getInstance() {
		if (jMemDao == null) {
			jMemDao = new JobMemberDaoImpl();
		}
		return jMemDao;
	}

	@Override
	public JobMemberVO Login_Check(JobMemberVO loginMember) throws SQLException {
		return (JobMemberVO) smc.queryForObject("jobMember.loginCheck", loginMember);
	}

	@Override
	public boolean insertMember(JobMemberVO jobMemberVO) throws SQLException {
		return false;
	}

	@Override
	public boolean memberChkId(String memId) throws SQLException {
		boolean result = false;
		JobMemberVO obj = (JobMemberVO) smc.queryForObject("jobMember.chkIdMember", memId);
		if (obj == null) {
			result = true;
		}
		return result;
	}

	@Override
	public List<JobMemberVO> selectJobMemberInfo() throws SQLException {
		return smc.queryForList("jobMember.selectJobMemberInfo");
	}

	@Override
	public int updateJobMember(JobMemberVO jobMemVO) throws SQLException {
		return smc.update("jobMember.updateJobMember", jobMemVO);
	}

	@Override
	public int deleteJobMember(String memId) throws SQLException {
		int result = 0;
		int cnt1 = smc.delete("jobMember.deleteJobMember", memId);
		int cnt2 = smc.delete("jobMember.deleteItMember", memId);
		
		if(cnt1==1 && cnt2 ==1) {
			result = 1;
		}
		return result;
	}

	@Override
	public int updateMyInfo(JobMemberVO jobMemVO) throws SQLException {
		int result = 0;
		int cnt1 = smc.update("jobMember.updateMyInfoItmem", jobMemVO);
		int cnt2 = smc.update("jobMember.updateMyInfoJmem", jobMemVO);
		if (cnt1 > 0 && cnt2 > 0) {
			result = 1;
		}
		return result;
	}

	// 회원가입(ITMEMBER)
	@Override
	public boolean signupMain(JobMemberVO signupIt) throws SQLException {
		boolean result = false;
		Object obj = smc.insert("jobMember.insertItMember", signupIt);
		if (obj == null) {
			result = true;
		}
		return result;
	}

	// 회원가입(JOBMEMBER)
	@Override
	public boolean signupSub(JobMemberVO signupJob) throws SQLException {
		boolean result = false;
		Object obj = smc.insert("jobMember.insertJobMember", signupJob);
		if (obj == null) {
			result = true;
		}
		return result;
	}

	@Override
	public List<JobMemberVO> selectJobConId(String memId) throws SQLException {
		return smc.queryForList("jobMember.selectJobConId", memId);
	}

	@Override
	public List<JobMemberVO> selectJobConSta(String jState) throws SQLException {
		return smc.queryForList("jobMember.selectJobConSta", jState);
	}

	@Override
	public List<JobMemberVO> selectJobConAddr(String jAddr) throws SQLException {
		return smc.queryForList("jobMember.selectJobConAddr", jAddr);
	}
	
	@Override
	public String memFindID(JobMemberVO IDFinder) throws SQLException {
		return (String) smc.queryForObject("FindID.memFindID",IDFinder);
	}

	@Override
	public String memFindPw(JobMemberVO PwFinder) throws SQLException {
		return (String) smc.queryForObject("FindPW.memFindPW", PwFinder);
	}

	@Override
	public int updateProfileImg(JobMemberVO jmemvo) throws SQLException {
		return smc.update("jobMember.updateProfileImg",jmemvo);
	}

	@Override
	public JobMemberVO selectApplyMember(String mem_name) throws SQLException {
		return (JobMemberVO) smc.queryForObject("jobMember.selectApplyMember",mem_name);
	}

	

}