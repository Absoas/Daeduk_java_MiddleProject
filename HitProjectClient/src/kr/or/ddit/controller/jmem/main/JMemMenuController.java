package kr.or.ddit.controller.jmem.main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;

import ch.qos.logback.core.joran.action.Action;
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
import kr.or.ddit.jobmem.JobMemberVO;

public class JMemMenuController implements Initializable {
	@FXML
	private JFXButton menubtn, boardbtn;
	@FXML
	private AnchorPane drawerPane;

	@FXML
	private JFXDrawer drawer, drawer2,drawer3;
	@FXML
	private AnchorPane changePane;
	@FXML
	private Label namelabel;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		namelabel.setText(LoginController.ssessionJMem.getMem_name());
		setDrawer();
		setDrawer2();
	}

	public void setDrawer() {
		menubtn.addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent e) -> {
			if (drawer.isShown()) {
				drawer2.close();
				drawer3.close();
				
			} else {
				drawer2.toBack();
				drawer.toFront();
				drawer.open();
			}
		});

		try {
			AnchorPane sidePane = FXMLLoader.load(getClass().getResource("MyPageSubMenu.fxml"));
			sidePane.toFront();

			drawer.setSidePane(sidePane);
			for (Node node : sidePane.getChildren()) {
				if (node.getAccessibleText() != null) {
					node.addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent ev) -> {
						switch (node.getAccessibleText()) {
						case "btn7":
							if(drawer3.isShown()) {
								drawer3.close();
								drawer3.toBack();
							}
							if(!drawer.isShown()) {
								drawer.toFront();
								drawer.open();
							}
							drawer2.close();
							
							changePane.getChildren().clear();
							AnchorPane pnlSeven = null;
							try {
								pnlSeven = FXMLLoader
										.load(this.getClass().getResource("../mylicense/mylicense.fxml"));
							} catch (IOException e) {
								e.printStackTrace();
							}
							changePane.getChildren().setAll(pnlSeven);
							break;
						case "btn8":
							if(drawer3.isShown()) {
								drawer3.close();
								drawer3.toBack();
							}
							if(!drawer.isShown()) {
								drawer.toFront();
								drawer.open();
							}
							drawer2.close();
							changePane.getChildren().clear();
							AnchorPane pnlEight = null;
							try {
								pnlEight = FXMLLoader
										.load(this.getClass().getResource("../mycalendar/JMemMyCalendar.fxml"));
								changePane.getChildren().setAll(pnlEight);
							} catch (IOException e) {
								e.printStackTrace();
							}
							break;
						case "btn9":
							if(drawer3.isShown()) {
								drawer3.close();
								drawer3.toBack();
							}
							if(!drawer.isShown()) {
								drawer.toFront();
								drawer.open();
							}
							drawer2.close();
							changePane.getChildren().clear();
							AnchorPane pnlNine = null;
							try {
								pnlNine = FXMLLoader.load(this.getClass().getResource("../myinfo/JMemberInfo.fxml"));
								changePane.getChildren().setAll(pnlNine);
							} catch (IOException e) {
								e.printStackTrace();
							}
							break;
						case "btn10":
							if(drawer3.isShown()) {
								drawer3.close();
								drawer3.toBack();
							}
							if(!drawer.isShown()) {
								drawer.toFront();
								drawer.open();
							}
							drawer2.close();
							changePane.getChildren().clear();
							AnchorPane pnlTen = null;
							try {
								pnlTen = FXMLLoader
										.load(this.getClass().getResource("../myintro/AddMyIntro.fxml"));
								changePane.getChildren().setAll(pnlTen);
							} catch (IOException e) {
								e.printStackTrace();
							}
							break;
						case "btn11":
							if(drawer3.isShown()) {
								drawer3.close();
								drawer3.toBack();
							}
							if(!drawer.isShown()) {
								drawer.toFront();
								drawer.open();
							}
							drawer2.close();
							changePane.getChildren().clear();
							AnchorPane pnlElev = null;
							try {
								pnlElev = FXMLLoader
										.load(this.getClass().getResource("../myApply/myApply.fxml"));
								changePane.getChildren().setAll(pnlElev);
							} catch (IOException e) {
								e.printStackTrace();
							}
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
		drawer2.toFront();
		boardbtn.addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent e) -> {
			if (drawer2.isShown()) {
				drawer.close();
				drawer3.close();
			} else {
				drawer2.open();
				drawer2.toFront();
				drawer.toBack();
			}
		});

		try {
			AnchorPane sidePane = FXMLLoader.load(getClass().getResource("BoardSubMenu.fxml"));
			sidePane.toFront();
			drawer2.setSidePane(sidePane);
			for (Node node : sidePane.getChildren()) {
				if (node.getAccessibleText() != null) {
					node.addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent ev) -> {
						switch (node.getAccessibleText()) {
						case "btn1":
							if(drawer3.isShown()) {
								drawer3.close();
								drawer3.toBack();
							}
							if(!drawer2.isShown()) {
								drawer2.toFront();
								drawer2.open();
							}
							drawer.close();
							
							changePane.getChildren().clear();
							FXMLLoader pnlOne = null;
							try {
								pnlOne = new FXMLLoader(
										this.getClass().getResource("../../noticeboard/NoticeBoard.fxml"));
								changePane.getChildren().setAll((AnchorPane) pnlOne.load());
							} catch (IOException e) {
								e.printStackTrace();
							}
							break;
						case "btn2":
							if(drawer3.isShown()) {
								drawer3.close();
								drawer3.toBack();
							}
							if(!drawer2.isShown()) {
								drawer2.toFront();								
								drawer2.open();
							}
							drawer.close();
							
							changePane.getChildren().clear();
							AnchorPane pnlTwo = null;
							try {
								pnlTwo = FXMLLoader
										.load(this.getClass().getResource("../../passintroboard/passboard.fxml"));
								changePane.getChildren().setAll(pnlTwo);
							} catch (IOException e) {
								e.printStackTrace();
							}
							break;
						case "btn3":
							if(drawer3.isShown()) {
								drawer3.close();
								drawer3.toBack();
							}
							if(!drawer2.isShown()) {
								drawer2.toFront();
								drawer2.open();
							}
							drawer.close();
							
							changePane.getChildren().clear();
							FXMLLoader pnlThr = null;
							try {
								pnlThr = new FXMLLoader(
										this.getClass().getResource("../../reviewboard/reviewBoard.fxml"));
								changePane.getChildren().setAll((AnchorPane) pnlThr.load());
							} catch (IOException e) {
								e.printStackTrace();
							}
							break;
						case "btn4":
							if(drawer3.isShown()) {
								drawer3.close();
								drawer3.toBack();
							}
							if(!drawer2.isShown()) {
								drawer2.toFront();
								drawer2.open();
							}
							drawer.close();
							
							changePane.getChildren().clear();
							AnchorPane pnlFour = null;
							try {
								pnlFour = FXMLLoader.load(this.getClass().getResource("../../qnaboard/qnaBoard.fxml"));
								changePane.getChildren().setAll(pnlFour);
							} catch (IOException e) {
								e.printStackTrace();
							}
							break;
						case "btn5":
							if(drawer3.isShown()) {
								drawer3.close();
								drawer3.toBack();
							}
							drawer.close();
							if(!drawer2.isShown()) {
								drawer2.toFront();
								drawer2.open();
							}
							
							changePane.getChildren().clear();
							AnchorPane pnlFive = null;
							try {
								pnlFive = FXMLLoader
										.load(this.getClass().getResource("../../faqboard/FreAskQnAFx.fxml"));
								changePane.getChildren().setAll(pnlFive);
							} catch (IOException e) {
								e.printStackTrace();
							}
							break;
						case "btn6":
							if (drawer3.isShown()) {
								drawer3.close();
								changePane.toFront();
							}else {
								drawer3.open();
								drawer3.toFront();
							}
							if(!drawer2.isShown()) {
								drawer2.open();
							}
							
							drawer.close();
							
							AnchorPane sidePane1 = null;
							try {
								sidePane1 = FXMLLoader.load(getClass().getResource("../../cor/creqboard/CreqRankList2.fxml"));
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
	public void CorpList(ActionEvent event) {
		drawer2.close();
		drawer.close();
		drawer3.close();
		
		changePane.getChildren().clear();
		AnchorPane change = null;
		try {
			change = FXMLLoader
					.load(this.getClass().getResource("../../creq/CReqBoardAnchoFx.fxml"));
			changePane.getChildren().setAll(change);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void JMemList(ActionEvent event) {
		drawer2.close();
		drawer.close();
		drawer3.close();
		
		changePane.getChildren().clear();
		AnchorPane change = null;
		try {
			change = FXMLLoader
					.load(this.getClass().getResource("../../jmemlist/JobMemListBoard.fxml"));
			changePane.getChildren().setAll(change);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void publicCalendar(ActionEvent event) {
		drawer2.close();
		drawer.close();
		drawer3.close();
		
		
		changePane.getChildren().clear();
		AnchorPane change = null;
		try {
			change = FXMLLoader
					.load(this.getClass().getResource("../mycalendar/JMemCalendar.fxml"));
			changePane.getChildren().setAll(change);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@FXML
	public void logOut(ActionEvent event) throws IOException {
		LoginController.ssessionJMem = null;
		Parent root = FXMLLoader.load(getClass().getResource("../../login/Login.fxml"));
		Scene scene = new Scene(root);
		Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		appStage.setTitle("Main Menu");
		appStage.setScene(scene);
		appStage.show();
	}

	@FXML
	public void goHome(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("JMemMainMenu.fxml"));
		Scene scene = new Scene(root);
		Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		appStage.setTitle("Main Menu");
		appStage.setScene(scene);
		appStage.show();

	}
	
	

}