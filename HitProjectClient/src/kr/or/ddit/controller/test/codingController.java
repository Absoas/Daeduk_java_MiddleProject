package kr.or.ddit.controller.test;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXComboBox;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import kr.or.ddit.common.ShowAlert;
import kr.or.ddit.rmi.client.ClientConnect;
import kr.or.ddit.rmi.interf.IRemote;
import kr.or.ddit.test.TestVO;

public class codingController implements Initializable{
	
	@FXML private TableView<TestVO> tableview;
	@FXML private TableColumn<TestVO,String> testno;
	@FXML private TableColumn<TestVO,String> testname;
	@FXML private TableColumn<TestVO,String> testtype;
	@FXML private TableColumn<TestVO,String> testexp;
	@FXML private JFXComboBox combolisttype;
	@FXML private Label label;
	@FXML private Pagination paging;
	
	int num;
	
	public static IRemote conn = null;
	static TestVO testvo = null;
	
	private int from, to, itemsForpage;
	private ObservableList<TestVO> AllTableData, currentPageData;
	
	
	@FXML
	private void selecttableview(MouseEvent event) {
		testvo = tableview.getSelectionModel().getSelectedItem();
		if(tableview.getSelectionModel().isEmpty()) {
			ShowAlert.showAlertError("공백입니다", "항목을 선택해주세요");
			return;
		}
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("codingselect.fxml"));
			Stage Cont = new Stage();
			Scene scene = new Scene(root);
			Cont.setTitle("코딩문제보기");
			Cont.setScene(scene);
			Cont.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
		tableview();
		
	}
	
	
	@FXML
	private void insert (ActionEvent event) {
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("codinginsert.fxml"));
			Stage addPost = new Stage();
			Scene scene = new Scene(root);
			addPost.setTitle("선택");
			addPost.setScene(scene);
			addPost.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		tableview();
		
	}
	
	@FXML
	private void combosel(ActionEvent event) {
		if(combolisttype.getSelectionModel().selectedItemProperty().getValue().equals("코딩문제")) {
			num = 1;
		}else {
			num = 2;
		}
	}
	
	@FXML
	private void search(ActionEvent event) {
		if(num == 1) {
			List<TestVO> testlist = conn.getITestService().selectCombo(num);
			ObservableList<TestVO> obsertestlist = FXCollections.observableArrayList(testlist);
			tableview.setItems(obsertestlist);
			testno.setCellValueFactory(new PropertyValueFactory<>("test_no"));
			testname.setCellValueFactory(new PropertyValueFactory<>("test_name"));
			testtype.setCellValueFactory(new PropertyValueFactory<>("test_type"));
			testexp.setCellValueFactory(new PropertyValueFactory<>("test_content"));

		}else if(num == 2){
			List<TestVO> testlist = conn.getITestService().selectCombo(num);
			ObservableList<TestVO> obsertestlist = FXCollections.observableArrayList(testlist);
			tableview.setItems(obsertestlist);
			testno.setCellValueFactory(new PropertyValueFactory<>("test_no"));
			testname.setCellValueFactory(new PropertyValueFactory<>("test_name"));
			testtype.setCellValueFactory(new PropertyValueFactory<>("test_type"));
			testexp.setCellValueFactory(new PropertyValueFactory<>("test_content"));
		}else {
			ShowAlert.showAlertError("검색할항목을 선택해주세요","");
		}
	}
	
	private void tableview() {
		List<TestVO> testlist = conn.getITestService().selectCoding();
		ObservableList<TestVO> obsertestlist = FXCollections.observableArrayList(testlist);
		tableview.setItems(obsertestlist);
		testno.setCellValueFactory(new PropertyValueFactory<>("test_no"));
		testname.setCellValueFactory(new PropertyValueFactory<>("test_name"));
		testtype.setCellValueFactory(new PropertyValueFactory<>("test_type"));
		testexp.setCellValueFactory(new PropertyValueFactory<>("test_content"));
		
		AllTableData = obsertestlist;
		itemsForpage = 7;
		int totPageCount = AllTableData.size()%itemsForpage == 0? 
				AllTableData.size()/itemsForpage : 
					AllTableData.size()/itemsForpage + 1;
		paging.setPageCount(totPageCount);
		paging.setPageFactory(this::createPage);
	}
	
	private Node createPage(int pageIndex) {
		from = pageIndex * itemsForpage;
		to = from + itemsForpage -1;
		tableview.setItems(getTableViewData(from, to));
		return tableview;
	}
	
	private ObservableList<TestVO> getTableViewData(int from, int to){
		currentPageData = FXCollections.observableArrayList();
		int totSize = AllTableData.size();
		
		for(int i = from; i < to && i < totSize; i++) {
			currentPageData.add(AllTableData.get(i));
		}
		return currentPageData;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ClientConnect clientConn = new ClientConnect();
		conn=clientConn.getConnect();
		
		testno.setStyle("-fx-alignment:CENTER");
		testtype.setStyle("-fx-alignment:CENTER");
		
		tableview();
		ObservableList<String> combo = FXCollections.observableArrayList("코딩문제","그림문제");
		combolisttype.setItems(combo);
		
		
	}

}
