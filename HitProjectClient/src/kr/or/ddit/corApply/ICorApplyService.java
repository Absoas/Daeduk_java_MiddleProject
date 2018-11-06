package kr.or.ddit.corApply;

import java.rmi.RemoteException;
import java.util.List;

public interface ICorApplyService {
	public List<CorApplyVO> selectCorApplyInfo(String corId);
	public List<CorApplyVO> selectComboCorApply(CorApplyVO corAppVO);
	public int updateComboCorApply(CorApplyVO corAppVO);
	public List<CorDetailVO> selectDetilList();
}
