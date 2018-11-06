package kr.or.ddit.rmi.interf;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import kr.or.ddit.admin.AdminServiceImpl;
import kr.or.ddit.admin.IAdminService;
import kr.or.ddit.calendar.CalendarServiceImpl;
import kr.or.ddit.calendar.ICalendarService;
import kr.or.ddit.common.CommonServiceImpl;
import kr.or.ddit.common.ICommonService;
import kr.or.ddit.corApply.CorApplyServiceImpl;
import kr.or.ddit.corApply.ICorApplyService;
import kr.or.ddit.corRegno.CorRegnoServiceImpl;
import kr.or.ddit.corRegno.ICorRegnoService;
import kr.or.ddit.cormem.CorMemberServiceImpl;
import kr.or.ddit.cormem.ICorMemberService;
import kr.or.ddit.creqboard.CreqBoardServiceImpl;
import kr.or.ddit.creqboard.ICreqBoardService;
import kr.or.ddit.jmemlist.IJMemListService;
import kr.or.ddit.jmemlist.JMemListServiceImpl;
import kr.or.ddit.jobmem.IJobMemberService;
import kr.or.ddit.jobmem.JobMemberServiceImpl;
import kr.or.ddit.licenseboard.ILicenseBoardService;
import kr.or.ddit.licenseboard.LicenseBoardServiceImpl;
import kr.or.ddit.myApply.IMyApplyService;
import kr.or.ddit.myApply.MyApplyServiceImpl;
import kr.or.ddit.myintro.IMyIntroService;
import kr.or.ddit.myintro.MyIntroServiceImpl;
import kr.or.ddit.mylicense.IMylicService;
import kr.or.ddit.mylicense.MylicServiceImpl;
import kr.or.ddit.noticeboard.INoticeBoardService;
import kr.or.ddit.noticeboard.NoticeBoardServiceImpl;
import kr.or.ddit.passIntroBoard.IPassIntroBoardService;
import kr.or.ddit.passIntroBoard.PassIntroBoardServiceImpl;
import kr.or.ddit.qnaboard.IQnaBoardService;
import kr.or.ddit.qnaboard.QnaBoardService;
import kr.or.ddit.revboard.IRevBoardService;
import kr.or.ddit.revboard.RevBoardService;
import kr.or.ddit.test.ITestService;
import kr.or.ddit.test.TestServiceImpl;

public class RemoteImpl extends UnicastRemoteObject implements IRemote {

	public RemoteImpl() throws RemoteException {
		super();
	}
	@Override
	public IJobMemberService getIJobMemSerivce() throws RemoteException {
		IJobMemberService jobMemService= JobMemberServiceImpl.getInstance();
		return jobMemService;
	}
	
	
	@Override
	public ICorRegnoService getIRegnoService() throws RemoteException {
		ICorRegnoService corRegnoService = CorRegnoServiceImpl.getInstance();
		return corRegnoService;
	}
	@Override
	public ICommonService getICommonService() throws RemoteException {
		ICommonService comService = CommonServiceImpl.getInstance();
		return comService;
	}
	@Override
	public ICorMemberService getICorMemberService() throws RemoteException {
		ICorMemberService corService = CorMemberServiceImpl.getInstance();
		return corService;
	}
	
	@Override
	public IAdminService getIAdminService() throws RemoteException {
		IAdminService adminService = AdminServiceImpl.getInstance();
		return adminService;
	}
	
	@Override
	public ILicenseBoardService getILicenseService() throws RemoteException {
		ILicenseBoardService licService = LicenseBoardServiceImpl.getInstance();
		return licService;
	}
	
	@Override
	public INoticeBoardService getINotiService() throws RemoteException {
		INoticeBoardService noitService = NoticeBoardServiceImpl.getInstance();
		return noitService;
	}
	@Override
	public IMyIntroService getIMyIntroService() throws RemoteException {
		IMyIntroService myIntroService = MyIntroServiceImpl.getInstance();
		return myIntroService;
	}
	
	@Override
	public ICreqBoardService getICreqBoardService() throws RemoteException {
		ICreqBoardService creqService = CreqBoardServiceImpl.getInstance();
		return creqService;
	}
	@Override
	public ICalendarService getICalendarService() throws RemoteException {
		ICalendarService calendarService = CalendarServiceImpl.getInstance();
		return calendarService;
	}
	
	@Override
	public IJMemListService getIJMemListService() throws RemoteException {
		IJMemListService jmemListService = JMemListServiceImpl.getInstance();
		return jmemListService;
	}
	
	@Override
	public IMylicService getIMylicService() throws RemoteException{
		IMylicService MylicService = MylicServiceImpl.getInstance(); 
		return MylicService;
	}
	
	@Override
	public IPassIntroBoardService getIPassIntroBoardService() throws RemoteException {
		IPassIntroBoardService passIntroService = PassIntroBoardServiceImpl.getInstance();
		return passIntroService;
	}
	
	@Override
	public ITestService getITestService() throws RemoteException {
		ITestService testService = TestServiceImpl.getInstance();
		return testService;
	}
	
	@Override
	public IRevBoardService getIBoardService() throws RemoteException {
		IRevBoardService revService = RevBoardService.getServerInstance();
		return revService;
	}

	@Override
	public IQnaBoardService getIQnaService() throws RemoteException {
		IQnaBoardService qnaService = QnaBoardService.getServerInstance();
		return qnaService;
	}
	
	@Override
	public IMyApplyService getIMyApplyService() throws RemoteException {
		IMyApplyService myApplyService = MyApplyServiceImpl.getInstance();
		return myApplyService;
	}

	@Override
	public ICorApplyService getICorApplyService() throws RemoteException {
		ICorApplyService corApplyService = CorApplyServiceImpl.getInstance();
		return corApplyService;
	}
	
	
}
