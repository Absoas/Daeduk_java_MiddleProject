package kr.or.ddit.licenseboard;

import java.io.IOException;
import java.io.Reader;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class LicenseBoardDaoImpl implements ILicenseBoardDao{
	private static ILicenseBoardDao licDao;
	LicenseBoardVO vo;
	Reader rd;
	SqlMapClient smc;
	
	public static ILicenseBoardDao getInstance() {
		if(licDao == null) {
			licDao = new LicenseBoardDaoImpl();
		}
		return licDao;
	}
	
	
	
	@Override
	public int insertBoard(LicenseBoardVO licensevo) throws RemoteException {
		int cnt = 0;
		try {
			System.out.println(1);
			rd = Resources.getResourceAsReader("kr/or/ddit/ibatis/config/SqlMapConfig.xml");
			smc = SqlMapClientBuilder.buildSqlMapClient(rd);
			rd.close();
			Object obj = smc.insert("LicenseBoard.insertBoard", licensevo);
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
	public List<LicenseBoardVO> getAllBoard() throws RemoteException {
		List<LicenseBoardVO> list = null;
		try {
			System.out.println(1);
			rd = Resources.getResourceAsReader("kr/or/ddit/ibatis/config/SqlMapConfig.xml");
			smc = SqlMapClientBuilder.buildSqlMapClient(rd);
			rd.close();
			list = smc.queryForList("LicenseBoard.selectBoard");
		
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public int deleteBoard(String liccode) throws RemoteException {
		System.out.println("asdad");
		int cnt = 0;
		try {
			rd = Resources.getResourceAsReader("kr/or/ddit/ibatis/config/SqlMapConfig.xml");
			smc = SqlMapClientBuilder.buildSqlMapClient(rd);
			rd.close();

			cnt = smc.delete("LicenseBoard.deleteBoard", liccode);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int updateBoard(LicenseBoardVO licensevo) throws RemoteException {
		int cnt = 0;
		try {

			rd = Resources.getResourceAsReader("kr/or/ddit/ibatis/config/SqlMapConfig.xml");
			smc = SqlMapClientBuilder.buildSqlMapClient(rd);
			rd.close();

			int obj = smc.update("LicenseBoard.updateBoard", licensevo);

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return cnt;
	}

}
