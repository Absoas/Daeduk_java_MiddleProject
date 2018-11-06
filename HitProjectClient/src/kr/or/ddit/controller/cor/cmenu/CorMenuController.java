package kr.or.ddit.controller.cor.cmenu;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import kr.or.ddit.controller.login.LoginController;
import kr.or.ddit.cormem.CorMemberVO;

public class CorMenuController implements Initializable {


    @FXML
    private JFXButton applyJMemBtn;

    @FXML
    private JFXButton TotalCalendar;

    @FXML
    private AnchorPane changePane;

    @FXML
    private JFXButton applyCorBtn;

    @FXML
    private JFXDrawer drawer1;

    @FXML
    private JFXButton myinfoBtn;

    @FXML
    private JFXDrawer drawer , drawer3;

    @FXML
    private JFXButton myBoardBtn;

    @FXML
    private Label userName;

	// 기업vo만들어면 쎄션멤버 바꾸기
	private CorMemberVO ssessionCMem;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ssessionCMem = LoginController.ssessionCMem;
		setDrawer();
		setDrawer2();
		setPath();
		
		userName.setText(ssessionCMem.getCor_name());
		
	}
	
	
	private void setPath() {
		applyCorBtn.setOnAction(event->{
			drawer.close();
			drawer1.close();
			AnchorPane btn = null;
			try {
				changePane.getChildren().clear();
				btn = FXMLLoader.load(this.getClass().getResource("../../creq/CReqBoardAnchoFx.fxml"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			changePane.getChildren().setAll(btn);
		});
		
		applyJMemBtn.setOnAction(event->{
			drawer.close();
			drawer1.close();
			AnchorPane btn = null;
			try {
				changePane.getChildren().clear();
				btn = FXMLLoader.load(this.getClass().getResource("../../jmemlist/JobMemListBoard.fxml"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			changePane.getChildren().setAll(btn);
		});
		
		TotalCalendar.setOnAction(event->{
			drawer.close();
			drawer1.close();
			AnchorPane btn = null;
			try {
				changePane.getChildren().clear();
				btn = FXMLLoader.load(this.getClass().getResource("../../totalCalendar/TotalCalendar.fxml"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			changePane.getChildren().setAll(btn);
		});
	}

	public void setDrawer() {
		myinfoBtn.addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent e) -> {
			if (drawer.isShown()) {
				drawer1.close();
				drawer3.close();
			} else {
				drawer1.toBack();
				drawer.toFront();
				drawer1.close();
				drawer.open();
			}
		});
		try {
			AnchorPane sidePane = FXMLLoader.load(getClass().getResource("MyPageSubMenu.fxml"));
			drawer.setSidePane(sidePane);
			for (Node node : sidePane.getChildren()) {
				if (node.getAccessibleText() != null) {
					node.addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent ev) -> {
						switch (node.getAccessibleText()) {
						case "btn1":
							if(drawer3.isShown()) {
								drawer3.close();
								drawer3.toBack();
							}
							if(!drawer.isShown()) {
								drawer.toFront();
								drawer.open();
							}
							drawer1.close();
							
							AnchorPane btn = null;
							try {
								changePane.getChildren().clear();
								btn = FXMLLoader.load(this.getClass().getResource("../myinfo/CorMemUpdateMyInfoFx.fxml"));
							} catch (IOException e) {
								e.printStackTrace();
							}
							changePane.getChildren().setAll(btn);
							break;
							
						case "btn2":
							if(drawer3.isShown()) {
								drawer3.close();
								drawer3.toBack();
							}
							if(!drawer.isShown()) {
								drawer.toFront();
								drawer.open();
							}
							drawer1.close();
							
							AnchorPane btn2 = null;
							try {
								changePane.getChildren().clear();
								btn2 = FXMLLoader.load(this.getClass().getResource("../creqboard/CorMemCreqBoard.fxml"));
							} catch (IOException e) {
								e.printStackTrace();
							}
							changePane.getChildren().setAll(btn2);
							break;

						case "btn3":	
							if(drawer3.isShown()) {
								drawer3.close();
								drawer3.toBack();
							}
							if(!drawer.isShown()) {
								drawer.toFront();
								drawer.open();
							}
							drawer1.close();
							AnchorPane btn3 = null;
							try {
								changePane.getChildren().clear();
								btn3 = FXMLLoader.load(this.getClass().getResource("../corApply/CorApplyFx.fxml"));
							} catch (IOException e) {
								e.printStackTrace();
							}
							changePane.getChildren().setAll(btn3);
							break;
							
						case "btn4":
							
							break;
						}
					});
				}
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}
	
	public void setDrawer2() {
		myBoardBtn.addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent e) -> {
			drawer1.getChildren().clear();
			if (drawer1.isShown()) {
				drawer.close();
			} else {
				drawer.close();
				drawer1.toFront();
				drawer1.open();
			}
		});
		
		try {
			AnchorPane sidePane = FXMLLoader.load(getClass().getResource("BoardSubMenu.fxml"));
			drawer1.setSidePane(sidePane);
			for (Node node : sidePane.getChildren()) {
				if (node.getAccessibleText() != null) {
					node.addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent ev) -> {
						switch (node.getAccessibleText()) {
						case "btn1":
							if(drawer3.isShown()) {
								drawer3.close();
								drawer3.toBack();
							}
							if(!drawer1.isShown()) {
								drawer1.toFront();
								drawer1.open();
							}
							drawer.close();
							AnchorPane one = null;
							try {
								one = FXMLLoader.load(this.getClass().getResource("../../noticeboard/noticeboard.fxml"));
							} catch (IOException e) {
								e.printStackTrace();
							}
							changePane.getChildren().setAll(one);
							break;
							
						case "btn2":
							if(drawer3.isShown()) {
								drawer3.close();
								drawer3.toBack();
							}
							if(!drawer1.isShown()) {
								drawer1.toFront();
								drawer1.open();
							}
							drawer.close();
							AnchorPane two = null;
							try {
								two = FXMLLoader.load(this.getClass().getResource("../../passintroboard/passboard.fxml"));
							} catch (IOException e) {
								e.printStackTrace();
							}
							changePane.getChildren().setAll(two);
							break;
							
						case "btn3":
							if(drawer3.isShown()) {
								drawer3.close();
								drawer3.toBack();
							}
							if(!drawer1.isShown()) {
								drawer1.toFront();
								drawer1.open();
							}
							drawer.close();
							AnchorPane three = null;
							try {
								three = FXMLLoader.load(this.getClass().getResource("../../reviewboard/reviewBoard.fxml"));
							} catch (IOException e) {
								e.printStackTrace();
							}
							changePane.getChildren().setAll(three);
							break;
							
						case "btn4":
							if(drawer3.isShown()) {
								drawer3.close();
								drawer3.toBack();
							}
							if(!drawer1.isShown()) {
								drawer1.toFront();
								drawer1.open();
							}
							drawer.close();
							AnchorPane four = null;
							try {
								four = FXMLLoader.load(this.getClass().getResource("../../qnaboard/qnaBoard.fxml"));
							} catch (IOException e) {
								e.printStackTrace();
							}
							changePane.getChildren().setAll(four);
							break;
							
						case "btn5":
							if(drawer3.isShown()) {
								drawer3.close();
								drawer3.toBack();
							}
							if(!drawer1.isShown()) {
								drawer1.toFront();
								drawer1.open();
							}
							drawer.close();
							AnchorPane five = null;
							try {
								five = FXMLLoader.load(this.getClass().getResource("../../faqboard/freaskqnafx.fxml"));
							} catch (IOException e) {
								e.printStackTrace();
							}
							changePane.getChildren().setAll(five);
							break;
							
						//기업랭킹
						case "btn6":
							if (drawer3.isShown()) {
								drawer3.close();
								drawer3.toBack();
								changePane.toFront();
							}else {
								drawer3.toFront();
								drawer3.open();
							}
							if(!drawer1.isShown()) {
								drawer1.toFront();
								drawer1.open();
							}

							drawer.close();
							
							AnchorPane sidePane1 = null;
							try {
								sidePane1 = FXMLLoader.load(getClass().getResource("../creqboard/CreqRankList2.fxml"));
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							drawer3.setSidePane(sidePane1);
						}
					});
				}
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	@FXML
	public void logOut(ActionEvent event) throws IOException {
		LoginController.ssessionCMem = null;
		Parent root = FXMLLoader.load(getClass().getResource("../../login/Login.fxml"));
		Scene scene = new Scene(root);
		Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		appStage.setTitle("Main Menu");
		appStage.setScene(scene);
		appStage.show();
	}

	@FXML
	public void goHome(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("CMemMain.fxml"));
		Scene scene = new Scene(root);
		Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		appStage.setTitle("Main Menu");
		appStage.setScene(scene);
		appStage.show();

	}

}
