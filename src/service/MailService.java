package service;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailService {
    private String mail = "ahmedaziz.rebhi@esprit.tn";
    private String password = "223JMT0181";

    public void envoyer(String recepient, String newPassword) {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.starttls.required", "true");
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mail, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("ahmedaziz.rebhi@esprit.tn"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recepient));
            message.setSubject("Password Reset");
            message.setText("Dear User,\n\nYour password has been reset. Your new password is: " + newPassword + "\n\nPlease log in using this new password and change it immediately for security reasons.\n\nThank you,\nTun'ART Admin");

            // Send the message
            Transport.send(message);

            System.out.println("Password reset email sent to " + recepient);
        } catch (MessagingException e) {
            System.out.println("Error sending password reset email: " + e.getMessage());
        }
    }
}
