package kr.or.ddit.admin;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import kr.or.ddit.common.ZipVO;

public interface IAdminService extends Remote{
	public List<AdminCalendarVO> selectCalendar() throws RemoteException;
	public AdminCalendarVO detailCalendar(String name) throws RemoteException;
	
}
