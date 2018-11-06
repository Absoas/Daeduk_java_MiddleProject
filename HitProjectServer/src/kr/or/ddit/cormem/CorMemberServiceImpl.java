package kr.or.ddit.cormem;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.List;

public class CorMemberServiceImpl extends UnicastRemoteObject implements ICorMemberService {

	private static ICorMemberService corService;
	private ICorMemberDao corDao;

	public static ICorMemberService getInstance() throws RemoteException {
		if (corService == null) {
			corService = new CorMemberServiceImpl();
		}
		return corService;
	}

	private CorMemberServiceImpl() throws RemoteException {
		corDao = CorMemberDaoImpl.getInstance();
	}

	@Override
	public CorMemberVO Login_Check(CorMemberVO loginMember) throws RemoteException {
		CorMemberVO result = null;
		try {
			result = corDao.Login_Check(loginMember);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<CorMemberVO> selectCorMemberInfo() throws RemoteException {
		List<CorMemberVO> list = null;
		try {
			list = corDao.selectCorMemberInfo();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int deleteCorMember(String memId) throws RemoteException {
		int cnt = 0;
		try {
			cnt = corDao.deleteCorMember(memId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int updateCorMember(CorMemberVO corMemVO) throws RemoteException {
		int cnt = 0;
		try {
			cnt = corDao.updateCorMember(corMemVO);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	// 회원가입

	@Override
	public boolean signupMain(CorMemberVO signup) throws RemoteException {
		boolean result = false;
		try {
			result = corDao.signupMain(signup);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean signupSub(CorMemberVO signup) throws RemoteException {
		boolean result = false;
		try {
			result = corDao.signupSub(signup);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int updateMyInfo(CorMemberVO cmemVO) throws RemoteException {
		int cnt = 0;
		try {
			cnt = corDao.updateMyInfo(cmemVO);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	// id중복체크
	@Override
	public boolean idCheck(String colId) throws RemoteException {
		boolean result = false;
		try {
			result = corDao.idCheck(colId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<CorMemberVO> selectCorConId(String memId) throws RemoteException {
		List<CorMemberVO> list = null;
		try {
			list = corDao.selectCorConId(memId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<CorMemberVO> selectCorConSta(String cState) throws RemoteException {
		List<CorMemberVO> list = null;
		try {
			list = corDao.selectCorConSta(cState);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public String CormemFindID(CorMemberVO corIDFinder) throws RemoteException {
		String corIdFinder =null;
		try {
			corIdFinder = corDao.CormemFindID(corIDFinder);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return corIdFinder;
	}
	
	@Override
	public String cormemFindPw(CorMemberVO corPwFinder) throws RemoteException {
		String cPwFinder = null;
		try {
			cPwFinder = corDao.cormemFindPw(corPwFinder);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cPwFinder;
	}

	@Override
	public int updateProfileImg(CorMemberVO sessionMember) throws RemoteException {
		int result = 0;
		try {
			result = corDao.updateProfileImg(sessionMember);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}