package kr.or.ddit.controller.totalCalendar;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.time.LocalDate;

public class AnchorPaneDate extends AnchorPane {

	private AnchorPane root;
    private LocalDate date;
    
    private boolean clickCheck;
    private boolean sunCheck;

    public AnchorPaneDate(Node... children) {
        super(children);
        root = this;
    
    }

	public boolean isClickCheck() {
		return clickCheck;
	}

	public void setClickCheck(boolean clickCheck) {
		this.clickCheck = clickCheck;
	}

	public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

	public boolean isSunCheck() {
		return sunCheck;
	}

	public void setSunCheck(boolean sunCheck) {
		this.sunCheck = sunCheck;
	}
	
	
    
    
}
