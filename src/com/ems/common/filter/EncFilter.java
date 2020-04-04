// 
// Decompiled by Procyon v0.5.36
// 

package com.ems.common.filter;

import javax.servlet.ServletException;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletResponse;
import javax.servlet.ServletRequest;
import javax.servlet.FilterConfig;
import javax.servlet.Filter;

public class EncFilter implements Filter
{
    private FilterConfig fc;
    private String enc;
    
    public void destroy() {
        this.fc = null;
    }
    
    public void doFilter(final ServletRequest arg0, final ServletResponse arg1, final FilterChain arg2) throws IOException, ServletException {
        arg0.setCharacterEncoding(this.enc);
        arg2.doFilter(arg0, arg1);
    }
    
    public void init(final FilterConfig fc) throws ServletException {
        this.fc = fc;
        this.enc = fc.getInitParameter("encoding");
    }
}
