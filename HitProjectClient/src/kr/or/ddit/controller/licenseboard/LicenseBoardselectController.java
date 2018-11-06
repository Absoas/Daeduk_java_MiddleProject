package kr.or.ddit.controller.licenseboard;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import kr.or.ddit.common.ShowAlert;
import kr.or.ddit.controller.login.LoginController;
import kr.or.ddit.licenseboard.LicenseBoardVO;
import kr.or.ddit.rmi.interf.IRemote;

public class LicenseBoardselectController implements Initializable{
	
		@FXML
	    private JFXTextField select_jugwan, select_name, select_code;

	    @FXML
	    private JFXComboBox<String> select_class;

	    @FXML
	    private JFXButton select_savbtn, select_delbtn, select_canbtn;

	    public IRemote conn = LicenseBoardController.conn;
	    
	    LicenseBoardController licensecontroller = new LicenseBoardController();
	    
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if (LoginController.ssessionJMem!=null) {
			select_savbtn.setVisible(false);
			select_delbtn.setVisible(false);
		}
		
		if(LoginController.ssessionCMem!=null) {
			select_savbtn.setVisible(false);
			select_delbtn.setVisible(false);
		}
		
		LicenseBoardVO selectVO = new LicenseBoardVO();
		selectVO = licensecontroller.licVO;
		
		select_code.setText(String.valueOf(selectVO.getLic_code()));
		select_name.setText(selectVO.getLic_name());
		select_class.setPromptText(selectVO.getLic_class());
		select_jugwan.setText(selectVO.getLic_jugwan());
		
		select_code.setEditable(false);
		select_name.setEditable(false);
		select_class.setEditable(true);
		select_jugwan.setEditable(false);
		
		
		ObservableList<String> list = FXCollections.observableArrayList();
		list.addAll("프로그래밍","네트워크","서버","보안","데이터베이스","국가자격증");
		select_class.setItems(list);
		
//		select_class.setValue();
		
		select_savbtn.setOnAction(event->{update();});
		select_delbtn.setOnAction(event->{delete();});
		select_canbtn.setOnAction(event->{cancel();});
	}
	
	private void cancel() {
		Stage window = (Stage)select_canbtn.getScene().getWindow();
		window.close();
	}

	private void update() {
		
		select_name.setEditable(true);
		select_class.setEditable(true);
		select_jugwan.setEditable(true);
		
		select_savbtn.setOnAction(event->{
			if(select_name.getText().isEmpty()|| select_class.getPromptText().isEmpty()|| select_jugwan.getText().isEmpty()) {
				ShowAlert.showAlertWarning("공백입니다.", "내용을 입력하세요");
			}else {
				LicenseBoardVO licensevo = new LicenseBoardVO();
				String str1 = select_code.getText();
				String str2 = select_name.getText();
				String str3 = select_class.getValue().toString();
				String str4 = select_jugwan.getText();
				
				licensevo.setLic_code(str1);
				licensevo.setLic_name(str2);
				licensevo.setLic_class(str3);
				licensevo.setLic_jugwan(str4);
				
				boolean cnt = ShowAlert.showAlert1CONFIRMATION("글을 수정하시겠습니까?", "입력하시려면 Okay 버튼을 취소하시려면 Cancel 버튼을 눌러주세요");
				if(cnt == true) {
					conn.getILicenseService().updateBoard(licensevo);
					Stage window = (Stage) select_savbtn.getScene().getWindow();
					window.close();
				}else {
					return;
				}
//				select_savbtn.setOnAction(event2-> {
//					int cnt3 = conn.getILicenseService().updateBoard(licensevo);
//					
//					if(cnt3 == 1) {
//						Stage window = (Stage)select_savbtn.getScene().getWindow();
//					}
//				});
			}
		});
		
	}

	private void delete() {
		String liccode = select_code.getText();
		
		boolean result = ShowAlert.showAlert1CONFIRMATION("삭제하시겠습니까?", "입력하시려면 Okay 버튼을 취소하시려면 Cancel 버튼을 눌러주세요");
		if(result == true) {
			int cnt = LicenseBoardController.conn.getILicenseService().deleteBoard(liccode);
			Stage window = (Stage) select_delbtn.getScene().getWindow();
			window.close();
		}else {
			return;
		}
//		int cnt = LicenseBoardController.conn.getILicenseService().deleteBoard(liccode);
//		select_delbtn.setOnAction(event->{
//			if(select_code.getText().equals(liccode)) {
//				if(cnt == 1) {
//					Stage window = (Stage)select_delbtn.getScene().getWindow();
//					window.close();
//				}
//			}
//		});
		
	}

}
