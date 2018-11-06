package kr.or.ddit.controller.signup;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableColumn.CellDataFeatures;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import kr.or.ddit.common.ZipVO;
import kr.or.ddit.controller.login.LoginController;

public class ZipDrawerController implements Initializable {

   @FXML
   private TreeTableColumn<Zip, String> gugun;

   @FXML
   private JFXButton search;

   @FXML
   private JFXTextField menuTextField;

   @FXML
   private JFXTreeTableView<Zip> zipTable;

   @FXML
   private TreeTableColumn<Zip, String> sido;

   @FXML
   private TreeTableColumn<Zip, String> ZipCode;

   @FXML
   private TreeTableColumn<Zip, String> dong;

   @FXML
   private JFXComboBox<String> menuCombo;
   
   @FXML
   private JFXButton btnCheck , btnCancel;
   
   public  ZipVO zipData;

   @Override
   public void initialize(URL location, ResourceBundle resources) {
      setComboName();
      search.setOnAction(event -> searchZip());
   }

   private void setComboName() {
      menuCombo.setValue("동이름");
      menuCombo.getItems().addAll("동이름", "우편번호");
   }

   private void searchZip() {
      sido = new JFXTreeTableColumn<>("시도");
      sido.setPrefWidth(94);
      ZipCode = new JFXTreeTableColumn<>("우편번호");
      ZipCode.setPrefWidth(131);
      dong = new JFXTreeTableColumn<>("동");
      dong.setPrefWidth(119);
      gugun = new JFXTreeTableColumn<>("구군");
      gugun.setPrefWidth(91);
      
      sido.setCellValueFactory(
            new Callback<TreeTableColumn.CellDataFeatures<Zip, String>, ObservableValue<String>>() {
               @Override
               public ObservableValue<String> call(CellDataFeatures<Zip, String> param) {
                  return param.getValue().getValue().sido;
               }
            });

      ZipCode.setCellValueFactory(
            new Callback<TreeTableColumn.CellDataFeatures<Zip, String>, ObservableValue<String>>() {
               @Override
               public ObservableValue<String> call(CellDataFeatures<Zip, String> param) {
                  return param.getValue().getValue().ZipCode;
               }
            });

      dong.setCellValueFactory(
            new Callback<TreeTableColumn.CellDataFeatures<Zip, String>, ObservableValue<String>>() {
               @Override
               public ObservableValue<String> call(CellDataFeatures<Zip, String> param) {
                  return param.getValue().getValue().dong;
               }
            });

      gugun.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Zip, String>, ObservableValue<String>>() {
         @Override
         public ObservableValue<String> call(CellDataFeatures<Zip, String> param) {
            return param.getValue().getValue().gugun;
         }
      });

      if (menuTextField.getText().isEmpty()) {
         return;
      }


      if (menuCombo.getValue().equals("동이름")) {
         List<ZipVO> list = LoginController.conn.getICommonService().searchDong(menuTextField.getText());
         ObservableList<Zip> zipdata = FXCollections.observableArrayList();
         for (int i = 0; i < list.size(); i++) {
            Zip zip = new Zip(list.get(i).getGugun(), list.get(i).getSido(), list.get(i).getZipcode(),
                  list.get(i).getDong());
            zipdata.add(zip);
         }
         final TreeItem<Zip> root = new RecursiveTreeItem<Zip>(zipdata, RecursiveTreeObject::getChildren);

         zipTable.getColumns().setAll( ZipCode,sido,gugun,  dong);
         zipTable.setRoot(root);
         zipTable.setShowRoot(false);
      } else {
         List<ZipVO> list = LoginController.conn.getICommonService().searchZip(menuTextField.getText());

         ObservableList<Zip> zipdata = FXCollections.observableArrayList();
         for (int i = 0; i < list.size(); i++) {
            Zip zip = new Zip(list.get(i).getGugun(), list.get(i).getSido(), list.get(i).getZipcode(),
                  list.get(i).getDong());
            zipdata.add(zip);
         }
         final TreeItem<Zip> root = new RecursiveTreeItem<Zip>(zipdata, RecursiveTreeObject::getChildren);

         zipTable.getColumns().setAll(ZipCode,sido,gugun,  dong);
         zipTable.setRoot(root);
         zipTable.setShowRoot(false);
      }

   }

   public class Zip extends RecursiveTreeObject<Zip> {
      StringProperty gugun;
      StringProperty sido;
      StringProperty ZipCode;
      StringProperty dong;

      public Zip(String gugun, String sido, String zipCode, String dong) {
         this.gugun = new SimpleStringProperty(gugun);
         this.sido = new SimpleStringProperty(sido);
         this.ZipCode = new SimpleStringProperty(zipCode);
         this.dong = new SimpleStringProperty(dong);
      }

      public StringProperty getGugun() {
         return gugun;
      }

      public void setGugun(StringProperty gugun) {
         this.gugun = gugun;
      }

      public StringProperty getSido() {
         return sido;
      }

      public void setSido(StringProperty sido) {
         this.sido = sido;
      }

      public StringProperty getZipCode() {
         return ZipCode;
      }

      public void setZipCode(StringProperty zipCode) {
         ZipCode = zipCode;
      }

      public StringProperty getDong() {
         return dong;
      }

      public void setDong(StringProperty dong) {
         this.dong = dong;
      }
   }

}