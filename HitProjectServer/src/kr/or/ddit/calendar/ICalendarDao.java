package kr.or.ddit.calendar;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

public interface ICalendarDao {
	public boolean insertMyCalendar(MyCalendarVO calendarVO) throws SQLException;
	public List<MyCalendarVO> selectMyCalendar(String memId) throws SQLException;
	public int deleteMyCalendar(MyCalendarVO calendarVO) throws SQLException;
	public boolean checkCalendar(MyCalendarVO vo) throws SQLException;
	public boolean insertMemoCalendar(MemoCalendarVO vo) throws SQLException;
	public List<MemoCalendarVO> selectMemoCalendar(String mem_id) throws SQLException;
	public int updateMemoCalendar(MemoCalendarVO vo) throws SQLException;
	public int deleteMemoCalendar(MemoCalendarVO vo) throws SQLException;
	public MemoCalendarVO selectDetailMemo(String no) throws SQLException;
}
