// 
// Decompiled by Procyon v0.5.36
// 

package com.ems.common.util;

public class EmsOption
{
    public static String getOption(final EmsHashtable[] hash, final String defaultValue) {
        final StringBuffer sb1 = new StringBuffer("");
        final StringBuffer sb2 = new StringBuffer("");
        for (int i = 0; i < hash.length; ++i) {
            sb1.append(hash[i].getString("CD_ID")).append("|");
            sb2.append(hash[i].getString("CD_MEANING")).append("|");
        }
        return getOption(sb1.toString(), sb2.toString(), defaultValue);
    }
    
    public static String getOption(final String key, final String value, final String defaultValue) {
        final StringBuffer sb1 = new StringBuffer("");
        if (key != null && value != null && key.length() > 0 && value.length() > 0 && !key.equals("") && !value.equals("")) {
            final String[] arrKey = key.split("\\|");
            final String[] arrValue = value.split("\\|");
            for (int size = Math.min(arrKey.length, arrValue.length), i = 0; i < size; ++i) {
                if (arrKey[i].equals(defaultValue)) {
                    sb1.append("<option value='").append(arrKey[i]).append("' selected >").append(arrValue[i]).append("</option>");
                }
                else {
                    sb1.append("<option value='").append(arrKey[i]).append("' >").append(arrValue[i]).append("</option>");
                }
            }
        }
        return sb1.toString();
    }
    
    public static String getOption(final Object obj, final String nameAttribute, final String valueAttribute, final String defaultValue) {
        final EmsHashtable[] hash = (EmsHashtable[])obj;
        final StringBuffer sb = new StringBuffer("");
        if (hash != null) {
            String key = null;
            String value = null;
            for (int i = 0; i < hash.length; ++i) {
                key = hash[i].get(nameAttribute).toString();
                value = hash[i].get(valueAttribute).toString();
                if (defaultValue.equals(key)) {
                    sb.append("<option value='").append(key).append("' selected >").append(value).append("</option>");
                }
                else {
                    sb.append("<option value='").append(key).append("' >").append(value).append("</option>");
                }
            }
        }
        return sb.toString();
    }
}
