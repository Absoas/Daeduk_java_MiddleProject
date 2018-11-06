package kr.or.ddit.myApply;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.List;

public class MyApplyServiceImpl extends UnicastRemoteObject implements IMyApplyService{
	private IMyApplyDao myapplydao;
	private static IMyApplyService myapplyservice;
	
	public static IMyApplyService getInstance() throws RemoteException{
		if(myapplyservice == null) {
			myapplyservice = new MyApplyServiceImpl();
		}
		return myapplyservice;
	}
	
	private MyApplyServiceImpl() throws RemoteException{
		myapplydao = MyApplyDaoImpl.getInstance();
	}

	@Override
	public List<MyApplyVO> selectMyApply(String mem_id) throws RemoteException {
		List<MyApplyVO> selectMyApplyList = null;
		try {
			selectMyApplyList = myapplydao.selectMyApply(mem_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return selectMyApplyList;
	}

	@Override
	public boolean insertApply(MyApplyVO vo) throws RemoteException {
		boolean result = false;
		
		try {
			result = myapplydao.insertApply(vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public int selectTestNo(MyApplyVO vo) throws RemoteException {
		int i = 0;
		try {
			i= myapplydao.selectTestNo(vo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}
}
