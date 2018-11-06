package kr.or.ddit.calendar;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;


public interface ICalendarService extends Remote{
	public boolean insertMyCalendar(MyCalendarVO calendarVO) throws RemoteException;
	public List<MyCalendarVO> selectMyCalendar(String memId) throws RemoteException;
	public int deleteMyCalendar(MyCalendarVO calendarVO) throws RemoteException;
	public boolean checkCalendar(MyCalendarVO vo) throws RemoteException;
	public boolean insertMemoCalendar(MemoCalendarVO vo) throws RemoteException;
	public List<MemoCalendarVO> selectMemoCalendar(String mem_id) throws RemoteException;
	public int updateMemoCalendar(MemoCalendarVO vo) throws RemoteException;
	public int deleteMemoCalendar(MemoCalendarVO vo) throws RemoteException;
	public MemoCalendarVO selectDetailMemo(String no) throws RemoteException;
}
