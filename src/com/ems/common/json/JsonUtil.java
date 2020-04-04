// 
// Decompiled by Procyon v0.5.36
// 

package com.ems.common.json;

import com.ems.common.util.EmsOption;
import java.util.HashMap;
import java.util.Set;
import org.json.simple.JSONObject;
import com.ems.common.util.EmsHashtable;

public class JsonUtil
{
    public static void convertJson(final EmsHashtable row, final JSONObject outJson) {
        Object key = null;
        final Set set = row.keySet();
        final Object[] obj = set.toArray();
        for (int j = 0; j < obj.length; ++j) {
            key = obj[j].toString();
            outJson.put((Object)key.toString(), (Object)row.getString(key.toString()));
        }
    }
    
    public static void convertJson(final int idx, final EmsHashtable row, final JSONObject outJson, final HashMap columnType) {
        try {
            final StringBuffer id = new StringBuffer("");
            Object key = null;
            final Set set = row.keySet();
            final String[] obj = (String[]) set.toArray(new String[set.size()]);
            FormObj formObj = null;
            Option option = null;
            final StringBuffer sb = new StringBuffer("");
            for (int j = 0; j < obj.length; ++j) {
                key = obj[j].toString();
                id.append(idx).append("_").append(key);
                formObj = (FormObj) columnType.get(key);
                if (formObj == null) {
                    sb.append(row.getString(key.toString()));
                }
                else if (formObj.getType().equals("SELECT")) {
                    sb.append("<SELECT NAME='" + id.toString() + "' ID='" + id.toString() + "' >");
                    option = formObj.getOption();
                    sb.append(EmsOption.getOption(option.k, option.v, row.getString(key.toString())));
                    sb.append("</SELECT>");
                }
                else if (formObj.getType().equals("INPUT")) {
                    sb.append("<input type='TEXT' ");
                    sb.append("NAME='").append(id).append("' ");
                    sb.append("ID='").append(id).append("' ");
                    sb.append("VALUE='").append(row.getString(key.toString())).append("' >");
                    sb.append("<div class='hidden_data'>").append(row.getString(key.toString())).append("</div>");
                }
                else if (formObj.getType().equals("CHECK")) {
                    sb.append("<input TYPE='checkbox' ");
                    sb.append("NAME='").append(id).append("' ");
                    sb.append("ID='").append(id).append("' ");
                    sb.append("VALUE='").append(row.getString(key.toString())).append("' >");
                }
                else {
                    sb.append(row.getString(key.toString()));
                }
                outJson.put((Object)key.toString(), (Object)sb.toString());
                id.setLength(0);
                sb.setLength(0);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @SuppressWarnings("unchecked")
	public static void convertJson(final JSONObject outJson, final HashMap columnType) {
        try {
            final int idx = -999;
            final StringBuffer id = new StringBuffer("");
            Object key = null;
            final Set set = columnType.keySet();
            final String[] obj = (String[]) set.toArray(new String[set.size()]);
            FormObj formObj = null;
            Option option = null;
            final StringBuffer sb = new StringBuffer("");
            for (int j = 0; j < obj.length; ++j) {
                key = obj[j].toString();
                id.append(idx).append("_").append(key);
                formObj = (FormObj) columnType.get(key);
                if (formObj == null) {
                    sb.append("");
                }
                else if (formObj.getType().equals("SELECT")) {
                    sb.append("<SELECT NAME='" + id.toString() + "' ID='" + id.toString() + "' >");
                    option = formObj.getOption();
                    sb.append(EmsOption.getOption(option.k, option.v, ""));
                    sb.append("</SELECT>");
                }
                else if (formObj.getType().equals("INPUT")) {
                    sb.append("<input type='TEXT' ");
                    sb.append("NAME='").append(id).append("' ");
                    sb.append("ID='").append(id).append("' ");
                    sb.append("VALUE='").append("").append("' >");
                    sb.append("<div class='hidden_data'>").append("").append("</div>");
                }
                else if (formObj.getType().equals("CHECK")) {
                    sb.append("<input TYPE='checkbox' ");
                    sb.append("NAME='").append(id).append("' ");
                    sb.append("ID='").append(id).append("' ");
                    sb.append("VALUE='").append("").append("' >");
                }
                else {
                    sb.append("");
                }
                outJson.put((Object)key.toString(), (Object)sb.toString());
                id.setLength(0);
                sb.setLength(0);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void main(final String[] args) {
        final Option LOV_SORT_SEQ = new Option("1|2|3", "1|2|3");
        final HashMap columnType = new HashMap();
        final JSONObject json = new JSONObject();
        columnType.put("CK", new FormObj("CHECK"));
        columnType.put("CD_GROUP_ID", new FormObj("INPUT"));
        columnType.put("CD_GROUP_NM", new FormObj("INPUT"));
        columnType.put("CD_ID", new FormObj("INPUT"));
        columnType.put("CD_MEANING", new FormObj("INPUT"));
        columnType.put("EXT1", new FormObj("INPUT"));
        columnType.put("EXT2", new FormObj("INPUT"));
        columnType.put("EXT3", new FormObj("INPUT"));
        columnType.put("SORT_SEQ", new FormObj("SELECT").setLov(LOV_SORT_SEQ));
        convertJson(json, columnType);
        System.out.println(json.toString());
    }
}
