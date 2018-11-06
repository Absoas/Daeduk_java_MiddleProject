package kr.or.ddit.noticeboard;

import java.io.IOException;
import java.io.Reader;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class NoticeBoardDaoImpl implements INoticeBoardDao {
	private static INoticeBoardDao notiDao;
	NoticeBoardVO vo;
	Reader rd;
	SqlMapClient smc;

	public static INoticeBoardDao getInstance() {
		if (notiDao == null) {
			notiDao = new NoticeBoardDaoImpl();
		}
		return notiDao;
	}

	@Override
	public int insertBoard(NoticeBoardVO noticevo) throws RemoteException {
		int cnt = 0;
		try {
			System.out.println(1);
			rd = Resources.getResourceAsReader("kr/or/ddit/ibatis/config/SqlMapConfig.xml");
			smc = SqlMapClientBuilder.buildSqlMapClient(rd);
			rd.close();
			Object obj = smc.insert("NoticeBoard.insertBoard", noticevo);
			if (obj == null) {
				cnt = 1;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	
	@Override
	public List<NoticeBoardVO> getAllBoard() throws RemoteException {
		List<NoticeBoardVO> list = null;
		try {
			System.out.println(1);
			rd = Resources.getResourceAsReader("kr/or/ddit/ibatis/config/SqlMapConfig.xml");
			smc = SqlMapClientBuilder.buildSqlMapClient(rd);
			rd.close();
			list = smc.queryForList("NoticeBoard.selectBoard");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	

	@Override
	public int deleteBoard(String notiid) throws RemoteException {
		System.out.println("asdad");
		int cnt = 0;
		try {
			rd = Resources.getResourceAsReader("kr/or/ddit/ibatis/config/SqlMapConfig.xml");
			smc = SqlMapClientBuilder.buildSqlMapClient(rd);
			rd.close();

			cnt = smc.delete("NoticeBoard.deleteBoard", notiid);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int updateBoard(NoticeBoardVO noticevo) throws RemoteException {
		int cnt = 0;
		try {

			rd = Resources.getResourceAsReader("kr/or/ddit/ibatis/config/SqlMapConfig.xml");
			smc = SqlMapClientBuilder.buildSqlMapClient(rd);
			rd.close();

			int obj = smc.update("NoticeBoard.updateBoard", noticevo);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	public int lookBoard(NoticeBoardVO noticevo) {
		int cnt = 0;
		try {
			rd = Resources.getResourceAsReader("kr/or/ddit/ibatis/config/SqlMapConfig.xml");
		smc = SqlMapClientBuilder.buildSqlMapClient(rd);
		rd.close();
		
		cnt = smc.update("NoticeBoard.lookBoard", noticevo);
		
	} catch (SQLException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
	return cnt;
	}

	
}
