package kr.or.ddit.controller.jmem.mycalendar;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class MemCalendarHBox extends HBox {
	
	private HBox root;
	private  boolean goodCheck;
	
	public MemCalendarHBox(Node... children) {
		super(children);
		root = this;
	}
	 
	public HBox getRoot() {
		return root;
	}
	public void setRoot(HBox root) {
		this.root = root;
	}
	public boolean isGoodCheck() {
		return goodCheck;
	}
	public void setGoodCheck(boolean goodCheck) {
		this.goodCheck = goodCheck;
	}
	
	

}
