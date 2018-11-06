package kr.or.ddit.common;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class ShowAlert {
   static Alert alertWarning = new Alert(AlertType.WARNING);
   static Alert alertERROR = new Alert(AlertType.ERROR);
   static Alert alertINFORMATION = new Alert(AlertType.INFORMATION);
   static Alert alertCONFIRMATION = new Alert(AlertType.CONFIRMATION);
   static Alert alert1CONFIRMATION = new Alert(AlertType.CONFIRMATION);
   
   public static void showAlertWarning(String headerText, String contentText) { //기존에 사용하고있는 warning  ex) 실패하였을때 사용
      alertWarning.setHeaderText(headerText);
      alertWarning.setContentText(contentText);
      alertWarning.show();
   }
   
   public static void showAlertError(String headerText, String contentText) { // 위험하게 잘못했다는 식으로 줄때사용 엑박표시
      alertERROR.setHeaderText(headerText);
      alertERROR.setContentText(contentText);
      alertERROR.show();
   }
   
   public static void showAlertINFORMATION(String headerText, String contentText) { //긍정적인 아이디 완성같은거 할때 
      alertINFORMATION.setHeaderText(headerText);
      alertINFORMATION.setContentText(contentText);
      alertINFORMATION.show();
   }
   
   public static void showAlertCONFIRMATION(String headerText, String contentText) { //한번더 물어볼때 사용 무엇을 하시겠습니까 라고 예시
      alertCONFIRMATION.setHeaderText(headerText);
      alertCONFIRMATION.setContentText(contentText);
      alertCONFIRMATION.show();
   }
   
   public static boolean showAlert1CONFIRMATION(String headerText, String contentText) { //한번더 물어볼때 사용 무엇을 하시겠습니까 라고 예시
      boolean result = false;
      
      alert1CONFIRMATION.setHeaderText(headerText);
      alert1CONFIRMATION.setContentText(contentText);
      
      Optional<ButtonType> result1 = alert1CONFIRMATION.showAndWait();
      if(result1.get() == ButtonType.OK) {
         result = true;
      }
      
      return result;
   }
   
}
