package forgetPwHelper;

import dao.usrDao;
import domain.usrinfo;
import impl.UsrDAOImpl;

import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

public class EmailUtils {

    private static final String FROM = "jie1fuh@163.com";

    private static MimeMessage message = new MimeMessage(getSession());

    /**
     * @param user 未激活的用户
     * @Todo 注册成功后, 向用户发送帐户激活链接的邮件
     */
    public static void sendAccountActivateEmail(usrinfo user) {
        try {
            message.setSubject("IPPP - 帐户激活邮件");
            message.setSentDate(new Date());
            message.setFrom(new InternetAddress(FROM));
            message.setRecipient(RecipientType.TO, new InternetAddress(user.getUsrEmail()));
            String link = GenerateLinkUtils.generateActivateLink(user);
            message.setContent("点击链接激活帐户：<a href='" + link
                    + "'>" + link + "</a>", "text/html;charset=utf-8");
            // 发送邮件
            Transport.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param user
     * @Todo 发送重设密码链接的邮件
     */
    public static void sendResetPasswordEmail(usrinfo user) {
        Session session = getSession();
        MimeMessage message = new MimeMessage(session);
        try {
            message.setSubject("找回您的帐户密码");
            message.setSentDate(new Date());
            message.setFrom(new InternetAddress(FROM));
            message.setRecipient(RecipientType.TO, new InternetAddress(user.getUsrEmail()));
            message.setContent("要使用新的密码, 请使用以下链接启用密码:<br/><a href='"
                    + GenerateLinkUtils.generateResetPwdLink(user) + "'>点击重新设置密码</a>", "text/html;charset=utf-8");
            // 发送邮件
            Transport.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Session getSession() {
        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.smtp.host", "smtp.163.com");
        props.setProperty("mail.smtp.port", "25");
        props.setProperty("mail.smtp.auth", "true");
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                String password = null;
                InputStream is = EmailUtils.class.getResourceAsStream("password.dat");
                byte[] b = new byte[1024];
                try {
                    int len = is.read(b);
                    password = new String(b, 0, len);
                } catch (IOException e) {
                	System.out.println("check the content of 'password.dat'!");
                    e.printStackTrace();
                }
                return new PasswordAuthentication(FROM, password);
            }
        });
        return session;
    }
    public static void main(String[] args) {
    	usrDao dbu = new UsrDAOImpl();
    	usrinfo user = null;
		try {
			user = dbu.findByName("王元斗");
			System.out.println(user.getUsrEmail());
			EmailUtils.sendAccountActivateEmail(user );
			System.out.println("Connect! Check your e-mail ~...~");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Mail Error");
			e.printStackTrace();
		}
		
    }
}