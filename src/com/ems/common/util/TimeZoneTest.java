// 
// Decompiled by Procyon v0.5.36
// 

package com.ems.common.util;

import java.util.TimeZone;

public class TimeZoneTest
{
    public static void main(final String[] args) {
        final TimeZone tz = TimeZone.getDefault();
        final String[] ids = TimeZone.getAvailableIDs();
        for (int i = 0; i < ids.length; ++i) {
            System.out.println(ids[i]);
        }
    }
}
