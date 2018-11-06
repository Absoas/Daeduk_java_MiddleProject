package kr.or.ddit.controller.jmem.mycalendar;

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
import javafx.scene.control.Button;
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
import kr.or.ddit.calendar.MyCalendarVO;
import kr.or.ddit.common.ShowAlert;
import kr.or.ddit.controller.login.LoginController;
import kr.or.ddit.rmi.interf.IRemote;

public class JMemCalendarController implements Initializable {

	@FXML
	private Text calendarTitle;

	@FXML
	private Pane pane;

	@FXML
	private JFXButton previousBtn, nextBtn;

	private ArrayList<MemAnchorPaneDate> allCalendarDays = new ArrayList<>(35);
	private ArrayList<Label> allLabelDays = new ArrayList<>(35);
	private ArrayList<JFXButton> allBtn;
	private ArrayList<Button> fillImageBtn = new ArrayList<>(35);
	private ArrayList<MemCalendarHBox> allHBox;
	private ArrayList<VBox> allVbox;
	
	private YearMonth currentYearMonth;
	private IRemote conn;

	private List<AdminCalendarVO> list = null;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		previousBtn.setOnAction(e -> previousMonth());
		nextBtn.setOnAction(e -> nextMonth());
		conn = LoginController.conn;

		list = conn.getIAdminService().selectCalendar();
		
		allBtn = new ArrayList<>(35);
		allHBox = new ArrayList<>(list.size()*2);
		allVbox = new ArrayList<>(list.size()*2);
		
		initSetCalendar();
		initPopulateCalendar(currentYearMonth);
		initEventMouseClick();
		initEventBtnClick();
		initEventImageClick();
	}

	/**
	 * 7행 5열의 anchorpane을 생성하는 메서드
	 */
	private void initSetCalendar() {
		currentYearMonth = YearMonth.now();
		GridPane calendar = new GridPane();
		calendar.setPrefSize(600, 508.0);
		calendar.setGridLinesVisible(true);
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 7; j++) {
				MemAnchorPaneDate ap = new MemAnchorPaneDate();
				if (j % 7 == 0) {
					// 일요일만 true
					ap.setSunCheck(true);
				} else {
					ap.setSunCheck(false);
				}
				ap.setPrefSize(85, 80.6);
				calendar.add(ap, j, i);
				allCalendarDays.add(ap);
				
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

		for (MemAnchorPaneDate ap : allCalendarDays) {
			int  c = 0; 
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
			// 구인 일정 버튼 생성
			allLabelDays.add(txt);
			
			ap.getChildren().removeAll(allLabelDays);
			ap.getChildren().removeAll(allHBox);
			ap.getChildren().removeAll(fillImageBtn);
			ap.getChildren().removeAll(allBtn);
			ap.getChildren().removeAll(allVbox);
		
			ap.getChildren().add(txt);
			
			VBox vbox = new VBox();
			vbox.setPrefSize(80, 80);
			ap.getChildren().add(vbox);
			allVbox.add(vbox);
			for (int i = 0; i < list.size(); i++) {
				if (ap.getDate().toString().equals(list.get(i).getCreq_startdate().trim())) {
					
					MemCalendarHBox hBox = new MemCalendarHBox();
					hBox.setPrefSize(80, 10);
					hBox.setPadding(new Insets(10, 0, 5, 0));
					
					ap.setTopAnchor(vbox, 25.0);
					ap.setLeftAnchor(vbox, 5.0);
					
					Button imageBtn = new Button();
					imageBtn.setPrefSize(7.5, 7.5);
					imageBtn.setStyle("-fx-background-color:  #ffffff; -fx-font-size: 1px;");
					ImageView image = new ImageView();
					Image im = new Image(getClass().getResourceAsStream("../../../img/goodCheck.png"));
					image.setImage(im);
					image.setFitWidth(15);
					image.setFitHeight(15);
					imageBtn.setGraphic(image);
					hBox.setGoodCheck(false);
					
					JFXButton btn = new JFXButton();
					btn.setPrefSize(55, 10);
					btn.setText(list.get(i).getCor_name());
					ap.setPadding(new Insets(5, 0, 5, 0));
					btn.setStyle("-fx-background-color: #445252; -fx-font-size: 12px; -fx-text-fill: white; -fx-font-weight: bold;");
					
					allBtn.add(btn);
					fillImageBtn.add(imageBtn);
					allHBox.add(hBox);
					
					hBox.getChildren().addAll(imageBtn,btn);
					vbox.getChildren().add(hBox);

				} 
				else if (ap.getDate().toString().equals(list.get(i).getCreq_enddate().trim())) {
					MemCalendarHBox hBox = new MemCalendarHBox();
					hBox.setPrefSize(80, 10);
					hBox.setPadding(new Insets(10, 0, 5, 0));
					
					ap.setTopAnchor(vbox, 25.0);
					ap.setLeftAnchor(vbox, 5.0);
					
					Button imageBtn = new Button();
					imageBtn.setPrefSize(7.5, 7.5);
					imageBtn.setStyle("-fx-background-color:  #ffffff; -fx-font-size: 1px;");
					ImageView image = new ImageView();
					Image im = new Image(getClass().getResourceAsStream("../../../img/goodCheck.png"));
					image.setImage(im);
					image.setFitWidth(15);
					image.setFitHeight(15);
					imageBtn.setGraphic(image);
					hBox.setGoodCheck(false);
					
					JFXButton btn = new JFXButton();
					btn.setPrefSize(55, 10);
					btn.setText(list.get(i).getCor_name());
					ap.setPadding(new Insets(5, 0, 5, 0));
					
					btn.setStyle("-fx-background-color: #E73938; -fx-font-size: 12px;"
							+ "-fx-text-fill: white;"
							+ "-fx-font-weight: bold;"
							);
					allBtn.add(btn);
					fillImageBtn.add(imageBtn);
					allHBox.add(hBox);
					
					hBox.getChildren().addAll(imageBtn,btn);
					vbox.getChildren().add(hBox);
					
				}
			}
			
			calendarTitle.setText(yearMonth.getYear() + "  " + yearMonth.getMonth().toString());
		}

		initEventBtnClick();
		initEventImageClick();
	}

	/**
	 * 기업 이름 버튼을 클릭하면 해당 기업의 상세정보창을 띄워주는 메서드
	 */
	private void initEventBtnClick() {
		for (JFXButton jfxButton : allBtn) {
			jfxButton.setOnAction(event->{
				Parent root = null;
				try {
					root = FXMLLoader.load(getClass().getResource("CorSelectCalendar.fxml"));
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

				if(vo.getCor_name() == null) {
					return;
				}
				
				cor_name.setEditable(false);
				cor_tel.setEditable(false);
				cor_salary.setEditable(false);
				cor_career.setEditable(false);
				cor_content.setEditable(false);
				
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
		for (MemAnchorPaneDate ap : allCalendarDays) {
			ap.setOnMousePressed(event -> {
				ap.setStyle("-fx-background-color: #F2F5A9; -fx-border-color: black;");
				ap.setClickCheck(true);
			});

			ap.setOnMouseReleased(event -> {
				ap.setStyle("");
			});
		}
	}
	
	private void initEventImageClick() {
		for(Button btn : fillImageBtn) {
			btn.setOnAction(event->{
				ImageView image = new ImageView();
				Image im = new Image(getClass().getResourceAsStream("../../../img/goodCheck.png"));
				image.setImage(im);
				image.setFitWidth(15);
				image.setFitHeight(15);
				btn.setGraphic(image);
				
				HBox hp = (HBox)btn.getParent();
				JFXButton gp = (JFXButton)hp.getChildren().get(1);
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i).getCor_name().equals(gp.getText())) {
						MyCalendarVO vo = new MyCalendarVO();
						vo.setCalendar_title(list.get(i).getCor_name());
						vo.setCalendar_date(list.get(i).getCreq_startdate());
						vo.setCalendar_enddate(list.get(i).getCreq_enddate());
						vo.setCalendar_content(list.get(i).getCreq_content());
						vo.setMem_id(LoginController.ssessionJMem.getMem_id());
						
						if(conn.getICalendarService().checkCalendar(vo)) {
							ShowAlert.showAlertWarning("경고창", "이미 좋아요를 클릭하셨습니다.");
							return;
						}

						conn.getICalendarService().insertMyCalendar(vo);
						ShowAlert.showAlertINFORMATION("좋아요!", "마이 캘린더에 등록되었습니다.");
					}
				}
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

	public ArrayList<MemAnchorPaneDate> getAllCalendarDays() {
		return allCalendarDays;
	}

	public void setAllCalendarDays(ArrayList<MemAnchorPaneDate> allCalendarDays) {
		this.allCalendarDays = allCalendarDays;
	}
}
