package kr.or.ddit.noticeboard;

import java.rmi.RemoteException;
import java.util.List;

public interface INoticeBoardService {
	
	public int insertBoard(NoticeBoardVO noticevo);
	public List<NoticeBoardVO> getAllBoard();
	public int deleteBoard(String notiid);
	public int updateBoard(NoticeBoardVO noticevo);
	public int lookBoard(NoticeBoardVO noticevo);
	
}
