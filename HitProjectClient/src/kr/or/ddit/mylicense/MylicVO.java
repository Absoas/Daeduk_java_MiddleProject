package kr.or.ddit.mylicense;

import java.io.Serializable;

public class MylicVO implements Serializable{
	private String mem_id;
	private String lic_code;
	private String lic_name;
	private String lic_class;
	private String lic_jugwan;
	
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getLic_code() {
		return lic_code;
	}
	public void setLic_code(String lic_code) {
		this.lic_code = lic_code;
	}
	public String getLic_name() {
		return lic_name;
	}
	public void setLic_name(String lic_name) {
		this.lic_name = lic_name;
	}
	public String getLic_class() {
		return lic_class;
	}
	public void setLic_class(String lic_class) {
		this.lic_class = lic_class;
	}
	public String getLic_jugwan() {
		return lic_jugwan;
	}
	public void setLic_jugwan(String lic_jugwan) {
		this.lic_jugwan = lic_jugwan;
	}
	
	
	
	

}
