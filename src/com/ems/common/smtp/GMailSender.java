//
// Decompiled by Procyon v0.5.36
//

package com.ems.common.smtp;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import org.apache.log4j.Logger;

import com.ems.common.util.EmsStringUtil;

public class GMailSender {

	private static Logger log = Logger.getLogger(GMailSender.class.getName());

	private static final String S_HOST = "smtp.naver.com";
	private static final String S_FROM = "bookseei@naver.com";

	private String osName = null;

	public GMailSender() {

		Properties pr = System.getProperties();

		osName = pr.getProperty("os.name");

		log.debug("OS Name : " + pr.getProperty("os.name"));

	}

	public void mailSender(final String fromName , final String subject, final String to,  String content, boolean html) {
    	InetAddress ip=null;

    	try {
			 ip = InetAddress.getLocalHost();
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

    	//윈도우에서는 보내지 않는다, 단 집에서는 보낸다.
        if (osName.equals("Windows 10")	&& !ip.toString().contains("192.168.0") ) {
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
                final Session mailSession = Session.getInstance(props, (Authenticator)auth);
                final Message msg = (Message)new MimeMessage(mailSession);
                msg.setFrom((Address)new InternetAddress(S_FROM, MimeUtility.encodeText(fromName, "UTF-8", "B")));

                InternetAddress[] address = null;

                String[] mailto = to.split(",");

                address = new InternetAddress[mailto.length];

                for(int i=0; i<mailto.length; i++) {
                	address[i]=new InternetAddress(mailto[i]);
                	System.out.println(i + ":"+address[i]);
                }

                msg.setRecipients(Message.RecipientType.TO, address);
                msg.setSubject(subject);
                msg.setSentDate(new Date());

                if(html) {

                	msg.setHeader("Content-Type", "text/html;charset=UTF-8");

                	content =  EmsStringUtil.gahangBR(content);

                    msg.setContent(content, "text/html;charset=UTF-8");
                }else {
                	msg.setText(content);
                }

                Transport.send(msg);

                this.log.debug("메일 발송을 완료하였습니다.");
            }
            catch (MessagingException ex) {
            	this.log.error("mail send error : " + ex.getMessage());
            }
            catch (Exception e) {
                this.log.error(("error : " + e.getMessage()));
            }
        }
    }

	/**
	 *
	 * @param fromName 예약시스템관리자 , \uc608\uc57d\uc2dc\uc2a4\ud15c\uad00\ub9ac\uc790
	 * @param subject
	 * @param to
	 * @param content
	 */
	public void mailSender(final String fromName, final String subject, final String to, String content) {
		mailSender(fromName, subject, to, content, true);
	}

	public static void main(final String[] args) throws Exception {
//        new GMailSender().mailSender("테스트","hello", "fmjj007@naver.com", "hello");

//    	InetAddress ip = InetAddress.getLocalHost();
//
//    	System.out.println(ip.toString().contains("192.168.0"));
//    	System.out.println(ip.toString().contains("192.167.0"));

		String s = "aaa\r\nbbb";

		System.out.println(EmsStringUtil.gahangBR(s));

//    	System.out.println(  "escapeHtml3 "+StringEscapeUtils.escapeHtml3(s) );
//    	System.out.println(  "escapeHtml4 "+StringEscapeUtils.escapeHtml4(s) );

	}

}
