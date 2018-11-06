package kr.or.ddit.corApply;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

public interface ICorApplyDao {
	public List<CorApplyVO> selectCorApplyInfo(String corId) throws SQLException;
	public List<CorApplyVO> selectComboCorApply(CorApplyVO corAppVO) throws SQLException;
	public int updateComboCorApply(CorApplyVO corAppVO) throws SQLException;
	public List<CorDetailVO> selectDetilList() throws SQLException;

}
