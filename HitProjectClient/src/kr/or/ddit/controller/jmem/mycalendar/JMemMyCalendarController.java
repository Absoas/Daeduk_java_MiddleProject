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
import kr.or.ddit.calendar.MemoCalendarVO;
import kr.or.ddit.calendar.MyCalendarVO;
import kr.or.ddit.common.ShowAlert;
import kr.or.ddit.controller.login.LoginController;
import kr.or.ddit.rmi.interf.IRemote;

public class JMemMyCalendarController implements Initializable{
	@FXML
	private Text calendarTitle;

	@FXML
	private Pane pane;

	@FXML
	private JFXButton previousBtn, nextBtn;

	private ArrayList<MemAnchorPaneDate> allCalendarDays = new ArrayList<>(35);
	private ArrayList<Label> allLabelDays = new ArrayList<>(35);
	private ArrayList<JFXButton> allBtn;
	private ArrayList<VBox> allVbox;
	private ArrayList<Button> fillImageBtn = new ArrayList<>(35);
	private ArrayList<Button> allMemo;
	private ArrayList<MemCalendarHBox> allHBox;
	
	private YearMonth currentYearMonth;
	private IRemote conn;
	
	private List<MyCalendarVO> list = null;
	private List<MemoCalendarVO> memoList = null;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		previousBtn.setOnAction(e -> previousMonth());
		nextBtn.setOnAction(e -> nextMonth());
		
		conn = LoginController.conn;

		initSetList();
		initSetCalendar();
		initEventMouseClick();
		initEventBtnClick();
		initEventImageClick();
		initPopulateCalendar(currentYearMonth);
		memoBtnClick();
	}
	
	private void initSetList() {
		list = conn.getICalendarService().selectMyCalendar(LoginController.ssessionJMem.getMem_id());
		memoList = conn.getICalendarService().selectMemoCalendar(LoginController.ssessionJMem.getMem_id());
		allBtn = new ArrayList<>(list.size()*2);
		allHBox = new ArrayList<>(list.size()*2);
		allVbox = new ArrayList<>(list.size()*2);
		allMemo = new ArrayList<>(memoList.size());
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
			
			ap.getChildren().removeAll(allMemo);
			ap.getChildren().removeAll(allLabelDays);
			ap.getChildren().removeAll(allVbox);

			allLabelDays.add(txt);
			
			for(int i = 0;  i<memoList.size(); i++) {
				if(ap.getDate().toString().equals(memoList.get(i).getMemo_date())) {
					Button imageBtn = new Button();
					imageBtn.setPrefSize(15.5, 15.5);
					imageBtn.setStyle("-fx-background-color:  #ffffff; -fx-font-size: 1px;");
					ImageView image = new ImageView();
					Image im = new Image(getClass().getResourceAsStream("../../../img/memo.png"));
					image.setImage(im);
					image.setFitWidth(25);
					image.setFitHeight(25);
					imageBtn.setGraphic(image);
					allMemo.add(imageBtn);
					ap.setTopAnchor(imageBtn, 5.0);
					ap.setLeftAnchor(imageBtn, 80.0);
					
					ap.getChildren().add(imageBtn);
				}
			}
			
			VBox vbox = new VBox();
			vbox.setPrefSize(80, 80);
			ap.getChildren().add(vbox);
			ap.setTopAnchor(vbox, 25.0);
			ap.setLeftAnchor(vbox, 5.0);
			allVbox.add(vbox);
			for (int i = 0; i < list.size(); i++) {
				
				// 구인 일정 시작날짜 셋팅
				if (ap.getDate().toString().equals(list.get(i).getCalendar_date().trim())) {
					MemCalendarHBox hBox = new MemCalendarHBox();
					hBox.setPrefSize(80, 10);
					hBox.setPadding(new Insets(10, 0, 5, 0));
					
					
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
					btn.setPrefSize(70, 10);
					btn.setText(list.get(i).getCalendar_title());
					ap.setPadding(new Insets(5, 0, 5, 0));
					btn.setStyle("-fx-background-color: #445252; -fx-font-size: 12px; -fx-text-fill: white; -fx-font-weight: bold;");
					
					allBtn.add(btn);
					fillImageBtn.add(imageBtn);
					allHBox.add(hBox);
					
					hBox.getChildren().addAll(imageBtn,btn);
					vbox.getChildren().add(hBox);

				} 
				else if (ap.getDate().toString().equals(list.get(i).getCalendar_enddate().trim())) {
					
					MemCalendarHBox hBox = new MemCalendarHBox();
					hBox.setPrefSize(80, 10);
					hBox.setPadding(new Insets(5, 0, 5, 0));
					
					
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
					btn.setPrefSize(70, 10);
					btn.setText(list.get(i).getCalendar_title());
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

			ap.getChildren().add(txt);
			calendarTitle.setText(yearMonth.getYear() + "  " + yearMonth.getMonth().toString());
		}
		initEventBtnClick();
		initEventImageClick();
		memoBtnClick();
	}
	
	
	private void memoBtnClick() {
		for (Button btn : allMemo) {
			btn.setOnAction(event->{
				Parent root = null;
				try {
					root = FXMLLoader.load(getClass().getResource("JMemMemoDetail.fxml"));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				Stage primaryStage = new Stage();
				Scene scene = new Scene(root);
				primaryStage.setTitle("Corperation Signup");
				primaryStage.setScene(scene);
				
				JFXTextArea memo_content = (JFXTextArea) root.lookup("#memo_content");
				JFXTextField memo_title = (JFXTextField) root.lookup("#memo_title");
				JFXTextField memo_date = (JFXTextField) root.lookup("#memo_date");
				JFXButton updateBtn = (JFXButton) root.lookup("#updateBtn");
				JFXButton deleteBtn = (JFXButton) root.lookup("#deleteBtn");
				JFXButton cancelBtn = (JFXButton) root.lookup("#cancelBtn");
				
				MemAnchorPaneDate ap =  (MemAnchorPaneDate)btn.getParent();
				
				memo_date.setText(ap.getDate().toString());
				
				MemoCalendarVO vo = new MemoCalendarVO();
				
				String no = null;
				for(int i = 0; i<memoList.size(); i++) {
					if(memoList.get(i).getMemo_date().equals(ap.getDate().toString())) {
						no = memoList.get(i).getMemo_no();
					}
				}
				
				MemoCalendarVO vo3 =  conn.getICalendarService().selectDetailMemo(no);
				memo_title.setText(vo3.getMemo_title());
				memo_content.setText(vo3.getMemo_content());
				memo_date.setText(vo3.getMemo_date());
				
				vo.setMemo_no(no);
				
				updateBtn.setOnAction(e->{
					vo.setMem_id(LoginController.ssessionJMem.getMem_id());
					vo.setMemo_content(memo_content.getText());
					vo.setMemo_date(memo_date.getText());
					vo.setMemo_title(memo_title.getText());
					int result = conn.getICalendarService().updateMemoCalendar(vo);
					if(result > 0) {
						ShowAlert.showAlertINFORMATION("수정 완료", "수정이 완료되었습니다.");
						primaryStage.close();
					}else {
						return;
					}
				});
				
				deleteBtn.setOnAction(e->{
					vo.setMem_id(LoginController.ssessionJMem.getMem_id());
					vo.setMemo_content(memo_content.getText());
					vo.setMemo_date(memo_date.getText());
					vo.setMemo_title(memo_title.getText());
					ap.getChildren().remove(btn);
					allMemo.remove(btn);
					int result = conn.getICalendarService().deleteMemoCalendar(vo);
					if(result > 0) {
						ShowAlert.showAlertINFORMATION("삭제 완료", "메모 삭제가 완료되었습니다.");
						primaryStage.close();
					}else {
						return;
					}
				});
				
				cancelBtn.setOnAction(e -> {
					primaryStage.close();
				});
				
				primaryStage.showAndWait();
			});
		}
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
				
				if(vo==null) {
					return;
				}
				
				JFXTextField cor_name = (JFXTextField) root.lookup("#cor_name");
				JFXTextField cor_tel = (JFXTextField) root.lookup("#cor_tel");
				JFXTextField cor_salary = (JFXTextField) root.lookup("#cor_salary");
				JFXTextField cor_career = (JFXTextField) root.lookup("#cor_career");
				JFXTextArea cor_content = (JFXTextArea) root.lookup("#cor_content");
				
				cor_name.setEditable(false);
				cor_tel.setEditable(false);
				cor_salary.setEditable(false);
				cor_career.setEditable(false);
				cor_content.setEditable(false);
				
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
		for (MemAnchorPaneDate ap : allCalendarDays) {
			ap.setOnMousePressed(event -> {
				ap.setStyle("-fx-background-color: #F2F5A9; -fx-border-color: black;");
				ap.setClickCheck(true);
				
				String no = null;
				for(int i = 0; i<memoList.size(); i++) {
					if(memoList.get(i).getMemo_date().equals(ap.getDate().toString())) {
						no = memoList.get(i).getMemo_no();
					}
				}
				
				MemoCalendarVO vo3 =  conn.getICalendarService().selectDetailMemo(no);
				if(vo3 == null) {
					Parent root = null;
					try {
						root = FXMLLoader.load(getClass().getResource("JMemMemoInsert.fxml"));
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					Stage primaryStage = new Stage();
					Scene scene = new Scene(root);
					primaryStage.setTitle("Corperation Signup");
					primaryStage.setScene(scene);
					
					JFXTextArea memo_content = (JFXTextArea) root.lookup("#memo_content");
					JFXTextField memo_title = (JFXTextField) root.lookup("#memo_title");
					JFXTextField memo_date = (JFXTextField) root.lookup("#memo_date");
					JFXButton insertBtn = (JFXButton) root.lookup("#insertBtn");
					JFXButton cancelBtn = (JFXButton) root.lookup("#cancelBtn");
					
					memo_date.setText(ap.getDate().toString());
					MemoCalendarVO vo = new MemoCalendarVO();
					
					memo_date.setEditable(false);

					insertBtn.setOnAction(e->{
						vo.setMem_id(LoginController.ssessionJMem.getMem_id());
						vo.setMemo_content(memo_content.getText());
						vo.setMemo_date(memo_date.getText());
						vo.setMemo_title(memo_title.getText());
						boolean result = conn.getICalendarService().insertMemoCalendar(vo);
						if(result == false) {
							ShowAlert.showAlertError("실패", "메모 추가 실패하였습니다.");
							return;
						}else {
							ShowAlert.showAlertINFORMATION("성공", "메모 추가되었습니다.");
							initSetList();
							initPopulateCalendar(currentYearMonth);
							primaryStage.close();
							return;
						}
					});
					cancelBtn.setOnAction(e -> {
						primaryStage.close();
					});
					primaryStage.showAndWait();
				}else {
					ShowAlert.showAlertINFORMATION("이미 메모가 추가되어있습니다.", "위 상단 메모 아이콘을 클릭해주세요");
					ap.setStyle("");
					return;
				}
			});

			ap.setOnMouseReleased(event -> {
				ap.setStyle("");
			});
		}
	}
	
	
	private void initEventImageClick() {
		for(Button btn : fillImageBtn) {
			btn.setOnAction(event->{
				HBox hp = (HBox)btn.getParent();
				JFXButton gp = (JFXButton)hp.getChildren().get(1);
				MyCalendarVO vo = new MyCalendarVO();
				vo.setMem_id(LoginController.ssessionJMem.getMem_id());
				vo.setCalendar_title(gp.getText());
				conn.getICalendarService().deleteMyCalendar(vo);
				ShowAlert.showAlertWarning("지워짐", "지워짐");
				
				HBox hb = (HBox)btn.getParent();
				VBox vb = (VBox) hb.getParent();
				for (MemAnchorPaneDate ap : allCalendarDays) {
					ap.getChildren().remove(vb);
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
