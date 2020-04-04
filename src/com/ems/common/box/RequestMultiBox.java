// 
// Decompiled by Procyon v0.5.36
// 

package com.ems.common.box;

import java.util.Enumeration;
import com.oreilly.servlet.MultipartRequest;

public class RequestMultiBox
{
    MultipartRequest multi;
    
    public RequestMultiBox(final MultipartRequest multi) {
        this.multi = null;
        this.multi = multi;
    }
    
    public String getString(final String key) {
        return this.getString(key, "");
    }
    
    public String getString(final String key, final String defaultValue) {
        String returnVal = null;
        if (this.multi != null && this.multi.getParameter(key) != null) {
            System.out.println("sssssssssssssss");
            returnVal = this.multi.getParameter(key).toString();
        }
        if (returnVal == null) {
            returnVal = defaultValue;
            System.out.println("111111111111");
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
    
    @Override
    public String toString() {
        final Enumeration en = this.multi.getParameterNames();
        String key = null;
        String[] values = null;
        final StringBuffer sb = new StringBuffer("");
        while (en.hasMoreElements()) {
            key = en.nextElement().toString();
            values = this.multi.getParameterValues(key);
            sb.append(key).append("=").append("[");
            for (int i = 0; i < values.length; ++i) {
                sb.append(values[i]);
                if (i < values.length - 1) {
                    sb.append(",");
                }
            }
            sb.append("]").append("\n");
        }
        return sb.toString();
    }
}
