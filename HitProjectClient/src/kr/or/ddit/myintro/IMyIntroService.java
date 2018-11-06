package kr.or.ddit.myintro;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface IMyIntroService{
	public MyIntroVO selectMyIntro(String mem_id);
	
	public int insertMyIntro(MyIntroVO newIntro);
	
	public int updateMyIntro(MyIntroVO newIntro);
}
