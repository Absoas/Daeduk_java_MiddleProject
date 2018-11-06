package kr.or.ddit.controller.reviewboard;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import kr.or.ddit.qnaboard.QnaBoardVO;
import kr.or.ddit.revboard.RevBoardVO;
import kr.or.ddit.rmi.client.ClientConnect;
import kr.or.ddit.rmi.interf.IRemote;

public class RevBoardController implements Initializable {

	@FXML
	private TableView<RevBoardVO> NoticeTableView;

	@FXML
	private TableColumn<RevBoardVO, String> BoardTitle, BoardWriter, BoardDate;

	@FXML
	private TableColumn<RevBoardVO, Number> BoardNo, BoardViews;
	
	@FXML
	private Pagination paging;

	@FXML
	private Button btnClick;

	public static RevBoardVO selBoard; //Insert�� ���� ���콺 �̺�Ʈ�� Ŭ���� �ؼ�
								   // ���ο� ȭ�鿡 ���� �ҷ��� �� �ִ� ��������
	
	public static IRemote conn = null;
	
	private int from, to, itemsForPage;
	
	private ObservableList<RevBoardVO> AllTableData, currentPageData;

	@FXML
	private void insertBoard() {
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("reviewdetailBoard.fxml"));
			Stage addPost = new Stage();
			Scene scene = new Scene(root);
			addPost.setTitle("후기게시판");
			addPost.setScene(scene);
			addPost.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
		setTable();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ClientConnect clientConn= new ClientConnect();
		conn=clientConn.getConnect();
		
		setTable();
	}

	
	private void setTable() {
		List<RevBoardVO> list = conn.getIBoardService().selectAll();
		ObservableList<RevBoardVO> data = FXCollections.observableArrayList(list);
		
		NoticeTableView.setItems(data);
		
		BoardNo.setStyle("-fx-alignment:CENTER");
		BoardWriter.setStyle("-fx-alignment:CENTER");
		BoardDate.setStyle("-fx-alignment:CENTER");
		BoardViews.setStyle("-fx-alignment:CENTER");
		
		BoardNo.setCellValueFactory(new PropertyValueFactory<>("rev_board_no"));
		BoardTitle.setCellValueFactory(new PropertyValueFactory<>("rev_board_title"));
		BoardWriter.setCellValueFactory(new PropertyValueFactory<>("rev_board_writer"));
		BoardDate.setCellValueFactory(new PropertyValueFactory<>("rev_board_date"));
		BoardViews.setCellValueFactory(new PropertyValueFactory<>("rev_board_views"));
		
		AllTableData = data;
		
		itemsForPage = 14; // 한페이지 보여줄 항목 수 설정
		int totPageCount = AllTableData.size()%itemsForPage == 0 ?
				AllTableData.size()/itemsForPage :
				AllTableData.size()/itemsForPage + 1;
		paging.setPageCount(totPageCount); // 전체 페이지 수 설정
		
		paging.setPageFactory(this::createPage);
		
	}
	
	private ObservableList<RevBoardVO> getTableViewData(int from, int to) {
		currentPageData = FXCollections.observableArrayList(); // 현재페이지 데이터 초기화
		int totSize = AllTableData.size();

		for (int i = from; i <= to && i < totSize; i++) {
			currentPageData.add(AllTableData.get(i));
		}

		return currentPageData;

	}

	private Node createPage(int pageIndex) {

		from = pageIndex * itemsForPage;
		to = from + itemsForPage - 1;
		NoticeTableView.setItems(getTableViewData(from, to));

		return NoticeTableView;
	}

	@FXML
	private void clickTable(MouseEvent event) { // �Ű������� ���콺�̺�Ʈ Ÿ���� �̺�Ʈ
		if (!NoticeTableView.getSelectionModel().isEmpty()) {
			selBoard = NoticeTableView.getSelectionModel().getSelectedItem(); // NoticeBoardVO Ÿ���� ������
			RevBoardVO revBoardVO = new RevBoardVO();
			revBoardVO.setRev_board_no(selBoard.getRev_board_no());
			revBoardVO.setRev_board_views(selBoard.getRev_board_views()+1);
			conn.getIBoardService().clickBoard(revBoardVO);
			insertBoard();
			selBoard = null;
		}
	}
}
