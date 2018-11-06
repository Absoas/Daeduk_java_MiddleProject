package kr.or.ddit.test;

import java.io.Serializable;

public class TestVO implements Serializable{
	private int test_no;
	private String test_name;
	private int test_type;
	private String test_content;
	private String test_file;
	
	public int getTest_no() {
		return test_no;
	}
	public void setTest_no(int test_no) {
		this.test_no = test_no;
	}
	public String getTest_name() {
		return test_name;
	}
	public void setTest_name(String test_name) {
		this.test_name = test_name;
	}
	public int getTest_type() {
		return test_type;
	}
	public void setTest_type(int test_type) {
		this.test_type = test_type;
	}
	public String getTest_content() {
		return test_content;
	}
	public void setTest_content(String test_content) {
		this.test_content = test_content;
	}
	public String getTest_file() {
		return test_file;
	}
	public void setTest_file(String test_file) {
		this.test_file = test_file;
	}
	
	

}
