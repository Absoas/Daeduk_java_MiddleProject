package kr.or.ddit.controller.admin.manage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.Pagination;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.ComboBoxTreeTableCell;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import kr.or.ddit.cormem.CorMemberVO;
import kr.or.ddit.rmi.client.ClientConnect;
import kr.or.ddit.rmi.interf.IRemote;

public class CorMemberController implements Initializable{
	
	@FXML
    private TreeTableColumn<CorMemberVO, String> tr_cor_post;

    @FXML
    private TreeTableColumn<CorMemberVO, String> tr_cor_no;

    @FXML
    private TreeTableColumn<CorMemberVO, String> tr_mem_pass;

    @FXML
    private TreeTableColumn<CorMemberVO, String> tr_mem_type;

    @FXML
    private TreeTableColumn<CorMemberVO, String> tr_cor_ceo;

    @FXML
    private TreeTableColumn<CorMemberVO, String> tr_mem_name;

    @FXML
    private TreeTableView<CorMemberVO> treeTableView;

    @FXML
    private TreeTableColumn<CorMemberVO, String> tr_cor_tel;

    @FXML
    private TreeTableColumn<CorMemberVO, String> tr_cor_addr;

    @FXML
    private TreeTableColumn<CorMemberVO, String> tr_mem_id;

    @FXML
    private TreeTableColumn<CorMemberVO, String> tr_mem_mail;

    @FXML
    private TreeTableColumn<CorMemberVO, String> tr_cor_name;

    @FXML
    private TreeTableColumn<CorMemberVO, String> tr_mem_image;
	
    @FXML
    private TreeTableColumn<CorMemberVO, String> tr_cor_state;
    
    @FXML
    private Button delBtn, selBtn;
    
    @FXML
    private Pagination pagination;
    
    @FXML
    private JFXComboBox<String> comboBox;
    
    @FXML
    private JFXTextField textField;
    
    public static IRemote conn = null;
    
    static Alert alertWarning = new Alert(AlertType.WARNING);
    
    private int from, to, itemsForPage;
    private ObservableList<CorMemberVO> AllTableData, currentPageData;
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ClientConnect clientConn = new ClientConnect();
		conn=clientConn.getConnect();
		
		tr_cor_post.setStyle("-fx-alignment:CENTER");
		tr_mem_pass.setStyle("-fx-alignment:CENTER");
		tr_mem_type.setStyle("-fx-alignment:CENTER");
		tr_cor_ceo.setStyle("-fx-alignment:CENTER");
		tr_mem_name.setStyle("-fx-alignment:CENTER");
		tr_cor_tel.setStyle("-fx-alignment:CENTER");
		tr_cor_addr.setStyle("-fx-alignment:CENTER");
		tr_mem_id.setStyle("-fx-alignment:CENTER");
		tr_mem_mail.setStyle("-fx-alignment:CENTER");
		tr_cor_name.setStyle("-fx-alignment:CENTER");
		tr_mem_image.setStyle("-fx-alignment:CENTER");
		tr_cor_state.setStyle("-fx-alignment:CENTER");
		tr_cor_no.setStyle("-fx-alignment:CENTER");
		
		start();
		ObservableList<String> combo = FXCollections.observableArrayList();
		combo.add("ALL");
		combo.add("I D");
		combo.add("STATE");
		
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
			}
		});
		
		
		treeTableView.setOnMouseClicked(event -> {
			if(treeTableView.getSelectionModel().isEmpty()) {
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
		
		tr_cor_state.setCellFactory(ComboBoxTreeTableCell.forTreeTableColumn(list));
		
		tr_cor_state.setOnEditCommit(event3 -> {
			if(event3.getRowValue().isLeaf()) {
				CorMemberVO corMemVO = new CorMemberVO();

				corMemVO.setMem_id(event3.getRowValue().getValue().getMem_id());
				corMemVO.setCor_state(event3.getNewValue());
				
				conn.getICorMemberService().updateCorMember(corMemVO);
				
				return;
			}
			start();
		});
		
	}

	private void selectState() {
		String cState = textField.getText().trim();
		
		TreeItem<CorMemberVO> rootNode = new TreeItem<CorMemberVO>();
		List<CorMemberVO> list;
		
		if(cState.equals("T")) {
			list = conn.getICorMemberService().selectCorConSta(cState);
		} else if(cState.equals("F")) {
			list = conn.getICorMemberService().selectCorConSta(cState);
		} else {
			showAlertWarning("T / F", "둘 중에 하나만 입력할 수 있습니다. 대소문자를 확인해주세요");
			return;
		}
		
		ObservableList<CorMemberVO> obsList = FXCollections.observableArrayList(list);
		
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
		
		TreeItem<CorMemberVO> rootNode = new TreeItem<CorMemberVO>();
		List<CorMemberVO> list = conn.getICorMemberService().selectCorConId(memId);
		ObservableList<CorMemberVO> obsList = FXCollections.observableArrayList(list);

		AllTableData = obsList;
		itemsForPage = 7;
		int toPageCount = AllTableData.size()%itemsForPage == 0 ?
				AllTableData.size()/itemsForPage :
					AllTableData.size()/itemsForPage+1;
		pagination.setPageCount(toPageCount);
				
		pagination.setPageFactory(this::createPage);
		
		
	}

	private void delete() {
		String memId = treeTableView.getSelectionModel().getSelectedItem().getValue().getMem_id();
		conn.getICorMemberService().deleteCorMember(memId);
	}

	private void start() {
		TreeItem<CorMemberVO> rootNode = new TreeItem<CorMemberVO>();
		List<CorMemberVO> list = conn.getICorMemberService().selectCorMemberInfo();
		ObservableList<CorMemberVO> obsList = FXCollections.observableArrayList(list);
		
		for(int i = 0; i < obsList.size() ; i++) {
			if(list.get(i).getMem_type().trim().equals("2")) {
				list.get(i).setMem_type("구인 기업");
			}
			TreeItem<CorMemberVO> node = new TreeItem<CorMemberVO>(list.get(i));
			rootNode.getChildren().add(node);
		}
		
		tr_mem_id.setCellValueFactory(new TreeItemPropertyValueFactory<CorMemberVO, String>("mem_id"));
		tr_mem_pass.setCellValueFactory(new TreeItemPropertyValueFactory<CorMemberVO, String>("mem_pass"));
		tr_mem_name.setCellValueFactory(new TreeItemPropertyValueFactory<CorMemberVO, String>("mem_name"));
		tr_mem_mail.setCellValueFactory(new TreeItemPropertyValueFactory<CorMemberVO, String>("mem_mail"));
		tr_mem_type.setCellValueFactory(new TreeItemPropertyValueFactory<CorMemberVO, String>("mem_type"));
		tr_mem_image.setCellValueFactory(new TreeItemPropertyValueFactory<CorMemberVO, String>("mem_image"));
		tr_cor_state.setCellValueFactory(new TreeItemPropertyValueFactory<CorMemberVO, String>("cor_state"));
		tr_cor_no.setCellValueFactory(new TreeItemPropertyValueFactory<CorMemberVO, String>("cor_no"));
		tr_cor_name.setCellValueFactory(new TreeItemPropertyValueFactory<CorMemberVO, String>("cor_name"));
		tr_cor_ceo.setCellValueFactory(new TreeItemPropertyValueFactory<CorMemberVO, String>("cor_ceo"));
		tr_cor_tel.setCellValueFactory(new TreeItemPropertyValueFactory<CorMemberVO, String>("cor_tel"));
		tr_cor_post.setCellValueFactory(new TreeItemPropertyValueFactory<CorMemberVO, String>("cor_post"));
		tr_cor_addr.setCellValueFactory(new TreeItemPropertyValueFactory<CorMemberVO, String>("cor_addr"));
		
				
		
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
	
	private Node createPage(int pageIndex) {
		from = pageIndex * itemsForPage;
		to = from + itemsForPage;
		
		TreeItem<CorMemberVO> rootNode = new TreeItem<CorMemberVO>();
		
		ObservableList<CorMemberVO> list =  getTableViewData(from, to);
		

		for(int i = 0; i < list.size() ; i++) {
			TreeItem<CorMemberVO> node = new TreeItem<CorMemberVO>(list.get(i));
			rootNode.getChildren().add(node);
		}
		
		treeTableView.setRoot(rootNode);
		treeTableView.setVisible(true);
		treeTableView.setShowRoot(false);
		treeTableView.setEditable(true);
		
		
		return treeTableView;
	}
	
	private ObservableList<CorMemberVO> getTableViewData(int from, int to) {
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
