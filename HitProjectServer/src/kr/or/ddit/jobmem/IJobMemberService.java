package kr.or.ddit.jobmem;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.cormem.CorMemberVO;


public interface IJobMemberService extends Remote{
   public JobMemberVO Login_Check(JobMemberVO loginMember) throws RemoteException;
   public boolean insertMember(JobMemberVO jobMemberVO) throws RemoteException;
   
   public boolean memberChkId(String memId) throws RemoteException;
   
   public List<JobMemberVO> selectJobMemberInfo() throws RemoteException;
   public int updateJobMember(JobMemberVO jobMemVO) throws RemoteException;
   public int deleteJobMember(String memId) throws RemoteException;
   public int updateMyInfo(JobMemberVO jobMemVO) throws RemoteException;
   
   //회원가입
   //(ITMEMBER)
   public boolean signupMain(JobMemberVO signupIt) throws RemoteException;
   //(JOBMEMBER)
   public boolean signupSub(JobMemberVO signupJob) throws RemoteException;
   
   public List<JobMemberVO> selectJobConId(String memId) throws RemoteException;
   public List<JobMemberVO> selectJobConSta(String jState) throws RemoteException;
   public List<JobMemberVO> selectJobConAddr(String jAddr) throws RemoteException;
   
   public String memFindID(JobMemberVO IDFinder) throws RemoteException;
   
 //구직회원 비밀번호 찾기
   public String memFindPW(JobMemberVO PWFinder) throws RemoteException;
  	
   public int updateProfileImg(JobMemberVO jmemvo) throws RemoteException;	
   
   public JobMemberVO selectApplyMember(String mem_name) throws RemoteException;
   
   
}