package kr.or.ddit.rmi.interf;

import java.rmi.Remote;
import java.rmi.RemoteException;

import kr.or.ddit.admin.IAdminService;
import kr.or.ddit.calendar.ICalendarService;
import kr.or.ddit.common.ICommonService;
import kr.or.ddit.corApply.ICorApplyService;
import kr.or.ddit.corRegno.ICorRegnoService;
import kr.or.ddit.cormem.ICorMemberService;
import kr.or.ddit.creqboard.ICreqBoardService;
import kr.or.ddit.jmemlist.IJMemListService;
import kr.or.ddit.jobmem.IJobMemberService;
import kr.or.ddit.licenseboard.ILicenseBoardService;
import kr.or.ddit.myApply.IMyApplyDao;
import kr.or.ddit.myApply.IMyApplyService;
import kr.or.ddit.myintro.IMyIntroService;
import kr.or.ddit.mylicense.IMylicService;
import kr.or.ddit.noticeboard.INoticeBoardService;
import kr.or.ddit.passIntroBoard.IPassIntroBoardService;
import kr.or.ddit.qnaboard.IQnaBoardService;
import kr.or.ddit.revboard.IRevBoardService;
import kr.or.ddit.test.ITestService;

public interface IRemote extends Remote {
	public IJobMemberService getIJobMemSerivce() throws RemoteException;
	
	public ICorRegnoService getIRegnoService() throws RemoteException;

	public ICommonService getICommonService() throws RemoteException;
	
	public ICorMemberService getICorMemberService()	throws RemoteException;
	
	public IAdminService getIAdminService() throws RemoteException;
	
	public ILicenseBoardService getILicenseService() throws RemoteException;

	public INoticeBoardService getINotiService() throws RemoteException;
	
	public IMyIntroService getIMyIntroService() throws RemoteException;
	
	public ICreqBoardService getICreqBoardService() throws RemoteException;
	
	public ICalendarService getICalendarService() throws RemoteException;
	
	public IJMemListService getIJMemListService() throws RemoteException;
	
	public IMylicService getIMylicService() throws RemoteException;

	public IPassIntroBoardService getIPassIntroBoardService() throws RemoteException;
	
	public ITestService getITestService() throws RemoteException;
	
	public IRevBoardService getIBoardService() throws RemoteException;
	   
	public IQnaBoardService getIQnaService() throws RemoteException;

	
	public IMyApplyService getIMyApplyService() throws RemoteException;

	
	public ICorApplyService getICorApplyService() throws RemoteException;

}
