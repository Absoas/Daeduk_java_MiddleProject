package kr.or.ddit.corApply;

import java.io.Serializable;

public class CorApplyVO implements Serializable{
	private String cor_id;
	private String mem_name;
	private String test_name;
	private int test_no;
	private String res_state;
	
	
	public String getCor_id() {
		return cor_id;
	}
	public void setCor_id(String cor_id) {
		this.cor_id = cor_id;
	}
	public String getMem_name() {
		return mem_name;
	}
	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}
	public String getTest_name() {
		return test_name;
	}
	public void setTest_name(String test_name) {
		this.test_name = test_name;
	}
	public int getTest_no() {
		return test_no;
	}
	public void setTest_no(int test_no) {
		this.test_no = test_no;
	}
	public String getRes_state() {
		return res_state;
	}
	public void setRes_state(String res_state) {
		this.res_state = res_state;
	}
	
	
}
