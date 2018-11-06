package kr.or.ddit.rmi.client;


import com.jfoenix.controls.JFXDecorator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Border;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ClientStart extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}
		
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent parent = FXMLLoader.load(getClass().getResource("../../controller/login/Login.fxml"));
//		Parent parent = FXMLLoader.load(getClass().getResource("../../controller/cor/creqboard/CreqRankList2.fxml"));
		Scene scene = new Scene(parent);
		primaryStage.setTitle("취업의 기준 사람IT");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
		
	}
}
