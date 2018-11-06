package kr.or.ddit.controller.jmem.applyJob;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import kr.or.ddit.controller.creq.CreqBoardController;
import kr.or.ddit.controller.creq.CreqBoardInnerController;
import kr.or.ddit.controller.jmem.applyJob.coding.CodingController;
import kr.or.ddit.controller.login.LoginController;
import kr.or.ddit.cormem.CorMemberVO;
import kr.or.ddit.creqboard.CReqBoardVO;
import kr.or.ddit.rmi.client.ClientConnectFactory;
import kr.or.ddit.rmi.interf.IRemote;

public class TabController implements Initializable{
	
	@FXML
	private AnchorPane myintropane, testpane;
	
	
	private IRemote conn;

	private CReqBoardVO creqBoard;
	
	private List<CReqBoardVO> corList;
	
	public static String id = LoginController.ssessionJMem.getMem_id();
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		conn = ClientConnectFactory.getClientConnect();
		corList = CreqBoardController.creqVO; 
		String corname = CreqBoardInnerController.selCorname;
		
		
		for (int i = 0; i < corList.size(); i++) {
			if(corList.get(i).getCor_name().equals(corname)) {
				creqBoard = corList.get(i);
			}
		}
		System.out.println(creqBoard.getTest_type());
//		conn.getICreqBoardService().selectTestType(name);
		setIntro();
		if(creqBoard.getTest_type() ==1) {
			setCoding();
		}else if(creqBoard.getTest_type()==2) {
			setDrawer();
		}
		
//		setCoding();
//		setDrawer();
	}
	
	  String txt = "public class "+id+" {\n" + 
		         " \n" + 
		         "}\n" + 
		         "";
		 
	private void createFile() {
		String fileName = CodingController.setPath() + "/sourceFolder/" + id + ".java";

		try {

			// BufferedWriter 와 FileWriter를 조합하여 사용 (속도 향상)
			BufferedWriter fw = new BufferedWriter(new FileWriter(fileName, true));

			// 파일안에 문자열 쓰기
			fw.write(txt);
			fw.flush();

			// 객체 닫기
			fw.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	public void setIntro() {
		AnchorPane test = null;
		try {
			test = FXMLLoader.load(this.getClass().getResource("../myIntro/AddMyIntro.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		myintropane.getChildren().setAll(test);
	}
	
	
	public void setCoding() {
		createFile();
		AnchorPane test = null;
		try {
			test = FXMLLoader.load(this.getClass().getResource("coding/coding.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		testpane.getChildren().setAll(test);
	}
	
	public void setDrawer() {
		AnchorPane test = null;
		try {
			test = FXMLLoader.load(this.getClass().getResource("draw/canvas.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		testpane.getChildren().setAll(test);
	}
}
