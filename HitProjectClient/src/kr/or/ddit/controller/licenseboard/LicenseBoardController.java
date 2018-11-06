package kr.or.ddit.controller.licenseboard;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

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
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import kr.or.ddit.controller.login.LoginController;
import kr.or.ddit.licenseboard.LicenseBoardVO;
import kr.or.ddit.rmi.client.ClientConnect;
import kr.or.ddit.rmi.interf.IRemote;

public class LicenseBoardController implements Initializable {

	@FXML
	private TableView<LicenseBoardVO> license_tableView;

	@FXML
	private TableColumn<LicenseBoardVO, String> lic_code, lic_jugwan, lic_class, lic_name;

	@FXML
	private Button lic_board_insertbtn;

	@FXML
	private Pagination lic_board_paging;

	public static IRemote conn = null;

	private int from, to, itemsForPage;
	private ObservableList<LicenseBoardVO> AllTableData, currentPageData;

	static LicenseBoardVO licVO;

	@FXML
	public void insertbtn(ActionEvent event) throws IOException {
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("InsertLicense_Board.fxml"));
			Stage addPost = new Stage();
			Scene scene = new Scene(root);
			addPost.setTitle("선택");
			addPost.setScene(scene);
			addPost.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
		start();
	}

	public void start() {
		ObservableList<LicenseBoardVO> list = null;
		List<LicenseBoardVO> data = conn.getILicenseService().getAllBoard();
		list = FXCollections.observableArrayList(data);
		
		lic_class.setStyle("-fx-alignment:CENTER");
		lic_jugwan.setStyle("-fx-alignment:CENTER");
		lic_code.setStyle("-fx-alignment:CENTER");
		
		lic_code.setCellValueFactory(new PropertyValueFactory<>("lic_code"));
		lic_name.setCellValueFactory(new PropertyValueFactory<>("lic_name"));
		lic_class.setCellValueFactory(new PropertyValueFactory<>("lic_class"));
		lic_jugwan.setCellValueFactory(new PropertyValueFactory<>("lic_jugwan"));
		
		
		
		AllTableData = list;
		itemsForPage = 10;
		int toPageCount = AllTableData.size() % itemsForPage == 0 ? AllTableData.size() / itemsForPage
				: AllTableData.size() / itemsForPage + 1;
		lic_board_paging.setPageCount(toPageCount);

		lic_board_paging.setPageFactory(this::createPage);
	}

	private Node createPage(int pageIndex) {

		from = pageIndex * itemsForPage;
		to = from + itemsForPage - 1;

		license_tableView.setItems(getTableViewData(from, to));

		return license_tableView;
	}

	private ObservableList<LicenseBoardVO> getTableViewData(int from, int to) {
		currentPageData = FXCollections.observableArrayList();
		int toSize = AllTableData.size();

		for (int i = from; i < to && i < toSize; i++) {
			currentPageData.add(AllTableData.get(i));
		}

		return currentPageData;

	}

	private void selectDetail() {
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("selectLicense_Board.fxml"));
			Stage addPost = new Stage();
			Scene scene = new Scene(root);
			addPost.setTitle("선택");
			addPost.setScene(scene);
			addPost.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
		start();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if (LoginController.ssessionJMem!=null) {
			lic_board_insertbtn.setVisible(false);
		}
		if (LoginController.ssessionCMem!=null) {
			lic_board_insertbtn.setVisible(false);
		}
		ClientConnect clientConn = new ClientConnect();
		conn = clientConn.getConnect();
		start();
		
		license_tableView.setOnMouseClicked(event->{
			
			if(license_tableView.getSelectionModel().isEmpty()) {
				return;
			}
			licVO = new LicenseBoardVO();
			
			licVO.setLic_code(license_tableView.getSelectionModel().getSelectedItem().getLic_code());
			licVO.setLic_name(license_tableView.getSelectionModel().getSelectedItem().getLic_name());
			licVO.setLic_class(license_tableView.getSelectionModel().getSelectedItem().getLic_class());
			licVO.setLic_jugwan(license_tableView.getSelectionModel().getSelectedItem().getLic_jugwan());
			
			selectDetail();
		});
	}
}
