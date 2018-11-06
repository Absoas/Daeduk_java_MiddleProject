package kr.or.ddit.cormem;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ICorMemberService extends Remote {
	// id중복체크
	public boolean idCheck(String colId) throws RemoteException;

	public CorMemberVO Login_Check(CorMemberVO loginMember) throws RemoteException;

	public List<CorMemberVO> selectCorMemberInfo() throws RemoteException;

	public int deleteCorMember(String memId) throws RemoteException;

	public int updateCorMember(CorMemberVO corMemVO) throws RemoteException;

	public boolean signupMain(CorMemberVO signup) throws RemoteException;

	public boolean signupSub(CorMemberVO signup) throws RemoteException;

	public int updateMyInfo(CorMemberVO cmemVO) throws RemoteException;

	public List<CorMemberVO> selectCorConId(String memId) throws RemoteException;

	public List<CorMemberVO> selectCorConSta(String cState) throws RemoteException;

	public String CormemFindID(CorMemberVO corIDFinder) throws RemoteException;
	
	//구인기업 비밀번호 찾기
  	public String  cormemFindPw(CorMemberVO corPwFinder) throws RemoteException;
  	
  	public int updateProfileImg(CorMemberVO sessionMember) throws RemoteException;
}