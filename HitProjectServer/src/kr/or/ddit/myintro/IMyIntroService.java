package kr.or.ddit.myintro;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IMyIntroService extends Remote {
	public MyIntroVO selectMyIntro(String mem_id) throws RemoteException;

	public int insertMyIntro(MyIntroVO newIntro) throws RemoteException;

	public int updateMyIntro(MyIntroVO newIntro) throws RemoteException;
}
