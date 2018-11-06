package kr.or.ddit.controller.login;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import kr.or.ddit.common.SendMail;
import kr.or.ddit.common.ShowAlert;
import kr.or.ddit.cormem.CorMemberVO;
import kr.or.ddit.jobmem.JobMemberVO;
import kr.or.ddit.rmi.client.ClientConnectFactory;
import kr.or.ddit.rmi.interf.IRemote;

public class PswFindController implements Initializable {
	@FXML
    private JFXTabPane PswFindTypePane;

    @FXML
    private JFXTextField EmailChkField;

    @FXML
    private JFXButton authenticationButton;

    @FXML
    private JFXTextField authenticationField;

    @FXML
    private JFXButton CorPswCancleButton;

    @FXML
    private JFXTextField IdChkField;

    @FXML
    private JFXTextField CorEmailChkField;

    @FXML
    private JFXButton CorauthenticationButton;

    @FXML
    private JFXButton CorPswOKButton;

    @FXML
    private JFXButton PswCancleButton;

    @FXML
    private JFXTextField CorauthenticationField;

    @FXML
    private JFXTextField CorIdChkField;

    @FXML
    private JFXButton PswOKButton;
    
    private IRemote conn;
   
    static String jobPw;
    static String corPw;
    static int random;
    static int jobrandom;
    
    
    private void closeScene() {
	      Stage window = (Stage)PswCancleButton.getScene().getWindow();
	      window.close();
	}
    
    private void jobPwFind() {
    	String ran = String.valueOf(jobrandom);
    	if(authenticationField.getText().equals(ran)) {
    		ShowAlert.showAlertINFORMATION("", "회원님의 패스워드는 "  + jobPw + "입니다.");
    		Stage window = (Stage)IdChkField.getScene().getWindow();
    		window.close();
    	}else {
    		ShowAlert.showAlertINFORMATION("", "이메일 인증번호가 틀립니다");
    	}
    }
    
    private void corPwFind() {
    	String ran = String.valueOf(random);
    	if(CorauthenticationField.getText().equals(ran)) {
    		ShowAlert.showAlertINFORMATION("", "회원님의 패스워드는 "  + corPw + "입니다.");
    		Stage window = (Stage)IdChkField.getScene().getWindow();
	        window.close();
    	}else {
    		ShowAlert.showAlertINFORMATION("", "이메일 인증번호가 틀립니다");
    	}
    }
    
    
    
    private void corauthencation() {
    	CorMemberVO cormemPwFind = new CorMemberVO();
    	cormemPwFind.setCor_id(CorIdChkField.getText());
    	cormemPwFind.setMem_mail(CorEmailChkField.getText());
    	
    	corPw = conn.getICorMemberService().cormemFindPw(cormemPwFind);
    	
    	random = (int) ((Math.random()*8999) +1000);
    	if(corPw == null) {
    		ShowAlert.showAlertWarning("", "정보를 올바르게 다시 입력하거나, 일치하는 회원이 없습니다");
    		
    	}else {
    		SendMail.sendMail(CorEmailChkField.getText(), random); //DB 값으로 이메이 주소 변경해야함
    		ShowAlert.showAlertINFORMATION("메일 전송", "가입하신 메일로 인증번호가 전송되었습니다 인증번호를 확인해주세요");
    		
    	}
    }
    
    private void jobauthencation() {
    	JobMemberVO jobmemPwFind = new JobMemberVO();
    	jobmemPwFind.setMem_id(IdChkField.getText());
    	jobmemPwFind.setMem_mail(EmailChkField.getText());
    	
    	jobPw = conn.getIJobMemSerivce().memFindPW(jobmemPwFind);
    	
    	jobrandom = (int) ((Math.random()*8999) +1000);
    	if(jobPw == null) {
    		ShowAlert.showAlertWarning("", "정보를 올바르게 다시 입력하거나, 일치하는 회원이 없습니다");
    		
    	}else {
    		
    		SendMail.sendMail(EmailChkField.getText(), jobrandom); //DB 값으로 이메이 주소 변경해야함
    		ShowAlert.showAlertINFORMATION("메일 전송", "가입하신 메일로 인증번호가 전송되었습니다 인증번호를 확인해주세요");
    		
    	}
    }
    
    
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		conn = ClientConnectFactory.getClientConnect();
		
		PswCancleButton.setOnAction(event -> closeScene());
		PswOKButton.setOnAction(event -> jobPwFind());
		CorPswOKButton.setOnAction(event -> corPwFind());
		CorPswCancleButton.setOnAction(event -> closeScene());
		
		authenticationButton.setOnAction(event -> jobauthencation());
		CorauthenticationButton.setOnAction(event -> corauthencation());
		
	}



}
