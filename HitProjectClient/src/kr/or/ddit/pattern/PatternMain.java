package kr.or.ddit.pattern;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PatternMain extends Application {
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("encode.fxml"));
		
		Parent root = loader.load();
		Scene scene = new Scene(root);
		
		primaryStage.setTitle("Fxml 객체연습하기");
		primaryStage.setScene(scene);//Scene추가
		primaryStage.show();
	}
	public static void main(String[] args) {
		launch(args);
	}
}
