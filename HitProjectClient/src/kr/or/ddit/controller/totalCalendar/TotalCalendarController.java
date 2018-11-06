package kr.or.ddit.controller.totalCalendar;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import kr.or.ddit.admin.AdminCalendarVO;
import kr.or.ddit.controller.jmem.mycalendar.MemCalendarHBox;
import kr.or.ddit.rmi.client.ClientConnectFactory;
import kr.or.ddit.rmi.interf.IRemote;

public class TotalCalendarController implements Initializable {

	@FXML
	private Text calendarTitle;
	
	@FXML
	private Pane pane;

	@FXML
	private JFXButton previousBtn, nextBtn;

	private ArrayList<AnchorPaneDate> allCalendarDays = new ArrayList<>(35);
	private ArrayList<Label> allLabelDays = new ArrayList<>(35);
	private ArrayList<JFXButton> allBtn;
	private ArrayList<VBox> allVBox;
	private ArrayList<HBox> allHBox;
	private YearMonth currentYearMonth;
	private IRemote conn;

	private List<AdminCalendarVO> list = null;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		previousBtn.setOnAction(e -> previousMonth());
		nextBtn.setOnAction(e -> nextMonth());
		conn = ClientConnectFactory.getClientConnect();

		list = conn.getIAdminService().selectCalendar();
		

		allVBox = new ArrayList<>(list.size()*2);
		allBtn = new ArrayList<>(list.size()*2);
		allHBox = new ArrayList<>(list.size()*2);
		initSetCalendar();
		initPopulateCalendar(currentYearMonth);
		initEventMouseClick();
		initEventBtnClick();
	}

	/**
	 * 7행 5열의 anchorpane을 생성하는 메서드
	 */
	private void initSetCalendar() {
		currentYearMonth = YearMonth.now();
		GridPane calendar = new GridPane();
		calendar.setPrefSize(600, 508.0);
		calendar.setGridLinesVisible(true);
		
		int id = 1;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 7; j++) {
				AnchorPaneDate ap = new AnchorPaneDate();
				if (j % 7 == 0) {
					// 일요일만 true
					ap.setSunCheck(true);
				} else {
					ap.setSunCheck(false);
				}
				ap.setPrefSize(85, 80.6);
				ap.setId("pane" + id);
				calendar.add(ap, j, i);
				allCalendarDays.add(ap);
				id++;
				calendar.setMargin(ap, new Insets(1,1,1,1));
			}
		}
		pane.getChildren().add(calendar);
	}

	/**
	 * pane 안에 txt(날짜) btn(구인시작날짜 , 구인종료날짜) 생성해주는 코드
	 * 
	 * @param yearMonth
	 */
	public void initPopulateCalendar(YearMonth yearMonth) {
		LocalDate calendarDate = LocalDate.of(yearMonth.getYear(), yearMonth.getMonthValue(), 1);

		while (!calendarDate.getDayOfWeek().toString().equals("SUNDAY")) {
			calendarDate = calendarDate.minusDays(1);
		}

		// ObservableList<CalendarVO> list = dao.selectAllCalendar();

		for (AnchorPaneDate ap : allCalendarDays) {
			if (ap.getChildren().size() != 0) {
				ap.getChildren().remove(0);
			}

			Label txt = new Label(String.valueOf(calendarDate.getDayOfMonth()));
			ap.setDate(calendarDate);
			ap.setTopAnchor(txt, 5.0);
			ap.setLeftAnchor(txt, 5.0);
			if (ap.isSunCheck() == true) {
				txt.setStyle("-fx-text-fill: red;");
			} else {
				txt.setStyle("-fx-text-fill: black;");
			}
			calendarDate = calendarDate.plusDays(1);

			// if (ap.getChildren().size() != 0) {
			// ap.getChildren().remove(0);
			// }
			// 구인 일정 버튼 생성
			allLabelDays.add(txt);
			
			ap.getChildren().removeAll(allLabelDays);
			ap.getChildren().removeAll(allBtn);
			ap.getChildren().removeAll(allHBox);
			ap.getChildren().removeAll(allVBox);
			
			VBox vbox = new VBox();
			vbox.setPrefSize(80, 80);
			ap.getChildren().add(vbox);
			allVBox.add(vbox);
			
			for (int i = 0; i < list.size(); i++) {
				// 구인 일정 시작날짜 셋팅
				if (ap.getDate().toString().equals(list.get(i).getCreq_startdate())) {
					MemCalendarHBox hBox = new MemCalendarHBox();
					hBox.setPrefSize(80, 10);
					hBox.setPadding(new Insets(10, 0, 5, 0));
					ap.setTopAnchor(vbox, 25.0);
					ap.setLeftAnchor(vbox, 5.0);

					JFXButton btn = new JFXButton();
					allBtn.add(btn);
					allHBox.add(hBox);
					btn.setPrefWidth(75);
					btn.setPrefHeight(5);
					btn.setText(list.get(i).getCor_name());
					ap.setPadding(new Insets(5, 0, 5, 0));
					btn.setStyle("-fx-background-color: #445252; -fx-font-size: 12px; -fx-text-fill: white; -fx-font-weight: bold;");

					hBox.getChildren().add(btn);
					vbox.getChildren().add(hBox);
				}
			}
			for (int i = 0; i < list.size(); i++) {
				if (ap.getDate().toString().equals(list.get(i).getCreq_enddate().trim())) {

					MemCalendarHBox hBox = new MemCalendarHBox();
					hBox.setPrefSize(80, 10);
					hBox.setPadding(new Insets(10, 0, 5, 0));
					
					ap.setTopAnchor(vbox, 15.0);
					ap.setLeftAnchor(vbox, 5.0);

					JFXButton btn = new JFXButton();
					allBtn.add(btn);
					allHBox.add(hBox);

					btn.setPrefWidth(75);
					btn.setPrefHeight(10);
					btn.setText(list.get(i).getCor_name());
					ap.setPadding(new Insets(5, 0, 5, 0));
					btn.setStyle("-fx-background-color: #E73938; -fx-font-size: 12px;"
							+ "-fx-text-fill: white;"
							+ "-fx-font-weight: bold;"
							);

					hBox.getChildren().add(btn);
					vbox.getChildren().add(hBox);
				}
			}
			ap.getChildren().add(txt);
			calendarTitle.setText(yearMonth.getYear() + "  " + yearMonth.getMonth().toString());
		}
		initEventBtnClick();
	
		
//	    System.out.println(yearMonth.getMonth().toString());    
//		switch (yearMonth.getMonth().toString()) {
//		
//		case "JANUARY":
//			Image image1 = new Image(getClass().getResourceAsStream("january.png"));
//			monthImage.setImage(image1);
//			break;
//
//		case "FEBRUARY":
//			Image image2 = new Image(getClass().getResourceAsStream("febuary.png"));
//			monthImage.setImage(image2);
//			break;
//			
//		case "MARCH":
//			Image image3 = new Image(getClass().getResourceAsStream("march.png"));
//			monthImage.setImage(image3);
//			break;
//		case "APRIL":
//			Image image4 = new Image(getClass().getResourceAsStream("april.jpg"));
//			monthImage.setImage(image4);
//			break;
//		case "MAY":
//			Image image5 = new Image(getClass().getResourceAsStream("may.png"));
//			monthImage.setImage(image5);
//			break;
//		case "JUNE":
//			Image image6 = new Image(getClass().getResourceAsStream("june.png"));
//			monthImage.setImage(image6);
//			break;
//			
//		case "JULY":
//			Image image7 = new Image(getClass().getResourceAsStream("july.png"));
//			monthImage.setImage(image7);
//			break;
//		case "AUGUST":
//			Image image8 = new Image(getClass().getResourceAsStream("august.jpg"));
//			monthImage.setImage(image8);
//			break;
//		case "SEPTEMBER":
//			Image image9 = new Image(getClass().getResourceAsStream("september.png"));
//			monthImage.setImage(image9);
//			break;
//		case "OCTOBER":
//			Image image10 = new Image(getClass().getResourceAsStream("october.png"));
//			monthImage.setImage(image10);
//			break;
//		case "NOVEMBER":
//			Image image11 = new Image(getClass().getResourceAsStream("november.png"));
//			monthImage.setImage(image11);
//			break;
//		case "DECEMBER":
//			Image image12 = new Image(getClass().getResourceAsStream("december.png"));
//			monthImage.setImage(image12);
//			break;
//		default:
//			break;
//		}
	}
	
	

	/**
	 * 기업 이름 버튼을 클릭하면 해당 기업의 상세정보창을 띄워주는 메서드
	 */
	private void initEventBtnClick() {
		for (JFXButton jfxButton : allBtn) {
			jfxButton.setOnAction(event->{
				Parent root = null;
				try {
					root = FXMLLoader.load(getClass().getResource("../jmem/mycalendar/CorSelectCalendar.fxml"));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				Stage primaryStage = new Stage();
				Scene scene = new Scene(root);
				primaryStage.setTitle("Corperation Signup");
				primaryStage.setScene(scene);
				
				AdminCalendarVO vo =  conn.getIAdminService().detailCalendar(jfxButton.getText());
				
				JFXTextField cor_name = (JFXTextField) root.lookup("#cor_name");
				JFXTextField cor_tel = (JFXTextField) root.lookup("#cor_tel");
				JFXTextField cor_salary = (JFXTextField) root.lookup("#cor_salary");
				JFXTextField cor_career = (JFXTextField) root.lookup("#cor_career");
				JFXTextArea cor_content = (JFXTextArea) root.lookup("#cor_content");
				JFXButton cancelBtn = (JFXButton) root.lookup("#cancelBtn");

				cor_name.setText(vo.getCor_name());
				cor_tel.setText(vo.getCor_tel());
				cor_salary.setText(vo.getCreq_salary());
				cor_content.setText(vo.getCreq_content());
				cor_career.setText(vo.getCreq_career());
				
				cancelBtn.setOnAction(e -> {
					primaryStage.close();
				});

				primaryStage.showAndWait();
			});
		}
	}

	/**
	 * 마우스로 각각의 pane을 클릭했을 때 화면 클릭 이벤트를 줌 (배경 변경)
	 */
	private void initEventMouseClick() {
		for (AnchorPaneDate ap : allCalendarDays) {
			ap.setOnMousePressed(event -> {
				ap.setStyle("-fx-background-color: #F2F5A9; -fx-border-color: black;");
				ap.setClickCheck(true);
			});

			ap.setOnMouseReleased(event -> {
				ap.setStyle("");
			});
		}
	}

	private void previousMonth() {
		currentYearMonth = currentYearMonth.minusMonths(1);
		initPopulateCalendar(currentYearMonth);
	}

	private void nextMonth() {
		currentYearMonth = currentYearMonth.plusMonths(1);
		initPopulateCalendar(currentYearMonth);
	}

	public ArrayList<AnchorPaneDate> getAllCalendarDays() {
		return allCalendarDays;
	}

	public void setAllCalendarDays(ArrayList<AnchorPaneDate> allCalendarDays) {
		this.allCalendarDays = allCalendarDays;
	}

}
