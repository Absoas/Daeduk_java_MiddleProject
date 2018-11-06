package kr.or.ddit.myintro;

import java.io.Serializable;

public class MyIntroVO implements Serializable{
	private String my_no;
	private String my_id;
	private String my_career;
	private String my_hope;
	private String my_intro;
	
	public String getMy_no() {
		return my_no;
	}
	public void setMy_no(String my_no) {
		this.my_no = my_no;
	}
	public String getMy_id() {
		return my_id;
	}
	public void setMy_id(String my_id) {
		this.my_id = my_id;
	}
	public String getMy_career() {
		return my_career;
	}
	public void setMy_career(String my_career) {
		this.my_career = my_career;
	}
	public String getMy_hope() {
		return my_hope;
	}
	public void setMy_hope(String my_hope) {
		this.my_hope = my_hope;
	}
	public String getMy_intro() {
		return my_intro;
	}
	public void setMy_intro(String my_intro) {
		this.my_intro = my_intro;
	}
	
	
}
