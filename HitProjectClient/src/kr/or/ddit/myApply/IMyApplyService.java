package kr.or.ddit.myApply;

import java.util.List;

public interface IMyApplyService {
	public List<MyApplyVO> selectMyApply(String mem_id);
	
	public boolean insertApply(MyApplyVO vo);
	public int selectTestNo(MyApplyVO vo) ;

}
