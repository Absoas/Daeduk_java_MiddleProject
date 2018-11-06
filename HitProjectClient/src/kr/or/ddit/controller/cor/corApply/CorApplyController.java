package kr.or.ddit.controller.cor.corApply;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Pagination;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.ComboBoxTreeTableCell;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import kr.or.ddit.common.SendExcel;
import kr.or.ddit.common.SendSms;
import kr.or.ddit.controller.login.LoginController;
import kr.or.ddit.corApply.CorApplyVO;
import kr.or.ddit.corApply.CorDetailVO;
import kr.or.ddit.cormem.CorMemberVO;
import kr.or.ddit.jobmem.JobMemberVO;
import kr.or.ddit.myintro.MyIntroVO;
import kr.or.ddit.rmi.client.ClientConnectFactory;
import kr.or.ddit.rmi.interf.IRemote;
import kr.or.ddit.test.TestVO;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

public class CorApplyController implements Initializable {

	@FXML
	private Pagination pagination;
	@FXML
	private JFXComboBox<String> comboBox;
	@FXML
	private JFXButton selBtn , excelBtn;
	@FXML
	private TreeTableView<CorApplyVO> treeTableView;
	@FXML
	private TreeTableColumn<CorApplyVO, String> tr_mem_name, tr_test_name, tr_test_no, tr_res_state;

	private IRemote conn = null;
	private CorMemberVO ssessionCMem;

	static Alert alertWarning = new Alert(AlertType.WARNING);

	private int from, to, itemsForPage;
	private ObservableList<CorApplyVO> AllTableData, currentPageData;

	String corId = null;

	List<CorApplyVO> corAppVO = null;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ssessionCMem = LoginController.ssessionCMem;
		
		excelBtn.setOnAction(event->{
			excel();
		});

		corId = ssessionCMem.getMem_id();
		conn = ClientConnectFactory.getClientConnect();

		tr_mem_name.setStyle("-fx-alignment:CENTER");
		tr_test_name.setStyle("-fx-alignment:CENTER");
		tr_test_no.setStyle("-fx-alignment:CENTER");
		tr_res_state.setStyle("-fx-alignment:CENTER");
		
		tr_mem_name.setCellValueFactory(new TreeItemPropertyValueFactory<CorApplyVO, String>("mem_name"));
		tr_test_name.setCellValueFactory(new TreeItemPropertyValueFactory<CorApplyVO, String>("test_name"));
		tr_test_no.setCellValueFactory(new TreeItemPropertyValueFactory<CorApplyVO, String>("test_no"));
		tr_res_state.setCellValueFactory(new TreeItemPropertyValueFactory<CorApplyVO, String>("res_state"));

		ObservableList<String> list = FXCollections.observableArrayList();

		list.add("합격");
		list.add("불합격");

		tr_res_state.setCellFactory(ComboBoxTreeTableCell.forTreeTableColumn(list));
		tr_res_state.setOnEditCommit(event -> {
			if (event.getRowValue().isLeaf()) {
				CorApplyVO vo = new CorApplyVO();

				vo.setCor_id(corId);
				vo.setRes_state(event.getNewValue());
				vo.setMem_name(event.getRowValue().getValue().getMem_name());
				conn.getICorApplyService().updateComboCorApply(vo);
			}

			if(event.getNewValue().equals("합격")) {
				try {
					SendSms.sendSms("01051925962", "합격하셨습니다.");
				} catch (CoolsmsException e) {
					e.printStackTrace();
				}
			}else {
				try {
					SendSms.sendSms("01051925962", "불합격하셨습니다.");
				} catch (CoolsmsException e) {
					e.printStackTrace();
				}
			}
			
		});

		ObservableList<String> combo = FXCollections.observableArrayList();
		combo.add("전 체");
		combo.add("합격");
		combo.add("불합격");
		combo.add("심사중");

		comboBox.setItems(combo);
		comboBox.setPromptText("검색조건");

		selBtn.setOnAction(event -> {
			String selCombo = comboBox.getSelectionModel().getSelectedItem();

			if (selCombo == null || selCombo.isEmpty()) {
				showAlertWarning("공백", "콤보박스를 선택해주세요.");
				return;
			} else if (selCombo.equals("전 체")) {
				selectAll();
				return;
			} else if (selCombo.equals("합격")) {
				selectCombo(selCombo);
				return;
			} else if (selCombo.equals("불합격")) {
				selectCombo(selCombo);
				return;
			} else if (selCombo.equals("심사중")) {
				selectCombo(selCombo);
				return;
			}

		});

	}
	
	private void excel() {
		List<CorDetailVO> list = conn.getICorApplyService().selectDetilList();
		SendExcel sendExcel = new SendExcel();
		sendExcel.xlsxWiter(list);
		
	}

	public static JobMemberVO selJobMem;
	public static TestVO testInfo;
	
	@FXML
	private void selJMemInfo(ActionEvent event) {
		CorApplyVO corAppvo= treeTableView.getSelectionModel().getSelectedItem().getValue();
	
		selJobMem = conn.getIJobMemSerivce().selectApplyMember(corAppvo.getMem_name());
		testInfo =  conn.getITestService().selectTestInfo(corAppvo.getTest_no());
		
		Parent root = null;
		try {
			Stage primaryStage = (Stage) ((Node) pagination).getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("../applyinfo/ApplyInfo.fxml"));
			Stage addIntroStage = new Stage();
			Scene scene = new Scene(root);
			addIntroStage.setTitle("회원정보");
			addIntroStage.setScene(scene);
			addIntroStage.initOwner(primaryStage);
			addIntroStage.initModality(Modality.APPLICATION_MODAL);
			addIntroStage.showAndWait();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void selectCombo(String selCombo) {
		CorApplyVO corAppVO = new CorApplyVO();

		if (selCombo.equals("합격")) {
			corAppVO.setCor_id(corId);
			corAppVO.setRes_state("합격");
			this.corAppVO = conn.getICorApplyService().selectComboCorApply(corAppVO);
		

		} else if (selCombo.equals("불합격")) {
			corAppVO.setCor_id(corId);
			corAppVO.setRes_state("불합격");
			this.corAppVO = conn.getICorApplyService().selectComboCorApply(corAppVO);

		

		} else if (selCombo.equals("심사중")) {
			corAppVO.setCor_id(corId);
			corAppVO.setRes_state("심사중");
			this.corAppVO = conn.getICorApplyService().selectComboCorApply(corAppVO);

		}
		
		TreeItem<CorApplyVO> rootNode = new TreeItem<CorApplyVO>();
		ObservableList<CorApplyVO> obsList = FXCollections.observableArrayList(this.corAppVO);

		for (int i = 0; i < obsList.size(); i++) {
			TreeItem<CorApplyVO> node = new TreeItem<CorApplyVO>(this.corAppVO.get(i));
			rootNode.getChildren().add(node);
		}

		AllTableData = obsList;
		itemsForPage = 7;
		int toPageCount = AllTableData.size() % itemsForPage == 0 ? AllTableData.size() / itemsForPage
				: AllTableData.size() / itemsForPage + 1;
		pagination.setPageCount(toPageCount);

		pagination.setPageFactory(this::createPage);

	}

	private void selectAll() {

		TreeItem<CorApplyVO> rootNode = new TreeItem<CorApplyVO>();
		corAppVO = conn.getICorApplyService().selectCorApplyInfo(corId);

		ObservableList<CorApplyVO> obsList = FXCollections.observableArrayList(corAppVO);

		for (int i = 0; i < obsList.size(); i++) {
			TreeItem<CorApplyVO> node = new TreeItem<CorApplyVO>(corAppVO.get(i));
			rootNode.getChildren().add(node);
		}

		AllTableData = obsList;
		itemsForPage = 7;
		int toPageCount = AllTableData.size() % itemsForPage == 0 ? AllTableData.size() / itemsForPage
				: AllTableData.size() / itemsForPage + 1;
		pagination.setPageCount(toPageCount);

		pagination.setPageFactory(this::createPage);

	}

	private Node createPage(int pageIndex) {
		from = pageIndex * itemsForPage;
		to = from + itemsForPage;

		TreeItem<CorApplyVO> rootNode = new TreeItem<CorApplyVO>();

		ObservableList<CorApplyVO> list = getTableViewData(from, to);

		for (int i = 0; i < list.size(); i++) {
			TreeItem<CorApplyVO> node = new TreeItem<CorApplyVO>(list.get(i));
			rootNode.getChildren().add(node);
		}

		treeTableView.setRoot(rootNode);
		treeTableView.setVisible(true);
		treeTableView.setShowRoot(false);
		treeTableView.setEditable(true);

		return treeTableView;
	}

	private ObservableList<CorApplyVO> getTableViewData(int from, int to) {
		currentPageData = FXCollections.observableArrayList();
		int toSize = AllTableData.size();

		for (int i = from; i < to && i < toSize; i++) {
			currentPageData.add(AllTableData.get(i));
		}

		return currentPageData;
	}

	public static void showAlertWarning(String headerText, String contentText) { // 기존에 사용하고있는 warning ex) 실패하였을때 사용
		alertWarning.setHeaderText(headerText);
		alertWarning.setContentText(contentText);
		alertWarning.show();
	}

}
