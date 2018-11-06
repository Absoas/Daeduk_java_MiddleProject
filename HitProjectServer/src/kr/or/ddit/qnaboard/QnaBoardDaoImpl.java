package kr.or.ddit.qnaboard;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.List;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import kr.or.ddit.ibatis.config.SqlMapClientFatory;

public class QnaBoardDaoImpl implements IQnaBoardDao{
	SqlMapClient client;
	Reader rd;
	
	private static IQnaBoardDao dao;
	
	public static IQnaBoardDao getInstance() {
		if(dao==null) {
			dao=new QnaBoardDaoImpl();
		}
		return dao;
	}
	
	
	private QnaBoardDaoImpl() {
		client = SqlMapClientFatory.getSqlMapClient();
	}
	
	
	
	@Override
	public boolean insert(QnaBoardVO qv) {
		boolean a = false;
		try {
			rd = Resources.getResourceAsReader("kr/or/ddit/ibatis/config/SqlMapConfig.xml");
			client = SqlMapClientBuilder.buildSqlMapClient(rd);
			rd.close();
			Object obj = client.insert("qnaboard.insertBoard", qv);
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
	public List<QnaBoardVO> selectAll() {
		List<QnaBoardVO> list = null;
		try {
			rd = Resources.getResourceAsReader("kr/or/ddit/ibatis/config/SqlMapConfig.xml");
			client = SqlMapClientBuilder.buildSqlMapClient(rd);
			rd.close();
			list= client.queryForList("qnaboard.selectAllBoard");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int deleteBoard(int qna_board_no) { //board_no���� ������ ���� �����Ƿ� int Ÿ���� �Ű�����
		int cnt = 0; //board_no�� ���� ���� cnt��� intŸ���� ������ �ʱ�ȭ ��Ŵ.
		try {
			rd = Resources.getResourceAsReader("kr/or/ddit/ibatis/config/SqlMapConfig.xml");
			client = SqlMapClientBuilder.buildSqlMapClient(rd);
			rd.close();
			cnt = client.delete("qnaboard.deleteBoard",qna_board_no);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
		
	}

	@Override
	public boolean updateBoard(QnaBoardVO qna_board_no) {
		boolean a = false;
		try {
			rd = Resources.getResourceAsReader("kr/or/ddit/ibatis/config/SqlMapConfig.xml");
			client = SqlMapClientBuilder.buildSqlMapClient(rd);
			rd.close();
			int obj = client.update("qnaboard.updateBoard", qna_board_no);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
		return a;
	}
	
	@Override
	public int clickBoard(QnaBoardVO qna_board_no) {
		int cnt = 0;
		try {
			rd = Resources.getResourceAsReader("kr/or/ddit/ibatis/config/SqlMapConfig.xml");
			client = SqlMapClientBuilder.buildSqlMapClient(rd);
			rd.close();
			cnt = client.update("qnaboard.clickBoard", qna_board_no);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return cnt;
	}
}
