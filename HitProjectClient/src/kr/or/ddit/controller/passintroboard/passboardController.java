package kr.or.ddit.controller.passintroboard;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import kr.or.ddit.common.ShowAlert;
import kr.or.ddit.controller.login.LoginController;
import kr.or.ddit.passIntroBoard.PassIntroboardVO;
import kr.or.ddit.rmi.client.ClientConnect;
import kr.or.ddit.rmi.interf.IRemote;

public class passboardController implements Initializable{
   @FXML private TableView<PassIntroboardVO> tableview;
   @FXML private TableColumn<PassIntroboardVO, String> boardno;
   @FXML private TableColumn<PassIntroboardVO, String> title;
   @FXML private TableColumn<PassIntroboardVO, String> writer;
   @FXML private TableColumn<PassIntroboardVO, String> count;
   @FXML private TableColumn<PassIntroboardVO, String> date;
   @FXML private JFXButton insert;
   
   @FXML private Pagination paging;
   
   public static IRemote conn = null;
   
   private int from, to, itemsForpage;
   private ObservableList<PassIntroboardVO> AllTableData, currentPageData;
   
   
   
   //private IPassIntroBoardService passService;
   
   static PassIntroboardVO passVO = null;
   
   @FXML
   private void insert(ActionEvent event) {
      Parent root;
      try {
         root = FXMLLoader.load(getClass().getResource("passboardinsert.fxml"));
         Stage addPost = new Stage();
         Scene scene = new Scene(root);
         addPost.setTitle("합격자 자소서 글 추가");
         addPost.setScene(scene);
         addPost.showAndWait();
      } catch (IOException e) {
         e.printStackTrace();
      } 
      tableView();
   }
   
   
   @FXML
   private void tableviewSelect(MouseEvent event) throws IOException {
      passVO = tableview.getSelectionModel().getSelectedItem();
      if(tableview.getSelectionModel().isEmpty()) {
         ShowAlert.showAlertError("공백입니다.항목을 선택해주세요", "항목을 선택해주세요");
         return;
      }
      Parent root = FXMLLoader.load(getClass().getResource("passboardshow.fxml"));
       Stage Cont = new Stage();
       
       Scene scene = new Scene(root);
       Cont.setTitle("합격자자소서게시판");
       Cont.setScene(scene);
       Cont.showAndWait();
       updateBoardCount();
       tableView();
   }
   
   private void tableView() {
      List<PassIntroboardVO> passlist = conn.getIPassIntroBoardService().selectpassboard();
      ObservableList<PassIntroboardVO> passIntrolist = FXCollections.observableArrayList(passlist);
      tableview.setItems(passIntrolist);
      boardno.setCellValueFactory(new PropertyValueFactory<>("pass_board_no"));
      title.setCellValueFactory(new PropertyValueFactory<>("pass_board_title"));
      writer.setCellValueFactory(new PropertyValueFactory<>("pass_board_writer"));
      count.setCellValueFactory(new PropertyValueFactory<>("pass_board_count"));
      date.setCellValueFactory(new PropertyValueFactory<>("pass_board_date"));
      
      AllTableData = passIntrolist;
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
   private ObservableList<PassIntroboardVO> getTableViewData(int from, int to){
      currentPageData = FXCollections.observableArrayList();
      int totSize = AllTableData.size();
      
      for(int i = from; i < to && i < totSize; i++) {
         currentPageData.add(AllTableData.get(i));
      }
      return currentPageData;
   }
   
   private void updateBoardCount() {
      int count = tableview.getSelectionModel().getSelectedItem().getPass_board_count();
      System.out.println(count);
      int passboardno = tableview.getSelectionModel().getSelectedItem().getPass_board_no();
      PassIntroboardVO passboardvo = new PassIntroboardVO();
      passboardvo.setPass_board_no(passboardno);
      passboardvo.setPass_board_count(count + 1);
      
      conn.getIPassIntroBoardService().countNotice(passboardvo);
   }

   @Override
   public void initialize(URL location, ResourceBundle resources) {
      ClientConnect clientConn = new ClientConnect();
      conn=clientConn.getConnect();
      
      if(LoginController.memberType==3) {
         insert.setVisible(true);
      }else {
         insert.setVisible(false);
      }
      
      writer.setStyle("-fx-alignment:CENTER");
      count.setStyle("-fx-alignment:CENTER");
      date.setStyle("-fx-alignment:CENTER");
      boardno.setStyle("-fx-alignment:CENTER");
      
      tableView();
   }
   

}