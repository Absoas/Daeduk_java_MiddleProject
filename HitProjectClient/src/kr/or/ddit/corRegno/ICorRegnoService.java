package kr.or.ddit.corRegno;

import java.util.HashMap;
import java.util.List;

public interface ICorRegnoService{
	public List<CorRegnoVO> selectCorRegno();
	public List<CorRegnoVO> searchCorRegno(HashMap<String, String> corRegnoMap);
	public List<CorRegnoVO> searchCorName(HashMap<String, String> corRegnoMap);
}