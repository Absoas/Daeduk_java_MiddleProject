package kr.or.ddit.admin;

import java.io.Serializable;

public class AdminCalendarVO implements Serializable{
	
	String cor_regno;
	String cor_name;
	String cor_tel;
	String app_no;
	String creq_salary;
	String creq_career;
	String creq_no;
	String creq_content;
	String creq_startdate;
	String creq_enddate;
	
	public String getCor_tel() {
		return cor_tel;
	}
	public void setCor_tel(String cor_tel) {
		this.cor_tel = cor_tel;
	}
	public String getCor_name() {
		return cor_name;
	}
	public void setCor_name(String cor_name) {
		this.cor_name = cor_name;
	}
	public String getCor_regno() {
		return cor_regno;
	}
	public void setCor_regno(String cor_regno) {
		this.cor_regno = cor_regno;
	}
	public String getApp_no() {
		return app_no;
	}
	public void setApp_no(String app_no) {
		this.app_no = app_no;
	}

	public String getCreq_no() {
		return creq_no;
	}
	public void setCreq_no(String creq_no) {
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
	public String getCreq_salary() {
		return creq_salary;
	}
	public void setCreq_salary(String creq_salary) {
		this.creq_salary = creq_salary;
	}
	public String getCreq_career() {
		return creq_career;
	}
	public void setCreq_career(String creq_career) {
		this.creq_career = creq_career;
	}
	
	
	
	
}
