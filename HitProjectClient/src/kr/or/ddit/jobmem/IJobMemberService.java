package kr.or.ddit.jobmem;

import java.util.List;

public interface IJobMemberService {
	public JobMemberVO Login_Check(JobMemberVO loginMember);

	public boolean insertMember(JobMemberVO jobMemberVO);

	/* public boolean insertMember(JobMemberVO jobMemberVO); */

	public boolean memberChkId(String memId);

	public List<JobMemberVO> selectJobMemberInfo();

	public int updateJobMember(JobMemberVO jobMemVO);

	public int deleteJobMember(String memId);

	public int updateMyInfo(JobMemberVO jobMemVO);

	// 회원가입
	// (ITMEMBER)
	public boolean signupMain(JobMemberVO signupIt);

	// (JOBMEMBER)
	public boolean signupSub(JobMemberVO signupJob);
	
	public List<JobMemberVO> selectJobConId(String memId);
	public List<JobMemberVO> selectJobConSta(String jState);
	public List<JobMemberVO> selectJobConAddr(String jAddr);
	
	public String memFindID(JobMemberVO IDFinder);
	
	public String memFindPW(JobMemberVO PWFinder);
	
	public int updateProfileImg(JobMemberVO jmemvo);
	
	public JobMemberVO selectApplyMember(String mem_name);
	
	   

}