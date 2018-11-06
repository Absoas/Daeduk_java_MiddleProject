package kr.or.ddit.jmemlist;

import java.util.List;

public interface IJMemListService{
	public List<JMemListVO> selectJMemList();
	public int updateJMemClick(JMemListVO jmListvo);
	public List<JMemListVO> selectCareerJMemList(String career);
	public List<JMemListVO> selectDeptJMemList(String dept);
	public List<JMemListVO> selectNameJMemList(String name);
}
