package kr.or.ddit.controller.admin.manage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.ComboBoxTreeTableCell;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import kr.or.ddit.cormem.CorMemberVO;
import kr.or.ddit.jobmem.JobMemberVO;
import kr.or.ddit.rmi.client.ClientConnect;
import kr.or.ddit.rmi.interf.IRemote;

public class JobMemberController implements Initializable {
	@FXML
	private TreeTableColumn<JobMemberVO, String> tr_mem_pass;

	@FXML
	private TreeTableColumn<JobMemberVO, String> tr_mem_type;

	@FXML
	private TreeTableColumn<JobMemberVO, String> tr_jmem_state;

	@FXML
	private TreeTableColumn<JobMemberVO, String> tr_jmem_regno;

	@FXML
	private TreeTableColumn<JobMemberVO, String> tr_mem_name;

	@FXML
	private TreeTableColumn<JobMemberVO, String> tr_jmem_zip;

	@FXML
	private TreeTableColumn<JobMemberVO, String> tr_mem_id;

	@FXML
	private TreeTableColumn<JobMemberVO, String> tr_mem_mail;

	@FXML
	private TreeTableView<JobMemberVO> treeTableView;

	@FXML
	private TreeTableColumn<JobMemberVO, String> tr_mem_image;

	@FXML
	private TreeTableColumn<JobMemberVO, String> tr_jmem_addr;
	
	@FXML
	private JFXButton delBtn, selBtn;
	
	@FXML
	private Pagination pagination;
	
	@FXML
	private JFXComboBox<String> comboBox;
	
	@FXML
	private JFXTextField textField;

	public static IRemote conn = null;
	
	static Alert alertWarning = new Alert(AlertType.WARNING);
	
	private int from, to, itemsForPage;
	private ObservableList<JobMemberVO> AllTableData, currentPageData;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ClientConnect clientConn = new ClientConnect();
		conn=clientConn.getConnect(); // ������ ����
		
		tr_mem_pass.setStyle("-fx-alignment:CENTER");
		tr_mem_type.setStyle("-fx-alignment:CENTER");
		tr_jmem_state.setStyle("-fx-alignment:CENTER");
		tr_jmem_regno.setStyle("-fx-alignment:CENTER");
		tr_mem_name.setStyle("-fx-alignment:CENTER");
		tr_jmem_zip.setStyle("-fx-alignment:CENTER");
		tr_mem_id.setStyle("-fx-alignment:CENTER");
		tr_mem_mail.setStyle("-fx-alignment:CENTER");
		tr_mem_image.setStyle("-fx-alignment:CENTER");
		tr_jmem_addr.setStyle("-fx-alignment:CENTER");
		
		start();	
		
		ObservableList<String> combo = FXCollections.observableArrayList();
		combo.add("ALL");
		combo.add("I D");
		combo.add("STATE");
		combo.add("ADDR");
		
		comboBox.setItems(combo);
		comboBox.setPromptText(combo.get(0));
		
		changeCombo();
		
		selBtn.setOnAction(event -> {
			String txt = textField.getText();
			String selCombo = comboBox.getSelectionModel().getSelectedItem();
			
			if(selCombo == null) {
				showAlertWarning("공백", "체크박스를 선택해주세요");
				return;
			} else if(selCombo.equals("ALL")) {
				start();
				return;
			} else if (txt.isEmpty() || txt == null) {
				showAlertWarning("공백", "입력 후에 검색 버튼을 눌러주세요.");
				return;
			} else if(selCombo.equals("I D")){ //id
				selectId();
			} else if(selCombo.equals("STATE")){
				selectState();
			} else if(selCombo.equals("ADDR")) {
				selectAddr();
			}
			
		});
		
		treeTableView.setOnMouseClicked(event -> {
			if(treeTableView.getSelectionModel().isEmpty()) {
				showAlertWarning("공백", "다시 선택해주세요.");
				return;
			}
			delBtn.setOnAction(event2 -> {
				delete();
				start();
			});
		});
		
		ObservableList<String> list = FXCollections.observableArrayList();
		list.add("T");
		list.add("F");
		
		tr_jmem_state.setCellFactory(ComboBoxTreeTableCell.forTreeTableColumn(list));
		
		tr_jmem_state.setOnEditCommit(event -> {
			if(event.getRowValue().isLeaf()) {
				
				JobMemberVO jMemVO = new JobMemberVO();
				jMemVO.setMem_id(event.getRowValue().getValue().getMem_id());
				jMemVO.setJmem_state(event.getNewValue());

				conn.getIJobMemSerivce().updateJobMember(jMemVO);
				
				return;
			}
			start();
		});
	}

	private void selectAddr() {
		String jAddr = textField.getText().trim();
		
		TreeItem<JobMemberVO> rootNode = new TreeItem<JobMemberVO>();
		List<JobMemberVO> list = conn.getIJobMemSerivce().selectJobConAddr(jAddr);
		ObservableList<JobMemberVO> obsList = FXCollections.observableArrayList(list);
		
		AllTableData = obsList;
		itemsForPage = 7;
		int toPageCount = AllTableData.size()%itemsForPage == 0 ?
				AllTableData.size()/itemsForPage :
					AllTableData.size()/itemsForPage+1;
		pagination.setPageCount(toPageCount);
				
		pagination.setPageFactory(this::createPage);
		
		
		
	}

	private void selectState() {
		String jState = textField.getText().trim();
		
		TreeItem<JobMemberVO> rootNode = new TreeItem<JobMemberVO>();
		List<JobMemberVO> list;
		
		if(jState.equals("T")) {
			list = conn.getIJobMemSerivce().selectJobConSta(jState);
		} else if(jState.equals("F")) {
			list = conn.getIJobMemSerivce().selectJobConSta(jState);
		} else {
			showAlertWarning("T / F", "둘 중에 하나만 입력할 수 있습니다. 대소문자를 확인해주세요");
			return;
		}
		
		ObservableList<JobMemberVO> obsList = FXCollections.observableArrayList(list);
		
		AllTableData = obsList;
		itemsForPage = 7;
		int toPageCount = AllTableData.size()%itemsForPage == 0 ?
				AllTableData.size()/itemsForPage :
					AllTableData.size()/itemsForPage+1;
		
		pagination.setPageCount(toPageCount);
				
		pagination.setPageFactory(this::createPage);
		
	}

	private void selectId() {
		String memId = textField.getText().trim();
		
		TreeItem<JobMemberVO> rootNode = new TreeItem<JobMemberVO>();
		List<JobMemberVO> list = conn.getIJobMemSerivce().selectJobConId(memId);
		ObservableList<JobMemberVO> obsList = FXCollections.observableArrayList(list);
		
		
		AllTableData = obsList;
		itemsForPage = 7;
		int toPageCount = AllTableData.size()%itemsForPage == 0 ?
				AllTableData.size()/itemsForPage :
					AllTableData.size()/itemsForPage+1;
		pagination.setPageCount(toPageCount);
				
		pagination.setPageFactory(this::createPage);
		
		
	}

	private void changeCombo() {
		comboBox.valueProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if(newValue.equals("ALL")) {
					textField.setText("");
					textField.setEditable(false);
				}else {
					textField.setText("");
					textField.setEditable(true);
				}
			}
		});
		
	}

	private void delete() {
		String memId = treeTableView.getSelectionModel().getSelectedItem().getValue().getMem_id();
		conn.getIJobMemSerivce().deleteJobMember(memId);
	}

	private void start() {
		TreeItem<JobMemberVO> rootNode = new TreeItem<JobMemberVO>();
		List<JobMemberVO> list = conn.getIJobMemSerivce().selectJobMemberInfo();
		ObservableList<JobMemberVO> obsList = FXCollections.observableArrayList(list);
		
		for(int i = 0 ; i < obsList.size(); i++) {
			if(list.get(i).getMem_type().trim().equals("1")) {
				list.get(i).setMem_type("구직 회원");
			}
			TreeItem<JobMemberVO> node = new TreeItem<JobMemberVO>(list.get(i));
			rootNode.getChildren().add(node);
		}
		
		tr_mem_id.setCellValueFactory(new TreeItemPropertyValueFactory<JobMemberVO, String>("mem_id"));
		tr_mem_pass.setCellValueFactory(new TreeItemPropertyValueFactory<JobMemberVO, String>("mem_pass"));
		tr_mem_name.setCellValueFactory(new TreeItemPropertyValueFactory<JobMemberVO, String>("mem_name"));
		tr_mem_mail.setCellValueFactory(new TreeItemPropertyValueFactory<JobMemberVO, String>("mem_mail"));
		tr_mem_type.setCellValueFactory(new TreeItemPropertyValueFactory<JobMemberVO, String>("mem_type"));
		tr_mem_image.setCellValueFactory(new TreeItemPropertyValueFactory<JobMemberVO, String>("mem_image"));
		tr_jmem_zip.setCellValueFactory(new TreeItemPropertyValueFactory<JobMemberVO, String>("jmem_zip"));
		tr_jmem_addr.setCellValueFactory(new TreeItemPropertyValueFactory<JobMemberVO, String>("jmem_addr"));
		tr_jmem_regno.setCellValueFactory(new TreeItemPropertyValueFactory<JobMemberVO, String>("jmem_regno"));
		tr_jmem_state.setCellValueFactory(new TreeItemPropertyValueFactory<JobMemberVO, String>("jmem_state"));
		
		
		AllTableData = obsList;
		itemsForPage = 7;
		int toPageCount = AllTableData.size()%itemsForPage == 0 ?
				AllTableData.size()/itemsForPage :
					AllTableData.size()/itemsForPage+1;
		pagination.setPageCount(toPageCount);
				
		pagination.setPageFactory(this::createPage);
		
	}
	
	private Node createPage(int pageIndex) {
		from = pageIndex * itemsForPage;
		to = from + itemsForPage;
		
		TreeItem<JobMemberVO> rootNode = new TreeItem<JobMemberVO>();
		ObservableList<JobMemberVO> list = getTableViewData(from, to);
		
		for(int i = 0 ; i < list.size(); i++) {
			TreeItem<JobMemberVO> node = new TreeItem<JobMemberVO>(list.get(i));
			rootNode.getChildren().add(node);
		}
		
		treeTableView.setRoot(rootNode);
		treeTableView.setVisible(true);
		treeTableView.setShowRoot(false);
		treeTableView.setEditable(true);
		
		return treeTableView;
	}
	
	private ObservableList<JobMemberVO> getTableViewData(int from, int to){
		currentPageData = FXCollections.observableArrayList();
		int toSize = AllTableData.size();
		
		for(int i=from; i < to && i < toSize ; i++) {
			currentPageData.add(AllTableData.get(i));
		}
		
		return currentPageData;
	}
	
	public static void showAlertWarning(String headerText, String contentText) { //기존에 사용하고있는 warning  ex) 실패하였을때 사용
		alertWarning.setHeaderText(headerText);
		alertWarning.setContentText(contentText);
		alertWarning.show();
	}

}
