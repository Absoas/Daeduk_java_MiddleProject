package kr.or.ddit.controller.jmemlist;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import kr.or.ddit.common.ShowAlert;
import kr.or.ddit.controller.login.LoginController;
import kr.or.ddit.jmemlist.JMemListVO;
import kr.or.ddit.rmi.client.ClientConnectFactory;
import kr.or.ddit.rmi.interf.IRemote;

public class JobMemListBoardController implements Initializable {

	@FXML
	private Accordion parentJPane;

	@FXML
	private JFXButton searchBtn,totalListBtn;

	@FXML
	private JFXComboBox<String> searchCombo;

	@FXML
	private JFXTextField searchContent;

	private IRemote conn;

	private List<JMemListVO> jMemList;
	private List<JobMemBoardContController> jmbContList;
	private List<TitledPane> allTitleList;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		conn = ClientConnectFactory.getClientConnect();
		jMemList = new ArrayList<JMemListVO>();
		jmbContList = new ArrayList<JobMemBoardContController>();
		allTitleList = new ArrayList<TitledPane>();
		
		initComboValue();
		setJmemBoard();
		searchBtn.setOnAction(event-> comboEvent());
		totalListBtn.setOnAction(event -> setJmemBoard());
	}

	private void initComboValue() {
		searchCombo.getItems().addAll("경력" , "분야" , "이름");
	}
	
	private void comboEvent() {
		parentJPane.getPanes().clear();
		
		if(searchContent.getText().isEmpty()) {
			ShowAlert.showAlertWarning("값을 입력하지 않았습니다.", "검새할 항목을 입력해주세요");
			return;
		}
		
		if(searchCombo.getValue().equals("경력")) {
			jMemList = conn.getIJMemListService().selectCareerJMemList(searchContent.getText());
		}else if(searchCombo.getValue().equals("분야")) {
			jMemList = conn.getIJMemListService().selectDeptJMemList(searchContent.getText());
		}else if(searchCombo.getValue().equals("이름")){
			jMemList = conn.getIJMemListService().selectNameJMemList(searchContent.getText());
		}
		
		if(jMemList.size() == 0  || jMemList == null) {
			ShowAlert.showAlertWarning("데이터 없음", "검색 한 항목이 존재하지 않습니다.");
			return;
		}
		for (int i = 0; i < jMemList.size(); i++) {
			JMemListVO jMemvo = jMemList.get(i);
			try {
				TitledPane tp = new TitledPane();
				tp.setText("[" + jMemvo.getMy_hope() + "]" + jMemvo.getMem_name());
				FXMLLoader fl = new FXMLLoader(getClass().getResource("JobMemBoardCont.fxml"));
				AnchorPane anchor = fl.load();
				tp.setContent(anchor);
				String gender = null;
				if (jMemvo.getJmem_regno().charAt(6) == '1' || jMemvo.getJmem_regno().charAt(6) == '3') {
					gender = "남자";
				} else {
					gender = "여자";
				}

				JobMemBoardContController ac = fl.getController();
				ac.getJmem_name().setText(jMemvo.getMem_name());
				ac.getJmem_addr().setText(jMemvo.getJmem_addr());
				ac.getJmem_career().setText(jMemvo.getMy_career());
				ac.getJmem_click().setText(jMemvo.getJmem_click() + "");
				ac.getJmem_tel().setText(jMemvo.getJmem_tel());
				ac.getJmem_gender().setText(gender);
				ac.getJmem_hope().setText(jMemvo.getMy_hope());
				ac.getJmem_intro().setText(jMemvo.getMy_intro());
				ac.getJmem_mail().setText(jMemvo.getMem_mail());
				Image profile = new Image(getClass().getResourceAsStream("../../img/"+jMemvo.getMem_id()+".png"));
				ac.getJmem_profile().setImage(profile);
			
				parentJPane.getPanes().add(tp);
				allTitleList.add(tp);
				jmbContList.add(ac);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		setMouseEvent();
	}

	private void setJmemBoard() {
		parentJPane.getPanes().clear();
		jMemList = conn.getIJMemListService().selectJMemList();

		for (int i = 0; i < jMemList.size(); i++) {
			JMemListVO jMemvo = jMemList.get(i);
			try {
				TitledPane tp = new TitledPane();
				tp.setText("[" + jMemvo.getMy_hope() + "]" + jMemvo.getMem_name());
				FXMLLoader fl = new FXMLLoader(getClass().getResource("JobMemBoardCont.fxml"));
				AnchorPane anchor = fl.load();
				tp.setContent(anchor);
				String gender = null;
				if (jMemvo.getJmem_regno().charAt(6) == '1' || jMemvo.getJmem_regno().charAt(6) == '3') {
					gender = "남자";
				} else {
					gender = "여자";
				}

				JobMemBoardContController ac = fl.getController();
				ac.getJmem_name().setText(jMemvo.getMem_name());
				ac.getJmem_addr().setText(jMemvo.getJmem_addr());
				ac.getJmem_career().setText(jMemvo.getMy_career());
				ac.getJmem_click().setText(jMemvo.getJmem_click() + "");
				ac.getJmem_tel().setText(jMemvo.getJmem_tel());
				ac.getJmem_gender().setText(gender);
				ac.getJmem_hope().setText(jMemvo.getMy_hope());
				ac.getJmem_intro().setText(jMemvo.getMy_intro());
				ac.getJmem_mail().setText(jMemvo.getMem_mail());
				Image profile = new Image(getClass().getResourceAsStream("../../img/"+jMemvo.getMem_id()+".png"));
				ac.getJmem_profile().setImage(profile);
				parentJPane.getPanes().add(tp);
				parentJPane.setLayoutX(12);
				parentJPane.setLayoutY(5);
				allTitleList.add(tp);
				jmbContList.add(ac);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		setMouseEvent();
	}

	private void setMouseEvent() {

		for (TitledPane titledPane : allTitleList) {
			titledPane.expandedProperty().addListener(new ChangeListener<Boolean>() {

				@Override
				public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
					if (newValue == true) {
						JMemListVO jmListvo = new JMemListVO();
						for (int i = 0; i < jMemList.size(); i++) {
							int index = titledPane.getText().indexOf("]");
							String titleName = titledPane.getText().substring(index + 1);
							if (jMemList.get(i).getMem_name().equals(titleName)) {
								jmListvo = jMemList.get(i);
								jmListvo.setJmem_click((jmListvo.getJmem_click() + 1));
								jMemList.get(i).setJmem_click(jmListvo.getJmem_click());

								int cnt = conn.getIJMemListService().updateJMemClick(jmListvo);
								if (cnt > 0) {
									// 성공
								} else {
									// 실패
								}
								jmbContList.get(i).getJmem_click().setText(jMemList.get(i).getJmem_click() + "");

							}
						}
//	                  alltitle.clear();
					}
				}
			});
//	         
		}
	}

}
