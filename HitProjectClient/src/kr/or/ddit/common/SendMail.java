package kr.or.ddit.common;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {
	public static boolean sendMail(String email, int content){

		boolean result = false;


		final String username = "dydqls5757@gmail.com";	
		final String password ="153153dyd";

		String recipient = email;
		String subject = "비밀번호 찾기 인증번호입니다.";
		int valid_number = content;
		String body = "인증번호 : "+valid_number;

		Properties props = System.getProperties();
		props.put("mail.smtp.user", "dydqls5757@gmail.com"); 
		props.put("mail.smtp.host", "gmail-smtp.l.google.com");
		props.put("mail.smtp.port", 465);
		props.put("mail.smtp.auth","true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.transport.protocol","smtp");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); 
		props.put("mail.smtp.socketFactory.fallback", "false");  


		Session session = Session.getDefaultInstance(props, new Authenticator() {
			String un = username;
			String pw = password;
			protected PasswordAuthentication getPasswordAuthentication(){
				return new PasswordAuthentication(un, pw);
			}
		});

		session.setDebug(true);

		try {
			Message mimeMessage = new MimeMessage(session);
			mimeMessage.setFrom(new InternetAddress("dydqls5757@gmail.com"));	
			mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));	
			mimeMessage.setSubject(subject);
			mimeMessage.setText(body);

			Transport.send(mimeMessage);
			result = true;
			
		}catch(MessagingException e){
			e.printStackTrace();
		}

		return result;
	}
}
