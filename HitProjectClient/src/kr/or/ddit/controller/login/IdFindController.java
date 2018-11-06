package kr.or.ddit.controller.login;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import kr.or.ddit.common.ShowAlert;
import kr.or.ddit.cormem.CorMemberVO;
import kr.or.ddit.jobmem.JobMemberVO;
import kr.or.ddit.rmi.client.ClientConnectFactory;
import kr.or.ddit.rmi.interf.IRemote;

public class IdFindController implements Initializable{
	
	@FXML
	JFXTextField NameCheckField,RegnoCheckField,CorNameCheckField,CorNoCheckField;
	
	@FXML
	JFXButton CancleBtn,OKBtn,CorOKButton,CorCancleButton;
	
	@FXML
	JFXTabPane IdFindTypePane;
	
	public static IRemote conn;
//	JobMemberVO ssessionJMem;
//	CorMemberVO ssessionCMem;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		conn=ClientConnectFactory.getClientConnect();
		CancleBtn.setOnAction(event -> closeScene());
		OKBtn.setOnAction(event -> memIdFind());
		CorOKButton.setOnAction(event -> corIdFind());
		CorCancleButton.setOnAction(event -> closeScene());
	}
	// 확인번튼 누르면
	//이름이랑 주민ㄴ번호 입력받은걸 이비로 보내는 메서드
	
	
	
	
	
	
	
	// 취소버튼 기능 
	// 취소버튼 누르면 창 닫기
	
	
	
	
	
	
	//
	
	//취소버튼을 누를 때 열려있는지 창이 닫히는 메서드
	private void closeScene() {
	      Stage window = (Stage)CancleBtn.getScene().getWindow();
	      window.close();
	}
	
	//구직회원인지 기업회원인지 구별
//	private void CheckType()  {
//		if(IdFindTypePane.selectionModelProperty().getValue().getSelectedItem().getText().equals("구직회원")) {
//			memIdFind();
//		}else {
//			corIdFind();
//		}
//		
//	}
	
	//구인회원이 아이디를 찾을 때
	
	private void memIdFind() {
		int memberType =1; 
		JobMemberVO memberIdFind = new JobMemberVO();
		memberIdFind.setMem_name(NameCheckField.getText());
		memberIdFind.setJmem_regno(RegnoCheckField.getText());
		
		//이름,주민번호를 DB로 보내서 그것과 일치하는 ID값 가져오기
		String memId = conn.getIJobMemSerivce().memFindID(memberIdFind);
		if(memId==null) {
			ShowAlert.showAlertWarning("", "정보를 올바르게 다시 입력하거나, 일치하는 회원이 없습니다");
			return;
		}else {
			Stage window = (Stage)NameCheckField.getScene().getWindow();
            ShowAlert.showAlertINFORMATION("ID찾기완료", "회원님의 ID는 " + memId + " 입니다." );
            window.close();
		}
//		ssessionJMem = memId;
		
	}
	
	//기업회원이 아이디를 찾을 때
	private void corIdFind()  {
		int memberType =2; 
		CorMemberVO corIdFind = new CorMemberVO();
		corIdFind.setMem_name(CorNameCheckField.getText());
		corIdFind.setCor_no(CorNoCheckField.getText());
		
		String corID = conn.getICorMemberService().CormemFindID(corIdFind);
		if(corID==null) {
			ShowAlert.showAlertWarning("", "정보를 올바르게 다시 입력하거나, 일치하는 회원이 없습니다");
			return;
		}else {
			Stage window = (Stage)CorOKButton.getScene().getWindow();
			ShowAlert.showAlertINFORMATION("ID찾기완료", "회원님의 ID는 " + corID + "입니다.");
			window.close();
		}
//		ssessionCMem = corId;
		goIdFind(memberType);
	}
	
	
	//아이디 찾기 전에 구인회원인지 기업회원인지 구분하는 메서드
	private void goIdFind(int memberType) {
		switch (memberType) {
		case 1:
			try {
				Parent root1= FXMLLoader.load(getClass().getResource("IdfindFx.fxml"));
				Scene scene1 = new Scene(root1);
				Stage appStage1 = (Stage) ((Node)IdFindTypePane).getScene().getWindow();
				appStage1.setTitle("구직회원 ID찾기");
				appStage1.setScene(scene1);
				appStage1.show();
			}catch(IOException e) {
				e.printStackTrace();
			}
			break;
		case 2:
			try {
				Parent root2= FXMLLoader.load(getClass().getResource("IdfindFx.fxml"));
				Scene scene2 = new Scene(root2);
				Stage appStage2 = (Stage) ((Node)IdFindTypePane).getScene().getWindow();
				appStage2.setTitle("구인기업 ID찾기");
				appStage2.setScene(scene2);
				appStage2.show();
			}catch(IOException e) {
				e.printStackTrace();
			}
			break;
		}
	}
	

	
	
	
	
	
	
}
