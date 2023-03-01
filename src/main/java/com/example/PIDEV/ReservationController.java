package com.example.PIDEV;

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

import java.io.IOException;
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
    private void submit() {
        // Mise à jour des propriétés de la réservation avec les valeurs actuelles des champs


        int nbPlaces = nbPlace.getValue();


        // Vérifier si le nombre de places est valide
        if (nbPlaces <= 0) {
            // Afficher un message d'erreur

           showAlert("Nombre de places invalide", false);
        }

        ServiceUser SU =new ServiceUser();

        ServiceEvent SE= new ServiceEvent();

        Reservation r= new Reservation();
        r.setId_participant(SU.readById(2));
       r.setId_event(SE.readById(36));
     r.setConfirmed(checkBox.isSelected());
        r.setNb_place(nbPlace.getValue());
        SR.insert(r);


        FXMLLoader loader = new FXMLLoader(getClass().getResource("AffichageEvent.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        showAlert("Réservation effectuée", true);
        showAlert("Vous avez réservé " + nbPlaces + " places.",true);

        id_event.getScene().setRoot(root);

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
