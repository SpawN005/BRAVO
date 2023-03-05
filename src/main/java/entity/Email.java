package entity;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.awt.image.BufferedImage;

import java.io.IOException;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
public class Email {
    private String username = "meriam123.hammi@gmail.com";
    private String password = "pknwcusqdmwepsnq";
    User user = new User();
    Event event= new Event();


    public Email() throws IOException, WriterException {

    }
        // Envoyer l'email avec le QR code en pièce jointe
        // sendEmailWithQRCode(recipient, subject, messageText, qrBytes);

        public void sendEmail (String recepientEmail, String subject, String message_content, BufferedImage qrCodeImage) throws
        UnsupportedEncodingException, RuntimeException {
            // Etape 1 : Création de la session
            Properties props = new Properties();
            props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
            props.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");
            Session session = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                        @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(username, password);
                        }
                    });
            try {
                // Etape 2 : Création de l'objet Message
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress("meriam123.hammi@gmail.com", "tun'ART"));
                message.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(recepientEmail));
                message.setSubject(subject);
                message.setText(message_content);

                // Etape 3 : Envoyer le message
                Transport.send(message);
                System.out.println("Message_envoye");
            } catch (MessagingException | IOException e) {
                throw new RuntimeException(e);
            }
        }
        public static BufferedImage generateQRCodeImage (String text,int width, int height) throws
        WriterException, IOException {
            // Créer un objet QRCodeWriter
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            // Générer la matrice QR Code à partir du texte
            BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
            // Créer un objet BufferedImage pour contenir l'image générée
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            // Parcourir la matrice QR Code et dessiner les pixels de l'image
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    int grayValue = (bitMatrix.get(x, y) ? 0 : 1) & 0xff;
                    image.setRGB(x, y, (grayValue << 16) | (grayValue << 8) | grayValue);
                }
            }
            // Retourner l'image générée
            return image;

        }
    public void sendReminderEmail(Event event, User user) {
        // Etape 1 : Création de la session
        Properties props = new Properties();
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        Session session = Session.getInstance(props,
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        try {
            // Etape 2 : Création de l'objet Message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("meriam123.hammi@gmail.com", "tun'ART"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(user.getEmail()));
            message.setSubject("Rappel de l'événement : " + event.getTitle());

            // Calculer la date de début de l'événement moins 24 heures
            LocalDateTime dateTimeDebut = LocalDateTime.parse(event.getDate_beg().toString());
            LocalDateTime dateTimeReminder = dateTimeDebut.minusHours(24);



            // LocalDate eventDateTime = event.getDate_beg();
          //  LocalDate reminderDateTime = eventDateTime.minusDays(1);

            // Générer le corps du message
            String message_content = String.format("Bonjour %s,\n\nCeci est un rappel pour l'événement %s qui aura lieu demain .\n\nNous espérons vous y voir nombreux !\n\nCordialement,\nL'équipe tun'ART", user.getFirstName(), event.getTitle(),dateTimeReminder );

            message.setText(message_content);

            // Etape 3 : Envoyer le message
            Transport.send(message);
            System.out.println("Rappel envoyé à " + user.getEmail() + " pour l'événement " + event.getTitle());
        } catch (MessagingException | IOException e) {
            throw new RuntimeException(e);
        }
    }


}


