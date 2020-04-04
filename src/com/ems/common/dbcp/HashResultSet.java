// 
// Decompiled by Procyon v0.5.36
// 

package com.ems.common.dbcp;

import java.io.Reader;
import java.sql.Clob;
import java.util.Date;
import com.ems.common.util.EmsDateUtil;
import com.ems.common.util.EmsHashtable;
import java.util.ArrayList;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.ResultSet;

public class HashResultSet
{
    ResultSet rs;
    
    public HashResultSet(final ResultSet rs) {
        this.rs = null;
        this.rs = rs;
    }
    
    public int getSize() throws SQLException {
        this.rs.beforeFirst();
        this.rs.last();
        final int row = this.rs.getRow();
        this.rs.beforeFirst();
        return row;
    }
    
    public String[] getMetaData() throws SQLException {
        final ResultSetMetaData metaData = this.rs.getMetaData();
        final int size = metaData.getColumnCount();
        final String[] columnName = new String[size];
        for (int i = 1; i <= size; ++i) {
            columnName[i - 1] = metaData.getColumnLabel(i);
        }
        return columnName;
    }
    
    public String[] getMetaType() throws SQLException {
        final ResultSetMetaData metaData = this.rs.getMetaData();
        final int size = metaData.getColumnCount();
        final String[] columnType = new String[size];
        for (int i = 1; i <= size; ++i) {
            columnType[i - 1] = metaData.getColumnTypeName(i);
        }
        return columnType;
    }
    
    public ArrayList getMetaHash() throws SQLException {
        final ResultSetMetaData metaData = this.rs.getMetaData();
        final ArrayList al = new ArrayList();
        for (int size = metaData.getColumnCount(), i = 1; i <= size; ++i) {
            al.add(new EmsMetaData(metaData.getColumnName(i), metaData.getColumnTypeName(i), metaData.getColumnDisplaySize(i)));
        }
        return al;
    }
    
    public EmsHashtable[] getHashData() throws SQLException {
        final int size = this.getSize();
        final EmsHashtable[] hashData = new EmsHashtable[size];
        final String[] columnName = this.getMetaData();
        final String[] columnType = this.getMetaType();
        for (int i = 0; i < size; ++i) {
            this.rs.next();
            hashData[i] = new EmsHashtable();
            for (int j = 0; j < columnName.length; ++j) {
                if (columnType[j].equals("DATE")) {
                    hashData[i].put(columnName[j], (this.rs.getString(columnName[j]) == null) ? "" : EmsDateUtil.getCurrentDate("yyyyMMdd hh:mm:ss", this.rs.getDate(columnName[j])));
                }
                else if (columnType[j].equals("TIMESTAMP")) {
                    hashData[i].put(columnName[j], (this.rs.getTimestamp(columnName[j]) == null) ? "" : EmsDateUtil.getCurrentDate("yyyyMMdd hh:mm:ss", this.rs.getTimestamp(columnName[j])));
                }
                else if (columnType[j].equals("CLOB")) {
                    hashData[i].put(columnName[j], (this.rs.getClob(columnName[j]) == null) ? "" : this.writeClob(this.rs.getClob(columnName[j])));
                }
                else {
                    hashData[i].put(columnName[j], (this.rs.getString(columnName[j]) == null) ? "" : this.rs.getString(columnName[j]));
                }
            }
        }
        return hashData;
    }
    
    public String writeClob(final Clob p_clob) {
        String str = null;
        try {
            if (p_clob == null) {
                return "";
            }
            final Reader clobStream = p_clob.getCharacterStream();
            final StringBuffer suggestions = new StringBuffer();
            int l_nchars = 0;
            final char[] l_buffer = new char[1024];
            while ((l_nchars = clobStream.read(l_buffer)) != -1) {
                suggestions.append(l_buffer, 0, l_nchars);
            }
            clobStream.close();
            str = suggestions.toString();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return str;
    }
    
    public static void main(final String[] args) {
    }
}
