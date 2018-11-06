package kr.or.ddit.controller.test;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import kr.or.ddit.common.ShowAlert;
import kr.or.ddit.rmi.interf.IRemote;
import kr.or.ddit.test.TestVO;

public class codingselectController implements Initializable{
	
	@FXML private Label label;
	@FXML private Label label2;
	@FXML private Label label3;
	@FXML private Label label4;
	@FXML private JFXTextField testname;
	@FXML private JFXTextField testno;
	@FXML private JFXComboBox testtype;
	@FXML private JFXTextArea testexp;
	@FXML private JFXButton ok;
	@FXML private JFXButton delete;
	@FXML private JFXButton update;
	@FXML private JFXButton close;
	
	public static IRemote conn = null;
	
		
	@FXML
	private void updateOk(ActionEvent event) {
		int cnt = 0;
		TestVO testvo = new TestVO();
		testvo.setTest_no(Integer.parseInt(testno.getText()));
		testvo.setTest_name(testname.getText());
		testvo.setTest_content(testexp.getText());
		if(testname.getText().isEmpty()||testexp.getText().isEmpty()) {
			ShowAlert.showAlertWarning("공백이 있습니다!","항목을 채워주세요!" );
		}
		cnt = conn.getITestService().updatetest(testvo);
		if(cnt > 0) {
			ShowAlert.showAlertINFORMATION("완료!", "수정이 완료되었습니다.");
			Stage window = (Stage)update.getScene().getWindow();
			window.close();
		}
	}
	
	@FXML
	private void close(ActionEvent event) {
		Stage window = (Stage)close.getScene().getWindow();
		window.close();
	}
	
	@FXML
	private void delete(ActionEvent event) {
		boolean result = ShowAlert.showAlert1CONFIRMATION("글을 삭제하시겠습니까?", "삭제를 원하시면 ok를 눌러주세요.");
		int cnt = 0;
		int deleteno = codingController.testvo.getTest_no();
		if(result == true) {
			cnt = conn.getITestService().deletetest(deleteno);
			if (cnt == 1) {	
				ShowAlert.showAlertINFORMATION("삭제완료", "정상적으로 삭제되었습니다.");
				Stage window = (Stage)delete.getScene().getWindow();
				window.close();
			}
		}
	}
	
	@FXML
	private void update(ActionEvent event) {
		testname.setEditable(true);
		testexp.setEditable(true);
		testno.setDisable(true);
		testtype.setDisable(true);
	}
	
	
	private void contentview() {
		testno.setText(String.valueOf(codingController.testvo.getTest_no()));
		testtype.setPromptText(String.valueOf(codingController.testvo.getTest_type()));
		testname.setText(codingController.testvo.getTest_name());
		testexp.setText(codingController.testvo.getTest_content());
		
		testno.setEditable(false);
		testtype.setEditable(false);
		testname.setEditable(false);
		testexp.setEditable(false);
		
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		conn = codingController.conn;
		contentview();
		
	}

}
