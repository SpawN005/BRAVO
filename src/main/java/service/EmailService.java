package service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

    public class EmailService {
        private String mail = "aymen.majoul@esprit.tn";
        private String password = "Fe8m3aHHHj3";

        public void envoyer(String recepient, boolean b) {
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
                message.setFrom(new InternetAddress("aymen.majoul@esprit.tn"));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recepient));
                message.setSubject("Donation About to Expire");
                if (b){
                    message.setText("Dear Artiste,\n\nYour Donation is expired.");

                }else {
                    message.setText("Dear Artiste,\n\nYour Donation got less than 2 days to expire.");

                }

                // Send the message
                Transport.send(message);

                System.out.println("Reminder of a donation expiration " + recepient);
            } catch (MessagingException e) {
                System.out.println("Error sending password reset email: " + e.getMessage());
            }
        }
    }

