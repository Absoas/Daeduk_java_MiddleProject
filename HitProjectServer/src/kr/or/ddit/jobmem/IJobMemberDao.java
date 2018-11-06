package kr.or.ddit.jobmem;

import java.sql.SQLException;
import java.util.List;

import kr.or.ddit.cormem.CorMemberVO;
import kr.or.ddit.jobmem.JobMemberVO;

public interface IJobMemberDao {
	public JobMemberVO Login_Check(JobMemberVO loginMember) throws SQLException;

	public boolean insertMember(JobMemberVO jobMemberVO) throws SQLException;

	public boolean memberChkId(String memId) throws SQLException;

	public List<JobMemberVO> selectJobMemberInfo() throws SQLException;

	public int updateJobMember(JobMemberVO jobMemVO) throws SQLException;

	public int deleteJobMember(String memId) throws SQLException;

	public int updateMyInfo(JobMemberVO jobMemVO) throws SQLException;

	public boolean signupMain(JobMemberVO signupIt) throws SQLException;

	// (JOBMEMBER)
	public boolean signupSub(JobMemberVO signupJob) throws SQLException;

	public List<JobMemberVO> selectJobConId(String memId) throws SQLException;

	public List<JobMemberVO> selectJobConSta(String jState) throws SQLException;

	public List<JobMemberVO> selectJobConAddr(String jAddr) throws SQLException;

	// 구직회원 ID 찾기
	public String memFindID(JobMemberVO IDFinder) throws SQLException;

	// 구직회원 비밀번호 찾기
	public String memFindPw(JobMemberVO PwFinder) throws SQLException;

	public int updateProfileImg(JobMemberVO jmemvo) throws SQLException;
	
	public JobMemberVO selectApplyMember(String mem_name) throws SQLException;
}