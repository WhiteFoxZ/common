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
    private static final String S_HOST = "smtp.naver.com";
    private static final String S_FROM = "bookseei@naver.com";

    private String ip = null ;

    public GMailSender() {
        this.log = Logger.getLogger((Class)this.getClass());
        try {
            this.ip = InetAddress.getLocalHost().getHostAddress();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param fromName 예약시스템관리자 , \uc608\uc57d\uc2dc\uc2a4\ud15c\uad00\ub9ac\uc790
     * @param subject
     * @param to
     * @param content
     */
    public void mailSender(final String fromName , final String subject, final String to,  String content) {


        if (this.ip.equals("172.29.43.44")) {
            this.log.debug("개발계입니다.실제 메일을 보내지 않습니다.");
            this.log.debug(("받는사람 : " + to));
            this.log.debug(("제목 : " + subject));
            this.log.debug(content);
        }
        else {
            try {
                final Properties props = new Properties();
                props.put("mail.smtp.starttls.enable", "true");
                props.put("mail.transport.protocol", "smtp");
                props.put("mail.smtp.host", S_HOST);
                props.put("mail.smtp.port", "465");
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.ssl.enable", "true");
                props.put("mail.smtp.ssl.trust", S_HOST);


                props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");



                final MyAuthenticator auth = new MyAuthenticator("bookseei@naver.com", "kim0525486#");
                this.log.debug(auth.toString());
                final Session mailSession = Session.getDefaultInstance(props, (Authenticator)auth);
                final Message msg = (Message)new MimeMessage(mailSession);
                msg.setFrom((Address)new InternetAddress(S_FROM, MimeUtility.encodeText(fromName, "UTF-8", "B")));
                final InternetAddress[] address = { new InternetAddress(to) };
                msg.setRecipients(Message.RecipientType.TO, (Address[])address);
                msg.setSubject(subject);
                msg.setSentDate(new Date());
                msg.setHeader("Content-Type", "text/html;charset=UTF-8");

                content = content.replaceAll("\n", "<br/>");

                msg.setContent(content, "text/html;charset=UTF-8");

                Transport.send(msg);

                this.log.debug("메일 발송을 완료하였습니다."); //\uba54\uc77c \ubc1c\uc1a1\uc744 \uc644\ub8cc\ud558\uc600\uc2b5\ub2c8\ub2e4.
            }
            catch (MessagingException ex) {
                System.out.println("mail send error : " + ex.getMessage());
            }
            catch (Exception e) {
                this.log.error(("error : " + e.getMessage()));
            }
        }
    }

    public static void main(final String[] args) throws Exception {
        new GMailSender().mailSender("테스트","hello", "fmjj007@naver.com", "hello");
    }
}
