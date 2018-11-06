package kr.or.ddit.pattern;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternRegno {
	
	/**
	 * 아이디정규식(첫글자는 영어로시작)4-20글자
	 * @param saram_id
	 * @return
	 */
	public boolean regId(String saram_id) {
		Pattern p = Pattern.compile("^[A-Za-z]{1}[A-Za-z0-9]{3,19}$");
	    Matcher m = p.matcher(saram_id);
	    
	    if(m.matches()) {
	    	return true;
	    }else {
	    	return false;
	    }
	}
	
	/**
	 * 비밀번호정규식(첫글자는영어대문자로 시작 
	 * 6~16자리
	 * @param saram_pass
	 * @return
	 */
	public boolean regPass(String saram_pass) {
		Pattern p = Pattern.compile("^[A-Z]{1}[A-Za-z0-9]{5,15}$");
	    Matcher m = p.matcher(saram_pass);
	    
	    if(m.matches()) {
	    	return true;
	    }else {
	    	return false;
	    }
	}
	
	/**
	 * 핸드폰번호정규식 010(011,016,017,018,019)숫자4개-숫자4개
	 * @param saram_phonenum
	 * @return
	 */
	public boolean regPhoneNo(String saram_phonenum) {
		Pattern p = Pattern.compile("^[0,1]{2}[0,1,6,7,8,9]{1}-\\d{4}-\\d{4}$");
	    Matcher m = p.matcher(saram_phonenum);
	    
	    if(m.matches()) {
	    	return true;
	    }else {
	    	return false;
	    }
	}
	
	/**
	 * 주민번호 정규식-뺴고
	 * @param saram_regno
	 * @return
	 */
	public boolean regNo(String saram_regno) {
		Pattern p = Pattern.compile("\\d{2}([0][1-9]|[1][0-2])([0][1-9]|[1][0-9]|[2][0-9]|[3][0-1])[1-4]{1}\\d{6}");
	    Matcher m = p.matcher(saram_regno);
	    
	    if(m.matches()) {
	    	return true;
	    }else {
	    	return false;
	    }
	}
	
	/**
	 * 이메일 정규식 영문자로 시작해야하며, 특수문자-_/,영문자,숫자 있을수도있고없을수도있고
	 * '@'이후에는 영문자2~7개, .이후에는 영문자가 2~3개, .kr이 하나 있을수도 없을수도
	 * @param saram_email
	 * @return
	 */
	public boolean regEmail(String saram_email) {
		Pattern p = Pattern.compile("^[a-zA-Z]([-_\\\\.]|[a-zA-Z]|\\d)*@[a-zA-Z]{2,7}.[a-zA-Z]{2,3}[.kr]*");
	    Matcher m = p.matcher(saram_email);
	    
	    if(m.matches()) {
	    	return true;
	    }else {
	    	return false;
	    }
	}

}
