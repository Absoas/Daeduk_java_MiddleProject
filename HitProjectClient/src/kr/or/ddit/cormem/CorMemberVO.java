package kr.or.ddit.cormem;

import java.io.Serializable;

public class CorMemberVO implements Serializable {
   private String mem_id;
   private String mem_pass;
   private String mem_name;
   private String mem_mail;
   private String mem_type;
   private String mem_image;
   private String cor_no;
   private String cor_name;
   private String cor_ceo;
   private String cor_tel;
   private String cor_post;
   private String cor_addr;
   private String cor_state;
   private String cor_id;
   private String cor_regno;

   public String getCor_regno() {
      return cor_regno;
   }

   public void setCor_regno(String cor_regno) {
      this.cor_regno = cor_regno;
   }

   public String getCor_id() {
      return cor_id;
   }

   public void setCor_id(String cor_id) {
      this.cor_id = cor_id;
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

   public String getCor_state() {
      return cor_state;
   }

   public void setCor_state(String cor_state) {
      this.cor_state = cor_state;
   }

}