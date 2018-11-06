package kr.or.ddit.controller.signup;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.validation.NumberValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import kr.or.ddit.common.ShowAlert;
import kr.or.ddit.common.ZipVO;
import kr.or.ddit.controller.login.LoginController;
import kr.or.ddit.controller.signup.ZipDrawerController.Zip;
import kr.or.ddit.jobmem.JobMemberVO;
import kr.or.ddit.pattern.PatternRegno;

public class MemSignupController implements Initializable {

	@FXML
	JFXButton btnIdCheck, btnSignUp, btnCancel, btnSearchZip;
	@FXML
	JFXTextField inputId, inputPass, inputCheckPass, inputName, inputAddr, inputZip, inputReg, inputEmail, inputTel;
	// @FXML Label failsignup , faillabel, successlabel;
	@FXML
	JFXDrawer drawer;
	@FXML
	private Label checkRegno;

	@FXML
	private Label checkId;

	@FXML
	private Label checkEmail;

	@FXML
	private Label checkPw, pwCheck;

	boolean patternc = true;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btnSignUp.setOnAction(event -> signUpMember());
		btnIdCheck.setOnAction(event -> idCheck());
		btnSearchZip.setOnAction(event -> zipCheck());
		btnCancel.setOnAction(event -> closeScene());
		inputValueCheck();
		// 비밀번호 체크
	}

	private void closeScene() {
		Stage window = (Stage) btnCancel.getScene().getWindow();
		window.close();
	}

	private void zipCheck() {
		if (drawer.isShown()) {
			drawer.close();
		} else {
			drawer.open();
		}
		AnchorPane sidePane = null;
		try {
			sidePane = FXMLLoader.load(getClass().getResource("ZipDrawer.fxml"));
			drawer.setSidePane(sidePane);
			JFXButton btnCheck = (JFXButton) drawer.lookup("#btnCheck");
			JFXButton btnCancel = (JFXButton) drawer.lookup("#btnCancel");

			Platform.runLater(() -> {
				btnCheck.setOnAction(event -> {
					JFXTreeTableView<Zip> zipTable = (JFXTreeTableView<Zip>) drawer.lookup("#zipTable");
					TreeItem<Zip> root = zipTable.getSelectionModel().getSelectedItem();
					if (root == null) {
						return;
					}

					String dong = root.getValue().getDong().get();
					String gugun = root.getValue().getGugun().get();
					String zipcode = root.getValue().getZipCode().get();
					String sido = root.getValue().getSido().get();
					inputZip.setText(zipcode);
					if (dong == null) {
						dong = "";
					} else if (gugun == null) {
						gugun = "";
					} else if (sido == null) {
						sido = "";
					} else if (zipcode == null) {
						zipcode = "";
					}

					String addr = sido + " " + gugun + " " + dong;
					inputAddr.setText(addr);
					drawer.close();
				});

				btnCancel.setOnAction(event -> {
					drawer.close();
				});
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private boolean idCheck() {
		boolean result = inputId.getText() == null || inputId.getText().equals("");
		boolean result2 = LoginController.conn.getIJobMemSerivce().memberChkId(inputId.getText());
		if (result == true) {
			ShowAlert.showAlertINFORMATION("", "ID를 입력해주세요");
			return result;
		}
		if (result2 == false) {
			ShowAlert.showAlertWarning("ID 중복Check", "중복된 ID 입니다.");
			return result2;
		} else {
			ShowAlert.showAlertINFORMATION("ID 중복Check", "사용 가능한 ID 입니다.");

		}
		return result2;
	}

	private void inputValueCheck() {
		PatternRegno pattern = new PatternRegno();

		inputId.focusedProperty().addListener((arg0, oldValue, newValue) -> {
			if (!newValue) { // when focus lost
				if (pattern.regId(inputId.getText())) {
					checkId.setText("id가 확인되었습니다.");
					checkId.setStyle("-fx-text-fill: green;");
					patternc = true;
				} else {
					checkId.setText("id를 주어진 형식으로 다시 입력해주세요.");
					checkId.setStyle("-fx-text-fill: red;");
					patternc = false;
				}
			}
		});

		inputCheckPass.focusedProperty().addListener((arg0, oldValue, newValue) -> {
			if (inputCheckPass.getText().length() > 5) {
				if (!inputPass.getText().equals(inputCheckPass.getText())) {
					pwCheck.setText("pw가 일치하지 않습니다.");
					pwCheck.setStyle("-fx-text-fill: red;");
					patternc = false;
				} else {
					pwCheck.setText("pw가 일치합니다.");
					pwCheck.setStyle("-fx-text-fill: green;");
					patternc = true;
				}
			}
		});

		inputPass.focusedProperty().addListener((arg0, oldValue, newValue) -> {
			if (!newValue) { // when focus lost
				if (pattern.regPass(inputPass.getText())) {
					checkPw.setText("pw가 확인되었습니다.");
					checkPw.setStyle("-fx-text-fill: green;");
					patternc = true;
				} else {
					checkPw.setText("pw를 주어진 형식으로 다시 입력해주세요.");
					checkPw.setStyle("-fx-text-fill: red;");
					patternc = false;
				}
			}

			if (inputCheckPass.getText().length() > 5) {
				if (!inputPass.getText().equals(inputCheckPass.getText())) {
					pwCheck.setText("pw가 일치하지 않습니다.");
					pwCheck.setStyle("-fx-text-fill: red;");
					patternc = false;
				} else {
					pwCheck.setText("pw가 일치합니다.");
					pwCheck.setStyle("-fx-text-fill: green;");
					patternc = true;
				}
			}

		});

		inputEmail.focusedProperty().addListener((arg0, oldValue, newValue) -> {
			if (!newValue) { // when focus lost
				if (pattern.regEmail(inputEmail.getText())) {
					checkEmail.setText("email이 확인되었습니다.");
					checkEmail.setStyle("-fx-text-fill: green;");
					patternc = true;
				} else {
					checkEmail.setText("email을 주어진 형식으로 다시 입력해주세요.");
					checkEmail.setStyle("-fx-text-fill: red;");
					patternc = false;
				}
			}
		});

		inputReg.focusedProperty().addListener((arg0, oldValue, newValue) -> {
			if (!newValue) { // when focus lost
				if (pattern.regNo(inputReg.getText())) {
					checkRegno.setText("주민등록번호가 확인되었습니다.");
					checkRegno.setStyle("-fx-text-fill: green;");
					patternc = true;
				} else {
					checkRegno.setText("주민등록번호 주어진 형식으로 다시 입력해주세요.");
					checkRegno.setStyle("-fx-text-fill: red;");
					patternc = false;
				}
			}
		});

	}

	private void signUpMember() {

		// PatternRegno pattern = new PatternRegno();

		String id = inputId.getText();
		String pass = inputPass.getText();
		String name = inputName.getText();
		String addr = inputAddr.getText();
		String regno = inputReg.getText();
		String email = inputEmail.getText();
		String zip = inputZip.getText();
		String tel = inputTel.getText();

		// if (idCheck()) {
		// ShowAlert.showAlertError("ID중복체크", "ID 중복체크 해주세요");
		// return;
		// }

		JobMemberVO membervo = new JobMemberVO();
		membervo.setMem_id(id);
		membervo.setJmem_id(id);
		membervo.setMem_name(name);
		membervo.setMem_pass(pass);
		membervo.setJmem_zip(zip);
		membervo.setJmem_addr(addr);
		membervo.setJmem_regno(regno);
		membervo.setMem_mail(email);
		membervo.setMem_type("1");
		membervo.setJmem_state("F");
		membervo.setJmem_tel(tel);

		if (patternc == false) {
			ShowAlert.showAlertError("정규식", "양식에 맞게 입력해주세요.");
			return;
		}

		if (id.isEmpty() || pass.isEmpty() || name.isEmpty() || addr.isEmpty() || regno.isEmpty() || email.isEmpty()
				|| zip.isEmpty() || tel.isEmpty()) {
			ShowAlert.showAlertError("공백입니다", "양식에 맞게 입력해주세요.");
		} else {
			boolean result3 = ShowAlert.showAlert1CONFIRMATION("회원가입 하시겠습니까?", "회원가입을 하시려면 OK버튼을, 취소하시려면 취소버튼을 눌러주세요");
			if (result3 == true) {
				boolean result = LoginController.conn.getIJobMemSerivce().signupMain(membervo);
				boolean result2 = LoginController.conn.getIJobMemSerivce().signupSub(membervo);
				Stage window = (Stage) btnSignUp.getScene().getWindow();
				ShowAlert.showAlertINFORMATION("congratulation", "회원이 되신걸 환영합니다.");
				window.close();
			} else {
				ShowAlert.showAlertINFORMATION("", "회원가입이 취소되었습니다.");
				return;
			}
		}

	}
}