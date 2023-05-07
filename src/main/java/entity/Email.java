package entity;

import com.google.zxing.*;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import javax.imageio.ImageIO;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
public class Email {
    private String username = "ahmedaziz.rebhi@esprit.tn";
    private String password = "223JMT0181";
    User user = new User();
    Event event= new Event();



    public Email() throws ChecksumException, NotFoundException, IOException, FormatException {

    }
        // Envoyer l'email avec le QR code en pièce jointe
        // sendEmailWithQRCode(recipient, subject, messageText, qrBytes);

        public void sendEmail (String recepientEmail, String subject, String message_content) throws
                IOException, RuntimeException, WriterException {
            // Générer le code QR
            int size = 250; // Taille en pixels du code QR
            QRCodeWriter qrWriter = new QRCodeWriter();
            Map<EncodeHintType, Object> hints = new HashMap<>();
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            BitMatrix bitMatrix = qrWriter.encode("https://www.facebook.com/profile.php?id=100090880503846", BarcodeFormat.QR_CODE, size, size,hints);
            BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB);
            for (int x = 0; x < size; x++) {
                for (int y = 0; y < size; y++) {
                    image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
                }
            }
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "png", baos);
            byte[] imageBytes = baos.toByteArray();
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

                // message.setText(message_content);
                MimeBodyPart textPart = new MimeBodyPart();
                textPart.setText(message_content);
                // Créer la partie image du message
                MimeBodyPart imagePart = new MimeBodyPart();
                imagePart.setContent(imageBytes, "image/png");
                imagePart.setFileName("qrcode.png");

                // Créer le message multipart avec les parties texte et image
                Multipart multipart = new MimeMultipart();
                multipart.addBodyPart(textPart);
                multipart.addBodyPart(imagePart);
                message.setContent(multipart);



                // Etape 3 : Envoyer le message
                Transport.send(message);
                System.out.println("Message_envoye");
            } catch (MessagingException | IOException e) {
                throw new RuntimeException(e);
            }
        }


    public void sendReminderEmail(Event event, User user) {
        LocalDateTime dateTimeDebut = LocalDateTime.parse(event.getDate_beg().toString());
        LocalDateTime dateTimeReminder = dateTimeDebut.minusHours(24);
        if(dateTimeReminder.isBefore(LocalDateTime.now())){ // Etape 1 : Création de la session
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


}


