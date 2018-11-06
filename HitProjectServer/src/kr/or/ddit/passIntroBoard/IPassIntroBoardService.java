package kr.or.ddit.passIntroBoard;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;


public interface IPassIntroBoardService extends Remote{
	
	/**
	 * 전체게시판 목록을 가져오는 메서드
	 * @return PassboardVO타입인 ObservableList로 반환
	 */
	public List<PassIntroboardVO> selectpassboard()throws RemoteException;
	
	
	/**
	 * 조회수를 늘려주는 메서드 
	 * @param passVO
	 */
	public int countNotice(PassIntroboardVO  passVO)throws RemoteException;
	
	/**
	 * 글삭제
	 * @param passno
	 * @return
	 * @throws RemoteException
	 */
	public int deletepassboard(int passno) throws RemoteException;
	
	/**
	 * 글추가
	 * @param passVO
	 * @return
	 * @throws RemoteException
	 */
	public int insertpassboard(PassIntroboardVO passVO)throws RemoteException;
	
	/**
	 * 글수정
	 * @param passVO
	 * @return
	 * @throws RemoteException
	 */
	public int updatepassboard(PassIntroboardVO passVO) throws RemoteException;
}
