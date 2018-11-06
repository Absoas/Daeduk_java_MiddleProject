package kr.or.ddit.noticeboard;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class NoticeBoardServiceImpl extends UnicastRemoteObject implements INoticeBoardService{
	
	private static INoticeBoardService notiService;
	private INoticeBoardDao notiDao;
	public static INoticeBoardService getInstance() throws RemoteException {
		if(notiService==null) {
			notiService = new NoticeBoardServiceImpl(); 
		}
		return notiService;
	}
	private NoticeBoardServiceImpl() throws RemoteException{
		notiDao=NoticeBoardDaoImpl.getInstance();
	}
	
	@Override
	public int insertBoard(NoticeBoardVO noticevo) throws RemoteException {
		return notiDao.insertBoard(noticevo);
	}
	
	@Override
	public List<NoticeBoardVO> getAllBoard() throws RemoteException {
		return notiDao.getAllBoard();
	}
	@Override
	public int deleteBoard(String notiid) throws RemoteException {
		return notiDao.deleteBoard(notiid);
	}
	@Override
	public int updateBoard(NoticeBoardVO noticevo) throws RemoteException {
		return notiDao.updateBoard(noticevo);
	}
	@Override
	public int lookBoard(NoticeBoardVO noticevo) throws RemoteException {
		return notiDao.lookBoard(noticevo);
	}

}
