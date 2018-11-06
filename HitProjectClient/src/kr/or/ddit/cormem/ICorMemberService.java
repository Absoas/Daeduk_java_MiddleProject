package kr.or.ddit.cormem;

import java.util.List;

public interface ICorMemberService {
   public CorMemberVO Login_Check(CorMemberVO loginMember) ;
   public List<CorMemberVO> selectCorMemberInfo();
   public int deleteCorMember(String memId);
   public int updateCorMember(CorMemberVO corMemVO);
   public boolean signupMain(CorMemberVO signup);
   public boolean signupSub(CorMemberVO signup);
   public int updateMyInfo(CorMemberVO cmemVO);
   public List<CorMemberVO> selectCorConId(String memId);
   public List<CorMemberVO> selectCorConSta(String cState);
   public String CormemFindID(CorMemberVO corIDFinder);
   
   public String cormemFindPw(CorMemberVO corPwFinder);
   
   public int updateProfileImg(CorMemberVO sessionMember);
}