package kr.or.ddit.calendar;

import java.io.Serializable;

public class MemoCalendarVO implements Serializable{
	
	String memo_no;
	String memo_date;
	String memo_content;
	String mem_id;
	String memo_title;
	
	
	public String getMemo_title() {
		return memo_title;
	}
	public void setMemo_title(String memo_title) {
		this.memo_title = memo_title;
	}
	
	public String getMemo_no() {
		return memo_no;
	}
	public void setMemo_no(String memo_no) {
		this.memo_no = memo_no;
	}
	public String getMemo_date() {
		return memo_date;
	}
	public void setMemo_date(String memo_date) {
		this.memo_date = memo_date;
	}
	public String getMemo_content() {
		return memo_content;
	}
	public void setMemo_content(String memo_content) {
		this.memo_content = memo_content;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	
	

}
