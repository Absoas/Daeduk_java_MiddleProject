package kr.or.ddit.creqboard;

import java.rmi.RemoteException;
import java.util.List;


public interface ICreqBoardService {
   
	public List<CReqBoardVO> selectCReqBoard();

	public boolean insertApply_board(String mem_id);

	public boolean insertCreqBoard(CReqBoardVO creqVO);

	public int deleteCreqBoard(String corid);

	public int updateCreqBoard(CReqBoardVO creqVO);

	public List<String> selectCReqName();

	public CReqBoardVO selectCreqBoardInfo(String corName);

	public int updateCreqClick(CReqBoardVO creqVO);
	
	public List<String> selectCreqArea(String corArea);
	
	public List<String> selectCreqCorName(String corName);
	
	public List<String> selectCreqSal(String creqSalary);

	public List<String> setRanckView();
	
}