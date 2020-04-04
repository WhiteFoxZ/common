// 
// Decompiled by Procyon v0.5.36
// 

package com.ems.common.encrypt;

import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.codec.binary.Base64;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.Cipher;
import java.io.UnsupportedEncodingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

public class AES256Util
{
    private String iv;
    private Key keySpec;
    
    public AES256Util(final String key) throws UnsupportedEncodingException {
        this.iv = key.substring(0, 16);
        final byte[] keyBytes = new byte[16];
        final byte[] b = key.getBytes("UTF-8");
        int len = b.length;
        if (len > keyBytes.length) {
            len = keyBytes.length;
        }
        System.arraycopy(b, 0, keyBytes, 0, len);
        final SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
        this.keySpec = keySpec;
    }
    
    public String encrypt(final String str) throws NoSuchAlgorithmException, GeneralSecurityException, UnsupportedEncodingException {
        final Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
        c.init(1, this.keySpec, new IvParameterSpec(this.iv.getBytes()));
        final byte[] encrypted = c.doFinal(str.getBytes("UTF-8"));
        final String enStr = new String(Base64.encodeBase64(encrypted));
        return enStr;
    }
    
    public String decrypt(final String str) throws NoSuchAlgorithmException, GeneralSecurityException, UnsupportedEncodingException {
        final Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
        c.init(2, this.keySpec, new IvParameterSpec(this.iv.getBytes()));
        final byte[] byteStr = Base64.decodeBase64(str.getBytes());
        return new String(c.doFinal(byteStr), "UTF-8");
    }
    
    public static void main(final String[] args) {
        try {
            final AES256Util aes = new AES256Util("asdwsx1031902461");
            final String str = "";
            System.out.println(aes.decrypt(str));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
