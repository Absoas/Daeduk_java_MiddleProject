package kr.or.ddit.controller.cor.creqboard;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import kr.or.ddit.rmi.client.ClientConnectFactory;
import kr.or.ddit.rmi.interf.IRemote;

public class CreqDetailController implements Initializable {
	
	@FXML
	private JFXTextField careerField;

	@FXML
	private JFXButton updateBtn;

	@FXML
	private JFXTextArea contentArea;

	@FXML
	private DatePicker endDate;

	@FXML
	private JFXButton cancelBtn;

	@FXML
	private JFXTextField salaryField;

	@FXML
	private DatePicker startDate;

	@FXML
	private JFXButton deleteBtn;
	
	IRemote conn;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		deleteBtn.setOnAction(event -> deleteBoard());
		updateBtn.setOnAction(event -> updateBoard());
		cancelBtn.setOnAction(event -> closeBoard());
		conn = ClientConnectFactory.getClientConnect();
	}

	private void closeBoard() {
		
	}

	private void updateBoard() {
		
	}
	
	private void deleteBoard() {
		
	}
	
	
}
