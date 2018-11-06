package kr.or.ddit.calendar;

import java.util.List;


public interface ICalendarService {
	public boolean insertMyCalendar(MyCalendarVO calendarVO);
	public List<MyCalendarVO> selectMyCalendar(String memId);
	public int deleteMyCalendar(MyCalendarVO calendarVO);
	public boolean checkCalendar(MyCalendarVO vo);
	public boolean insertMemoCalendar(MemoCalendarVO vo);
	public List<MemoCalendarVO> selectMemoCalendar(String mem_id);
	public int updateMemoCalendar(MemoCalendarVO vo);
	public int deleteMemoCalendar(MemoCalendarVO vo);
	public MemoCalendarVO selectDetailMemo(String no);
}
