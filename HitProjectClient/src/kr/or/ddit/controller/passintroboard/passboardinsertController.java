package kr.or.ddit.controller.passintroboard;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import kr.or.ddit.common.ShowAlert;
import kr.or.ddit.passIntroBoard.PassIntroboardVO;
import kr.or.ddit.rmi.interf.IRemote;

public class passboardinsertController implements Initializable{
	@FXML private JFXTextField title;
	@FXML private JFXTextArea contents;
	@FXML private JFXButton ok;
	@FXML private JFXButton cancel;
	
	public static IRemote conn = null;
	
	@FXML
	private void ok(ActionEvent event) {
		int cnt = 0;
		
		PassIntroboardVO passvo = new PassIntroboardVO();
		passvo.setPass_board_title(title.getText());
		passvo.setPass_board_contents(contents.getText());
		cnt = conn.getIPassIntroBoardService().insertpassboard(passvo);
		
		if(cnt == 1) {
			ShowAlert.showAlertINFORMATION("완료!", "글이 정상적으로 작성되었습니다.");
			Stage window = (Stage)ok.getScene().getWindow();
			window.close();
		}
	}
	
	@FXML
	private void cancel(ActionEvent event) {
		Stage window = (Stage)cancel.getScene().getWindow();
		window.close();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		conn = passboardController.conn;
		
	}

}
