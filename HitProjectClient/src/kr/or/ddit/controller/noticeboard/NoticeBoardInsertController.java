package kr.or.ddit.controller.noticeboard;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import kr.or.ddit.common.ShowAlert;
import kr.or.ddit.noticeboard.NoticeBoardVO;
import kr.or.ddit.rmi.interf.IRemote;

public class NoticeBoardInsertController implements Initializable {

	@FXML
	private TextField insertBoard_title;

	@FXML
	private Button insertBoard_savbtn;

	@FXML
	private TextArea insertBoard_content;

	@FXML
	private Button insertBoard_canbtn;

	Alert aleart = new Alert(AlertType.WARNING);
	IRemote conn = NoticeBoardController.conn;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		insertBoard_savbtn.setOnAction(event -> {
			savebtn();
		});
		insertBoard_canbtn.setOnAction(event -> {
			cancelbtn();
		});
	}

	private void cancelbtn() {
		Stage window = (Stage) insertBoard_canbtn.getScene().getWindow();
		window.close();
	}

	private void savebtn() {
		System.out.println("d");
		String str1 = insertBoard_title.getText();
		String str2 = insertBoard_content.getText();

		if (str1.isEmpty() || str2.isEmpty()) {
			ShowAlert.showAlertWarning("공백입니다.", "공백이니 내용을 입력하십시오");
			return;
		} else {
			NoticeBoardVO notivo = new NoticeBoardVO();
			notivo.setNoti_board_title(str1);
			notivo.setNoti_board_content(str2);
			boolean result = ShowAlert.showAlert1CONFIRMATION("글을 입력하시겠습니까?",
					"입력하시려면 Okay 버튼을 취소하시려면 Cancel 버튼을 눌러주세요");
			if (result == true) {
				conn.getINotiService().insertBoard(notivo);
				Stage window = (Stage) insertBoard_canbtn.getScene().getWindow();
				window.close();
			} else {

				return;
			}
		}

	}

}