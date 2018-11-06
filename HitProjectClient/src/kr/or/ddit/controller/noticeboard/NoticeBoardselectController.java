package kr.or.ddit.controller.noticeboard;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import kr.or.ddit.common.ShowAlert;
import kr.or.ddit.controller.login.LoginController;
import kr.or.ddit.noticeboard.NoticeBoardVO;
import kr.or.ddit.rmi.interf.IRemote;

public class NoticeBoardselectController implements Initializable {

	@FXML
	private TextArea selectBoard_content;

	@FXML
	private TextField selectBoard_no, selectBoard_date, selectBoard_title, selectBoard_lookup;

	@FXML
	private Button selectBoard_updbtn, selectBoard_delbtn, selectBoard_canbtn;

	private NoticeBoardVO noiceboard;

	public IRemote conn = NoticeBoardController.conn;

	NoticeBoardController noticontroller = new NoticeBoardController();

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		if (LoginController.ssessionJMem!=null) {
			selectBoard_updbtn.setVisible(false);
			selectBoard_delbtn.setVisible(false);
		}
		
		if(LoginController.ssessionCMem!=null) {
			selectBoard_updbtn.setVisible(false);
			selectBoard_delbtn.setVisible(false);
		}

		NoticeBoardVO selectVO = new NoticeBoardVO();
		selectVO = noticontroller.notiVO;

		selectBoard_no.setText(String.valueOf(selectVO.getNoti_board_no()));
		selectBoard_title.setText(selectVO.getNoti_board_title());
		selectBoard_content.setText(selectVO.getNoti_board_content());
		selectBoard_date.setText(selectVO.getNoti_board_date());
		selectBoard_lookup.setText(String.valueOf(selectVO.getNoti_board_lookup()));

			
		
		selectBoard_no.setEditable(false);
		selectBoard_title.setEditable(false);
		selectBoard_content.setEditable(false);
		selectBoard_date.setEditable(false);
		selectBoard_lookup.setEditable(false);

		selectBoard_canbtn.setOnAction(event -> {
			cancel();
		});
		selectBoard_delbtn.setOnAction(event -> {
			delete();
		});
		selectBoard_updbtn.setOnAction(event -> {
			update();
		});
	}


	private void delete() {
		
//		selectBoard_no.clear();
//		selectBoard_no.setEditable(true);
		String notiid = selectBoard_no.getText();

		boolean result = ShowAlert.showAlert1CONFIRMATION("삭제하시겠습니까?", "입력하시려면 Okay 버튼을 취소하시려면 Cancel 버튼을 눌러주세요");
		if (result == true) {
			int cnt2 = NoticeBoardController.conn.getINotiService().deleteBoard(notiid);
			Stage window = (Stage) selectBoard_delbtn.getScene().getWindow();
			window.close();
		} else {
			return;
		}
			
	}

	private void cancel() {
		Stage window = (Stage) selectBoard_canbtn.getScene().getWindow();
		window.close();
	}

	private void update() {
			
		selectBoard_title.setEditable(true);
		selectBoard_content.setEditable(true);
		
		
		selectBoard_updbtn.setOnAction(event->{
			
			if(selectBoard_title.getText().isEmpty() || selectBoard_content.getText().isEmpty()) {
				ShowAlert.showAlertWarning("공백입니다.", "내용을 입력하세요");
			}else {
				NoticeBoardVO notiboardvo = new NoticeBoardVO();
				int cnt = Integer.parseInt(selectBoard_no.getText());
				notiboardvo.setNoti_board_title(selectBoard_title.getText());
				notiboardvo.setNoti_board_content(selectBoard_content.getText());
				notiboardvo.setNoti_board_no(cnt);
					boolean cnt2 = ShowAlert.showAlert1CONFIRMATION("글을 입력하시겠습니까?", "입력하시려면 Okay 버튼을 취소하시려면 Cancel 버튼을 눌러주세요");
					if(cnt2 == true) {
						conn.getINotiService().updateBoard(notiboardvo);
						Stage window = (Stage)selectBoard_updbtn.getScene().getWindow();
						window.close();
					}else {
						return;
					}
				
			}
		});
	}
}
