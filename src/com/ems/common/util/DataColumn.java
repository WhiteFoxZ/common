// 
// Decompiled by Procyon v0.5.36
// 

package com.ems.common.util;

public class DataColumn
{
    String colName;
    String colDesc;
    String width;
    int colPostion;
    boolean orderable;
    
    public DataColumn(final String colName, final String colDesc, final String width, final boolean orderable) {
        this.colName = colName;
        this.colDesc = colDesc;
        this.width = width;
        this.orderable = orderable;
    }
    
    public String getColName() {
        return this.colName;
    }
    
    public void setColName(final String colName) {
        this.colName = colName;
    }
    
    public String getColDesc() {
        return this.colDesc;
    }
    
    public void setColDesc(final String colDesc) {
        this.colDesc = colDesc;
    }
    
    public String getWidth() {
        return this.width;
    }
    
    public void setWidth(final String width) {
        this.width = width;
    }
    
    public int getColPostion() {
        return this.colPostion;
    }
    
    public void setColPostion(final int colPostion) {
        this.colPostion = colPostion;
    }
    
    public boolean isOrderable() {
        return this.orderable;
    }
    
    public void setOrderable(final boolean orderable) {
        this.orderable = orderable;
    }
    
    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("");
        sb.append(this.colName).append(",").append(this.colDesc).append(",").append(this.getColPostion());
        return sb.toString();
    }
}
