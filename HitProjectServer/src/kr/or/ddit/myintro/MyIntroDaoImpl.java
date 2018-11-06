package kr.or.ddit.myintro;

import java.sql.SQLException;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.ibatis.config.SqlMapClientFatory;

public class MyIntroDaoImpl implements IMyIntroDao{
	private static IMyIntroDao myIntroDao;
	
	private SqlMapClient smc;
	
	private MyIntroDaoImpl() {
		smc = SqlMapClientFatory.getSqlMapClient();
	}
	
	public static IMyIntroDao getInstance() {
		if(myIntroDao==null) {
			myIntroDao=new MyIntroDaoImpl();
		}
		return myIntroDao;
	}

	@Override
	public MyIntroVO selectMyIntro(String mem_id) throws SQLException {
		return (MyIntroVO) smc.queryForObject("myIntro.selectMyIntro", mem_id);
	}

	@Override
	public int insertMyIntro(MyIntroVO newIntro) throws SQLException {
		int result = 0;
		
		Object obj =smc.insert("myIntro.insertMyIntro",newIntro);
		if(obj==null) {
			result = 1;
		}
		return result;
	}

	@Override
	public int updateMyIntro(MyIntroVO newIntro) throws SQLException {
		return smc.update("myIntro.updateMyIntro",newIntro);
	}
}
