package kr.or.ddit.admin;

import java.sql.SQLException;
import java.util.List;

public interface IAdminDao {
	public List<AdminCalendarVO> selectCalendar() throws SQLException;

	public AdminCalendarVO detailCelendar(String name) throws SQLException;
}
