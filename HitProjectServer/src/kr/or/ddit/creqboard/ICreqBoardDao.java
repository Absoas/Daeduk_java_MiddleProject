package kr.or.ddit.creqboard;

import java.sql.SQLException;
import java.util.List;


public interface ICreqBoardDao {

   public boolean insertCreqBoard(CReqBoardVO creqVO) throws SQLException;
   public int deleteCreqBoard(String corid) throws SQLException;
   public int updateCreqBoard(CReqBoardVO creqVO) throws SQLException;
   public boolean insertApply_board(String mem_id) throws SQLException;
   public List<String> selectCReqName() throws SQLException;
   public CReqBoardVO selectCreqBoardInfo(String corName) throws SQLException;
   public int updateCreqClick(CReqBoardVO creqVO) throws SQLException;
   public List<CReqBoardVO> selectCReqBoard()  throws SQLException;
   public List<String> selectCreqArea(String corArea) throws SQLException;
   public List<String> selectCreqCorName(String corName) throws SQLException;
   public List<String> selectCreqSal(String creqSalary) throws SQLException;
   public List<String> setRanckView() throws SQLException;
}