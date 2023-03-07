package com.example.PIDEV;

import entity.Dons;
import entity.DonsUser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.LoggedInUser;
import service.ServiceDons;
import service.ServiceDonsUser;

public class DonateInfo {
    @FXML
    private TextField amounttextfield;

    @FXML
    private Button btnDonate;

    private ServiceDons serviceDons;
    private Stage dialogStage;
    private Dons don;

    @FXML
    private void initialize() {
        serviceDons = new ServiceDons();
        amounttextfield.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) { // Check if entered text is not a number
                amounttextfield.setText(newValue.replaceAll("[^\\d]", "")); // Remove non-numeric characters
            }
        });
    }


    public void setDon(Dons dons) {
        don= dons;

    }




    @FXML
    void Donate(ActionEvent event) {
        // Create a new instance of the ServiceDonsUser class
        ServiceDonsUser serviceDonsUser = new ServiceDonsUser();

        // Get the donation amount from the text field
        int donationAmount = Integer.parseInt(amounttextfield.getText());
        LoggedInUser loggedInUser = new LoggedInUser();
        // Update the amount field of the donuser object with the new donation amount
        if (donationAmount> don.getAmount()){
            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText("Amount exceeded");

            alert.showAndWait();



        }else {
            don.setAmount(donationAmount);
            don.setOwner(loggedInUser.getUser());
            System.out.println(don);
            DonsUser donUser = new DonsUser(loggedInUser.getUser(),don.getId(), donationAmount);
            serviceDonsUser.insert(donUser);
            serviceDons.updateAmount(donUser);
        }


        // Save the updated don object to the database


        // Get the donid of the selected don object



        // Close the dialog
        Stage s = (Stage) btnDonate.getScene().getWindow();
        s.close();
    }




}
