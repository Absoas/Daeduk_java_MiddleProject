package kr.or.ddit.controller.login;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXDecorator;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import kr.or.ddit.cormem.CorMemberVO;
import kr.or.ddit.jobmem.JobMemberVO;
import kr.or.ddit.rmi.client.ClientConnectFactory;
import kr.or.ddit.rmi.interf.IRemote;

public class LoginController implements Initializable{
	@FXML
	private JFXTextField textf_id;
	@FXML
	private JFXPasswordField passf_pass;
	@FXML
	private Label labelfail;
	@FXML
	private JFXTabPane memTypePane;
	
	public static int memberType;
	public static IRemote conn;
	public static JobMemberVO ssessionJMem;
	public static CorMemberVO ssessionCMem;
	
	
	@FXML
	public void loginCheck(ActionEvent event) throws IOException{
		
		if(textf_id.getText().equals("admin")&&passf_pass.getText().equals("admin")) {
			adminLogin();
			return;
		}
		
		if(memTypePane.selectionModelProperty().getValue().getSelectedItem().getText().equals("구직회원")) {
			jobMemLogin();
		}else {
			corMemLogin();
		}
		
		
	}
	
	@FXML
	public void IdFind(ActionEvent event) throws IOException{
		Parent root= FXMLLoader.load(getClass().getResource("IdfindFx.fxml"));
		Scene scene = new Scene(root);
		Stage appStage = new Stage();
		appStage.setTitle("아이디 찾기");
		appStage.setScene(scene);
		appStage.show();
	}
	
	@FXML
	public void PswFind(ActionEvent event) throws IOException{
		Parent root= FXMLLoader.load(getClass().getResource("PswFindFx.fxml"));
		Scene scene = new Scene(root);
		Stage appStage = new Stage();
		appStage.setTitle("아이디 찾기");
		appStage.setScene(scene);
		appStage.show();
		
	}
	
	@FXML
	public void selectSignupMenu(ActionEvent event) throws IOException {
		Parent root= FXMLLoader.load(getClass().getResource("../signup/SignupMenu.fxml"));
		Scene scene = new Scene(root);
		Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		appStage.setTitle("SignUp Menu");
		appStage.setScene(scene);
		appStage.show();
	}
	
	private void jobMemLogin()  {
		memberType =1; 
		JobMemberVO loginMember = new JobMemberVO();
		loginMember.setMem_id(textf_id.getText());
		loginMember.setMem_pass(passf_pass.getText());
		
		JobMemberVO logMem = conn.getIJobMemSerivce().Login_Check(loginMember);
		if(logMem==null) {
			labelfail.setText("일치하는 회원이 없습니다.");
			return;
		}
		ssessionJMem = logMem;
		goMain(memberType);
	}
	
	private void corMemLogin() {
		memberType=2;
		CorMemberVO loginMember = new CorMemberVO();
		loginMember.setMem_id(textf_id.getText());
		loginMember.setMem_pass(passf_pass.getText());
		
		CorMemberVO logMem = conn.getICorMemberService().Login_Check(loginMember);
		if(logMem==null) {
			labelfail.setText("일치하는 회원이 없습니다.");
			return; 
		}
		
		ssessionCMem = logMem;
		goMain(memberType);
	}
	
	private void adminLogin() {
		memberType=3;
		//아이디값으로 db에서 관리자 vo가져오기
		goMain(memberType);
	}
	
	

	private void goMain(int memberType) {
		switch (memberType) {
		case 1:
			try {
				Parent root1= FXMLLoader.load(getClass().getResource("../jmem/main/JMemMainMenu.fxml"));
				Stage appStage1 = (Stage) ((Node)memTypePane).getScene().getWindow();
				Scene scene = new Scene(root1);
				appStage1.setScene(scene);
				appStage1.show();
			}catch(IOException e) {
				e.printStackTrace();
			}
			break;
		case 2:
			try {
				Parent root2= FXMLLoader.load(getClass().getResource("../cor/cmenu/CMemMain.fxml"));
				Stage appStage2 = (Stage) ((Node)memTypePane).getScene().getWindow();
				Scene scene = new Scene(root2);
				appStage2.setScene(scene);
				appStage2.show();
			}catch(IOException e) {
				e.printStackTrace();
			}
			break;

		case 3:
			try {
				Parent root3= FXMLLoader.load(getClass().getResource("../admin/amenu/AdminMain.fxml"));
				Stage appStage3 = (Stage) ((Node)memTypePane).getScene().getWindow();
				Scene scene = new Scene(root3);
				appStage3.setScene(scene);
				appStage3.show();
			}catch(IOException e) {
				e.printStackTrace();
			}
			break;
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		conn=ClientConnectFactory.getClientConnect();
	}

}
