package kr.or.ddit.admin;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.List;

import kr.or.ddit.common.CommonDaoImpl;
import kr.or.ddit.common.CommonServiceImpl;
import kr.or.ddit.common.ICommonDao;
import kr.or.ddit.common.ICommonService;

public class AdminServiceImpl  extends UnicastRemoteObject implements IAdminService{
	private static IAdminService adminService;
	private IAdminDao adminDao;
	
	public static IAdminService getInstance() throws RemoteException{
		if(adminService == null) {
			adminService = new AdminServiceImpl();
		}
		return adminService;
	}
	
	private AdminServiceImpl() throws RemoteException {
		adminDao = AdminDaoImpl.getInstance();
	}

	@Override
	public List<AdminCalendarVO> selectCalendar() throws RemoteException {
		List<AdminCalendarVO> list = null;
		try {
			list= adminDao.selectCalendar();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public AdminCalendarVO detailCalendar(String name) throws RemoteException {
		AdminCalendarVO vo = null;
		try {
			vo= adminDao.detailCelendar(name);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;
	}
	
}
