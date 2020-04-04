// 
// Decompiled by Procyon v0.5.36
// 

package com.ems.common.dbcp;

import org.apache.log4j.Logger;
import org.apache.commons.dbcp.BasicDataSource;

public class DataSource extends BasicDataSource
{
    private Logger log;
    
    public DataSource() {
        this.log = Logger.getLogger((Class)this.getClass());
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer("");
        sb.append(super.toString()).append(" MaxActive ").append(this.getMaxActive()).append(" MaxIdle ").append(this.getMaxIdle()).append(" NumActive: " + this.getNumActive()).append(" NumIdle: " + this.getNumIdle());
        return sb.toString();
    }
}
