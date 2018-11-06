package kr.or.ddit.cormem;

import java.sql.SQLException;
import java.util.List;

import kr.or.ddit.jobmem.JobMemberVO;


public interface ICorMemberDao {
   //id중복체크
   public boolean idCheck(String colId) throws SQLException;
   
   public CorMemberVO Login_Check(CorMemberVO loginMember) throws SQLException;
   
   public List<CorMemberVO> selectCorMemberInfo() throws SQLException;
   public int deleteCorMember(String memId) throws SQLException;
   public int updateCorMember(CorMemberVO corMemVO) throws SQLException;
   public boolean signupMain(CorMemberVO signup) throws SQLException;
   public boolean signupSub(CorMemberVO signup) throws SQLException;
   public int updateMyInfo(CorMemberVO cmemVO) throws SQLException;
   public List<CorMemberVO> selectCorConId(String memId) throws SQLException;
   public List<CorMemberVO> selectCorConSta(String cState) throws SQLException;

   public String CormemFindID(CorMemberVO corIDFinder) throws SQLException;
   
 //구인기업 비밀번호 찾기
  	public String  cormemFindPw(CorMemberVO corPwFinder) throws SQLException;
  	
  	public int updateProfileImg(CorMemberVO sessionMember)  throws SQLException;
}