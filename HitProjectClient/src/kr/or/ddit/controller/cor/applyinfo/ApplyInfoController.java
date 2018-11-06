package kr.or.ddit.controller.cor.applyinfo;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import kr.or.ddit.controller.cor.corApply.CorApplyController;
import kr.or.ddit.controller.login.LoginController;
import kr.or.ddit.cormem.CorMemberVO;
import kr.or.ddit.jobmem.JobMemberVO;
import kr.or.ddit.myintro.MyIntroVO;
import kr.or.ddit.rmi.client.ClientConnectFactory;
import kr.or.ddit.rmi.interf.IRemote;
import kr.or.ddit.test.TestVO;

public class ApplyInfoController implements Initializable {

	@FXML
	private AnchorPane memberinfo, testpane;
	@FXML
	private JFXTextField addrfield;

	@FXML
	private JFXTextArea introarea;

	@FXML
	private JFXTextField namefield;

	@FXML
	private JFXTextField telfield;

	@FXML
	private ImageView profileview;

	@FXML
	private JFXTextField careerfield;

	@FXML
	private JFXTextField genderfield;

	@FXML
	private JFXTextField mailfield;

	@FXML
	private JFXTextField hopefield;

	private IRemote conn;
	private CorMemberVO sessionMem;
	private JobMemberVO selJMem;
	private TestVO testvo;
	private MyIntroVO selIntro;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		conn = ClientConnectFactory.getClientConnect();
		sessionMem = LoginController.ssessionCMem;
		selJMem = CorApplyController.selJobMem;
		testvo = CorApplyController.testInfo;
		selIntro = conn.getIMyIntroService().selectMyIntro(selJMem.getMem_id());
		
		settingInfo();
		
		if(testvo.getTest_type()==2) {
			AnchorPane test = null;
			try {
				test = FXMLLoader.load(this.getClass().getResource("CavasResult.fxml"));
				testpane.getChildren().setAll(test);
			}catch (IOException e) {
				e.printStackTrace();
			}
		}else if(testvo.getTest_type()==1) {
			AnchorPane test = null;
			try {
				test = FXMLLoader.load(this.getClass().getResource("CodingResult.fxml"));
				testpane.getChildren().setAll(test);
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void settingInfo() {
		namefield.setText(selJMem.getMem_name());
		hopefield.setText(selIntro.getMy_hope());
		mailfield.setText(selJMem.getMem_mail());
		String gender = null;
		if (selJMem.getJmem_regno().charAt(6) == '1' || selJMem.getJmem_regno().charAt(6) == '3') {
			gender = "남자";
		} else {
			gender = "여자";
		}
		genderfield.setText(gender);
		telfield.setText(selJMem.getJmem_tel());
		addrfield.setText(selJMem.getJmem_addr());
		careerfield.setText(selIntro.getMy_career());
		introarea.setText(selIntro.getMy_intro());
//		File file = new File(selJMem.getMem_image());
//   	    Image image = new Image(file.toURI().toString());
   	    Image image = new Image(getClass().getResourceAsStream("../../../img/"+selJMem.getMem_id()+".png"));
   	    profileview.setImage(image);
		
	}
}
