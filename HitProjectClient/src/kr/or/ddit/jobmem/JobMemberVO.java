package kr.or.ddit.jobmem;

import java.io.Serializable;

public class JobMemberVO implements Serializable{

   private String mem_id;
   private String mem_pass;
   private String mem_name;
   private String mem_mail;
   private String mem_type;
   private String mem_image;
   private String jmem_zip;
   private String jmem_addr;
   private String jmem_regno;
   private String jmem_state;
   private String jmem_id;
   private String jmem_tel;
   
   public String getJmem_tel() {
      return jmem_tel;
   }
   public void setJmem_tel(String jmem_tel) {
      this.jmem_tel = jmem_tel;
   }
   public String getJmem_id() {
      return jmem_id;
   }
   public void setJmem_id(String jmem_id) {
      this.jmem_id = jmem_id;
   }
   public String getMem_id() {
      return mem_id;
   }
   public void setMem_id(String mem_id) {
      this.mem_id = mem_id;
   }
   public String getMem_pass() {
      return mem_pass;
   }
   public void setMem_pass(String mem_pass) {
      this.mem_pass = mem_pass;
   }
   public String getMem_name() {
      return mem_name;
   }
   public void setMem_name(String mem_name) {
      this.mem_name = mem_name;
   }
   public String getMem_mail() {
      return mem_mail;
   }
   public void setMem_mail(String mem_mail) {
      this.mem_mail = mem_mail;
   }
   public String getMem_type() {
      return mem_type;
   }
   public void setMem_type(String mem_type) {
      this.mem_type = mem_type;
   }
   public String getMem_image() {
      return mem_image;
   }
   public void setMem_image(String mem_image) {
      this.mem_image = mem_image;
   }
   public String getJmem_zip() {
      return jmem_zip;
   }
   public void setJmem_zip(String jmem_zip) {
      this.jmem_zip = jmem_zip;
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
   public String getJmem_state() {
      return jmem_state;
   }
   public void setJmem_state(String jmem_state) {
      this.jmem_state = jmem_state;
   }
   
   
}