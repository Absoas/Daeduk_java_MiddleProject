package kr.or.ddit.revboard;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.List;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import kr.or.ddit.ibatis.config.SqlMapClientFatory;

public class RevBoardDaoImpl implements IRevBoardDao{
	SqlMapClient client;
	Reader rd;
	
	private static IRevBoardDao dao;
	
	public static IRevBoardDao getInstance() {
		if(dao==null) {
			dao=new RevBoardDaoImpl();
		}
		return dao;
	}
	
	
	private RevBoardDaoImpl() {
		client = SqlMapClientFatory.getSqlMapClient();
	}
	
	
	
	@Override
	public boolean insert(RevBoardVO rv) {
		boolean a = false;
		try {
			rd = Resources.getResourceAsReader("kr/or/ddit/ibatis/config/SqlMapConfig.xml");
			client = SqlMapClientBuilder.buildSqlMapClient(rd);
			rd.close();
			Object obj = client.insert("revboard.insertBoard", rv);
			if(obj == null) {
				a = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return a;
		
	}

	@Override
	public List<RevBoardVO> selectAll() {
		List<RevBoardVO> list = null;
		try {
			rd = Resources.getResourceAsReader("kr/or/ddit/ibatis/config/SqlMapConfig.xml");
			client = SqlMapClientBuilder.buildSqlMapClient(rd);
			rd.close();
			list= client.queryForList("revboard.selectAllBoard");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int deleteBoard(int rev_board_no) { //board_no���� ������ ���� �����Ƿ� int Ÿ���� �Ű�����
		int cnt = 0; //board_no�� ���� ���� cnt��� intŸ���� ������ �ʱ�ȭ ��Ŵ.
		try {
			rd = Resources.getResourceAsReader("kr/or/ddit/ibatis/config/SqlMapConfig.xml");
			client = SqlMapClientBuilder.buildSqlMapClient(rd);
			rd.close();
			cnt = client.delete("revboard.deleteBoard",rev_board_no);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
		
	}

	@Override
	public boolean updateBoard(RevBoardVO rev_board_no) {
		boolean a = false;
		try {
			rd = Resources.getResourceAsReader("kr/or/ddit/ibatis/config/SqlMapConfig.xml");
			client = SqlMapClientBuilder.buildSqlMapClient(rd);
			rd.close();
			int obj = client.update("revboard.updateBoard", rev_board_no);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
		return a;
	}


	@Override
	public int clickBoard(RevBoardVO rev_board_no) {
		int cnt = 0;
		try {
			rd = Resources.getResourceAsReader("kr/or/ddit/ibatis/config/SqlMapConfig.xml");
			client = SqlMapClientBuilder.buildSqlMapClient(rd);
			rd.close();
			cnt = client.update("revboard.clickBoard",rev_board_no);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return cnt;
	}


	

	
	
	
	
	
	
	
}
