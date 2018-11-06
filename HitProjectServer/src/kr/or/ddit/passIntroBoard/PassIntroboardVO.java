package kr.or.ddit.passIntroBoard;

import java.io.Serializable;

public class PassIntroboardVO implements Serializable{
	private int pass_board_no;
	private String pass_board_title;
	private String pass_board_writer;
	private int pass_board_count;
	private String pass_board_date;
	private String pass_board_contents;
	
	
	public int getPass_board_no() {
		return pass_board_no;
	}
	public void setPass_board_no(int pass_board_no) {
		this.pass_board_no = pass_board_no;
	}
	public String getPass_board_title() {
		return pass_board_title;
	}
	public void setPass_board_title(String pass_board_title) {
		this.pass_board_title = pass_board_title;
	}
	public String getPass_board_writer() {
		return pass_board_writer;
	}
	public void setPass_board_writer(String pass_board_writer) {
		this.pass_board_writer = pass_board_writer;
	}
	public int getPass_board_count() {
		return pass_board_count;
	}
	public void setPass_board_count(int pass_board_count) {
		this.pass_board_count = pass_board_count;
	}
	public String getPass_board_date() {
		return pass_board_date;
	}
	public void setPass_board_date(String pass_board_date) {
		this.pass_board_date = pass_board_date;
	}
	public String getPass_board_contents() {
		return pass_board_contents;
	}
	public void setPass_board_contents(String pass_board_contents) {
		this.pass_board_contents = pass_board_contents;
	}
	
	
	
	
}
