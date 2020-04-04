// 
// Decompiled by Procyon v0.5.36
// 

package com.ems.common.util;

import java.util.Hashtable;

public class EmsHashtable extends Hashtable
{
    public String getString(final String s) {
        if (super.get(s) == null) {
            return "";
        }
        return super.get(s).toString();
    }
}
