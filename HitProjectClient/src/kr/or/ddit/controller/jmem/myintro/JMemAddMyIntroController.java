package kr.or.ddit.controller.jmem.myintro;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.event.ChangeEvent;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import kr.or.ddit.controller.login.LoginController;
import kr.or.ddit.jobmem.JobMemberVO;
import kr.or.ddit.myintro.MyIntroVO;
import kr.or.ddit.rmi.client.ClientConnectFactory;
import kr.or.ddit.rmi.interf.IRemote;

public class JMemAddMyIntroController implements Initializable {
	@FXML
	private JFXTextArea introArea;

	@FXML
	private JFXTextField hopeField;

	@FXML
	private JFXTextField careerField;

	@FXML
	private JFXComboBox<String> hopeCombo;

	@FXML
	private JFXComboBox<String> careerCombo;

	@FXML
	private Label successlabel;


	private IRemote conn;
	private JobMemberVO sessionMem;
	public static String intro;
	private MyIntroVO myIntro;
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		conn = ClientConnectFactory.getClientConnect();
		sessionMem = LoginController.ssessionJMem;
		settingBasic();
		setField();
		hopeCombo.setOnAction(e -> {
			if (hopeCombo.getSelectionModel().getSelectedItem().equals("기타")) {
				hopeField.setDisable(false);
				hopeField.setText("");
			} else {
				hopeField.setDisable(true);
				hopeField.setText("");
			}
		});

		careerCombo.setOnAction(e -> {
			if (careerCombo.getSelectionModel().getSelectedItem().equals("신입")) {
				careerField.setDisable(true);
				careerField.setText("");
			} else {
				careerField.setDisable(false);
			}
		});
		
	}

	@FXML
	public void saveIntro(ActionEvent event) {
		int result = 0;
		MyIntroVO newIntro = new MyIntroVO();
		newIntro.setMy_id(sessionMem.getMem_id());
		if (hopeCombo.getSelectionModel().getSelectedItem().equals("기타")) {
			newIntro.setMy_hope(hopeField.getText());
		}else {
			newIntro.setMy_hope(hopeCombo.getSelectionModel().getSelectedItem());
		}
		
		
		if (careerCombo.getSelectionModel().getSelectedItem().equals("신입")) {
			newIntro.setMy_career(careerCombo.getSelectionModel().getSelectedItem());
		} else {
			newIntro.setMy_career(careerField.getText());
		}
		newIntro.setMy_intro(introArea.getText());
		if (myIntro == null) {
			result = conn.getIMyIntroService().insertMyIntro(newIntro);
			if (result > 0) {
				successlabel.setText("저장 성공");
			}
		} else {
			result = conn.getIMyIntroService().updateMyIntro(newIntro);
			if (result > 0) {
				successlabel.setText("저장 성공");
			}
		}
	}

	@FXML
	public void newIntro(ActionEvent event) {
		intro = "";
		Parent root;
		try {
			Stage primaryStage = (Stage) ((Node) introArea).getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("IntroModal.fxml"));
			Stage addIntroStage = new Stage();
			Scene scene = new Scene(root);
			addIntroStage.setTitle("자기소개서");
			addIntroStage.setScene(scene);
			addIntroStage.initOwner(primaryStage);
			addIntroStage.initModality(Modality.APPLICATION_MODAL);
			addIntroStage.showAndWait();
			introArea.setText(JMemIntroModalController.intro);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void editIntro(ActionEvent event) {
		intro = introArea.getText();
		Parent root;
		try {
			Stage primaryStage = (Stage) ((Node) introArea).getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("IntroModal.fxml"));
			Stage addIntroStage = new Stage();
			Scene scene = new Scene(root);
			addIntroStage.setTitle("자기소개서");
			addIntroStage.setScene(scene);
			addIntroStage.initOwner(primaryStage);
			addIntroStage.initModality(Modality.APPLICATION_MODAL);
			addIntroStage.showAndWait();
			introArea.setText(JMemIntroModalController.intro);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 경력콤보박스와 희망직종콤보박스 및 텍스트Area 세팅 메서드
	 */
	public void settingBasic() {
		ObservableList<String> careerList = FXCollections.observableArrayList();
		careerList.addAll("신입", "경력");
		careerCombo.setItems(careerList);

		ObservableList<String> hopeList = FXCollections.observableArrayList();
		hopeList.addAll("WEB개발", "APP개발", "정보보안", "네트워크/서버", "WEB디자인", "유지보수", "기타");
		hopeCombo.setItems(hopeList);
		introArea.setEditable(false);

		hopeField.setDisable(true);
		careerField.setDisable(true);
	}

	/**
	 * 이미 입력해서 저장한 사항이 있으면 DB에서 그값을 호출해서 세팅
	 */
	public void setField() {
		myIntro = conn.getIMyIntroService().selectMyIntro(sessionMem.getMem_id());
		if (myIntro == null) {
			return;
		}
		hopeField.setText(myIntro.getMy_hope());
		careerField.setText(myIntro.getMy_career());
		introArea.setText(myIntro.getMy_intro());

	}

}
