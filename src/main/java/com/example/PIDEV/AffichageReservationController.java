package com.example.PIDEV;

import entity.Event;
import entity.Reservation;
import entity.User;
import javafx.collections.FXCollections;
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
import service.ServiceUser;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AffichageReservationController implements Initializable {

    @FXML
    private TableView<Reservation> reservationTableView;

    @FXML
    private TableColumn<Reservation, Integer> idColumn= new TableColumn<>("id");

    @FXML
    private TableColumn<Reservation, String> participantColumn=new TableColumn<>("id_participant");

    @FXML
    private TableColumn<Reservation, String> eventColumn=new TableColumn<>("id_event");

    @FXML
    private TableColumn<Reservation, Integer> nbPlaceColumn=new TableColumn<>("nb_place");
    @FXML
    private Button btn_back;

    @FXML
    private TableColumn<Reservation, String> statusColumn=new TableColumn<>("isConfirmed");



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Connexion à la base de données
        try (Connection cnx = DriverManager.getConnection("jdbc:mysql://localhost:3306/bravo", "root", "")) {

            // Préparation de la requête SQL
            String sql = "SELECT * FROM reservation";
            PreparedStatement stmt = cnx.prepareStatement(sql);

            // Exécution de la requête SQL et récupération des résultats
            ResultSet rs = stmt.executeQuery();

            // Initialisation de la liste des réservations
            List<Reservation> reservations = new ArrayList<>();

            // On parcourt tous les résultats de la requête
            while (rs.next()) {

                // On récupère les attributs de chaque réservation
                int id = rs.getInt("id");
                int id_participant = rs.getInt("id_participant");
                int id_event = rs.getInt("id_event");
                boolean isConfirmed = rs.getBoolean("isConfirmed");
                int nb_place = rs.getInt("nb_place");

                // On récupère les objets User et Event associés à la réservation
                ServiceUser SU = new ServiceUser();
                User participant = SU.readById(id_participant);

                ServiceEvent SE = new ServiceEvent();
                Event event = SE.readById(id_event);

                // On crée l'objet Reservation et on l'ajoute à la liste
                Reservation reservation = new Reservation(id, participant, event, isConfirmed, nb_place);
                reservations.add(reservation);
            }

            // Configurer les cellules des colonnes
            idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            participantColumn.setCellValueFactory(new PropertyValueFactory<>("id_participant"));
            eventColumn.setCellValueFactory(new PropertyValueFactory<>("id_event"));
            statusColumn.setCellValueFactory(new PropertyValueFactory<>("isConfirmed"));
            nbPlaceColumn.setCellValueFactory(new PropertyValueFactory<>("nb_place"));

            // Ajouter les réservations à la TableView
            reservationTableView.setItems(FXCollections.observableArrayList(reservations));


        } catch (SQLException e) {
            // En cas d'erreur lors de l'accès à la base de données, on affiche le message d'erreur dans la console
            e.printStackTrace();
        }
    }
    @FXML
    void goBack(ActionEvent event) throws IOException {
        Stage stage = (Stage) btn_back.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("AffichageEvent.fxml"));
        primaryStage.setTitle("hello again");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }
}
