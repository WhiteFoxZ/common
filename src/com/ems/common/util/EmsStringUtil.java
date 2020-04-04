// 
// Decompiled by Procyon v0.5.36
// 

package com.ems.common.util;

public class EmsStringUtil
{
    public static String capitalize(final String s) {
        final String[] arr = s.split("_");
        final StringBuffer returnVal = new StringBuffer("");
        for (int i = 0; i < arr.length; ++i) {
            returnVal.append(arr[i].substring(0, 1).toUpperCase());
            returnVal.append(arr[i].substring(1).toLowerCase());
        }
        return returnVal.toString();
    }
    
    public static String gahang(final String org) {
        return org.replaceAll("&", "&amp;").replaceAll("<", "&lt").replaceAll(">", "&gt").replaceAll(" ", "&nbsp;").replaceAll("\"", "&quot").replaceAll("\\r\\n", "<BR>");
    }
    
    public static void main(final String[] args) {
        final String BudgetName = "2008_4Q";
        System.out.println(String.valueOf(BudgetName.substring(2, 4)) + "_Q" + BudgetName.substring(5, 6) + "(S)");
    }
}
