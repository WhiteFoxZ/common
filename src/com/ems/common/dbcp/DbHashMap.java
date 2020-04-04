// 
// Decompiled by Procyon v0.5.36
// 

package com.ems.common.dbcp;

import java.util.Set;
import com.ems.common.util.EmsDateUtil;
import java.util.LinkedHashMap;

public class DbHashMap extends LinkedHashMap
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String userid;
    String progid;
    
    public DbHashMap(final String userid, final String progid) {
        this.userid = null;
        this.progid = null;
        this.userid = userid;
        if (progid.length() > 22) {
            this.progid = progid.substring(0, 22);
        }
        else {
            this.progid = progid;
        }
    }
    
    public void insert() {
        this.put("CREATED_OBJECT_ID", this.userid);
        this.put("CREATED_PROGRAM_ID", this.progid);
        this.put("CREATION_TIMESTAMP", EmsDateUtil.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
        this.put("LAST_UPDATED_OBJECT_ID", this.userid);
        this.put("LAST_UPDATE_PROGRAM_ID", this.progid);
        this.put("LAST_UPDATE_TIMESTAMP", EmsDateUtil.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
    }
    
    public void update() {
        this.put("LAST_UPDATED_OBJECT_ID", this.userid);
        this.put("LAST_UPDATE_PROGRAM_ID", this.progid);
        this.put("LAST_UPDATE_TIMESTAMP", EmsDateUtil.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
    }
    
    public static void main(final String[] args) {
        final LinkedHashMap lm = new LinkedHashMap();
        lm.put("fgh", "3");
        lm.put("def", "2");
        lm.put("asdf", "1");
        final Set s = lm.keySet();
        final String[] arr = (String[]) s.toArray(new String[s.size()]);
        for (int i = 0; i < arr.length; ++i) {
            System.out.println(arr[i]);
        }
    }
}
