//
// Decompiled by Procyon v0.5.36
//

package com.ems.common.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.text.StringEscapeUtils;
import org.apache.commons.text.translate.LookupTranslator;

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

    public static String gahangBR(final String org) {
        return org. replaceAll("(\r\n|\r|\n|\n\r)", "</br>");
    }

    public static void main(final String[] args) {
        final String BudgetName = "2008_4Q";
        System.out.println(String.valueOf(BudgetName.substring(2, 4)) + "_Q" + BudgetName.substring(5, 6) + "(S)");
    }





}
