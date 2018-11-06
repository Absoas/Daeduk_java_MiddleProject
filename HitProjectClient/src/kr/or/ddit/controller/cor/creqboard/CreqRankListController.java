package kr.or.ddit.controller.cor.creqboard;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.chainsaw.Main;

import com.jfoenix.controls.JFXButton;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Callback;
import kr.or.ddit.creqboard.CReqBoardVO;
import kr.or.ddit.rmi.client.ClientConnect;
import kr.or.ddit.rmi.client.ClientConnectFactory;
import kr.or.ddit.rmi.interf.IRemote;


public class CreqRankListController implements Initializable {
   

    @FXML
    private VBox vbox;
    
    @FXML
    private JFXButton clear;
    
    
    public static IRemote conn = null;
    List<String> data = null;
    
//    private final Image one  = new Image(getClass().getResourceAsStream("1.PNG"));
//    private final Image two  = new Image(getClass().getResourceAsStream("2.PNG"));
//    private final Image three  = new Image(getClass().getResourceAsStream("3.PNG"));
//    private final Image four  = new Image(getClass().getResourceAsStream("4.PNG"));
//    private final Image five  = new Image(getClass().getResourceAsStream("5.PNG"));
//    private final Image six  = new Image(getClass().getResourceAsStream("6.PNG"));
//    private final Image seven  = new Image(getClass().getResourceAsStream("7.PNG"));
//    private final Image eight  = new Image(getClass().getResourceAsStream("8.PNG"));
//    private final Image nine  = new Image(getClass().getResourceAsStream("9.PNG"));
//    private final Image ten  = new Image(getClass().getResourceAsStream("10.PNG"));
//    private ImageView imageView = new ImageView();
//    private Image[] listOfImages = {one, two, three, four, five, six, seven, eight, nine, ten};
    Label label = new Label();
    ObservableList<String> list = null;
    ListView<String> listview = new ListView<>();
    
    
   @Override
   public void initialize(URL location, ResourceBundle resources) {
      clear.setOnAction(event-> setListView2());
      conn = ClientConnectFactory.getClientConnect();
      setListView2();
      vbox.getChildren().addAll(listview, label);
   }
   
   private void setListView2() {

      data = conn.getICreqBoardService().setRanckView();
      list = FXCollections.observableArrayList(data);
      
      label.setFont(new Font(16));
      
      // ListView에 데이터 셋팅하기 1
      listview.setItems(list);
//      listview.set
      listview.setFixedCellSize(34);
      // ListView에서 값이 선택되었을 때 처리
//      listview.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

//         @Override
//         public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
//            label.setText(newValue); // 현재 선택된 값 => newValue
//            label.setTextFill(Color.web(newValue)); // 이전의 값 => oldValue
//
//         }

//      });
      

      // ListView의 내용은 변경되지 않고 화면에 보이는 부분만 변경하는 방법
//      listview.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
//         @Override
//         public ListCell<String> call(ListView<String> param) {
//            return new ListCell<String>() {
//               protected void updateItem(String item, boolean empty) {
//                  super.updateItem(item, empty);
//                  if (item != null) { // 또는 !empty
//                     Label lbTxt = new Label(item);
//                     HBox hb2 = new HBox();
//                     int i = 0;
//                     System.out.println(i);
//                     System.out.println(data.get(0));
//                     System.out.println("item:"+item);
//                     if(item.trim().equals(data.get(0).trim())) {
//                          imageView.setImage(listOfImages[0]);
//                     }
////                     else if(item.equals(data.get(1))){
////                          imageView.setImage(listOfImages[1]);
////                     }else if(item.equals(data.get(2))){
////                          imageView.setImage(listOfImages[2]);
////                     }
//                     hb2.getChildren().addAll(imageView, lbTxt);
//                     setGraphic(hb2);
//                     i++;
//                     System.out.println(i);
//                  }
//               }
//            };
//         }
//      });
   }
}