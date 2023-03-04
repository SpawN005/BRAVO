package com.example.PIDEV;

import entity.Dons;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import service.ServiceDons;
import service.ServiceOeuvre;
import java.time.*;

import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.List;
import java.util.ResourceBundle;

import static java.lang.Integer.parseInt;


public class ModifyDonsController implements Initializable {

    @FXML
    private TableView<Dons> tableDonations;

    @FXML
    private TableColumn<Dons, Integer> colId;

    @FXML
    private TableColumn<Dons, String> colTitle;

    @FXML
    private TableColumn<Dons, String> colDescription;

    @FXML
    private TableColumn<Dons, String> colDateCreation;

    @FXML
    private TableColumn<Dons, String> colDateExpiration;

    @FXML
    private TableColumn<Dons, Integer> colAmount;

    @FXML
    private TableColumn<Dons, String> colOwner;

    private ObservableList<Dons> donationsData = FXCollections.observableArrayList();

    private ServiceDons serviceDons = new ServiceDons();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Map the columns to the Dons object properties
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colDateCreation.setCellValueFactory(new PropertyValueFactory<>("date_creation"));
        colDateExpiration.setCellValueFactory(new PropertyValueFactory<>("date_expiration"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));

        // Load the data from the database and add it to the table
        List<Dons> donations = serviceDons.readAll();
        donationsData.addAll(donations);
        tableDonations.setItems(donationsData);
    }



    @FXML
    void updatebtn(ActionEvent event) {

    }



    public void Deletebtn(ActionEvent actionEvent) {
        ObservableList<Dons> selectedItems = tableDonations.getSelectionModel().getSelectedItems();
        ServiceDons serviceDons = new ServiceDons();
        for (Dons donation : selectedItems) {
            serviceDons.delete(donation); // Delete the selected donation from the database
            donationsData.remove(donation); // Remove the selected donation from the ObservableList
        }
        tableDonations.getSelectionModel().clearSelection(); // Clear the selection after deletion
    }

}

