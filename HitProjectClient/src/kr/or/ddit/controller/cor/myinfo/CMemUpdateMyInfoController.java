package kr.or.ddit.controller.cor.myinfo;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import kr.or.ddit.common.ShowAlert;
import kr.or.ddit.controller.cor.myinfo.imagetool.ImageToolController;
import kr.or.ddit.controller.login.LoginController;
import kr.or.ddit.cormem.CorMemberVO;
import kr.or.ddit.rmi.client.ClientConnectFactory;
import kr.or.ddit.rmi.interf.IRemote;

public class CMemUpdateMyInfoController implements Initializable {

   @FXML
   private JFXTextField cmem_name;

   @FXML
   private JFXTextField cor_addr;

   @FXML
   private JFXTextField cmem_id;

   @FXML
   private JFXTextField cmem_pass;

   @FXML
   private JFXButton canBtn, addprofile;

   @FXML
   private JFXTextField cor_name;

   @FXML
   private JFXTextField cor_no;

   @FXML
   private JFXButton updBtn;

   @FXML
   private JFXButton savBtn;

   @FXML
   private JFXTextField cmem_mail;
   
   @FXML
   private ImageView logoview;

   private IRemote conn;
   
   @Override
   public void initialize(URL location, ResourceBundle resources) {
      conn = ClientConnectFactory.getClientConnect();
      if(LoginController.ssessionCMem.getMem_image()==null) {
    	  Image image = new Image(getClass().getResourceAsStream("../../../img/noimg.png"));
    	  logoview.setImage(image);
      }else {
    	  Image image = new Image(getClass().getResourceAsStream("../../../img/"+LoginController.ssessionCMem.getCor_name()+".png"));
    	  logoview.setImage(image);
      }
      
      start();
      
      savBtn.setVisible(false);
      canBtn.setVisible(false);
      
      updBtn.setOnAction(event-> {update();});
      
      addprofile.setOnAction(event->{
    	  Parent root = null;  
    	  
    	  Stage primaryStage = (Stage) ((Node) logoview).getScene().getWindow();
		    try {
				root = FXMLLoader.load(getClass().getResource("imagetool/ImageTool.fxml"));
				Stage addIntroStage = new Stage();
				Scene scene = new Scene(root);
				addIntroStage.setTitle("프로필사진등록");
				addIntroStage.setScene(scene);
				addIntroStage.initOwner(primaryStage);
				addIntroStage.initModality(Modality.APPLICATION_MODAL);
				addIntroStage.showAndWait();
				
				logoview.setImage(ImageToolController.img);
				
				
			} catch (IOException e) {
				e.printStackTrace();
			}
      });

   }

   private void update() {
      updBtn.setDisable(true);
      savBtn.setVisible(true);
      canBtn.setVisible(true);
      
      cmem_pass.setEditable(true);
      cmem_name.setEditable(true);
      cmem_mail.setEditable(true);
      
      savBtn.setOnAction(event -> {
         CorMemberVO cmemVO = new CorMemberVO();
         cmemVO.setMem_id(cmem_id.getText());
         cmemVO.setMem_pass(cmem_pass.getText());
         cmemVO.setMem_name(cmem_name.getText());
         cmemVO.setMem_mail(cmem_mail.getText());
         
         int cnt = 0;
         
         boolean result = ShowAlert.showAlert1CONFIRMATION("수정", "정말 수정하시겠습니까??");
        
         if(result) {
         	cnt =  conn.getICorMemberService().updateMyInfo(cmemVO);
         }else {
         	ShowAlert.showAlertError("취소", "취소하였습니다.");
         }
         
         if(cnt > 0) {
//            ssessionCMem.setMem_pass(cmem_id.getText());
//            ssessionCMem.setMem_name(cmem_name.getText());
//            ssessionCMem.setMem_mail(cmem_mail.getText());

            savBtn.setVisible(false);
            canBtn.setVisible(false);
            updBtn.setDisable(false);
            
            start();
         }
      });
      
      canBtn.setOnAction(event -> {
         //나중에 용빈이가 close 로바꾸기로함
         savBtn.setVisible(false);
         canBtn.setVisible(false);
         updBtn.setDisable(false);
         start();
      });
      
     
   }

   private void start() {
      
      cmem_id.setText(LoginController.ssessionCMem.getMem_id());
      cmem_pass.setText(LoginController.ssessionCMem.getMem_pass());
      cmem_name.setText(LoginController.ssessionCMem.getMem_name());
      cor_no.setText(LoginController.ssessionCMem.getCor_no());
      cor_name.setText(LoginController.ssessionCMem.getCor_name());
      cmem_mail.setText(LoginController.ssessionCMem.getMem_mail());
      cor_addr.setText(LoginController.ssessionCMem.getCor_addr());
      
//      cmem_id.setText("chaeyeon");
//      cmem_pass.setText("pass");
//      cmem_name.setText("name");
//      cor_no.setText("no");
//      cor_name.setText("corname");
//      cmem_mail.setText("mail");
//      cor_addr.setText("coraddr");
      
      cmem_id.setEditable(false);
      cmem_pass.setEditable(false);
      cmem_name.setEditable(false);
      cor_no.setEditable(false);
      cor_name.setEditable(false);
      cmem_mail.setEditable(false);
      cor_addr.setEditable(false);
      
   }

}