package kr.or.ddit.rmi.interf;

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
import kr.or.ddit.myApply.IMyApplyService;
import kr.or.ddit.myintro.IMyIntroService;
import kr.or.ddit.mylicense.IMylicService;
import kr.or.ddit.noticeboard.INoticeBoardService;
import kr.or.ddit.passIntroBoard.IPassIntroBoardService;
import kr.or.ddit.qnaboard.IQnaBoardService;
import kr.or.ddit.revboard.IRevBoardService;
import kr.or.ddit.test.ITestService;

public interface IRemote{
	public IJobMemberService getIJobMemSerivce();

	public ICorRegnoService getIRegnoService();

	public ICommonService getICommonService();
	
	public ICorMemberService getICorMemberService();
	
	public IAdminService getIAdminService();
	
	public ILicenseBoardService getILicenseService();
	
	public INoticeBoardService getINotiService();
	
	public IMyIntroService getIMyIntroService();
	
	public ICreqBoardService getICreqBoardService();
	
	public ICalendarService getICalendarService();
	
	public IJMemListService getIJMemListService();
	
	public IMylicService getIMylicService();
	
	public IPassIntroBoardService getIPassIntroBoardService();
	
	public ITestService getITestService();
	
	public IRevBoardService getIBoardService();
	   
	public IQnaBoardService getIQnaService();
	

	public IMyApplyService getIMyApplyService();

	public ICorApplyService getICorApplyService();

}
