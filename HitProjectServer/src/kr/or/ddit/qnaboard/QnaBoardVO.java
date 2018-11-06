package kr.or.ddit.qnaboard;

import java.io.Serializable;

public class QnaBoardVO implements Serializable{
	private int qna_board_no;
	private String qna_board_title;
	private String qna_board_writer; 
	private String qna_board_date;
	private String qna_board_contents;
	private int qna_board_views;
	
	
	public void setQna_board_no(int qna_board_no) {
		this.qna_board_no = qna_board_no;
	}
	public void setQna_board_title(String qna_board_title) {
		this.qna_board_title = qna_board_title;
	}
	public void setQna_board_writer(String qna_board_writer) {
		this.qna_board_writer = qna_board_writer;
	}
	public void setQna_board_date(String qna_board_date) {
		this.qna_board_date = qna_board_date;
	}
	public void setQna_board_contents(String qna_board_contents) {
		this.qna_board_contents = qna_board_contents;
	}
	public void setQna_board_views(int qna_board_views) {
		this.qna_board_views = qna_board_views;
	}
	public int getQna_board_no() {
		return qna_board_no;
	}
	public String getQna_board_title() {
		return qna_board_title;
	}
	public String getQna_board_writer() {
		return qna_board_writer;
	}
	public String getQna_board_date() {
		return qna_board_date;
	}
	public String getQna_board_contents() {
		return qna_board_contents;
	}
	public int getQna_board_views() {
		return qna_board_views;
	}
	
	
	
	
	
	
}
