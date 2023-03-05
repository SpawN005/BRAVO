package service;

import java.net.PasswordAuthentication;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class NewsletterService {
    private final String SMTP_SERVER = "smtp.gmail.com";
    private final String USERNAME = "tasnim ben hamouda";
    private final String PASSWORD = "223JFT1894";
    private final String EMAIL_FROM = "tasnim.benhamouda@esprit.tn";
    private final String EMAIL_SUBJECT = "Un nouveau blog est ajouté dans à notre plateform!";

    public void sendEmail(String emailTo, String blogTitle) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", SMTP_SERVER);
        props.put("mail.smtp.port", "587");


        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(EMAIL_FROM, PASSWORD);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(EMAIL_FROM));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(emailTo)
            );
            message.setSubject(EMAIL_SUBJECT);
            message.setText("Bonjour,\n\nUn nouveau blog intitulé '" + blogTitle + "' vient d'être ajouté à notre plateforme!");

            Transport.send(message);
            System.out.println("Le message a été envoyé avec succès!");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}