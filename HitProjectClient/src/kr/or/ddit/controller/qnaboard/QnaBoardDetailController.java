package kr.or.ddit.controller.qnaboard;

import java.awt.Window;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import kr.or.ddit.common.ShowAlert;
import kr.or.ddit.controller.login.LoginController;
import kr.or.ddit.qnaboard.QnaBoardVO;
import kr.or.ddit.rmi.interf.IRemote;

public class QnaBoardDetailController implements Initializable{
	
	@FXML
	private TextField insertTitle, insertWriter;
	
	@FXML
	private TextArea insertContents;
	
	@FXML
	private Text txtDate, txtViews, txtNo;
	
	@FXML
	private Button btnInsert, btnUpdate, btnDelete;
	
	private QnaBoardVO selBoard;
	
	private IRemote conn = null;
	
	
	
	
	@FXML
	private void insertNotice(ActionEvent event) {
		
	
		String title = insertTitle.getText();
		String writer = insertWriter.getText();
		String contents = insertContents.getText();
		
		QnaBoardVO qv = new QnaBoardVO();
		
		qv.setQna_board_title(title);
		qv.setQna_board_writer(writer);
		qv.setQna_board_contents(contents);
		
		boolean result = ShowAlert.showAlert1CONFIRMATION("", "글을 등록 하시겠습니까?");
		if(result ==true) {
			conn.getIQnaService().insert(qv);
			Stage window = (Stage)btnInsert.getScene().getWindow();
            ShowAlert.showAlertINFORMATION("", "글이 등록 되었습니다.");
	        window.close();
		}else {
			return;
		}
	}
	
	@FXML
	private void updateNotice(ActionEvent event) {
		QnaBoardVO qv = new QnaBoardVO();
		
		qv = selBoard;
		
		qv.setQna_board_title(insertTitle.getText());
		qv.setQna_board_writer(insertWriter.getText());
		qv.setQna_board_contents(insertContents.getText());
		boolean result =ShowAlert.showAlert1CONFIRMATION("", "글을 수정 하시겠습니까?");
		if(result == true) {
			conn.getIQnaService().updateBoard(qv);
			Stage window = (Stage)btnUpdate.getScene().getWindow();
            ShowAlert.showAlertINFORMATION("", "글이 수정 되었습니다.");
	        window.close();
		}else {
			return;
		}
	}
	
	@FXML
	private void deleteNotice(ActionEvent event) {
		boolean result =ShowAlert.showAlert1CONFIRMATION("", "글을 삭제 하시겠습니까?");
		if(result == true) {
			int cnt = conn.getIQnaService().deleteBoard(selBoard.getQna_board_no());
			Stage window = (Stage)btnDelete.getScene().getWindow();
            ShowAlert.showAlertINFORMATION("", "글이 삭제 되었습니다.");
	        window.close();
		}else {
			return;
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		selBoard = QnaBoardController.selBoard;
		conn = QnaBoardController.conn;
		
		if(LoginController.memberType == 1) {
			btnInsert.setVisible(true);
			btnUpdate.setVisible(true);
			btnDelete.setVisible(true);
		}else if(LoginController.memberType == 2) {
			btnInsert.setVisible(false);
			btnUpdate.setVisible(false);
			btnDelete.setVisible(false);
		}else {
			btnInsert.setVisible(true);
			btnUpdate.setVisible(true);
			btnDelete.setVisible(true);
		}

		if (!(selBoard == null)) {
			insertTitle.setText(selBoard.getQna_board_title());
			insertWriter.setText(selBoard.getQna_board_writer());
			insertContents.setText(selBoard.getQna_board_contents());
			txtDate.setText(selBoard.getQna_board_date());
			txtNo.setText(selBoard.getQna_board_no() + "");
			txtViews.setText(selBoard.getQna_board_views() + "");
		}
	}

}
