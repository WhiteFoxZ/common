// 
// Decompiled by Procyon v0.5.36
// 

package com.ems.common.dbcp;

public class EmsMetaData
{
    String columnName;
    String columnType;
    int columnSize;
    
    public EmsMetaData(final String columnName, final String columnType, final int columnSize) {
        this.columnName = null;
        this.columnType = null;
        this.columnSize = 0;
        this.columnName = columnName;
        this.columnType = columnType;
        this.columnSize = columnSize;
    }
    
    public String getColumnName() {
        return this.columnName;
    }
    
    public String getColumnType() {
        return this.columnType;
    }
    
    public int getColumnSize() {
        return this.columnSize;
    }
    
    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("");
        sb.append(this.columnName).append("[").append(this.columnType).append("]:").append(this.columnSize);
        return sb.toString();
    }
}
