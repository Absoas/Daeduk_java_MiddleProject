package kr.or.ddit.admin;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.ibatis.config.SqlMapClientFatory;

public class AdminDaoImpl implements IAdminDao{

	private static IAdminDao dao;
	private SqlMapClient smc;
	
	private AdminDaoImpl() {
		smc = SqlMapClientFatory.getSqlMapClient();
	}
	
	public static IAdminDao getInstance() {
		if(dao ==null) {
			dao = new AdminDaoImpl();
		}
		return dao;
	}
	
	@Override
	public List<AdminCalendarVO> selectCalendar() throws SQLException {
		return smc.queryForList("admin.selectCalendar");
	}
	
	@Override
	public AdminCalendarVO detailCelendar(String name) throws SQLException {
		return (AdminCalendarVO) smc.queryForObject("admin.detailCalendar" , name);
	}
}
