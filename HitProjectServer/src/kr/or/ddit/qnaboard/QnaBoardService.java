package kr.or.ddit.qnaboard;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;



public class QnaBoardService extends UnicastRemoteObject implements IQnaBoardService{
	private IQnaBoardDao dao;
	
	private static IQnaBoardService bservice;
	
	
	public static IQnaBoardService getServerInstance() throws RemoteException {
		if (bservice==null) {
			bservice=new QnaBoardService();
		}
		return bservice;
	}
	
	private QnaBoardService() throws RemoteException{
		dao = QnaBoardDaoImpl.getInstance();
	}
	
	@Override
	public boolean insert(QnaBoardVO qv) throws RemoteException{
		return dao.insert(qv);
	}

	@Override
	public List<QnaBoardVO> selectAll() throws RemoteException {
		return dao.selectAll();
	}

	@Override
	public int deleteBoard(int qna_board_no) throws RemoteException{
		return dao.deleteBoard(qna_board_no);
	}

	@Override
	public boolean updateBoard(QnaBoardVO qna_board_no) throws RemoteException{
		return dao.updateBoard(qna_board_no);
	}
	
	@Override
	public int clickBoard(QnaBoardVO qna_board_no) throws RemoteException {
		int cnt = 0;
		return cnt = dao.clickBoard(qna_board_no);
	}
}
