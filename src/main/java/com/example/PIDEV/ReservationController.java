package com.example.PIDEV;

import entity.Email;
import entity.Event;
import entity.Reservation;
import entity.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.stage.Stage;
import service.ServiceEvent;
import service.ServiceReservation;
import service.ServiceUser;

import javax.mail.MessagingException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ResourceBundle;

public class ReservationController implements Initializable {

    @FXML
    private Button cancelButton;

    @FXML
    private TextField id_event;

    @FXML
    private TextField id_participant;

    @FXML
    private CheckBox checkBox;



    @FXML
    private ChoiceBox<Integer> nbPlace;

    @FXML
    private Button submitButton;



    private Reservation reservation;

    private ServiceReservation SR= new ServiceReservation();

Reservation r= new Reservation();
User u= new User();
Event e= new Event();
ServiceEvent SE= new ServiceEvent();


    @FXML
    private void handleCheckBoxAction(ActionEvent event) {
        if (checkBox.isSelected()) {
            System.out.println("CheckBox is checked");
        } else {
            System.out.println("CheckBox is unchecked");
        }
    }

  /*  public void setReservation(Reservation r) {
        this.reservation = r;

        // Mise à jour des champs avec les valeurs de la réservation existante

        r.setId_participant(SU.readById(2));
        r.setId_event(SE.readById(32));

        nbPlace.setItems(nbPlace.getItems());
        checkBox.setSelected(r.isConfirmed());
        reservation = new Reservation(r.getId(), r.getId_participant(), r.getId_event(), r.isConfirmed(), r.getNb_place());

    }*/
    private void showAlert(String message, boolean b) {
        Alert alert;
        if (b)
            alert = new Alert(Alert.AlertType.INFORMATION);
        else
            alert = new Alert(Alert.AlertType.ERROR);


        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();

    }

    @FXML
    private void submit() throws MessagingException, UnsupportedEncodingException {
int nbplace = nbPlace.getValue();
        int nbPlaces = nbPlace.getValue();


        // Vérifier si le nombre de places est valide
        if (nbPlaces <= 0) {
            // Afficher un message d'erreur

           showAlert("Nombre de places invalide", false);
        }

        ServiceUser SU =new ServiceUser();

        ServiceEvent SE= new ServiceEvent();

        Reservation r= new Reservation();
        r.setId_participant(SU.readById(7));
       // r.setId_event(event.getId());
        r.setId_event(SE.readById(39));
        r.setConfirmed(checkBox.isSelected());
        r.setNb_place(nbPlace.getValue());
        SR.insert(r);

        Event e = SE.readById(r.getId_event().getId());
        User currentUser = new User("ghailene","boughzala",123456789,"myriam123.hammi@gmail.com",
                "étudiant");
        if (e != null) {



            // L'événement a été trouvé
            int maxPlaces = e.getNb_placeMax(); // Nombre de places maximum de l'événement
            int reservedPlaces = r.getNb_place(); // Nombre de places réservées pour cet événement
            int availablePlaces = maxPlaces - reservedPlaces; // Nombre de places disponibles pour cet événement

            // Affichage des informations sur l'événement
            showAlert("L'événement " + e.getTitle() + " a " + maxPlaces + " places maximum.", true);
            showAlert("Il y a actuellement " + reservedPlaces + " places réservées pour cet événement.", true);
            showAlert("Il reste " + availablePlaces + " places disponibles pour cet événement.",true);


            e.setNb_placeMax(availablePlaces);
            SE.update(e);
            String recepient = currentUser.getEmail();
            String subject = "Confirmation de la réservation";
            String recepientEmail= recepient;
            String message_content="Votre réservation a été bien éffectué";

          Email email= new Email();
            email.sendEmail(recepientEmail,subject,message_content);




            FXMLLoader loader = new FXMLLoader(getClass().getResource("AffichageEvent.fxml"));
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }


            nbPlace.getScene().setRoot(root);

        } else {
            // pas de place pour cet event
            showAlert("Il ne reste plus de places disponibles pour cet événement", false);

        }



    }

    @FXML
    private void cancel() {
        // Fermeture de la fenêtre de réservation

        FXMLLoader loader = new FXMLLoader(getClass().getResource("AffichageEvent.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        submitButton.getScene().setRoot(root);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Integer> ListNbPlace = FXCollections.observableArrayList(0,1, 2, 3, 4, 5);
        nbPlace.setItems(ListNbPlace);
        //id_participant.getText();



    }


}
