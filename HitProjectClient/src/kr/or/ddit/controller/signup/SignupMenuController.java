package kr.or.ddit.controller.signup;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SignupMenuController implements Initializable{
	
	
	@FXML
	public void backHome(ActionEvent event) throws IOException {
		Parent root= FXMLLoader.load(getClass().getResource("../login/Login.fxml"));
		Scene scene = new Scene(root);
		Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		appStage.setTitle("Main");
		appStage.setScene(scene);
		appStage.show();
		
	}
	
	@FXML
	public void selectMemSignup(ActionEvent event) throws IOException{
		Parent root= FXMLLoader.load(getClass().getResource("MemSignup.fxml"));
		Stage primaryStage = new Stage();
		
		Scene scene = new Scene(root);
		
		primaryStage.setTitle("Member Signup");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	@FXML
	public void selectCorSignup(ActionEvent event) throws IOException{
		Parent root= FXMLLoader.load(getClass().getResource("CorSignup.fxml"));
		Stage primaryStage = new Stage();
		
		Scene scene = new Scene(root);
		
		primaryStage.setTitle("Corperation Signup");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}

}
