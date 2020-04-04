// 
// Decompiled by Procyon v0.5.36
// 

package com.ems.common.json;

import java.util.Map;
import org.json.simple.JSONObject;

public class JSONString extends JSONObject
{
    public JSONString() {
    }
    
    public JSONString(final Map map) {
        super(map);
    }
    
    public String getString(final Object key) {
        final Object obj = super.get(key);
        if (obj == null) {
            return "";
        }
        return obj.toString();
    }
}
