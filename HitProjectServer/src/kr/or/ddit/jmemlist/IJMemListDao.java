package kr.or.ddit.jmemlist;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

public interface IJMemListDao {
	public List<JMemListVO> selectJMemList() throws SQLException;
	
	public int updateJMemClick(JMemListVO jmListvo) throws SQLException;
	
	public List<JMemListVO> selectCareerJMemList(String career) throws SQLException;
	public List<JMemListVO> selectDeptJMemList(String dept) throws SQLException;
	public List<JMemListVO> selectNameJMemList(String name) throws SQLException;
}
