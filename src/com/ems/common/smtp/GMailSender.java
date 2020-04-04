// 
// Decompiled by Procyon v0.5.36
// 

package com.ems.common.smtp;

import javax.mail.MessagingException;
import javax.mail.Transport;
import java.util.Date;
import javax.mail.Message;
import javax.mail.Address;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeUtility;
import javax.mail.internet.MimeMessage;
import javax.mail.Authenticator;
import javax.mail.Session;
import java.util.Properties;
import java.net.InetAddress;
import org.apache.log4j.Logger;

public class GMailSender
{
    private Logger log;
    String ip;
    
    public GMailSender() {
        this.log = Logger.getLogger((Class)this.getClass());
        this.ip = null;
        try {
            this.ip = InetAddress.getLocalHost().getHostAddress();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void mailSender(final String subject, final String to, final String content) {
        final String host = "smtp.naver.com";
        final String from = "bookseei@naver.com";
        final String fromName = "\uc608\uc57d\uc2dc\uc2a4\ud15c\uad00\ub9ac\uc790";
        if (this.ip.equals("172.29.43.44")) {
            this.log.debug((Object)"\uac1c\ubc1c\uacc4\uc785\ub2c8\ub2e4.\uc2e4\uc81c \uba54\uc77c\uc744 \ubcf4\ub0b4\uc9c0 \uc54a\uc2b5\ub2c8\ub2e4.");
            this.log.debug((Object)("\ubc1b\ub294\uc0ac\ub78c : " + to));
            this.log.debug((Object)("\uc81c\ubaa9 : " + subject));
            this.log.debug((Object)content);
        }
        else {
            try {
                final Properties props = new Properties();
                props.put("mail.smtp.starttls.enable", "true");
                props.put("mail.transport.protocol", "smtp");
                props.put("mail.smtp.host", host);
                props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
                props.put("mail.smtp.port", "465");
                props.put("mail.smtp.auth", "true");
                final MyAuthenticator auth = new MyAuthenticator("bookseei@naver.com", "asdwsx2461");
                this.log.debug((Object)auth.toString());
                final Session mailSession = Session.getDefaultInstance(props, (Authenticator)auth);
                final Message msg = (Message)new MimeMessage(mailSession);
                msg.setFrom((Address)new InternetAddress(from, MimeUtility.encodeText(fromName, "UTF-8", "B")));
                final InternetAddress[] address = { new InternetAddress(to) };
                msg.setRecipients(Message.RecipientType.TO, (Address[])address);
                msg.setSubject(subject);
                msg.setSentDate(new Date());
                msg.setContent((Object)content, "text/html;charset=euc-kr");
                Transport.send(msg);
                this.log.debug((Object)"\uba54\uc77c \ubc1c\uc1a1\uc744 \uc644\ub8cc\ud558\uc600\uc2b5\ub2c8\ub2e4.");
            }
            catch (MessagingException ex) {
                System.out.println("mail send error : " + ex.getMessage());
            }
            catch (Exception e) {
                this.log.error((Object)("error : " + e.getMessage()));
            }
        }
    }
    
    public static void main(final String[] args) throws Exception {
        new GMailSender().mailSender("hello", "fmjj007@naver.com", "hello");
    }
}
