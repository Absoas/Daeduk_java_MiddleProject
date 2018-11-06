package kr.or.ddit.common;

import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;


public class SendSms {
	public static void sendSms(String phoneNumber, String content) throws CoolsmsException {
		String api_key = "NCSDML7NFXPAWFFH";
	    String api_secret = "7LVUZQX8URJXIFJVCYG4EEKTIBDKGA1J";
	    Message coolsms = new Message(api_key, api_secret);

	    HashMap<String, String> set = new HashMap<String, String>();
        set.put("to", phoneNumber); // 수신번호
        
        set.put("from", "01054283127"); // 발신번호 
        set.put("text", content); // 문자내용
        set.put("type", "sms"); // 문자 타입

        
        JSONObject result = coolsms.send(set); // 보내기&전송결과받기
       
	}
}
