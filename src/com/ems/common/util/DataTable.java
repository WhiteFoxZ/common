// 
// Decompiled by Procyon v0.5.36
// 

package com.ems.common.util;

import java.util.HashMap;
import java.util.ArrayList;

public class DataTable extends ArrayList
{
    private static final long serialVersionUID = 1L;
    private static String actionUrl;
    private static String tableId;
    private static String searchId;
    private static String save_form;
    HashMap colPostion;
    
    public DataTable(final String actionUrl, final String tableId, final String searchId, final String save_form) {
        this.colPostion = new HashMap();
        DataTable.actionUrl = actionUrl;
        DataTable.tableId = tableId;
        DataTable.searchId = searchId;
        DataTable.save_form = save_form;
    }
    
    public int getColPostion(final String colName) {
        final DataColumn d = (DataColumn) this.colPostion.get(colName);
        if (d != null) {
            return d.getColPostion();
        }
        System.out.println(String.valueOf(colName) + " DataColumn null");
        return 0;
    }
    
    @Override
    public boolean add(final Object obj) {
        final boolean bol = super.add(obj);
        if (bol) {
            final int size = this.size() - 1;
            final DataColumn d = (DataColumn)obj;
            d.setColPostion(size);
            this.colPostion.put(d.getColName(), d);
        }
        return bol;
    }
    
    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("datatable[\n");
        for (int i = 0; i < this.size(); ++i) {
            final DataColumn d = (DataColumn) this.get(i);
            sb.append(d.toString()).append("\n");
        }
        sb.append("]");
        return sb.toString();
    }
    
    public String columns() {
        final StringBuffer sb = new StringBuffer("");
        for (int i = 0; i < this.size(); ++i) {
            final DataColumn d = (DataColumn) this.get(i);
            sb.append("{");
            sb.append("'data' : '").append(d.getColName()).append("'");
            sb.append("}");
            if (i < this.size() - 1) {
                sb.append(",\n");
            }
            else {
                sb.append("\n");
            }
        }
        sb.append("");
        return sb.toString();
    }
    
    public String headers(final boolean response) {
        final StringBuffer sb = new StringBuffer("");
        int sum = 0;
        for (int i = 0; i < this.size(); ++i) {
            final DataColumn d = (DataColumn) this.get(i);
            if (d.getWidth().length() > 0) {
                if (response) {
                    sb.append("<th>");
                    sum += Integer.parseInt(d.getWidth());
                }
                else if (d.getWidth().equals("0")) {
                    sb.append("<th width='0'>");
                }
                else {
                    sb.append("<th width='").append(d.getWidth()).append("%' >");
                    sum += Integer.parseInt(d.getWidth());
                }
            }
            else {
                sb.append("<th>");
            }
            sb.append(d.getColDesc()).append("</th>\n");
        }
        sb.append("<!-- widths=").append(sum).append(" -->");
        return sb.toString();
    }
    
    public String widthZero() {
        final StringBuffer sb = new StringBuffer("");
        final StringBuffer sb2 = new StringBuffer("");
        for (int i = 0; i < this.size(); ++i) {
            final DataColumn d = (DataColumn) this.get(i);
            if (d.getWidth().equals("0")) {
                sb2.append(d.getColPostion()).append(",");
            }
        }
        String targets = sb2.toString();
        if (targets.length() > 0) {
            targets = targets.substring(0, targets.lastIndexOf(","));
            sb.append("[");
            sb.append(targets);
            sb.append("]");
        }
        if (sb2.toString().length() < 1) {
            sb.setLength(0);
            sb.append("null");
        }
        return sb.toString();
    }
    
    public String exportColumn() {
        final StringBuffer sb = new StringBuffer("");
        final StringBuffer sb2 = new StringBuffer("");
        for (int i = 0; i < this.size(); ++i) {
            final DataColumn d = (DataColumn) this.get(i);
            if (!d.getColName().equals("CK") && !d.getWidth().equals("0")) {
                sb2.append(d.getColPostion()).append(",");
            }
        }
        String targets = sb2.toString();
        if (targets.length() > 0) {
            targets = targets.substring(0, targets.lastIndexOf(","));
            sb.append("[");
            sb.append(targets);
            sb.append("]");
        }
        return sb.toString();
    }
    
    public String orderable() {
        final StringBuffer sb = new StringBuffer("");
        final StringBuffer sb2 = new StringBuffer("");
        for (int i = 0; i < this.size(); ++i) {
            final DataColumn d = (DataColumn) this.get(i);
            if (!d.isOrderable()) {
                sb2.append(d.getColPostion()).append(",");
            }
        }
        String targets = sb2.toString();
        if (targets.length() > 0) {
            targets = targets.substring(0, targets.lastIndexOf(","));
            sb.append("{ 'orderable': false,'targets': [");
            sb.append(targets);
            sb.append("] }");
        }
        return sb.toString();
    }
    
    public static String getActionUrl() {
        return DataTable.actionUrl;
    }
    
    public static void setActionUrl(final String actionUrl) {
        DataTable.actionUrl = actionUrl;
    }
    
    public static String getTableId() {
        return DataTable.tableId;
    }
    
    public static void setTableId(final String tableId) {
        DataTable.tableId = tableId;
    }
    
    public static String getSearchId() {
        return DataTable.searchId;
    }
    
    public static void setSearchId(final String searchId) {
        DataTable.searchId = searchId;
    }
    
    public static String getSave_form() {
        return DataTable.save_form;
    }
    
    public static void setSave_form(final String save_form) {
        DataTable.save_form = save_form;
    }
    
    public static void main(final String[] args) {
        final DataTable dt = new DataTable("Sb10100Action", "#table_list", "#search_form", "#save_form");
        dt.add(new DataColumn("CK", "\u25a0", "2", false));
        dt.add(new DataColumn("CD_GROUP_ID", "CD_GROUP_ID", "INPUT", true));
        dt.add(new DataColumn("AUTO_KEY", "AUTO_KEY", "0", false));
        System.out.println(dt.exportColumn());
    }
}
