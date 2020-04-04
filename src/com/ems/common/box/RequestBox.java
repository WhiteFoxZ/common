// 
// Decompiled by Procyon v0.5.36
// 

package com.ems.common.box;

import java.util.Set;
import java.util.Map;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;

public class RequestBox
{
    HttpServletRequest request;
    
    public RequestBox() {
        this.request = null;
    }
    
    public RequestBox(final HttpServletRequest request) {
        this.request = null;
        this.request = request;
    }
    
    public String getString(final String key) {
        return this.getString(key, "");
    }
    
    public String getString(final String key, final String defaultValue) {
        String returnVal = null;
        if (this.request != null && this.request.getParameter(key) != null) {
            returnVal = this.request.getParameter(key).toString();
        }
        if (returnVal == null) {
            returnVal = defaultValue;
        }
        return returnVal;
    }
    
    public double getDouble(final String key, final double defaultValue) {
        final String val = this.getString(key, new StringBuilder().append(defaultValue).toString());
        final double returnVal = Double.parseDouble(val);
        return returnVal;
    }
    
    public float getFloat(final String key, final float defaultValue) {
        final float returnVal = (float)this.getDouble(key, defaultValue);
        return returnVal;
    }
    
    public int getInt(final String key, final int defaultValue) {
        final int returnVal = (int)this.getDouble(key, defaultValue);
        return returnVal;
    }
    
    public long getLong(final String key, final long defaultValue) {
        final long returnVal = (long)this.getDouble(key, (double)defaultValue);
        return returnVal;
    }
    
    public static void main(final String[] args) {
        final float i = 0.0f;
        System.out.println(i);
    }
    
    public static boolean isOverIE50(final HttpServletRequest req) {
        final String user_agent = req.getHeader("user-agent");
        if (user_agent == null) {
            return false;
        }
        final int index = user_agent.indexOf("MSIE");
        if (index == -1) {
            return false;
        }
        int version = 0;
        try {
            version = Integer.parseInt(user_agent.substring(index + 5, index + 5 + 1));
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return version >= 5;
    }
    
    public static String iSoToKsc(final String str) {
        String returnValue = null;
        try {
            if (str == null || str.trim().length() == 0) {
                return "";
            }
            returnValue = new String(str.trim().getBytes("8859_1"), "EUC-KR");
        }
        catch (UnsupportedEncodingException e) {
            returnValue = str;
        }
        return returnValue;
    }
    
    public static String kscToIso(final String str) {
        String returnValue = null;
        try {
            if (str == null || str.trim().length() == 0) {
                return "";
            }
            returnValue = new String(str.trim().getBytes("EUC-KR"), "8859_1");
        }
        catch (UnsupportedEncodingException e) {
            returnValue = str;
        }
        return returnValue;
    }
    
    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("");
        try {
            final Map map = this.request.getParameterMap();
            final int size = map.size();
            final Set set = map.keySet();
            final String[] s = (String[]) set.toArray(new String[size]);
            String[] arrs = null;
            String ss = null;
            for (int i = 0; i < s.length; ++i) {
                if (map.get(s[i]).getClass().isArray()) {
                    arrs = (String[]) map.get(s[i]);
                    sb.append(String.valueOf(s[i]) + "=[");
                    for (int j = 0; j < arrs.length; ++j) {
                        sb.append(arrs[j]);
                        if (j < arrs.length - 1) {
                            sb.append(", ");
                        }
                    }
                    sb.append("]").append("\n");
                }
                else {
                    ss = (String) map.get(s[i]);
                    sb.append(String.valueOf(s[i]) + "=" + ss).append("\n");
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
