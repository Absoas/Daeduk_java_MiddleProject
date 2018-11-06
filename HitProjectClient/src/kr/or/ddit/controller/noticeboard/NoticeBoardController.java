package kr.or.ddit.controller.noticeboard;

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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import kr.or.ddit.controller.login.LoginController;
import kr.or.ddit.noticeboard.NoticeBoardVO;
import kr.or.ddit.rmi.client.ClientConnect;
import kr.or.ddit.rmi.interf.IRemote;

public class NoticeBoardController implements Initializable{
	
	@FXML
	private TableView<NoticeBoardVO> notiboard_tableView;
	
	@FXML
	private TableColumn<NoticeBoardVO, String> noti_board_no, noti_board_title, noti_board_content, noti_board_date, noti_board_lookup, noti_board_writer;

	@FXML
	private Button noti_board_insertbtn, noti_board_refresh;
	
	@FXML
	private Pagination noti_board_pagination;
	
	public static IRemote conn = null;
	
	private int from, to, itemsForPage;
	private ObservableList<NoticeBoardVO> AllTableData, currentPageData;
	
	static NoticeBoardVO notiVO;
	Alert alert = new Alert(AlertType.WARNING);
	

	@FXML
	public void select(ActionEvent event) throws IOException{
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("InsertBoard.fxml"));
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
		ObservableList<NoticeBoardVO> list = null;
		List<NoticeBoardVO> data = conn.getINotiService().getAllBoard();
		list = FXCollections.observableArrayList(data);
		/*List<NoticeBoardVO> list = conn.getINotiService().getAllBoard();
		ObservableList<NoticeBoardVO> noticelist = FXCollections.observableArrayList(list);*/
		
//		notiboard_tableView.setItems(list);
		noti_board_date.setStyle("-fx-alignment:CENTER");
		noti_board_lookup.setStyle("-fx-alignment:CENTER");
		noti_board_writer.setStyle("-fx-alignment:CENTER");
		noti_board_no.setStyle("-fx-alignment:CENTER");
		
		noti_board_no.setCellValueFactory(new PropertyValueFactory<>("noti_board_no"));
		noti_board_title.setCellValueFactory(new PropertyValueFactory<>("noti_board_title"));
		noti_board_date.setCellValueFactory(new PropertyValueFactory<>("noti_board_date"));
		noti_board_lookup.setCellValueFactory(new PropertyValueFactory<>("noti_board_lookup"));
		noti_board_writer.setCellValueFactory(new PropertyValueFactory<>("noti_board_writer"));
		
		AllTableData = list;
		itemsForPage = 10;
		int toPageCount = AllTableData.size()%itemsForPage == 0 ?
				AllTableData.size()/itemsForPage :
					AllTableData.size()/itemsForPage + 1;
				noti_board_pagination.setPageCount(toPageCount);
				noti_board_pagination.setPageFactory(this::createPage);
				
	}
	
	private Node createPage(int pageIndex) {
		
		from = pageIndex * itemsForPage;
		to = from + itemsForPage -1;
		
		notiboard_tableView.setItems(getTableViewData(from,to));
		
		return notiboard_tableView;
	}
	
	private ObservableList<NoticeBoardVO> getTableViewData(int from, int to) {
		currentPageData = FXCollections.observableArrayList();
		int toSize = AllTableData.size();
		
		for(int i=from; i < to && i < toSize ; i++) {
			currentPageData.add(AllTableData.get(i));
		}
		
		return currentPageData;
	}



	private void selectDetail() {
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("NoticeBoardSelect.fxml"));
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
	
	private void NullCheck(String header, String content) {
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.show();
		return;
	}
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if (LoginController.ssessionJMem!=null) {
			noti_board_insertbtn.setVisible(false);
		}
		
		if(LoginController.ssessionCMem!=null) {
			noti_board_insertbtn.setVisible(false);
		}
		
		ClientConnect clientConn = new ClientConnect();
		conn = clientConn.getConnect();
		start();
		
		notiboard_tableView.setOnMouseClicked(event->{
			if(notiboard_tableView.getSelectionModel().isEmpty()) {
				NullCheck("공백입니다.","똑바로 눌러주세요");
				return;
			}
			notiVO = new NoticeBoardVO();
			notiVO = notiboard_tableView.getSelectionModel().getSelectedItem();
			int lookup = notiVO.getNoti_board_lookup()+1;
			
			NoticeBoardVO lookboard = new NoticeBoardVO();
			
			lookboard.setNoti_board_no(notiVO.getNoti_board_no());
			lookboard.setNoti_board_lookup(lookup);
			
			conn.getINotiService().lookBoard(lookboard);
			
			
			notiVO.setNoti_board_no(notiboard_tableView.getSelectionModel().getSelectedItem().getNoti_board_no());
			notiVO.setNoti_board_title(notiboard_tableView.getSelectionModel().getSelectedItem().getNoti_board_title());
			notiVO.setNoti_board_content(notiboard_tableView.getSelectionModel().getSelectedItem().getNoti_board_content());
			notiVO.setNoti_board_date(notiboard_tableView.getSelectionModel().getSelectedItem().getNoti_board_date());
			notiVO.setNoti_board_lookup(notiboard_tableView.getSelectionModel().getSelectedItem().getNoti_board_lookup());
			notiVO.setNoti_board_writer(notiboard_tableView.getSelectionModel().getSelectedItem().getNoti_board_writer());
			
			selectDetail();
			
		});
	}
}
