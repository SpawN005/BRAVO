package entity;

import com.google.zxing.*;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import service.ServiceEvent;
import service.ServiceReservation;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class QRCode {
    private ServiceEvent SE = new ServiceEvent();
    private ServiceReservation SR = new ServiceReservation();
    private Reservation r= new Reservation();

    public QRCode() {
        Result result = null;
        try {
            // Lecture de l'image du QR code
            BufferedImage image = ImageIO.read(new File("qrcode.png"));

            // Création d'un objet LuminanceSource à partir de l'image
            LuminanceSource source = new BufferedImageLuminanceSource(image);

            // Création d'un objet BinaryBitmap à partir de l'objet LuminanceSource
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

            // Création d'un objet QRCodeReader
            QRCodeReader reader = new QRCodeReader();

            // Décodage du QR code
            result = reader.decode(bitmap);

            // Affichage des données du QR code
            System.out.println(result.getText());
        } catch (NotFoundException e) {
            System.err.println("Le QR code n'a pas été trouvé dans l'image.");
            return;
        } catch (IOException ex) {
            System.err.println("Erreur lors de la lecture de l'image du QR code.");
            return;
        } catch (FormatException e) {
            System.err.println("Format d'image non supporté.");
            return;
        } catch (ChecksumException e) {
            System.err.println("Le QR code est corrompu.");
            return;
        } catch (Exception e) {
            System.err.println("Une erreur est survenue lors du décodage du QR code.");
            return;
        }

        String qrCodeText = result.getText();
        System.out.println("QR code text: " + qrCodeText);

        // Récupération de la réservation à partir du QR code
        Reservation reservation = SR.readById(Integer.parseInt(qrCodeText));
        if (reservation == null) {
            System.err.println("La réservation n'a pas été trouvée dans la base de données.");
            return;
        }

        // Récupération de l'événement à partir de la réservation
        Event event = SE.readById(reservation.getId_event().getId());
        if (event == null) {
            System.err.println("L'événement n'a pas été trouvé dans la base de données.");
            return;
        }
        System.out.println("Nom de l'événement: " + event.getTitle());
        System.out.println("Date début: " + event.getDate_beg());
        System.out.println("Date fin: " + event.getDate_end());
        System.out.println("Description: " + event.getDescription());
        System.out.println("Image: " + event.getUrl());
        System.out.println("Nombre de places: " + event.getNb_placeMax());
    }
}
