package kr.or.ddit.controller.reviewboard;

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
import kr.or.ddit.revboard.RevBoardVO;
import kr.or.ddit.rmi.interf.IRemote;

public class RevBoardDetailController implements Initializable{
	
	@FXML
	private TextField insertTitle, insertWriter;
	
	@FXML
	private TextArea insertContents;
	
	@FXML
	private Text txtDate, txtViews, txtNo;
	
	@FXML
	private Button btnInsert, btnUpdate, btnDelete;
	
	private RevBoardVO selBoard; // NoticeBoardVOŸ���� selBoard�� ���������� ����
									// selBoard�� vo(������Ʈ�ѷ����� ������)�� ���� ������ ����
	private static IRemote conn = null;
	
	
	
	
	@FXML
	private void insertNotice(ActionEvent event) {
		
	
		String title = insertTitle.getText();
		String writer = insertWriter.getText();
		String contents = insertContents.getText();
		
		RevBoardVO rv = new RevBoardVO();
		
		rv.setRev_board_title(title);
		rv.setRev_board_writer(writer);
		rv.setRev_board_contents(contents);
		
		boolean result = ShowAlert.showAlert1CONFIRMATION("", "글을 등록 하시겠습니까?");
		if(result == true) {
			conn.getIBoardService().insert(rv);
			Stage window = (Stage)btnInsert.getScene().getWindow();
            ShowAlert.showAlertINFORMATION("", "글이 등록 되었습니다.");
	        window.close();
		}else {
			return;
		}
		
	}
	
	@FXML
	private void updateNotice(ActionEvent event) {
		RevBoardVO rv = new RevBoardVO();
		//insertTitle.setText(selBoard.getNotice_title()); //VO��ü�� selBoard�� �̿��Ͽ� title�� ��������
		//insertWriter.setText(selBoard.getNotice_writer());//inserTitle�� �ٽ� ȣ���ؼ� ������ �� �ְ� �����.
		//insertContents.setText(selBoard.getNotice_contents());
		
		rv = selBoard;
		
		
		rv.setRev_board_title(insertTitle.getText());
		rv.setRev_board_writer(insertWriter.getText());
		rv.setRev_board_contents(insertContents.getText());
		
		
		boolean result = ShowAlert.showAlert1CONFIRMATION("", "글을 수정 하시겠습니까?");
		if(result ==true) {
			conn.getIBoardService().updateBoard(rv);
			Stage window = (Stage)btnUpdate.getScene().getWindow();
            ShowAlert.showAlertINFORMATION("", "글이 수정 되었습니다.");
	        window.close();
		}else {
			return;
		}
		
		
		
		
	}
	
	@FXML
	private void deleteNotice(ActionEvent event) {
		boolean result = ShowAlert.showAlert1CONFIRMATION("", "글을 삭제 하시겠습니까?");
		if(result == true) {
			int cnt = conn.getIBoardService().deleteBoard(selBoard.getRev_board_no());
			Stage window = (Stage)btnDelete.getScene().getWindow();
            ShowAlert.showAlertINFORMATION("", "글이 삭제 되었습니다.");
	        window.close();
		}else {
			return;
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) { //�̴ϼȶ������ �ش� Ŭ������ ����Ǹ� �ʱ⿡ ���� ��������ִ� ����
		
		selBoard = RevBoardController.selBoard;
		conn = RevBoardController.conn;
		
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
		
		if(!(selBoard == null)) {
			insertTitle.setText(selBoard.getRev_board_title()); 
			insertWriter.setText(selBoard.getRev_board_writer()); 
			insertContents.setText(selBoard.getRev_board_contents()); 
			txtDate.setText(selBoard.getRev_board_date()); 
			txtNo.setText(selBoard.getRev_board_no()+""); 
			txtViews.setText(selBoard.getRev_board_views()+""); 
		}
	}

}
