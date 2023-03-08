package com.example.PIDEV;

import entity.Event;
import entity.Reservation;
import entity.User;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import service.ServiceEvent;
import service.ServiceReservation;
import service.ServiceUser;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AffichageReservationController implements Initializable {

    @FXML
    private TableColumn<Reservation, String> nomParticipantColumn;
    @FXML
    private TableView<Reservation> reservationTableView;
    @FXML
    private TableColumn<Reservation, String> titreEventColumn;
    private ObservableList<Reservation> reservations = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Reservation, String> nbPlaceColumn;

    ServiceReservation sr = new ServiceReservation();
    List<Reservation> reservationList = sr.readAll();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            nomParticipantColumn.setCellValueFactory(cellData -> {
            Reservation reservation = cellData.getValue();
            User user = reservation.getId_participant();
            String nom_participant = user.getFirstName() + " " + user.getLastName();
            return new SimpleStringProperty(nom_participant);});

            titreEventColumn.setCellValueFactory(cellData -> {
                Reservation reservation = cellData.getValue();
                Event event = reservation.getId_event();
                String titre_event = event.getTitle();
                return new SimpleStringProperty(titre_event);
            });
            nbPlaceColumn.setCellValueFactory(new PropertyValueFactory<>("nb_place"));


        reservations.addAll(reservationList);
        reservationTableView.setItems(reservations);

    }

}
