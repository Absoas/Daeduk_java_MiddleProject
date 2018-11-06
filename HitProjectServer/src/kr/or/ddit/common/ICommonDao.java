package kr.or.ddit.common;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

public interface ICommonDao {
	public List<ZipVO> searchZip(String zip) throws SQLException;
	public List<ZipVO> searchDong(String dong) throws SQLException;
}
