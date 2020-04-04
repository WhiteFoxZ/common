// 
// Decompiled by Procyon v0.5.36
// 

package com.ems.common.util;

import java.util.TreeMap;
import java.util.Iterator;
import java.util.Set;
import java.util.HashMap;

public class EmsHashMap extends HashMap
{
    public String getString(final Object obj) {
        final Object value = this.get(obj);
        String returnVal = null;
        if (value == null) {
            returnVal = "";
        }
        else {
            returnVal = value.toString();
        }
        return returnVal;
    }
    
    public int getInt(final Object obj) {
        int returnVal = 0;
        final String s = this.getString(obj);
        if (!s.equals("")) {
            returnVal = Integer.parseInt(s);
        }
        return returnVal;
    }
    
    public double getDouble(final Object obj) {
        double returnVal = 0.0;
        final String s = this.getString(obj);
        if (!s.equals("")) {
            returnVal = Double.parseDouble(s);
        }
        return returnVal;
    }
    
    public void put(final String s, final double d) {
        this.put(s, new StringBuilder().append(d).toString());
    }
    
    public void put(final String s, final int d) {
        this.put(s, new StringBuilder().append(d).toString());
    }
    
    @Override
    public String toString() {
        final Set s = this.entrySet();
        final Iterator it = s.iterator();
        final StringBuffer sb = new StringBuffer("");
        while (it.hasNext()) {
            sb.append(it.next().toString()).append("\n");
        }
        return sb.toString();
    }
    
    public String toHtml() {
        final Set s = this.entrySet();
        final Iterator it = s.iterator();
        final StringBuffer sb = new StringBuffer("");
        while (it.hasNext()) {
            sb.append(it.next().toString()).append("<BR>");
        }
        return sb.toString();
    }
    
    public EmsHashMap removeComma() {
        final EmsHashMap map = new EmsHashMap();
        final Set key = this.keySet();
        final String[] s = (String[]) key.toArray(new String[key.size()]);
        for (int i = 0; i < s.length; ++i) {
            map.put(s[i], this.getString(s[i]).replaceAll(",", ""));
        }
        return map;
    }
    
    public static void main(final String[] args) {
        final TreeMap map = new TreeMap();
        map.put("BB", "\uc774\uc2b9\uc6a9");
        map.put("AA", "\uc548\uc885\ub0a8");
        System.out.println(map.toString());
    }
}
