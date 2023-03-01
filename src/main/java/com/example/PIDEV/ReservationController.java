package com.example.PIDEV;

import entity.Event;
import entity.Reservation;
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
    private ServiceEvent SE =new ServiceEvent();
    private ServiceUser SU = new ServiceUser();
    private ServiceReservation SR= new ServiceReservation();

Reservation r= new Reservation();


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
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Nombre de places invalide");
            alert.setContentText("Veuillez sélectionner un nombre de places valide.");
            alert.showAndWait();
            return;
        }

        Reservation r= new Reservation(SU.readById(reservation.getId_participant().getId()),SE.readById(reservation.getId_event().getId()),checkBox.isSelected(),nbPlace.getValue());
        SR.insert(r);






        FXMLLoader loader = new FXMLLoader(getClass().getResource("AffichageEvent.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Réservation effectuée");
        alert.setContentText("Vous avez réservé " + nbPlaces + " places.");
        alert.showAndWait();
        submitButton.getScene().setRoot(root);

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
        ObservableList<Integer> ListNbPlace = FXCollections.observableArrayList(1, 2, 3, 4, 5);
        nbPlace.setItems(ListNbPlace);
        id_participant.getText();
        id_event.getText();
    }
}
