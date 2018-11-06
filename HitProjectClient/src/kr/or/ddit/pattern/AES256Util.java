package kr.or.ddit.pattern;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 * AES256Util AES256 암호화 복호화 Class 
 */
public class AES256Util {
    private String iv;
    private Key keySpec;
 
    /**
     * AES256Util 암호화, 복호화를위한 String key를 받아서 고유키를 만듬
     * @since 2018/10/13
     * @author 조병규
     * @params String key 암호화 값을 일정하게 하기위한 key값 입력
     *
     */
    public AES256Util(String key) throws UnsupportedEncodingException {
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
 
    /**
     * aesEncode String값을 입력받아서 그 값을 암호화(encoding) 
     * @since 2018/10/13
     * @author 조병규
     * @params String str 암호화 하기위한 String 값
     * @return enStr 입력받은 값을 암호화 한후 반환
     */
    public String aesEncode(String str) throws java.io.UnsupportedEncodingException, NoSuchAlgorithmException,
                                                     NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException,
                                                     IllegalBlockSizeException, BadPaddingException{
        Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
        c.init(Cipher.ENCRYPT_MODE, keySpec, new IvParameterSpec(iv.getBytes()));
 
        byte[] encrypted = c.doFinal(str.getBytes("UTF-8"));
        String enStr = new String(Base64.encodeBase64(encrypted));
 
        return enStr;
    }
 
    /**
     * aesDecode 암호화된 String str을 입력받아서 복호화 
     * @since 2018/10/13
     * @author 조병규
     * @params String str 복호화 하기위한 String 값
     * @return 입력받은 값을 복호화 한후 반환
     */
    public String aesDecode(String str) throws java.io.UnsupportedEncodingException, NoSuchAlgorithmException,
                                                     NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException,
                                                     IllegalBlockSizeException, BadPaddingException {
        Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
        c.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(iv.getBytes("UTF-8")));
 
        byte[] byteStr = Base64.decodeBase64(str.getBytes());
 
        return new String(c.doFinal(byteStr),"UTF-8");
    }
}