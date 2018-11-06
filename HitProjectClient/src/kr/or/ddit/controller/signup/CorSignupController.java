package kr.or.ddit.controller.signup;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import kr.or.ddit.common.ShowAlert;
import kr.or.ddit.controller.login.LoginController;
import kr.or.ddit.controller.signup.CorRegnoController.CorZip;
import kr.or.ddit.controller.signup.ZipDrawerController.Zip;
import kr.or.ddit.cormem.CorMemberVO;
import kr.or.ddit.jobmem.JobMemberVO;
import kr.or.ddit.pattern.PatternRegno;
import kr.or.ddit.rmi.client.ClientConnect;
import kr.or.ddit.rmi.interf.IRemote;

public class CorSignupController implements Initializable {
	@FXML
	private JFXButton signUpBtn;
	@FXML
	private JFXButton cancelBtn;
	@FXML
	private JFXTextField inputColAddr;
	@FXML
	private JFXTextField inputColId;
	@FXML
	private JFXTextField inputPass2;
	@FXML
	private JFXTextField inputColNum;
	@FXML
	private JFXTextField inputPass1;
	@FXML
	private JFXButton checkBtn, zipSearch;
	@FXML
	private JFXDrawer drawer;
	@FXML
	private Label failsignup;
	@FXML
	private JFXTextField inputName, inputEmail;
	@FXML
	private Label checkId;
	@FXML
	private Label checkEmail;
	@FXML
	private Label checkPw, pwCheck;
	boolean patternc = true;

	public static IRemote conn = null;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ClientConnect clientConn = new ClientConnect();
		conn = clientConn.getConnect();
		inputValueCheck();
		signUpBtn.setOnAction(event -> {
			insert();
		});
		checkBtn.setOnAction(event -> {
			check();
		});
		cancelBtn.setOnAction(event -> {
			cancel();
		});
		zipSearch.setOnAction(event -> {
			zipSearch();
		});

		// 비밀번호 체크
	}

	private void inputValueCheck() {
		PatternRegno pattern = new PatternRegno();

		inputColId.focusedProperty().addListener((arg0, oldValue, newValue) -> {
			if (!newValue) { // when focus lost
				if (pattern.regId(inputColId.getText())) {
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
		inputPass2.focusedProperty().addListener((arg0, oldValue, newValue) -> {
			if (inputPass2.getText().length() > 5) {
				if (!inputPass1.getText().equals(inputPass2.getText())) {
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

		inputPass1.focusedProperty().addListener((arg0, oldValue, newValue) -> {

			if (!newValue) { // when focus lost
				if (pattern.regPass(inputPass1.getText())) {
					checkPw.setText("pw가 확인되었습니다.");
					checkPw.setStyle("-fx-text-fill: green;");
					patternc = true;
				} else {
					checkPw.setText("pw를 주어진 형식으로 다시 입력해주세요.");
					checkPw.setStyle("-fx-text-fill: red;");
					patternc = false;
				}
			}
			if (inputPass1.getText().length() > 5) {
				if (!inputPass1.getText().equals(inputPass2.getText())) {
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
	}

	private void zipSearch() {
		if (drawer.isShown()) {
			drawer.close();
		} else {
			drawer.open();
		}
		AnchorPane sidePane = null;
		try {
			sidePane = FXMLLoader.load(getClass().getResource("CorRegnoDrawer.fxml"));
			drawer.setSidePane(sidePane);

			JFXButton btnCheck = (JFXButton) drawer.lookup("#btnCheck");
			JFXButton btnCancel = (JFXButton) drawer.lookup("#btnCancel");

			Platform.runLater(() -> {
				btnCheck.setOnAction(event -> {
					JFXTreeTableView<CorZip> zipTable = (JFXTreeTableView<CorZip>) drawer.lookup("#corZipTable");
					TreeItem<CorZip> root = zipTable.getSelectionModel().getSelectedItem();
					if (root == null) {
						return;
					}

					String no = root.getValue().getCor_no().get();
					String name = root.getValue().getCor_name().get();
					String ceo = root.getValue().getCor_ceo().get();
					String post = root.getValue().getCor_post().get();
					String addr = root.getValue().getCor_addr().get();
					String tel = root.getValue().getCor_tel().get();

					inputColNum.setText(no);

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

	private void cancel() {
		Stage window = (Stage) signUpBtn.getScene().getWindow();
		window.close();
	}

	private boolean check() {
		boolean result = inputColId.getText() == null || inputColId.getText().equals("");
		boolean result2 = LoginController.conn.getIJobMemSerivce().memberChkId(inputColId.getText());
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

	private void insert() {

		// CorperationVO corVO = new CorperationVO();

		// corVO.setCor_id(inputColId.getText());
		// corVO.setCor_pass(inputPass1.getText());
		// corVO.setCor_num(inputColNum.getText());
		// corVO.setAddr(inputColAddr.getText());
		String id = inputColId.getText();
		String name = inputName.getText();
		String pass1 = inputPass1.getText();
		String pass2 = inputPass2.getText();
		String regno = inputColNum.getText(); // 사업자등록번호
		String email = inputEmail.getText();

		/*
		 * private String mem_id; private String mem_pass; private String mem_name;
		 * private String mem_mail; private String mem_type; private String mem_image;
		 * private String cor_no; private String cor_name; private String cor_ceo;
		 * private String cor_tel; private String cor_post; private String cor_addr;
		 */

		if (pass1.equals(pass2)) {
			// cnt = conn.getICorperationService().insertCor(corVO);

		} else {
			System.out.println("nono");
		}

		CorMemberVO cmembervo = new CorMemberVO();

		cmembervo.setMem_id(id);
		cmembervo.setMem_pass(pass1);
		cmembervo.setMem_mail(email);
		cmembervo.setMem_name(name);
		cmembervo.setMem_type("2");
		cmembervo.setCor_name(name);
		cmembervo.setCor_state("F");
		cmembervo.setCor_id(id);
		cmembervo.setCor_regno(regno);

		// boolean result =
		// LoginController.conn.getICorMemberService().signupMain(cmembervo);
		// boolean result2=
		// LoginController.conn.getICorMemberService().signupSub(cmembervo);
		// boolean result3 =
		// LoginController.conn.getIJobMemSerivce().signupMain(jmembervo);

		// System.out.println(result3);
		// 비밀번호체크

		// 회원가입
		if (patternc == false) {
			ShowAlert.showAlertError("정규식", "양식에 맞게 입력해주세요.");
			return;
		}

		if (id.isEmpty() || pass1.isEmpty() || name.isEmpty() || email.isEmpty()) {
			ShowAlert.showAlertError("공백입니다", "양식에 맞게 입력해주세요.");
		} else {
			boolean result3 = ShowAlert.showAlert1CONFIRMATION("회원가입 하시겠습니까?", "회원가입을 하시려면 OK버튼을, 취소하시려면 취소버튼을 눌러주세요");
			if (result3 == true) {
				boolean result = LoginController.conn.getICorMemberService().signupMain(cmembervo);
				boolean result2 = LoginController.conn.getICorMemberService().signupSub(cmembervo);
				Stage window = (Stage) signUpBtn.getScene().getWindow();
				ShowAlert.showAlertINFORMATION("congratulation", "회원이 되신걸 환영합니다.");
				window.close();
			} else {
				ShowAlert.showAlertINFORMATION("", "회원가입이 취소되었습니다.");
				return;
			}
		}

	}

}