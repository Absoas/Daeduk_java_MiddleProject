package kr.or.ddit.common;

import java.rmi.RemoteException;
import java.util.List;

public interface ICommonService {
	public List<ZipVO> searchZip(String zip);
	public List<ZipVO> searchDong(String dong);
}
