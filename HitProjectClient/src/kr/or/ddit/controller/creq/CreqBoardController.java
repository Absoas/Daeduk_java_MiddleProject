package kr.or.ddit.controller.creq;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.InfoWindow;
import com.lynden.gmapsfx.javascript.object.InfoWindowOptions;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Alert;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import kr.or.ddit.controller.login.LoginController;
import kr.or.ddit.creqboard.CReqBoardVO;
import kr.or.ddit.rmi.client.ClientConnectFactory;
import kr.or.ddit.rmi.interf.IRemote;

public class CreqBoardController implements Initializable, MapComponentInitializedListener {

	private IRemote conn;

	private Accordion accordion;

	@FXML
	private AnchorPane parentPane;

	@FXML
	private JFXComboBox<String> comboBox;

	@FXML
	private JFXTextField textField;

	@FXML
	private ScrollPane scroll;

	@FXML
	private JFXButton selBtn;

	List<TitledPane> alltitle = null;

	public static List<CReqBoardVO> creqVO = null;

	List<String> list = null;

	List<Parent> rootlist;

	GoogleMapView mapView;
	GoogleMap map;

	static Alert alertWarning = new Alert(AlertType.WARNING);

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		conn = ClientConnectFactory.getClientConnect();

		ObservableList<String> combo = FXCollections.observableArrayList();
		combo.add("지 역");
		combo.add("이 름");
		combo.add("연 봉");
		combo.add("전 체");

		comboBox.setItems(combo);
		comboBox.setPromptText("검색조건");

		selBtn.setOnAction(event -> {
			String selCombo = comboBox.getSelectionModel().getSelectedItem();
			String txt = textField.getText();

			if (selCombo == null || selCombo.equals("전 체")) {
				list = conn.getICreqBoardService().selectCReqName();
				alltitle = new ArrayList<>(list.size());
				creqVO = new ArrayList<>(list.size());
				rootlist = new ArrayList<>(list.size());

				start();
				parentPane.getChildren().add(accordion);

			} else if (txt.isEmpty() || txt == null) {
				showAlertWarning("공백", "입력 후에 검색 버튼을 눌러주세요.");
				return;
			} else if (selCombo.equals("지 역")) {
				String corArea = textField.getText();
				list = conn.getICreqBoardService().selectCreqArea(corArea);
				alltitle = new ArrayList<>(list.size());
				creqVO = new ArrayList<>(list.size());
				rootlist = new ArrayList<>(list.size());

				start();
				parentPane.getChildren().add(accordion);

			} else if (selCombo.equals("이 름")) {
				String corName = textField.getText();
				list = conn.getICreqBoardService().selectCreqCorName(corName);
				alltitle = new ArrayList<>(list.size());
				creqVO = new ArrayList<>(list.size());
				rootlist = new ArrayList<>(list.size());

				start();
				parentPane.getChildren().add(accordion);
			} else if (selCombo.equals("연 봉")) {
				String creqSalary = textField.getText();
				list = conn.getICreqBoardService().selectCreqSal(creqSalary);
				alltitle = new ArrayList<>(list.size());
				creqVO = new ArrayList<>(list.size());
				rootlist = new ArrayList<>(list.size());

				start();
				parentPane.getChildren().add(accordion);
			}
		});

//    parentPane.getChildren().clear();
//      parentPane.getChildren().add(accordion);
	}

	private void start() {
		accordion = new Accordion();

		for (int i = 0; i < list.size(); i++) {
			TitledPane tp = new TitledPane();

			tp.setText(list.get(i));
			alltitle.add(tp);
			AnchorPane anchor = new AnchorPane();
			Parent root = null;
			try {

				root = FXMLLoader.load(getClass().getResource("CReqBoardAnchoInnerFx.fxml"));
				rootlist.add(root);
				JFXTextField cor_tel = (JFXTextField) root.lookup("#cor_tel");
				JFXTextField creq_career = (JFXTextField) root.lookup("#creq_career");
				JFXTextField creq_enddate = (JFXTextField) root.lookup("#creq_enddate");
				JFXTextField creq_startdate = (JFXTextField) root.lookup("#creq_startdate");
				JFXTextField cor_name = (JFXTextField) root.lookup("#cor_name");
				JFXTextField creq_click = (JFXTextField) root.lookup("#creq_click");
				JFXTextField creq_salary = (JFXTextField) root.lookup("#creq_salary");
				JFXTextArea creq_content = (JFXTextArea) root.lookup("#creq_content");
				ImageView cor_img = (ImageView) root.lookup("#cor_img");
				JFXButton creqBtn = (JFXButton) root.lookup("#creqBtn");
				JFXButton googleMapBtn = (JFXButton) root.lookup("#googleMapBtn");

				cor_tel.setEditable(false);
				creq_career.setEditable(false);
				creq_career.setEditable(false);
				creq_enddate.setEditable(false);
				creq_startdate.setEditable(false);
				cor_name.setEditable(false);
				creq_click.setEditable(false);
				creq_salary.setEditable(false);
				creq_content.setEditable(false);
				CReqBoardVO vo = conn.getICreqBoardService().selectCreqBoardInfo(tp.getText());
				creqVO.add(vo);
				
				Image img = new Image(getClass().getResourceAsStream("../../img/"+tp.getText()+".png"));
				cor_img.setImage(img);

				googleMapBtn.setOnAction(event -> {
					mapView = new GoogleMapView();
					mapView.addMapInializedListener(this);

					Stage primaryStage = new Stage();

					Scene scene = new Scene(mapView);

					primaryStage.setTitle("Corperation Signup");
					primaryStage.setScene(scene);
					primaryStage.show();

				});

//            cor_tel.setText(creqVO.get(i).getCor_tel());
//            creq_career.setText(creqVO.get(i).getCreq_career());
//            creq_enddate.setText(creqVO.get(i).getCreq_enddate());
//            creq_startdate.setText(creqVO.get(i).getCreq_startdate());
//            cor_name.setText(creqVO.get(i).getCor_name());
//            creq_click.setText(String.valueOf(creqVO.get(i).getCreq_click()));
//            creq_salary.setText(String.valueOf(creqVO.get(i).getCreq_salary()));
//            creq_content.setText(creqVO.get(i).getCreq_content());
				
				cor_tel.setText(vo.getCor_tel());
				creq_career.setText(vo.getCreq_career());
				creq_enddate.setText(vo.getCreq_enddate());
				creq_startdate.setText(vo.getCreq_startdate());
				cor_name.setText(vo.getCor_name());
				creq_click.setText(String.valueOf(vo.getCreq_click()));
				creq_salary.setText(String.valueOf(vo.getCreq_salary()));
				creq_content.setText(vo.getCreq_content());
//            rootlist.add(root);
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			anchor.getChildren().setAll(root);
			tp.setContent(anchor);
			accordion.setLayoutX(10);
			accordion.setLayoutY(5);
			accordion.getPanes().add(tp);

		}
		setMouseEvent();
	}

	private CReqBoardVO vo;
	private Parent papa;

	private void setMouseEvent() {

		for (TitledPane titledPane : alltitle) {
			titledPane.expandedProperty().addListener(new ChangeListener<Boolean>() {

				@Override
				public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
					if (newValue == true) {
						vo = new CReqBoardVO();
						
						Platform.runLater(new Runnable() {
							@Override
							public void run() {
								for (int i = 0; i < creqVO.size(); i++) {
									if (creqVO.get(i).getCor_name().equals(titledPane.getText())) {
										papa = rootlist.get(i);
										vo = creqVO.get(i);
										vo.setCreq_click(vo.getCreq_click() + 1);
										creqVO.get(i).setCreq_click(vo.getCreq_click());
//                        if(cnt>0) {
//                           //성공
//                        }else {
//                           //실패
//                        }
										
										int cnt = conn.getICreqBoardService().updateCreqClick(vo);
										
										JFXTextField creq_click = (JFXTextField) papa.lookup("#creq_click");
										creq_click.setText(vo.getCreq_click() + "");
										
									}
								}
							}
						});

//                  alltitle.clear();
					}
				}
			});
//         
		}
	}

	@Override
	public void mapInitialized() {
		// Set the initial properties of the map.
		MapOptions mapOptions = new MapOptions();

		mapOptions.center(new LatLong(36.318528, 127.379220)).overviewMapControl(false).panControl(false)
				.rotateControl(false).scaleControl(false).streetViewControl(false).zoomControl(false).zoom(12);

		map = mapView.createMap(mapOptions);

		// Add a marker to the map
		MarkerOptions markerOptions = new MarkerOptions();

		markerOptions.position(new LatLong(36.318528, 127.379220)).visible(Boolean.TRUE).title("My Marker");

		Marker marker = new Marker(markerOptions);

		map.addMarker(marker);

	}

	public static void showAlertWarning(String headerText, String contentText) { // 기존에 사용하고있는 warning ex) 실패하였을때 사용
		alertWarning.setHeaderText(headerText);
		alertWarning.setContentText(contentText);
		alertWarning.show();
	}
}