package kr.or.ddit.pattern;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class encodeController implements Initializable {
	
	@FXML Button encodebtn;
	@FXML Button decodebtn;
	@FXML TextField pass1, pass2, pass3;
	private String iv;
    private Key keySpec;
    AES256Util aes;
    
    

 /*   *//**
     * AES256Util ��ȣȭ, ��ȣȭ������ String key�� �޾Ƽ� ����Ű�� ����
     * @since 2018/10/13
     * @author ������
     * @params String key ��ȣȭ ���� �����ϰ� �ϱ����� key�� �Է�
     *//*
    public encodeController(String key) throws UnsupportedEncodingException {
        this.iv = key.substring(0, 16);
 
        byte[] keyBytes = new byte[16];
        byte[] b = key.getBytes("UTF-8");
        int len = b.length;
        if(len > keyBytes.length)
            len = keyBytes.length;
        System.arraycopy(b, 0, keyBytes, 0, len);
        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
 
        this.keySpec = keySpec;
    }
    
    *//**
     * aesEncode String���� �Է¹޾Ƽ� �� ���� ��ȣȭ(encoding) 
     * @since 2018/10/13
     * @author ������
     * @params String str ��ȣȭ �ϱ����� String ��
     * @return enStr �Է¹��� ���� ��ȣȭ ���� ��ȯ
     *//*
    public String aesEncode(String str) throws java.io.UnsupportedEncodingException, NoSuchAlgorithmException,
                                                     NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException,
                                                     IllegalBlockSizeException, BadPaddingException{
        Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
        c.init(Cipher.ENCRYPT_MODE, keySpec, new IvParameterSpec(iv.getBytes()));
 
        byte[] encrypted = c.doFinal(str.getBytes("UTF-8"));
        String enStr = new String(Base64.encodeBase64(encrypted));
 
        return enStr;
    }
    
    
    *//**
     * aesDecode ��ȣȭ�� String str�� �Է¹޾Ƽ� ��ȣȭ 
     * @since 2018/10/13
     * @author ������
     * @return 
     * @params String str ��ȣȭ �ϱ����� String ��
     * @return �Է¹��� ���� ��ȣȭ ���� ��ȯ
     *//*
    public String aesDecode(String str) throws java.io.UnsupportedEncodingException, NoSuchAlgorithmException,
                                                     NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException,
                                                     IllegalBlockSizeException, BadPaddingException {
        Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
        c.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(iv.getBytes("UTF-8")));
 
        byte[] byteStr = Base64.decodeBase64(str.getBytes());
 
        return new String(c.doFinal(byteStr),"UTF-8");
    }
	*/
    
   /* public void AES256Util(String pass1) throws UnsupportedEncodingException {
        this.iv = pass1.substring(0, 16);
 
        byte[] keyBytes = new byte[16];
        byte[] b = pass1.getBytes("UTF-8");
        int len = b.length;
        if(len > keyBytes.length)
            len = keyBytes.length;
        System.arraycopy(b, 0, keyBytes, 0, len);
        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
 
        this.keySpec = keySpec;
        
    }*/
	

	@FXML
	private void encodebtn(ActionEvent event) {
		String encode = null;
		String pass = pass1.getText();
			try {
				aes = new AES256Util("aes256-test-key!!");
				encode = aes.aesEncode(pass);
			} catch (InvalidKeyException | UnsupportedEncodingException | NoSuchAlgorithmException
					| NoSuchPaddingException | InvalidAlgorithmParameterException | IllegalBlockSizeException
					| BadPaddingException e) {
				e.printStackTrace();
			}
			pass2.setText(encode);
	}
	
	@FXML
	private void decodebtn(ActionEvent event) {
		String decode = null;
		String pass1 = pass2.getText();
		try {
			aes = new AES256Util("aes256-test-key!!");
			decode = aes.aesDecode(pass1);
		} catch (InvalidKeyException | UnsupportedEncodingException | NoSuchAlgorithmException | NoSuchPaddingException
				| InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
		}
		pass3.setText(decode);
		
	}
	
	
	
	
	
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	

}
