package kr.or.ddit.jmemlist;

import java.io.Serializable;

public class JMemListVO implements Serializable{
	//A.MEM_ID,A.MEM_NAME,b.jMEM_TEL,a.mem_image,a.MEM_MAIL,B.JMEM_ADDR,B.JMEM_REGNO,C.MY_CAREER,C.MY_HOPE,C.MY_INTRO,b.jmem_click
	private String mem_id;
	private String mem_name;
	private String jmem_tel;
	private String mem_image;
	private String mem_mail;
	private String jmem_addr;
	private String jmem_regno;
	private String my_career;
	private String my_hope;
	private String my_intro;
	private int	jmem_click;
	

	public String getJmem_tel() {
		return jmem_tel;
	}
	public void setJmem_tel(String jmem_tel) {
		this.jmem_tel = jmem_tel;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getMem_name() {
		return mem_name;
	}
	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}
	public String getMem_image() {
		return mem_image;
	}
	public void setMem_image(String mem_image) {
		this.mem_image = mem_image;
	}
	public String getMem_mail() {
		return mem_mail;
	}
	public void setMem_mail(String mem_mail) {
		this.mem_mail = mem_mail;
	}
	public String getJmem_addr() {
		return jmem_addr;
	}
	public void setJmem_addr(String jmem_addr) {
		this.jmem_addr = jmem_addr;
	}
	public String getJmem_regno() {
		return jmem_regno;
	}
	public void setJmem_regno(String jmem_regno) {
		this.jmem_regno = jmem_regno;
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
	public int getJmem_click() {
		return jmem_click;
	}
	public void setJmem_click(int jmem_click) {
		this.jmem_click = jmem_click;
	}
	
	
}
