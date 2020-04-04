// 
// Decompiled by Procyon v0.5.36
// 

package com.ems.common.json;

public class FormObj
{
    String type;
    Option lov;
    
    public FormObj(final String type) {
        this.type = type;
    }
    
    public FormObj setLov(final Option lov) {
        this.lov = lov;
        return this;
    }
    
    public String getType() {
        return this.type.toUpperCase();
    }
    
    public Option getOption() {
        return this.lov;
    }
}
