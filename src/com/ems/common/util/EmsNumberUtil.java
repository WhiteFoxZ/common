// 
// Decompiled by Procyon v0.5.36
// 

package com.ems.common.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class EmsNumberUtil
{
    public static String format(final long number, final String fmt) {
        final NumberFormat nf = NumberFormat.getInstance();
        ((DecimalFormat)nf).applyPattern(fmt);
        return nf.format(number);
    }
    
    public static String format(final double number, final String fmt) {
        final NumberFormat nf = NumberFormat.getInstance();
        ((DecimalFormat)nf).applyPattern(fmt);
        return nf.format(number);
    }
    
    public static String format(final float number, final String fmt) {
        final NumberFormat nf = NumberFormat.getInstance();
        ((DecimalFormat)nf).applyPattern(fmt);
        return nf.format(number);
    }
    
    public static String format(final int number, final String fmt) {
        final NumberFormat nf = NumberFormat.getInstance();
        ((DecimalFormat)nf).applyPattern(fmt);
        return nf.format(number);
    }
    
    public static String format(final String number, final String fmt) {
        final NumberFormat nf = NumberFormat.getInstance();
        ((DecimalFormat)nf).applyPattern(fmt);
        return nf.format(Integer.parseInt(number));
    }
    
    public static String format(final Number number, final String fmt) {
        final NumberFormat nf = NumberFormat.getInstance();
        ((DecimalFormat)nf).applyPattern(fmt);
        double dNumber = 0.0;
        dNumber = number.doubleValue();
        final String temp = nf.format(dNumber);
        if (temp.charAt(0) == '.') {
            return "0" + temp;
        }
        return temp;
    }
    
    public static void main(final String[] args) {
        System.out.println(format(1, "00"));
    }
}
