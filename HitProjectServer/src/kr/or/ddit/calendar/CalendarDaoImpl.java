package kr.or.ddit.calendar;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import kr.or.ddit.ibatis.config.SqlMapClientFatory;

public class CalendarDaoImpl implements ICalendarDao {
	private static ICalendarDao calDao;
	
	private SqlMapClient smc;
	
	private CalendarDaoImpl() {
		smc = SqlMapClientFatory.getSqlMapClient();
	}
	
	public static ICalendarDao getInstance() {
		if(calDao==null) {
			calDao=new CalendarDaoImpl();
		}
		return calDao;
	}
	
	@Override
	public boolean checkCalendar(MyCalendarVO vo) throws SQLException {
		boolean result = false;
		MyCalendarVO checkVo = (MyCalendarVO) smc.queryForObject("calendar.selectCheckCalendar",vo);
		if(checkVo != null) {
			result = true;
		}
		return result;
	}

	@Override
	public boolean insertMemoCalendar(MemoCalendarVO vo) throws SQLException {
		boolean result = false;
		MemoCalendarVO checkVo = (MemoCalendarVO) smc.insert("calendar.insertMemoCalendar",vo);
		if(checkVo == null) {
			result = true;
		}
		return result;
	}

	@Override
	public List<MemoCalendarVO> selectMemoCalendar(String mem_id) throws SQLException {
		return smc.queryForList("calendar.selectMemoCalendar",mem_id);
	}

	@Override
	public int updateMemoCalendar(MemoCalendarVO vo) throws SQLException {
		return smc.update("calendar.updateMemoCalendar",vo);
	}

	@Override
	public int deleteMemoCalendar(MemoCalendarVO vo) throws SQLException {
		return smc.delete("calendar.deleteMemoCalendar",vo);
	}
	
	@Override
	public MemoCalendarVO selectDetailMemo(String no) throws SQLException {
		return (MemoCalendarVO) smc.queryForObject("calendar.deatilMemo" , no);
	}

	@Override
	public boolean insertMyCalendar(MyCalendarVO calendarVO) throws SQLException {
		boolean result = false;
		Object obj = smc.insert("calendar.insertCalendar" , calendarVO);
		if(obj == null) {
			result = true;
		}
		return result;
	}
	
	@Override
	public List<MyCalendarVO> selectMyCalendar(String memId) throws SQLException {
		return smc.queryForList("calendar.selectMyCalendar" , memId);
	}
	
	@Override
	public int deleteMyCalendar(MyCalendarVO calendarVO) throws SQLException {
		return smc.delete("calendar.deleteMyCalendar" , calendarVO);
	}

}
