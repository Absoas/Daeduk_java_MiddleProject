package kr.or.ddit.creqboard;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;


public interface ICreqBoardService extends Remote{
   
   public List<CReqBoardVO> selectCReqBoard() throws RemoteException;
   public boolean insertApply_board(String mem_id) throws RemoteException;
   public boolean insertCreqBoard(CReqBoardVO creqVO) throws RemoteException;
   public int deleteCreqBoard(String corid) throws RemoteException;
   public int updateCreqBoard(CReqBoardVO creqVO) throws RemoteException; 
   public List<String> selectCReqName() throws RemoteException;
   public CReqBoardVO selectCreqBoardInfo(String corName) throws RemoteException;
   public int updateCreqClick(CReqBoardVO creqVO) throws RemoteException;
   public List<String> selectCreqArea(String corArea) throws RemoteException;
   public List<String> selectCreqCorName(String corName) throws RemoteException;
   public List<String> selectCreqSal(String creqSalary) throws RemoteException;
   public List<String> setRanckView() throws RemoteException;
}