package kr.or.ddit.jobmem;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.List;

import kr.or.ddit.cormem.CorMemberVO;

public class JobMemberServiceImpl extends UnicastRemoteObject implements IJobMemberService {
	private static IJobMemberService jMemService;
	private IJobMemberDao jMemDao;

	public static IJobMemberService getInstance() throws RemoteException {
		if (jMemService == null) {
			jMemService = new JobMemberServiceImpl();
		}
		return jMemService;
	}

	private JobMemberServiceImpl() throws RemoteException {
		jMemDao = JobMemberDaoImpl.getInstance();
	}

	@Override
	public JobMemberVO Login_Check(JobMemberVO loginMember) throws RemoteException {
		JobMemberVO result = null;
		try {
			result = jMemDao.Login_Check(loginMember);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean insertMember(JobMemberVO jobMemberVO) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean memberChkId(String memId) throws RemoteException {
		boolean result = false;
		try {
			result = jMemDao.memberChkId(memId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<JobMemberVO> selectJobMemberInfo() throws RemoteException {
		List<JobMemberVO> jobInfo = null;
		try {
			jobInfo = jMemDao.selectJobMemberInfo();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return jobInfo;
	}

	@Override
	public int updateJobMember(JobMemberVO jobMemVO) throws RemoteException {
		int cnt = 0;
		try {
			cnt = jMemDao.updateJobMember(jobMemVO);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int deleteJobMember(String memId) throws RemoteException {
		int cnt = 0;
		try {
			cnt = jMemDao.deleteJobMember(memId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int updateMyInfo(JobMemberVO jobMemVO) throws RemoteException {
		int cnt = 0;
		try {
			cnt = jMemDao.updateMyInfo(jobMemVO);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	// 회원가입(ITMEMBER)
	@Override
	public boolean signupMain(JobMemberVO signupIt) throws RemoteException {
		boolean result = false;
		try {
			result = jMemDao.signupMain(signupIt);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	// 회원가입(JOBMEMBER)
	@Override
	public boolean signupSub(JobMemberVO signupJob) throws RemoteException {
		boolean result2 = false;
		try {
			result2 = jMemDao.signupSub(signupJob);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result2;
	}

	@Override
	public List<JobMemberVO> selectJobConId(String memId) throws RemoteException {
		List<JobMemberVO> list = null;
		try {
			list = jMemDao.selectJobConId(memId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<JobMemberVO> selectJobConSta(String jState) throws RemoteException {
		List<JobMemberVO> list = null;
		try {
			list = jMemDao.selectJobConSta(jState);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<JobMemberVO> selectJobConAddr(String jAddr) throws RemoteException {
		List<JobMemberVO> list = null;
		try {
			list = jMemDao.selectJobConAddr(jAddr);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public String memFindID(JobMemberVO IDFinder) throws RemoteException {
		String idFinder = null; 
		try {
			idFinder = jMemDao.memFindID(IDFinder);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return idFinder;
	}

	@Override
	public String memFindPW(JobMemberVO PWFinder) throws RemoteException {
		String pwFinder = null;
		try {
			pwFinder = jMemDao.memFindPw(PWFinder);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pwFinder;
	}

	@Override
	public int updateProfileImg(JobMemberVO jmemvo) throws RemoteException {
		int result = 0;
		try {
			result = jMemDao.updateProfileImg(jmemvo);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public JobMemberVO selectApplyMember(String mem_name) throws RemoteException {
		JobMemberVO result = null;
		try {
			result = jMemDao.selectApplyMember(mem_name);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}



}