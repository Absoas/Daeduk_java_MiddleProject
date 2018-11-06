package kr.or.ddit.revboard;

import java.io.Serializable;

public class RevBoardVO implements Serializable{
	private int rev_board_no;
	private String rev_board_title;
	private String rev_board_writer; 
	private String rev_board_date;
	private String rev_board_contents;
	private int rev_board_views;
	
	
	public void setRev_board_no(int rev_board_no) {
		this.rev_board_no = rev_board_no;
	}
	public void setRev_board_title(String rev_board_title) {
		this.rev_board_title = rev_board_title;
	}
	public void setRev_board_writer(String rev_board_writer) {
		this.rev_board_writer = rev_board_writer;
	}
	public void setRev_board_date(String rev_board_date) {
		this.rev_board_date = rev_board_date;
	}
	public void setRev_board_contents(String rev_board_contents) {
		this.rev_board_contents = rev_board_contents;
	}
	public void setRev_board_views(int rev_board_views) {
		this.rev_board_views = rev_board_views;
	}
	public int getRev_board_no() {
		return rev_board_no;
	}
	public String getRev_board_title() {
		return rev_board_title;
	}
	public String getRev_board_writer() {
		return rev_board_writer;
	}
	public String getRev_board_date() {
		return rev_board_date;
	}
	public String getRev_board_contents() {
		return rev_board_contents;
	}
	public int getRev_board_views() {
		return rev_board_views;
	}
	
	
	
}
