package kr.or.ddit.myintro;

import java.sql.SQLException;

public interface IMyIntroDao {
	public MyIntroVO selectMyIntro(String mem_id) throws SQLException;
	
	public int insertMyIntro(MyIntroVO newIntro) throws SQLException;

	public int updateMyIntro(MyIntroVO newIntro) throws SQLException;
}
