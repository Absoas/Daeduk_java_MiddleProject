package kr.or.ddit.myApply;

import java.io.Serializable;

import javafx.fxml.Initializable;

public class MyApplyVO implements Serializable{
	private String cor_name;
	private String cor_addr;
	private String test_name;
	private String res_state;
	private String source;
	private String cor_id;
	private String jmem_id;
	private int test_no;
	
	
	public String getCor_id() {
		return cor_id;
	}
	public void setCor_id(String cor_id) {
		this.cor_id = cor_id;
	}
	public String getJmem_id() {
		return jmem_id;
	}
	public void setJmem_id(String jmem_id) {
		this.jmem_id = jmem_id;
	}
	
	public int getTest_no() {
		return test_no;
	}
	public void setTest_no(int test_no) {
		this.test_no = test_no;
	}
	public String getCor_name() {
		return cor_name;
	}
	public void setCor_name(String cor_name) {
		this.cor_name = cor_name;
	}
	public String getCor_addr() {
		return cor_addr;
	}
	public void setCor_addr(String cor_addr) {
		this.cor_addr = cor_addr;
	}
	public String getTest_name() {
		return test_name;
	}
	public void setTest_name(String test_name) {
		this.test_name = test_name;
	}
	public String getRes_state() {
		return res_state;
	}
	public void setRes_state(String res_state) {
		this.res_state = res_state;
	}
	
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}

	
}
