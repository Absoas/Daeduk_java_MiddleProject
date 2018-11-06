package kr.or.ddit.controller.faqboard;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import kr.or.ddit.rmi.client.ClientConnectFactory;
import kr.or.ddit.rmi.interf.IRemote;

public class FreAskQnAController implements Initializable{

	
	@FXML
	private AnchorPane parentPane;
	
	private IRemote conn;
	private Accordion accordion;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		conn = ClientConnectFactory.getClientConnect();
		start();
		parentPane.getChildren().add(accordion);
		
	}

	private void start() {
		
		accordion = new Accordion();
		List<String> tpList = new ArrayList<>();
		tpList.add("회원 탈퇴");
		tpList.add("창의력 문제, 코딩 문제");
		tpList.add("JAVA FX 관련");
		tpList.add("지원 절차");
		tpList.add("사람IT");
		
		for(int i=0; i<tpList.size() ; i++){
			
			TitledPane tp = new TitledPane();
			tp.setText(tpList.get(i));
			AnchorPane anchor = new AnchorPane();
			Parent root = null;
			
			switch (i) {
			case 0:
				
				try {
					root = FXMLLoader.load(getClass().getResource("FreAskQnAFx1.fxml"));
					
					JFXTextField faq_q = (JFXTextField)root.lookup("#faq_q");
					JFXTextArea faq_a = (JFXTextArea)root.lookup("#faq_a");
					
					faq_q.setEditable(false);
					faq_a.setEditable(false);
					
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				anchor.getChildren().setAll(root);
				tp.setContent(anchor);
				accordion.setLayoutX(10);
				accordion.setLayoutY(5);
				
				accordion.getPanes().add(tp);
				
				break;

			case 1:
				
				try {
					root = FXMLLoader.load(getClass().getResource("FreAskQnAFx2.fxml"));
					
					JFXTextField faq_q = (JFXTextField)root.lookup("#faq_q");
					JFXTextArea faq_a = (JFXTextArea)root.lookup("#faq_a");
					
					faq_q.setEditable(false);
					faq_a.setEditable(false);
					
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				anchor.getChildren().setAll(root);
				tp.setContent(anchor);
				
				accordion.getPanes().add(tp);
				
				break;
				
			case 2:
				
				try {
					root = FXMLLoader.load(getClass().getResource("FreAskQnAFx3.fxml"));
					
					JFXTextField faq_q = (JFXTextField)root.lookup("#faq_q");
					JFXTextArea faq_a = (JFXTextArea)root.lookup("#faq_a");
					
					faq_q.setEditable(false);
					faq_a.setEditable(false);
					
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				anchor.getChildren().setAll(root);
				tp.setContent(anchor);
				
				accordion.getPanes().add(tp);
				
				break;
				
			case 3:
				
				try {
					root = FXMLLoader.load(getClass().getResource("FreAskQnAFx4.fxml"));
					
					JFXTextField faq_q = (JFXTextField)root.lookup("#faq_q");
					JFXTextArea faq_a = (JFXTextArea)root.lookup("#faq_a");
					
					faq_q.setEditable(false);
					faq_a.setEditable(false);
					
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				anchor.getChildren().setAll(root);
				tp.setContent(anchor);
				
				accordion.getPanes().add(tp);
				
				break;
				
			case 4:
				
				try {
					root = FXMLLoader.load(getClass().getResource("FreAskQnAFx5.fxml"));
					
					JFXTextField faq_q = (JFXTextField)root.lookup("#faq_q");
					JFXTextArea faq_a = (JFXTextArea)root.lookup("#faq_a");
					
					faq_q.setEditable(false);
					faq_a.setEditable(false);
					
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				anchor.getChildren().setAll(root);
				tp.setContent(anchor);
				
				accordion.getPanes().add(tp);
				
				break;
				
			default:
				break;
			}
		}
		
		
	}

}
