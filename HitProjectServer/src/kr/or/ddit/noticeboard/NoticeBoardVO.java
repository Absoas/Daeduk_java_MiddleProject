package kr.or.ddit.noticeboard;

import java.io.Serializable;

public class NoticeBoardVO implements Serializable {
	private int noti_board_no;
	private String noti_board_title;
	private String noti_board_content;
	private String noti_board_date;
	private int noti_board_lookup;
	private String noti_board_writer;
	
	
	public String getNoti_board_writer() {
		return noti_board_writer;
	}
	public void setNoti_board_writer(String noti_board_writer) {
		this.noti_board_writer = noti_board_writer;
	}
	public int getNoti_board_no() {
		return noti_board_no;
	}
	public void setNoti_board_no(int noti_board_no) {
		this.noti_board_no = noti_board_no;
	}
	public String getNoti_board_title() {
		return noti_board_title;
	}
	public void setNoti_board_title(String noti_board_title) {
		this.noti_board_title = noti_board_title;
	}
	public String getNoti_board_content() {
		return noti_board_content;
	}
	public void setNoti_board_content(String noti_board_content) {
		this.noti_board_content = noti_board_content;
	}
	public String getNoti_board_date() {
		return noti_board_date;
	}
	public void setNoti_board_date(String noti_board_date) {
		this.noti_board_date = noti_board_date;
	}
	public int getNoti_board_lookup() {
		return noti_board_lookup;
	}
	public void setNoti_board_lookup(int noti_board_lookup) {
		this.noti_board_lookup = noti_board_lookup;
	}
	
	
	
}