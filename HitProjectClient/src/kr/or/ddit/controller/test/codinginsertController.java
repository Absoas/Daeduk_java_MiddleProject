package kr.or.ddit.controller.test;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import kr.or.ddit.common.ShowAlert;
import kr.or.ddit.rmi.interf.IRemote;
import kr.or.ddit.test.TestVO;

public class codinginsertController implements Initializable {
	@FXML private Label label;
	@FXML private Label label2;
	@FXML private Label label3;
	@FXML private Label label4;
	@FXML private JFXTextField testname;
	@FXML private JFXComboBox combolist;
	@FXML private JFXTextArea testexp;
	@FXML private JFXButton ok;
	
	public static IRemote conn = null;
	
	@FXML
	private void ok(ActionEvent event) {
		int cnt = 0;
		int combo=0;
		String str1 = combolist.getPromptText();
		String str2 = testname.getText();
		String str3 = testexp.getText();
		if(str1.isEmpty()||str2.isEmpty()||str3.isEmpty()) {
			ShowAlert.showAlertWarning("공백입니다!", "내용을 작성해주세요");
		}
		
		if(str1.equals("코딩문제")) {
			combo = 1;
		}else {
			combo = 2;
		}
		TestVO testvo = new TestVO();
		testvo.setTest_type(combo);
		testvo.setTest_name(str2);
		testvo.setTest_content(str3);
		cnt = conn.getITestService().inserttest(testvo);
		
		if(cnt == 1) {
			ShowAlert.showAlertINFORMATION("완료!", "글이 정상적으로 작성되었습니다.");
			Stage window = (Stage)ok.getScene().getWindow();
			window.close();
		}
		
		
	
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		conn = codingController.conn;
		ObservableList<String> combo = FXCollections.observableArrayList("코딩문제","그림문제");
		combolist.setItems(combo);
		
	}
	
	

}
