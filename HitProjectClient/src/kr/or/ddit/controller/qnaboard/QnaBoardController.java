package kr.or.ddit.controller.qnaboard;

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
import kr.or.ddit.rmi.client.ClientConnect;
import kr.or.ddit.rmi.interf.IRemote;

public class QnaBoardController implements Initializable {

   @FXML
   private TableView<QnaBoardVO> NoticeTableView;

   @FXML
   private TableColumn<QnaBoardVO, String> BoardTitle, BoardWriter, BoardDate;

   @FXML
   private TableColumn<QnaBoardVO, Number> BoardNo, BoardViews;

   @FXML
   private Button btnClick;

   @FXML
   private Pagination qnaPaging;
   
   public static QnaBoardVO selBoard; 
   
   public static IRemote conn = null;
   
   private int from, to, itemsForPage;
   
   private ObservableList<QnaBoardVO> AllTableData, currentPageData;
   

   @FXML
   private void insertBoard() {
      Parent root;
      try {
         root = FXMLLoader.load(getClass().getResource("qnadetailBoard.fxml"));
         Stage addPost = new Stage();
         Scene scene = new Scene(root);
         addPost.setTitle("Q&A게시판");
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
      List<QnaBoardVO> list = conn.getIQnaService().selectAll();
      ObservableList<QnaBoardVO> data = FXCollections.observableArrayList(list);
      NoticeTableView.setItems(data);
      
      BoardNo.setStyle("-fx-alignment:CENTER");
      BoardWriter.setStyle("-fx-alignment:CENTER");
      BoardDate.setStyle("-fx-alignment:CENTER");
      BoardViews.setStyle("-fx-alignment:CENTER");
      
      
      BoardNo.setCellValueFactory(new PropertyValueFactory<>("qna_board_no"));
      BoardTitle.setCellValueFactory(new PropertyValueFactory<>("qna_board_title"));
      BoardWriter.setCellValueFactory(new PropertyValueFactory<>("qna_board_writer"));
      BoardDate.setCellValueFactory(new PropertyValueFactory<>("qna_board_date"));
      BoardViews.setCellValueFactory(new PropertyValueFactory<>("qna_board_views"));
      AllTableData = data;
      
      itemsForPage = 10; // 한페이지 보여줄 항목 수 설정
      int totPageCount = AllTableData.size()%itemsForPage == 0 ?
            AllTableData.size()/itemsForPage :
            AllTableData.size()/itemsForPage + 1;
      qnaPaging.setPageCount(totPageCount); // 전체 페이지 수 설정
      
      qnaPaging.setPageFactory(this::createPage);
      
      

      }

   private ObservableList<QnaBoardVO> getTableViewData(int from, int to) {
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
   private void clickTable(MouseEvent event) { 
      if (!NoticeTableView.getSelectionModel().isEmpty()) {
         selBoard = NoticeTableView.getSelectionModel().getSelectedItem(); // NoticeBoardVO Ÿ���� ������
         QnaBoardVO vo = new QnaBoardVO();
         vo.setQna_board_no(selBoard.getQna_board_no());
         vo.setQna_board_views(selBoard.getQna_board_views()+1);
         conn.getIQnaService().clickBoard(vo);
         insertBoard();
         selBoard = null;
      }
   }
}