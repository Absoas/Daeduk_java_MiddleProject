package kr.or.ddit.corRegno;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public interface ICorRegnoDao {
	public List<CorRegnoVO> selectCorRegno() throws SQLException;
	public List<CorRegnoVO> searchCorRegno(HashMap<String, String> corRegnoMap) throws SQLException;
	public List<CorRegnoVO> searchCorName(HashMap<String, String> corRegnoMap) throws SQLException;
}
