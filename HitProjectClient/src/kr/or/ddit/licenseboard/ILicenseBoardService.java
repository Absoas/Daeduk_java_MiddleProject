package kr.or.ddit.licenseboard;

import java.rmi.RemoteException;
import java.util.List;


public interface ILicenseBoardService {

	public int insertBoard(LicenseBoardVO licensevo);
	public List<LicenseBoardVO> getAllBoard();
	public int deleteBoard(String liccode);
	public int updateBoard(LicenseBoardVO licensevo);
	
}
