package kr.or.ddit.mylicense;

import java.rmi.RemoteException;
import java.util.List;

public interface IMylicService {
	public List<MylicVO> selectMylic(String mem_id);
	
	public int insertLic(MylicVO mylicvo);
	
	public int deleteLic(MylicVO mylicvo);
	

}
