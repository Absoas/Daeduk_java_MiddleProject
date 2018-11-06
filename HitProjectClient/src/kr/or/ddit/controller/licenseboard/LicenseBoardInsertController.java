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
import kr.or.ddit.licenseboard.LicenseBoardVO;
import kr.or.ddit.rmi.interf.IRemote;

public class LicenseBoardInsertController implements Initializable{

		@FXML
	    private JFXTextField licinsert_jugwan, licinsert_name, licinsert_code;

	    @FXML
	    private JFXComboBox<String> licinsert_class;

	    @FXML
	    private JFXButton licinsert_savbtn, licinsert_canbtn;
	
	    IRemote conn = LicenseBoardController.conn;
	    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		licinsert_savbtn.setOnAction(event->{savebtn();});
		licinsert_canbtn.setOnAction(event->{cancelbtn();});
		
		ObservableList<String> list = FXCollections.observableArrayList();
		list.addAll("프로그래밍","네트워크","서버","보안","데이터베이스","국가자격증");
		licinsert_class.setItems(list);
	}

	private void cancelbtn() {
		Stage window = (Stage)licinsert_canbtn.getScene().getWindow();
		window.close();
	}

	private void savebtn() {
		System.out.println("d");
		String str1 = licinsert_code.getText();
		String str2 = licinsert_name.getText();
		String str3 = licinsert_class.getValue().toString();
		String str4 = licinsert_jugwan.getText();
		
		if(str1.isEmpty() || str2.isEmpty() || str3.isEmpty() || str4.isEmpty()) {
			ShowAlert.showAlertWarning("공백입니다.", "공백이니 내용을 입력하십시오");
			return;
		}else {
			LicenseBoardVO licensevo = new LicenseBoardVO();
			licensevo.setLic_code(str1);
			licensevo.setLic_name(str2);
			licensevo.setLic_class(str3);
			licensevo.setLic_jugwan(str4);
			boolean result = ShowAlert.showAlert1CONFIRMATION("글을 입력하시겠습니까?", "입력하시려면 Okay 버튼을 취소하시려면 Cancel 버튼을 눌러주세요");
			if(result == true) {
				conn.getILicenseService().insertBoard(licensevo);
				Stage window = (Stage) licinsert_savbtn.getScene().getWindow();
				window.close();
			}else {
				return;
			}
		}
	}

}
