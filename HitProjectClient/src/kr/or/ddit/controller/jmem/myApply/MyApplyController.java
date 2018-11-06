package kr.or.ddit.controller.jmem.myApply;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import kr.or.ddit.controller.login.LoginController;
import kr.or.ddit.jobmem.JobMemberVO;
import kr.or.ddit.myApply.MyApplyVO;
import kr.or.ddit.rmi.client.ClientConnect;
import kr.or.ddit.rmi.interf.IRemote;

public class MyApplyController implements Initializable{
   @FXML private TableView tableview;
   @FXML private TableColumn<MyApplyVO,String> corname;
   @FXML private TableColumn<MyApplyVO,String> coraddr;
   @FXML private TableColumn<MyApplyVO,String> testname;
   @FXML private TableColumn<MyApplyVO,String> resstate;
   
   List<MyApplyVO> myapplylist;
   
   public static IRemote conn = null;
   
   private JobMemberVO ssessionJMem;
   
   private void tableView() {
      myapplylist = new ArrayList<MyApplyVO>();
      myapplylist = conn.getIMyApplyService().selectMyApply(ssessionJMem.getMem_id());
      ObservableList<MyApplyVO> obserlist = FXCollections.observableArrayList(myapplylist);
      tableview.setItems(obserlist);
      corname.setCellValueFactory(new PropertyValueFactory<>("cor_name"));
      coraddr.setCellValueFactory(new PropertyValueFactory<>("cor_addr"));
      testname.setCellValueFactory(new PropertyValueFactory<>("test_name"));
      resstate.setCellValueFactory(new PropertyValueFactory<>("res_state"));
      
   }

   @Override
   public void initialize(URL location, ResourceBundle resources) {
      ClientConnect clientConn = new ClientConnect();
      conn=clientConn.getConnect();
      
      ssessionJMem = LoginController.ssessionJMem;
      
      corname.setStyle("-fx-alignment:CENTER");
      coraddr.setStyle("-fx-alignment:CENTER");
      testname.setStyle("-fx-alignment:CENTER");
      resstate.setStyle("-fx-alignment:CENTER");
      
      tableView();
      
      
   }

}