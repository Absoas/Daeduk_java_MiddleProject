package kr.or.ddit.corRegno;

import java.io.Serializable;

public class CorRegnoVO implements Serializable{
	private String cor_no;
	private String cor_name;
	private String cor_ceo;
	private String cor_tel;
	private String cor_post;
	private String cor_addr;
	
	public String getCor_no() {
		return cor_no;
	}
	public void setCor_no(String cor_no) {
		this.cor_no = cor_no;
	}
	public String getCor_name() {
		return cor_name;
	}
	public void setCor_name(String cor_name) {
		this.cor_name = cor_name;
	}
	public String getCor_ceo() {
		return cor_ceo;
	}
	public void setCor_ceo(String cor_ceo) {
		this.cor_ceo = cor_ceo;
	}
	public String getCor_tel() {
		return cor_tel;
	}
	public void setCor_tel(String cor_tel) {
		this.cor_tel = cor_tel;
	}
	public String getCor_post() {
		return cor_post;
	}
	public void setCor_post(String cor_post) {
		this.cor_post = cor_post;
	}
	public String getCor_addr() {
		return cor_addr;
	}
	public void setCor_addr(String cor_addr) {
		this.cor_addr = cor_addr;
	}
	
	
}
