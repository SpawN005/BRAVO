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
       /* Dons selectedDons = tableDonations.getSelectionModel().getSelectedItem();

        if (selectedDons != null) {
            // get the new values from the form
            String title = colTitle.getText();
            String description = colDescription.getText();
            int amount = parseInt(colAmount.getText());
            String owner = colOwner.getText();
            LocalDate creationDate = LocalDate.parse(colDateCreation.getCellData(selectedDons));
            LocalDate expirationDate = LocalDate.parse(colDateExpiration.getCellData(selectedDons));

            // update the selected donation with the new values
            selectedDons.setTitle(title);
            selectedDons.setDescription(description);
            selectedDons.setAmount(amount);
            selectedDons.setOwner(owner);
            selectedDons.setDate_creation(new Timestamp(creationDate.atStartOfDay().toEpochSecond(ZoneOffset.UTC)));
            selectedDons.setDate_expiration(new Timestamp(expirationDate.atStartOfDay().toEpochSecond(ZoneOffset.UTC)));

            // update the donation in the database
            serviceDons.update(selectedDons);

            // show a success message
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Donation updated successfully");
            alert.showAndWait();

        } else {
            // show an error message if no donation is selected
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please select a donation to update");
            alert.showAndWait();
        } */
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

