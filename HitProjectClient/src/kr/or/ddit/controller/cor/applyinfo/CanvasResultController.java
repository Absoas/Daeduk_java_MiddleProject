package kr.or.ddit.controller.cor.applyinfo;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTextArea;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import kr.or.ddit.controller.cor.corApply.CorApplyController;
import kr.or.ddit.jobmem.JobMemberVO;
import kr.or.ddit.test.TestVO;

public class CanvasResultController implements Initializable {
	 @FXML
	 private JFXTextArea testcontarea;

	 @FXML
	 private ImageView canvasview;
	
	 private JobMemberVO selJMem;
	 
	 private TestVO test;
	 
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		selJMem = CorApplyController.selJobMem;
		test = CorApplyController.testInfo;
//		Image img = new Image(getClass().getResourceAsStream("../../../../../../sourceFolder/"+selJMem.getMem_id()+".png"));
		Image img = new Image(getClass().getResourceAsStream("../../../canimg/"+selJMem.getJmem_id()+".png"));
		canvasview.setImage(img);
		testcontarea.setText(test.getTest_content());
	}
	
	@FXML
	private void close(ActionEvent event) {
		Stage resultStage = (Stage) ((Node)testcontarea).getScene().getWindow();
		resultStage.close();
	}

}
