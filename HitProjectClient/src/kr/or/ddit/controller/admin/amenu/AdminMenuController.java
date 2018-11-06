package kr.or.ddit.controller.admin.amenu;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXNodesList;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import com.sun.javafx.css.converters.PaintConverter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import kr.or.ddit.controller.login.LoginController;
import kr.or.ddit.jobmem.JobMemberVO;

public class AdminMenuController implements Initializable {
	@FXML
	private AnchorPane parent;
	@FXML
	private ImageView toolbarimg;
	@FXML
	private JFXDrawer drawer, drawer1,drawer3;
	@FXML
	private AnchorPane changePane;
	@FXML
	private AnchorPane menupane;
	@FXML
	private JFXButton ManageHamburger;

	@FXML
	private JFXButton applyJMemBtn;

	@FXML
	private JFXButton TotalCalendar;

	@FXML
	private JFXButton applyCorBtn;

	@FXML
	private JFXButton BoardHamburger;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// ssessionMem = LoginController.ssessionMem;
		setDrawer();
		setDrawer2();
		setPath();
	}	

	private void setPath() {
		applyCorBtn.setOnAction(event -> {
			drawer3.close();
			drawer1.close();
			drawer.close();
			AnchorPane btn = null;
			try {
				changePane.getChildren().clear();
				btn = FXMLLoader.load(this.getClass().getResource("../../admin/manage/CorMemberFx.fxml"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			changePane.getChildren().setAll(btn);
		});

		applyJMemBtn.setOnAction(event -> {
			drawer3.close();
			drawer1.close();
			drawer.close();
					
			AnchorPane btn = null;
			try {
				changePane.getChildren().clear();
				btn = FXMLLoader.load(this.getClass().getResource("../../admin/manage/JobMemberFx.fxml"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			changePane.getChildren().setAll(btn);
		});

		TotalCalendar.setOnAction(event -> {
			drawer3.close();
			drawer1.close();
			drawer.close();
					
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
		ManageHamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent e) -> {
			if (drawer.isShown()) {
				drawer1.close();
				drawer3.close();
				
			} else {
				drawer1.close();
				drawer1.toBack();
				drawer.toFront();
				drawer.open();
			}
		});

		try {
			AnchorPane sidePane = FXMLLoader.load(getClass().getResource("ManageSubMenu.fxml"));
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
							AnchorPane one = null;
							try {
								one = FXMLLoader.load(this.getClass().getResource("../manage/JobMemberFx.fxml"));
							} catch (IOException e) {
								e.printStackTrace();
							}
							changePane.getChildren().setAll(one);
							break;

						case "btn2":
							if(!drawer.isShown()) {
								drawer.toFront();
								drawer.open();
							}
							drawer1.close();
							if(drawer3.isShown()) {
								drawer3.close();
								drawer3.toBack();
							}
							AnchorPane two = null;
							try {
								two = FXMLLoader.load(this.getClass().getResource("../manage/CorMemberFx.fxml"));
							} catch (IOException e) {
								e.printStackTrace();
							}
							changePane.getChildren().setAll(two);
							break;

						case "btn3":
							if(!drawer.isShown()) {
								drawer.toFront();
								drawer.open();
							}
							drawer1.close();
							if(drawer3.isShown()) {
								drawer3.close();
								drawer3.toBack();
							}
							AnchorPane three = null;
							try {
								three = FXMLLoader.load(this.getClass().getResource("../../licenseboard/License_Board.fxml"));
							} catch (IOException e) {
								e.printStackTrace();
							}
							changePane.getChildren().setAll(three);
							break;
						
						
						case "btn4":
							if(!drawer.isShown()) {
								drawer.toFront();
								drawer.open();
							}
							drawer1.close();
							if(drawer3.isShown()) {
								drawer3.close();
								drawer3.toBack();
							}
							AnchorPane four = null;
							try {
								four = FXMLLoader.load(this.getClass().getResource("../../test/coding.fxml"));
							} catch (IOException e) {
								e.printStackTrace();
							}
							changePane.getChildren().setAll(four);
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
		BoardHamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent e) -> {
			if (drawer1.isShown()) {
				drawer.close();
				drawer3.close();
			} else {
				drawer.close();
				drawer1.toFront();
				drawer.toBack();
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
							drawer.close();
							if(!drawer1.isShown()) {
								drawer1.toFront();
								drawer1.open();
							}
							if(drawer3.isShown()) {
								drawer3.toBack();
								drawer3.close();
							}
							AnchorPane one = null;
							try {
								one = FXMLLoader
										.load(this.getClass().getResource("../../noticeboard/noticeboard.fxml"));
							} catch (IOException e) {
								e.printStackTrace();
							}
							changePane.getChildren().setAll(one);
							break;

						case "btn2":
							drawer.close();
							if(!drawer1.isShown()) {

								drawer1.toFront();
								drawer1.open();
							}
							if(drawer3.isShown()) {
								drawer3.close();
								drawer3.toBack();
							}
							AnchorPane two = null;
							try {
								two = FXMLLoader
										.load(this.getClass().getResource("../../passintroboard/passboard.fxml"));
							} catch (IOException e) {
								e.printStackTrace();
							}
							changePane.getChildren().setAll(two);
							break;

						case "btn3":
							drawer.close();
							if(!drawer1.isShown()) {

								drawer1.toFront();
								drawer1.open();
							}
							if(drawer3.isShown()) {
								drawer3.close();
								drawer3.toBack();
							}
							AnchorPane three = null;
							try {
								three = FXMLLoader
										.load(this.getClass().getResource("../../reviewboard/reviewBoard.fxml"));
							} catch (IOException e) {
								e.printStackTrace();
							}
							changePane.getChildren().setAll(three);
							break;

						case "btn4":
							drawer.close();
							if(!drawer1.isShown()) {

								drawer1.toFront();
								drawer1.open();
							}
							if(drawer3.isShown()) {
								drawer3.close();
								drawer3.toBack();
							}
							AnchorPane four = null;
							try {
								four = FXMLLoader.load(this.getClass().getResource("../../qnaboard/qnaBoard.fxml"));
							} catch (IOException e) {
								e.printStackTrace();
							}
							changePane.getChildren().setAll(four);
							break;

						case "btn5":
							drawer.close();
							if(!drawer1.isShown()) {
								drawer1.toFront();
								drawer1.open();
							}
							if(drawer3.isShown()) {
								drawer3.close();
								drawer3.toBack();
							}
							AnchorPane five = null;
							try {
								five = FXMLLoader.load(this.getClass().getResource("../../faqboard/freaskqnafx.fxml"));
							} catch (IOException e) {
								e.printStackTrace();
							}
							changePane.getChildren().setAll(five);
							break;

						// 기업랭킹
						case "btn6":
							if (drawer3.isShown()) {
								drawer3.close();
								drawer3.toBack();
							}else {
								drawer3.toFront();
								drawer3.open();
							}
							
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
	public void logOut(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("../../login/Login.fxml"));
		Scene scene = new Scene(root);
		Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		appStage.setTitle("Main Menu");
		appStage.setScene(scene);
		appStage.show();
	}

	@FXML
	public void goHome(ActionEvent event) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("AdminMain.fxml"));
			Scene scene = new Scene(root);
			Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			appStage.setTitle("Main Menu");
			appStage.setScene(scene);
			appStage.show();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

}
