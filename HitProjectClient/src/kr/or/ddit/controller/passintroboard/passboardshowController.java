package kr.or.ddit.controller.passintroboard;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import kr.or.ddit.common.ShowAlert;
import kr.or.ddit.controller.login.LoginController;
import kr.or.ddit.passIntroBoard.PassIntroboardVO;
import kr.or.ddit.rmi.interf.IRemote;

public class passboardshowController implements Initializable {
   @FXML private JFXButton ok;
   @FXML private JFXTextField title;
   @FXML private JFXTextField writer;
   @FXML private JFXTextArea contents;
   @FXML private JFXButton delete;
   @FXML private JFXButton update;
   @FXML private JFXButton updateok;
   @FXML private JFXTextField no;
   
   private IRemote conn = null;
   
   private void contentsView() {
      title.setText(passboardController.passVO.getPass_board_title());
      writer.setText(passboardController.passVO.getPass_board_writer());
      contents.setText(passboardController.passVO.getPass_board_contents());
      no.setText(passboardController.passVO.getPass_board_no()+"");
      
      
      title.setEditable(false);
       writer.setEditable(false);
       contents.setEditable(false);
       no.setEditable(false);
   }
   
   @FXML
   private void update(ActionEvent event) {
      title.setEditable(true);
      contents.setEditable(true);
      writer.setDisable(true);
   }
   
   @FXML
   private void updateok(ActionEvent event) {
      int cnt = 0;
      PassIntroboardVO passvo = new PassIntroboardVO();
      passvo.setPass_board_no(Integer.parseInt(no.getText()));
      passvo.setPass_board_title(title.getText());
      passvo.setPass_board_contents(contents.getText());
      
      cnt = conn.getIPassIntroBoardService().updatepassboard(passvo);
      if(cnt > 0) {
         ShowAlert.showAlertINFORMATION("완료!", "수정이 완료되었습니다.");
         Stage window = (Stage)update.getScene().getWindow();
         window.close();
      }
   }
   
   @FXML
   private void okbutton (ActionEvent event) {
      Stage ex = (Stage)ok.getScene().getWindow();
      ex.close();
   }
   
   @FXML
   private void delete (ActionEvent event) {
      boolean result = ShowAlert.showAlert1CONFIRMATION("글을 삭제하시겠습니까?", "삭제를 원하시면 ok를 눌러주세요.");
      int cnt = 0;
      int passno = passboardController.passVO.getPass_board_no();
      if(result == true) {
         cnt = conn.getIPassIntroBoardService().deletepassboard(passno);
         if(cnt == 1) {
            ShowAlert.showAlertINFORMATION("삭제완료", "정상적으로 삭제되었습니다.");
            Stage window = (Stage)delete.getScene().getWindow();
            window.close();
         }
      }
   }
   

   @Override
   public void initialize(URL location, ResourceBundle resources) {
      conn = passboardController.conn;
      
      if(LoginController.memberType==3) {
         delete.setVisible(true);
         update.setVisible(true);
         updateok.setVisible(true);
      }else {
         delete.setVisible(false);
         update.setVisible(false);
         updateok.setVisible(false);
      }
      
      contentsView();
      
   }

}