package kr.or.ddit.mylicense;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

public interface IMylicDao {
	/**
	 * license테이블과  mylicense테이블의 liccode가 겹치는 사람을 mylicense mem_id가져옴
	 * @param mem_id
	 * @return
	 * @throws SQLException
	 */
	public List<MylicVO> selectMylic(String mem_id)throws SQLException;
	
	
	/**
	 * mylicense테이블에 mem_id,와 lic_code를 추가
	 * @param mylicvo
	 * @return
	 * @throws SQLException
	 */
	public int insertLic(MylicVO mylicvo) throws SQLException;
	
	/**
	 * 멤버id를 들고와 해당 자격증 고유번호를 삭제
	 * @param mylicvo
	 * @return
	 * @throws SQLException
	 */
	public int deleteLic(MylicVO mylicvo) throws SQLException;
}
