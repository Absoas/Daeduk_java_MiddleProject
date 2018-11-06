package kr.or.ddit.controller.jmem.myinfo;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import com.jfoenix.controls.JFXTreeTableView;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import kr.or.ddit.common.ShowAlert;
import kr.or.ddit.controller.jmem.myinfo.imagetool.ImageToolController;
import kr.or.ddit.controller.login.LoginController;
import kr.or.ddit.jobmem.JobMemberVO;
import kr.or.ddit.rmi.client.ClientConnectFactory;
import kr.or.ddit.rmi.interf.IRemote;
import kr.or.ddit.controller.signup.ZipDrawerController.Zip;

public class JMemUpdateMyInfoController implements Initializable{

        @FXML
       private JFXTextField mem_addr;

       @FXML
       private JFXTextField mem_name;

       @FXML
       private JFXTextField mem_id;

       @FXML
       private JFXTextField mem_regno;

       @FXML
       private JFXTextField mem_zip;

       @FXML
       private JFXButton updBtn, zipBtn;

       @FXML
       private JFXButton savBtn, canBtn , addprofile;

       @FXML
       private JFXTextField mem_pass;

       @FXML
       private JFXToggleButton mem_state;
   
       @FXML
       private Label begong, gong;
       
       @FXML
       private ImageView profileview;
       @FXML
       private JFXDrawer drawer;
       
       private JobMemberVO ssessionMem;   
   
       private IRemote conn;
       
       
   @Override
   public void initialize(URL location, ResourceBundle resources) {
      conn = ClientConnectFactory.getClientConnect();
      ssessionMem = LoginController.ssessionJMem;
      if(ssessionMem.getMem_image()==null) {
    	  Image image = new Image(getClass().getResourceAsStream("../../../img/noimg.png"));
    	  profileview.setImage(image);
      }else {
    	  File file = new File(ssessionMem.getMem_image());
    	  Image image = new Image(getClass().getResourceAsStream("../../../img/"+LoginController.ssessionJMem.getMem_id()+".png"));
    	  profileview.setImage(image);
      }
      
      start();

      updBtn.setOnAction(event -> {
         updBtn.setDisable(true);
         savBtn.setVisible(true);
         canBtn.setVisible(true);
         zipBtn.setDisable(false);

         mem_pass.setEditable(true);
         mem_zip.setEditable(true);
         mem_addr.setEditable(true);

         savBtn.setOnAction(event2 -> {
            JobMemberVO jmemVO = new JobMemberVO();

            jmemVO.setMem_id(mem_id.getText());
            jmemVO.setJmem_id(mem_id.getText());
            jmemVO.setMem_pass(mem_pass.getText());
            jmemVO.setJmem_zip(mem_zip.getText());
            jmemVO.setJmem_addr(mem_addr.getText());

            if (mem_state.isSelected() == true) {
               jmemVO.setJmem_state("T");
            }else {
            	jmemVO.setJmem_state("F");
            }
            int cnt = 0;
            boolean result = ShowAlert.showAlert1CONFIRMATION("수정", "정말 수정하시겠습니까??");
            if(result) {
            	cnt = conn.getIJobMemSerivce().updateMyInfo(jmemVO);
            }else {
            	ShowAlert.showAlertError("취소", "취소하였습니다.");
            }
            if(cnt > 0) {
               ssessionMem.setMem_pass(mem_pass.getText());
               ssessionMem.setJmem_zip(mem_zip.getText());
               ssessionMem.setJmem_addr(mem_addr.getText());
               
               savBtn.setVisible(false);
               canBtn.setVisible(false);
               updBtn.setDisable(false);
               zipBtn.setDisable(true);
            }
         });

         canBtn.setOnAction(event2 -> {
            savBtn.setVisible(false);
            canBtn.setVisible(false);
            updBtn.setDisable(false);
            zipBtn.setDisable(true);
            start();
         });

      });
      
      addprofile.setOnAction(event->{
    	  Parent root = null;  
    	  
    	  Stage primaryStage = (Stage) ((Node) canBtn).getScene().getWindow();
		    try {
				root = FXMLLoader.load(getClass().getResource("imagetool/ImageTool.fxml"));
				Stage addIntroStage = new Stage();
				Scene scene = new Scene(root);
				addIntroStage.setTitle("프로필사진등록");
				addIntroStage.setScene(scene);
				addIntroStage.initOwner(primaryStage);
				addIntroStage.initModality(Modality.APPLICATION_MODAL);
				addIntroStage.showAndWait();
				
				ssessionMem = LoginController.ssessionJMem;
				profileview.setImage(ImageToolController.img);
				
				
			} catch (IOException e) {
				e.printStackTrace();
			}
      });

   }
   
   @FXML
   private void zipButton(ActionEvent event) {
      
      if(drawer.isShown()) {
         drawer.close();
      }else {
         drawer.open();
      }
   

      AnchorPane sidePane = null;
      try {
         sidePane = FXMLLoader.load(getClass().getResource("../../signup/ZipDrawer.fxml"));
         drawer.setSidePane(sidePane);
         JFXButton btnCheck = (JFXButton)drawer.lookup("#btnCheck");
         JFXButton btnCancel = (JFXButton)drawer.lookup("#btnCancel");
         
         Platform.runLater(()->{
            btnCheck.setOnAction(event3->{
               JFXTreeTableView<Zip> zipTable = (JFXTreeTableView<Zip>)drawer.lookup("#zipTable");
               TreeItem<Zip> root = zipTable.getSelectionModel().getSelectedItem();
               if(root == null) {
                  return;
               }
               
               String dong = root.getValue().getDong().get();
               String gugun = root.getValue().getGugun().get();
               String zipcode = root.getValue().getZipCode().get();
               String sido = root.getValue().getSido().get();
               mem_zip.setText(zipcode);
               if(dong == null) {
                  dong = "";
               }else if(gugun ==null ) {
                  gugun = "";
               }else if(sido == null) {
                  sido = "";
               }else if(zipcode ==null) {
                  zipcode = "";               
               }
            
               String addr = sido + " " + gugun + " " + dong; 
               mem_addr.setText(addr);
               drawer.close();
            });
            
            btnCancel.setOnAction(event4->{
               drawer.close();
            });
         });
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

private void start() {
   
     ssessionMem = LoginController.ssessionJMem;
     
     mem_id.setText(ssessionMem.getMem_id());
     mem_pass.setText(ssessionMem.getMem_pass());
     mem_name.setText(ssessionMem.getMem_name());
     mem_regno.setText(ssessionMem.getJmem_regno());
     mem_zip.setText(ssessionMem.getJmem_zip());
     mem_addr.setText(ssessionMem.getJmem_addr());
      String state = ssessionMem.getJmem_state();
      if(state.equals("T")) {
         gong.setText("공개");
         begong.setText("");
         mem_state.setSelected(true);
      } else {
         begong.setText("비공개");
          gong.setText("");
          mem_state.setSelected(false);
      }
      
//   mem_id.setText("chaeyeon");
//   mem_pass.setText("codus");
//   mem_name.setText("장채연");
//   mem_regno.setText("9501292477777");
//   mem_zip.setText("34200");
//   mem_addr.setText("관저동");
//   mem_state.setSelected(true);
   
   mem_id.setEditable(false);
   mem_pass.setEditable(false);
   mem_name.setEditable(false);
   mem_regno.setEditable(false);
   mem_zip.setEditable(false);
   mem_addr.setEditable(false);
         

   savBtn.setVisible(false);
   canBtn.setVisible(false);
   zipBtn.setDisable(true);
   
   updBtn.setDisable(false);
}

   @FXML
   public void togglechange(ActionEvent event) {
      if(mem_state.isSelected()==true) {
         gong.setText("공개");
         begong.setText("");
         
      }else if(mem_state.isSelected()==false){
         begong.setText("비공개");
         gong.setText("");
         
      }
      
      
   }

}