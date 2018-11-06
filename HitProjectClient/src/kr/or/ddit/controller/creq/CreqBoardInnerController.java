package kr.or.ddit.controller.creq;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import kr.or.ddit.controller.jmem.myintro.JMemIntroModalController;

public class CreqBoardInnerController implements Initializable{
	
	@FXML
	private AnchorPane parent;
	

	@FXML
    private JFXTextField cor_tel, creq_career, creq_enddate, creq_startdate, cor_name, creq_click
    					 , creq_salary;
	
	@FXML
	private JFXTextArea creq_content;
	
	@FXML
	private ImageView cor_img;
	
    @FXML
    private JFXButton creqBtn;
    
    public static String selCorname;
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		creqBtn.setOnAction(Event->{
			selCorname = cor_name.getText();
			
			Parent root;
			try {
				Stage primaryStage = (Stage) ((Node) cor_img).getScene().getWindow();
				root = FXMLLoader.load(getClass().getResource("../jmem/applyJob/tabtab.fxml"));
				Stage addIntroStage = new Stage();
				Scene scene = new Scene(root);
				addIntroStage.setTitle("지원서");
				addIntroStage.setScene(scene);
				addIntroStage.initOwner(primaryStage);
				addIntroStage.initModality(Modality.APPLICATION_MODAL);
				addIntroStage.showAndWait();
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}

}
