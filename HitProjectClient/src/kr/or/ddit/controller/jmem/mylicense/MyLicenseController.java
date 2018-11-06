package kr.or.ddit.controller.jmem.mylicense;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import kr.or.ddit.common.ShowAlert;
import kr.or.ddit.controller.login.LoginController;
import kr.or.ddit.jobmem.JobMemberVO;
import kr.or.ddit.licenseboard.LicenseBoardVO;
import kr.or.ddit.mylicense.IMylicService;
import kr.or.ddit.mylicense.MylicVO;
import kr.or.ddit.rmi.client.ClientConnect;
import kr.or.ddit.rmi.client.ClientConnectFactory;
import kr.or.ddit.rmi.interf.IRemote;

public class MyLicenseController implements Initializable{
   @FXML private Button select;
   @FXML private TableView<MylicVO> tableview;
   @FXML private TableColumn<MylicVO,String> memid;
   @FXML private TableColumn<MylicVO,String> lic_code;
   @FXML private TableColumn<MylicVO,String> lic_name;
   @FXML private TableColumn<MylicVO,String> lic_class;
   @FXML private TableColumn<MylicVO,String> lic_jugwan;
   @FXML private JFXButton insertmylic;
   @FXML private JFXButton deletemylic;
   @FXML private JFXComboBox selectlicname;
   @FXML private Pagination paging;
   
   private JobMemberVO ssessionJMem;
   /*
   static Alert alertCONFIRMATION;*/

   
   private IMylicService MylicService;
   private List<MylicVO> mylicvo;
   public static IRemote conn = null;
   private List<LicenseBoardVO> list;
   static MylicVO licvo;
   
   private int from, to, itemsForpage;
   private ObservableList<MylicVO> AllTableData, currentPageData;
   
   
   @FXML
   private void selectbutton(ActionEvent event) {
      view();
   }
   
   private Node createPage(int pageIndex) {
      from = pageIndex * itemsForpage;
      to = from + itemsForpage -1;
      tableview.setItems(getTableViewData(from,to));
      return tableview;
   }
   
   private ObservableList<MylicVO> getTableViewData(int from, int to){
      currentPageData = FXCollections.observableArrayList();
      int totSize = AllTableData.size();
      for(int i = from; i < to && i < totSize; i++) {
         currentPageData.add(AllTableData.get(i));
      }
      return currentPageData;
   }
   
   @FXML
   private void insertmylic(ActionEvent event) {
      LicenseBoardVO addLic = null;
      String selname =(String) selectlicname.getSelectionModel().getSelectedItem();
      if(selectlicname.getSelectionModel().isEmpty() || selectlicname.getSelectionModel() == null) {
         ShowAlert.showAlertWarning("자격증을 선택하지 않으셨습니다.", "자격증을 선택해주세요");
         return;
      }
      for (int i = 0; i < mylicvo.size(); i++) {
         if(mylicvo.get(i).getLic_name().equals(selname)) {
            ShowAlert.showAlertWarning(selname+" 자격증은 이미 등록되어있는 자격증 입니다.", "");
            return;
         }
      }
      for (int i = 0; i < list.size(); i++) {
         if(list.get(i).getLic_name().equals(selname)) {
            addLic = list.get(i);
         }
      }
      MylicVO addMyLic = new MylicVO();
      addMyLic.setLic_class(addLic.getLic_class());
      addMyLic.setLic_code(addLic.getLic_code());
      addMyLic.setLic_jugwan(addLic.getLic_jugwan());
      addMyLic.setLic_name(addLic.getLic_name());
      addMyLic.setMem_id(ssessionJMem.getMem_id());
      
      conn.getIMylicService().insertLic(addMyLic);
      ShowAlert.showAlertINFORMATION("성공", "등록되었습니다.");
   }
   
   @FXML
   private void deletemylic(ActionEvent event) {
      boolean result = ShowAlert.showAlert1CONFIRMATION("글을 삭제하시겠습니까?", "삭제를 원하시면 ok를 눌러주세요.");
      int cnt = 0;
      if(tableview.getSelectionModel().isEmpty()||tableview.getSelectionModel() == null) {
         ShowAlert.showAlertError("삭제할 항목을 선택해주세요!", "");
         return;
      }
      licvo=tableview.getSelectionModel().getSelectedItem();
      
      MylicVO mlicvo = new MylicVO();
      mlicvo.setMem_id(ssessionJMem.getMem_id());
      mlicvo.setLic_code(licvo.getLic_code());
      if(result == true) {
         cnt = conn.getIMylicService().deleteLic(mlicvo);
         if(cnt>0) {
            ShowAlert.showAlertINFORMATION("성공", "삭제되었습니다.");
            view();
         }else {
            ShowAlert.showAlertError("실패", "삭제에 실패하였습니다.");
         }
         return;
      }else {
         return;
      }
      
   }

   @Override
   public void initialize(URL location, ResourceBundle resources) {
      conn = ClientConnectFactory.getClientConnect();
      memid.setStyle("-fx-alignment:CENTER");
      lic_name.setStyle("-fx-alignment:CENTER");
      lic_class.setStyle("-fx-alignment:CENTER");
      lic_jugwan.setStyle("-fx-alignment:CENTER");
      lic_code.setStyle("-fx-alignment:CENTER");
      

      ssessionJMem = LoginController.ssessionJMem;
      //ssessionJMem.getMem_id();

      list = conn.getILicenseService().getAllBoard();
      ObservableList<String> licnamelist = FXCollections.observableArrayList(); 
      for(int i=0; i<list.size(); i++) {
         licnamelist.add(list.get(i).getLic_name());
      }
      selectlicname.setItems(licnamelist);
   }
   
   private void view() {
      mylicvo = new ArrayList<MylicVO>();
      mylicvo = conn.getIMylicService().selectMylic(ssessionJMem.getMem_id());
      memid.setCellValueFactory(new PropertyValueFactory<>("mem_id"));
      lic_code.setCellValueFactory(new PropertyValueFactory<>("lic_code"));
      lic_name.setCellValueFactory(new PropertyValueFactory<>("lic_name"));
      lic_class.setCellValueFactory(new PropertyValueFactory<>("lic_class"));
      lic_jugwan.setCellValueFactory(new PropertyValueFactory<>("lic_jugwan"));
      tableview.setItems(FXCollections.observableArrayList(mylicvo));
      
      ObservableList<MylicVO> liclist = FXCollections.observableArrayList(mylicvo);
      AllTableData = liclist;
      itemsForpage = 7;
      int totPageCount = AllTableData.size()%itemsForpage == 0? 
            AllTableData.size()/itemsForpage : 
               AllTableData.size()/itemsForpage + 1;
      paging.setPageCount(totPageCount);
      paging.setPageFactory(this::createPage);
   }
   
   

}