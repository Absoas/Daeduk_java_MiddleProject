package kr.or.ddit.noticeboard;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface INoticeBoardService extends Remote{
	public int insertBoard(NoticeBoardVO noticevo) throws RemoteException;
	public List<NoticeBoardVO> getAllBoard() throws RemoteException;
	public int deleteBoard(String notiid) throws RemoteException;
	public int updateBoard(NoticeBoardVO noticevo) throws RemoteException;
	public int lookBoard(NoticeBoardVO noticevo) throws RemoteException;
}
