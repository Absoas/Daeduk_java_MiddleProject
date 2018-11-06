package kr.or.ddit.controller.cor.creqboard;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import kr.or.ddit.common.ShowAlert;
import kr.or.ddit.controller.login.LoginController;
import kr.or.ddit.creqboard.CReqBoardVO;
import kr.or.ddit.rmi.client.ClientConnectFactory;
import kr.or.ddit.rmi.interf.IRemote;
import kr.or.ddit.test.TestVO;

public class CreqBoardInsertController implements Initializable{

    @FXML
    private JFXTextField careerField;

    @FXML
    private JFXTextArea contentArea;

    @FXML
    private DatePicker endDate;

    @FXML
    private JFXButton cancelBtn;

    @FXML
    private JFXTextField salaryField;
    
    @FXML
    private JFXComboBox<String> comboCareer;

    @FXML
    private DatePicker startDate;

    @FXML
    private JFXButton checkBtn;
  
    @FXML
    private JFXComboBox<String> examKindsCombo;
    
    @FXML
    private JFXComboBox<String> examSelectCombo;
    
    IRemote conn;
     
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		conn = ClientConnectFactory.getClientConnect();
		checkBtn.setOnAction(event-> insertCreq());
		cancelBtn.setOnAction(event -> cancelBrn());
		
		ObservableList<String> list = FXCollections.observableArrayList("신입","경력");
		ObservableList<String> examKind = FXCollections.observableArrayList("코딩문제","드로잉문제");
	
		comboCareer.setItems(list);
		examKindsCombo.setItems(examKind);
		
		careerField.setEditable(false);
		comboCareer.valueProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if(newValue.equals("경력")) {
					careerField.setEditable(true);
				}else {
					careerField.setText("");
					careerField.setEditable(false);
				}
			}
		});
		
		examKindsCombo.valueProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if(newValue.equals("코딩문제")) {
					setCodingExam();
				}else {
					setDrawingExam();
				}
			}
		});
	}
	
	 private void setCodingExam() {
		 List<TestVO> vo = conn.getITestService().selectCombo(1);
	      List<String> data =new ArrayList<String>();
	      
	      for(int i =0; i<vo.size(); i++) {
	         data.add(vo.get(i).getTest_name());
	      }
	      
	      ObservableList<String> list = FXCollections.observableArrayList(data);
	      examSelectCombo.setItems(list);
	   }
	   
	   private void setDrawingExam() {
	      List<TestVO> vo = conn.getITestService().selectCombo(2);
	      List<String> data = new ArrayList<String>();
	      
	      for(int i =0; i<vo.size(); i++) {
	         data.add(vo.get(i).getTest_name());
	      }
	      
	      ObservableList<String> list = FXCollections.observableArrayList(data);
	      examSelectCombo.setItems(list);
	   }


	private void cancelBrn() {
		Stage modalStage = (Stage) ((Node)checkBtn).getScene().getWindow();
		modalStage.close();
	}

	private void insertCreq() {
		LocalDate sDate = startDate.getValue();
		LocalDate eDate = endDate.getValue();
		
		if(sDate.isAfter(eDate)) {
			ShowAlert.showAlertError("날짜 오류", "구인시작 날짜가 구인 마감날짜보다 빠릅니다. 확인 해주세요");
			return;
		}
		
		CReqBoardVO vo = new CReqBoardVO();
		vo.setCreq_content(contentArea.getText());
		vo.setTest_name(examSelectCombo.getValue());
		vo.setCreq_salary(Integer.parseInt(salaryField.getText()));
		vo.setCreq_startdate(sDate.toString());
		vo.setCreq_enddate(eDate.toString());
		
		int test_type= 0;
		if(examKindsCombo.getValue().equals("코딩문제")) {
			test_type = 1;
		}else {
			test_type = 2;
		}
		vo.setTest_type(test_type);
		
		if(comboCareer.getValue().equals("신입")) {
			vo.setCreq_career(comboCareer.getValue());
		}else {
			vo.setCreq_career(careerField.getText());
		}
		
		conn.getICreqBoardService().insertCreqBoard(vo);
		conn.getICreqBoardService().insertApply_board(LoginController.ssessionCMem.getMem_id());
		ShowAlert.showAlertINFORMATION("구인신청", "구인신청이 완료되었습ㅂ니다.");

	}
}
