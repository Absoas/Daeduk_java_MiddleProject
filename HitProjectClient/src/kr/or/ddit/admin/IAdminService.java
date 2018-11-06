package kr.or.ddit.admin;

import java.rmi.RemoteException;
import java.util.List;

public interface IAdminService{
	public List<AdminCalendarVO> selectCalendar();
	public AdminCalendarVO detailCalendar(String name);
}
