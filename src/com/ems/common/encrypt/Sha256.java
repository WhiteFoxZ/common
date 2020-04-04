// 
// Decompiled by Procyon v0.5.36
// 

package com.ems.common.encrypt;

import java.security.MessageDigest;

public class Sha256
{
    public static String encrypt(final String planText) {
        try {
            final MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(planText.getBytes());
            final byte[] byteData = md.digest();
            final StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; ++i) {
                sb.append(Integer.toString((byteData[i] & 0xFF) + 256, 16).substring(1));
            }
            final StringBuffer hexString = new StringBuffer();
            for (int j = 0; j < byteData.length; ++j) {
                final String hex = Integer.toHexString(0xFF & byteData[j]);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
