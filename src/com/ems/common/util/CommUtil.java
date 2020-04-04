// 
// Decompiled by Procyon v0.5.36
// 

package com.ems.common.util;

import java.util.Collection;
import java.util.Set;
import java.util.TreeMap;
import com.ems.common.json.JSONString;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Enumeration;
import java.util.Hashtable;
import javax.servlet.ServletContext;

public class CommUtil
{
    public CommUtil(final ServletContext application) {
    }
    
    public static String concat(String org, final int len) {
        if (org.length() > len) {
            org = String.valueOf(org.substring(0, len)) + "...";
        }
        return org;
    }
    
    public static String concat2(final String org, final int size) {
        if (org.length() > size) {
            return org.substring(org.length() - size, org.length());
        }
        return org;
    }
    
    public static String replacePreview(final Object o) {
        final Hashtable hash = new Hashtable();
        hash.put("\\r\\n", "<br>");
        hash.put("'", "`");
        hash.put("\"", "``");
        String org = o.toString();
        final Enumeration en = hash.keys();
        String key = null;
        while (en.hasMoreElements()) {
            key = en.nextElement().toString();
            org = org.replaceAll(key, hash.get(key).toString());
        }
        return org;
    }
    
    public static int parseInt(final String str) {
        if (str == null || str.equals("")) {
            return 0;
        }
        return Integer.parseInt(str);
    }
    
    public static double parseDouble(final String str) {
        if (str == null || str.equals("")) {
            return 0.0;
        }
        try {
            return Double.parseDouble(str);
        }
        catch (Exception e) {
            return 0.0;
        }
    }
    
    public static String getClientOS(String userAgent) {
        String os = "";
        userAgent = userAgent.toLowerCase();
        if (userAgent.indexOf("windows nt 6.1") > -1) {
            os = "Windows7";
        }
        else if (userAgent.indexOf("windows nt 6.2") > -1 || userAgent.indexOf("windows nt 6.3") > -1) {
            os = "Windows8";
        }
        else if (userAgent.indexOf("windows nt 6.0") > -1) {
            os = "WindowsVista";
        }
        else if (userAgent.indexOf("windows nt 5.1") > -1) {
            os = "WindowsXP";
        }
        else if (userAgent.indexOf("windows nt 5.0") > -1) {
            os = "Windows2000";
        }
        else if (userAgent.indexOf("windows nt 4.0") > -1) {
            os = "WindowsNT";
        }
        else if (userAgent.indexOf("windows 98") > -1) {
            os = "Windows98";
        }
        else if (userAgent.indexOf("windows 95") > -1) {
            os = "Windows95";
        }
        else if (userAgent.indexOf("iphone") > -1) {
            os = "iPhone";
        }
        else if (userAgent.indexOf("ipad") > -1) {
            os = "iPad";
        }
        else if (userAgent.indexOf("android") > -1) {
            os = "android";
        }
        else if (userAgent.indexOf("mac") > -1) {
            os = "mac";
        }
        else if (userAgent.indexOf("linux") > -1) {
            os = "Linux";
        }
        else {
            os = userAgent;
        }
        return os;
    }
    
    public static String getClientBrowser(final String userAgent) {
        String browser = "";
        if (userAgent.indexOf("Trident/7.0") > -1) {
            browser = "ie11";
        }
        else if (userAgent.indexOf("MSIE 10") > -1) {
            browser = "ie10";
        }
        else if (userAgent.indexOf("MSIE 9") > -1) {
            browser = "ie9";
        }
        else if (userAgent.indexOf("MSIE 8") > -1) {
            browser = "ie8";
        }
        else if (userAgent.indexOf("Chrome/") > -1) {
            browser = "Chrome";
        }
        else if (userAgent.indexOf("Chrome/") == -1 && userAgent.indexOf("Safari/") >= -1) {
            browser = "Safari";
        }
        else if (userAgent.indexOf("Firefox/") >= -1) {
            browser = "Firefox";
        }
        else {
            browser = userAgent;
        }
        return browser;
    }
    
    public static boolean isMoblie(final String s) {
        final String[] mobileDevice = { "iPhone", "iPhone", "android" };
        for (int i = 0; i < mobileDevice.length; ++i) {
            if (mobileDevice[i].equals(s)) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean pattern(final String inputVal, final String pa) {
        final Pattern p = Pattern.compile(pa);
        final Matcher m = p.matcher(inputVal);
        return m.find();
    }
    
    private static String[] sortKey(final JSONString jsonSearchParam) {
        final Set key = jsonSearchParam.keySet();
        final String[] sKey = (String[]) key.toArray(new String[key.size()]);
        final String p = "(^P[0-9].*$)";
        final TreeMap<Integer, String> tmmV = new TreeMap<Integer, String>();
        for (int i = 0; i < sKey.length; ++i) {
            if (pattern(sKey[i], p)) {
                final String tmpIdx = sKey[i].substring(1, sKey[i].indexOf("_"));
                tmmV.put(new Integer(tmpIdx), sKey[i]);
            }
        }
        final Collection col = tmmV.values();
        final String[] sArr = (String[]) col.toArray(new String[col.size()]);
        return sArr;
    }
    
    public static void main(final String[] args) {
        final String p = "$aaaa)";
        final String sp = "bbb";
        System.out.println(p.startsWith("$"));
        System.out.println(sp.startsWith("\\$"));
    }
}
