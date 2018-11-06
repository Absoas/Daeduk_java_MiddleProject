package kr.or.ddit.creqboard;

import java.io.Serializable;

public class CReqBoardVO implements Serializable {
	private String cor_regno;
	private String cor_name;
	private String cor_tel;
	private int app_no;
	private int creq_salary;
	private String creq_career;
	private int creq_no;
	private String creq_content;
	private String creq_startdate;
	private String creq_enddate;
	private int creq_click;
	private int test_type;
	private String test_name;
	
	public int getTest_type() {
		return test_type;
	}

	public void setTest_type(int test_type) {
		this.test_type = test_type;
	}

	public String getCor_regno() {
		return cor_regno;
	}

	public void setCor_regno(String cor_regno) {
		this.cor_regno = cor_regno;
	}

	public String getCor_name() {
		return cor_name;
	}

	public void setCor_name(String cor_name) {
		this.cor_name = cor_name;
	}

	public String getCor_tel() {
		return cor_tel;
	}

	public void setCor_tel(String cor_tel) {
		this.cor_tel = cor_tel;
	}

	public int getApp_no() {
		return app_no;
	}

	public void setApp_no(int app_no) {
		this.app_no = app_no;
	}

	public int getCreq_salary() {
		return creq_salary;
	}

	public void setCreq_salary(int creq_salary) {
		this.creq_salary = creq_salary;
	}

	public String getCreq_career() {
		return creq_career;
	}

	public void setCreq_career(String creq_career) {
		this.creq_career = creq_career;
	}

	public int getCreq_no() {
		return creq_no;
	}

	public void setCreq_no(int creq_no) {
		this.creq_no = creq_no;
	}

	public String getCreq_content() {
		return creq_content;
	}

	public void setCreq_content(String creq_content) {
		this.creq_content = creq_content;
	}

	public String getCreq_startdate() {
		return creq_startdate;
	}

	public void setCreq_startdate(String creq_startdate) {
		this.creq_startdate = creq_startdate;
	}

	public String getCreq_enddate() {
		return creq_enddate;
	}

	public void setCreq_enddate(String creq_enddate) {
		this.creq_enddate = creq_enddate;
	}

	public int getCreq_click() {
		return creq_click;
	}

	public void setCreq_click(int creq_click) {
		this.creq_click = creq_click;
	}
	public String getTest_name() {
		return test_name;
	}

	public void setTest_name(String test_name) {
		this.test_name = test_name;
	}
}
