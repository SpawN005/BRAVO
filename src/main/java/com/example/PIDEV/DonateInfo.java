package com.example.PIDEV;

import entity.Dons;
import entity.DonsUser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
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


    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }




    @FXML
    void Donate(ActionEvent event) {
        // Create a new instance of the ServiceDonsUser class
        ServiceDonsUser serviceDonsUser = new ServiceDonsUser();

        // Get the donation amount from the text field
        int donationAmount = Integer.parseInt(amounttextfield.getText());

        // Update the amount field of the donuser object with the new donation amount
        don.setAmount(donationAmount);

        // Save the updated don object to the database
        serviceDons.update(don);

        // Get the donid of the selected don object
        int donId = don.getId();

        // Create a new donuser object with the donid and donation amount
        DonsUser donUser = new DonsUser(donId, donationAmount);

        // Save the donuser object to the database
        serviceDonsUser.insert(donUser);

        // Close the dialog
        dialogStage.close();
    }



    public Stage getDialogStage() {
        return dialogStage;
    }
}
