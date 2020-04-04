// 
// Decompiled by Procyon v0.5.36
// 

package com.ems.common.smtp;

import javax.mail.PasswordAuthentication;
import javax.mail.Authenticator;

public class MyAuthenticator extends Authenticator
{
    private String id;
    private String pw;
    
    public MyAuthenticator(final String id, final String pw) {
        this.id = id;
        this.pw = pw;
    }
    
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(this.id, this.pw);
    }
    
    public String toString() {
        return String.valueOf(this.id) + "/" + this.pw;
    }
}
