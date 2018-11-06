package kr.or.ddit.calendar;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.List;



public class CalendarServiceImpl extends UnicastRemoteObject implements ICalendarService {
	
	private static ICalendarService calService;
	private ICalendarDao calDao;

	public static ICalendarService getInstance() throws RemoteException {
		if (calService == null) {
			calService = new CalendarServiceImpl();
		}
		return calService;
	}

	private CalendarServiceImpl() throws RemoteException {
		calDao = CalendarDaoImpl.getInstance();
	}
	
	@Override
	public boolean insertMyCalendar(MyCalendarVO calendarVO) throws RemoteException {
		boolean result = false;
		try {
			result = calDao.insertMyCalendar(calendarVO);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@Override
	public List<MyCalendarVO> selectMyCalendar(String memId) throws RemoteException {
		List<MyCalendarVO> list = null;
		try {
			list = calDao.selectMyCalendar(memId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public int deleteMyCalendar(MyCalendarVO calendarVO) throws RemoteException {
		int i = 0;
		try {
			i = calDao.deleteMyCalendar(calendarVO);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}
	
	
	@Override
	public boolean checkCalendar(MyCalendarVO vo) throws RemoteException {
		boolean result = false;
		try {
			result = calDao.checkCalendar(vo);
		} catch (Exception e) {
		}
		return result;
	}
	
	@Override
	public boolean insertMemoCalendar(MemoCalendarVO vo) throws RemoteException {
		boolean result = false;
		try {
			result = calDao.insertMemoCalendar(vo);
		} catch (Exception e) {
		}
		return result;
	}
	@Override
	public List<MemoCalendarVO> selectMemoCalendar(String mem_id) throws RemoteException {
		List<MemoCalendarVO> list = null;
		try {
			list = calDao.selectMemoCalendar(mem_id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public int updateMemoCalendar(MemoCalendarVO vo) throws RemoteException {
		int  i = 0;
		
		try {
			i= calDao.updateMemoCalendar(vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return i;
	}
	@Override
	public int deleteMemoCalendar(MemoCalendarVO vo) throws RemoteException {
	int  i = 0;
		
		try {
			i= calDao.deleteMemoCalendar(vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return i;
	}
	
	@Override
	public MemoCalendarVO selectDetailMemo(String no) throws RemoteException {
		MemoCalendarVO vo = null;
		try {
			vo = calDao.selectDetailMemo(no);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;
	}	
}
