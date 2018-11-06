package kr.or.ddit.controller.jmemlist;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Handler;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXNodesList;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import com.jfoenix.transitions.hamburger.HamburgerBasicCloseTransition;
import com.jfoenix.transitions.hamburger.HamburgerNextArrowBasicTransition;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.NodeOrientation;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class JobMemBoardContController implements Initializable {
	@FXML
    private AnchorPane parent;

    @FXML
    private JFXTextField jmem_addr;

    @FXML
    private JFXTextField jmem_career;

    @FXML
    private JFXTextField jmem_mail;

    @FXML
    private JFXTextField jmem_name;

    @FXML
    private JFXTextField jmem_hope;

    @FXML
    private JFXTextField jmem_click;

    @FXML
    private JFXTextField jmem_gender;

    @FXML
    private JFXTextArea jmem_intro;

    @FXML
    private ImageView jmem_profile;

    @FXML
    private JFXTextField jmem_tel;

	public AnchorPane getParent() {
		return parent;
	}

	public void setParent(AnchorPane parent) {
		this.parent = parent;
	}

	public JFXTextField getJmem_addr() {
		return jmem_addr;
	}

	public void setJmem_addr(JFXTextField jmem_addr) {
		this.jmem_addr = jmem_addr;
	}

	public JFXTextField getJmem_career() {
		return jmem_career;
	}

	public void setJmem_career(JFXTextField jmem_career) {
		this.jmem_career = jmem_career;
	}

	public JFXTextField getJmem_mail() {
		return jmem_mail;
	}

	public void setJmem_mail(JFXTextField jmem_mail) {
		this.jmem_mail = jmem_mail;
	}

	public JFXTextField getJmem_name() {
		return jmem_name;
	}

	public void setJmem_name(JFXTextField jmem_name) {
		this.jmem_name = jmem_name;
	}

	public JFXTextField getJmem_hope() {
		return jmem_hope;
	}

	public void setJmem_hope(JFXTextField jmem_hope) {
		this.jmem_hope = jmem_hope;
	}

	public JFXTextField getJmem_click() {
		return jmem_click;
	}

	public void setJmem_click(JFXTextField jmem_click) {
		this.jmem_click = jmem_click;
	}

	public JFXTextField getJmem_gender() {
		return jmem_gender;
	}

	public void setJmem_gender(JFXTextField jmem_gender) {
		this.jmem_gender = jmem_gender;
	}

	public JFXTextArea getJmem_intro() {
		return jmem_intro;
	}

	public void setJmem_intro(JFXTextArea jmem_intro) {
		this.jmem_intro = jmem_intro;
	}

	public ImageView getJmem_profile() {
		return jmem_profile;
	}

	public void setJmem_profile(ImageView jmem_profile) {
		this.jmem_profile = jmem_profile;
	}

	public JFXTextField getJmem_tel() {
		return jmem_tel;
	}

	public void setJmem_tel(JFXTextField jmem_tel) {
		this.jmem_tel = jmem_tel;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

}
