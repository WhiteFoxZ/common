// 
// Decompiled by Procyon v0.5.36
// 

package com.ems.common.filter;

import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.FilterChain;
import javax.servlet.ServletResponse;
import javax.servlet.ServletRequest;
import org.apache.log4j.Logger;
import javax.servlet.Filter;

public class CharacterEncodingFilter implements Filter
{
    private Logger log;
    private String charSet;
    
    public CharacterEncodingFilter() {
        this.log = Logger.getLogger((Class)this.getClass());
        this.charSet = null;
    }
    
    public void destroy() {
        this.log.debug((Object)"Destory");
    }
    
    public void doFilter(final ServletRequest req, final ServletResponse resp, final FilterChain chain) throws IOException, ServletException {
        String uri = "";
        if (req instanceof HttpServletRequest) {
            final HttpServletRequest request = (HttpServletRequest)req;
            uri = request.getRequestURI();
            if (request.getMethod().equalsIgnoreCase("POST")) {
                if (uri.indexOf("ajax.do") != -1) {
                    request.setCharacterEncoding("utf-8");
                }
                else {
                    request.setCharacterEncoding(this.charSet);
                }
                this.log.debug((Object)"doFilter");
            }
        }
        chain.doFilter(req, resp);
    }
    
    public void init(final FilterConfig config) throws ServletException {
        this.charSet = config.getInitParameter("charSet");
        if (this.charSet == null) {
            this.charSet = "utf-8";
        }
    }
}
