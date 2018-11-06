package kr.or.ddit.controller.jmem.myintro;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTextArea;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.stage.Stage;

public class JMemIntroModalController implements Initializable {
	@FXML
	private JFXTextArea introArea;
	
	public static String intro;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		introArea.setText(JMemAddMyIntroController.intro);
	}
	
	
	@FXML
	public void saveIntro(ActionEvent event) {
		intro=introArea.getText();
		Stage modalStage = (Stage) ((Node)introArea).getScene().getWindow();
		modalStage.close();
	}
	
	@FXML
	public void closeModal(ActionEvent event) {
		Stage modalStage = (Stage) ((Node)introArea).getScene().getWindow();
		modalStage.close();
	}
	
	@FXML
	public void changeSpell(ActionEvent event) {
		String aa = introArea.getText();
		aa=aa.replaceAll("열시미", "열심히");
		aa=aa.replaceAll("되겟습니다", "되겠습니다");
		aa=aa.replaceAll("왓습니다", "왔습니다");
		aa=aa.replaceAll("마니", "많이");
		aa=aa.replaceAll("안습니다", "않습니다");
		aa=aa.replaceAll("안대요", "안돼요");
		aa=aa.replaceAll("햇습니다", "했습니다");
		aa=aa.replaceAll("하겟습니다", "하겠습니다");
		introArea.setText(aa);
	}

}
