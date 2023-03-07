package com.example.PIDEV;

import entity.Dons;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import service.ServiceDons;

import java.io.IOException;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static java.lang.Integer.parseInt;


public class MyDonsController implements Initializable {

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
        // Get the selected donation from the table view
        Dons selectedDonation = tableDonations.getSelectionModel().getSelectedItem();

        if (selectedDonation != null) {
            try {
                // Load the UpdateDonation view
                FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdateDons.fxml"));
                Parent root = loader.load();

                // Get the controller and set the selected donation
                UpdateDonsController updateDonationController = loader.getController();
                updateDonationController.setDon(selectedDonation);

                // Create a new scene and show it
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
                tableDonations.refresh();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // If no donation is selected, display an error message
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please select a donation to update.");
            alert.showAndWait();
        }
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

