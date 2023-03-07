package com.example.PIDEV;

import entity.Dons;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import service.ServiceDons;

import java.time.LocalDate;

public class AddDonsController {

    @FXML
    private TextField titleField;

    @FXML
    private TextArea descriptionArea;
    @FXML
    private DatePicker dateCreationPicker;

    @FXML
    private DatePicker dateExpirationPicker;

    @FXML
    private TextField amountField;

    @FXML
    private Button submitButton;

    private ServiceDons serviceDons;

    private Stage dialogStage;

    private Dons don;

    @FXML
    private void initialize() {
        serviceDons = new ServiceDons();
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setDon(Dons don) {
        this.don = don;
        titleField.setText(don.getTitle());
        descriptionArea.setText(don.getDescription());
        dateCreationPicker.setValue(don.getDate_creation());
        dateExpirationPicker.setValue(don.getDate_expiration());
        amountField.setText(Integer.toString(don.getAmount()));
    }

    @FXML
    private void handleSubmit() {
        if (isInputValid()) {
            String title = titleField.getText();
            String description = descriptionArea.getText();
            LocalDate creationDate = dateCreationPicker.getValue();
            LocalDate expirationDate = dateExpirationPicker.getValue();
            int amount = Integer.parseInt(amountField.getText());
            don = new Dons(title, description, creationDate, expirationDate, amount, "amir");
            serviceDons.insert(don);

            // Show donation confirmation alert
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initOwner(dialogStage);
            alert.setTitle("Donation Successful");
            alert.setHeaderText(null);
            alert.setContentText("Thank you for your donation of " + amount + " TND.");
            alert.showAndWait();

            handleCancel();
        }
    }




    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    private boolean isInputValid() {
        String errorMessage = "";

        // Check if expiration date is after creation date
        if (dateCreationPicker.getValue() != null && dateExpirationPicker.getValue() != null
                && dateExpirationPicker.getValue().isBefore(dateCreationPicker.getValue())) {
            errorMessage += "Expiration date must be after creation date.\n";
        }

        // Validate other fields
        if (titleField.getText() == null || titleField.getText().isEmpty()) {
            errorMessage += "Title is required.\n";
        }
        if (descriptionArea.getText() == null || descriptionArea.getText().isEmpty()) {
            errorMessage += "Description is required.\n";
        }
        if (dateCreationPicker.getValue() == null || dateCreationPicker.getValue().toString().isEmpty()) {
            errorMessage += "Creation date is required.\n";
        }
        if (dateExpirationPicker.getValue() == null || dateExpirationPicker.getValue().toString().isEmpty()) {
            errorMessage += "Expiration date is required.\n";
        }
        if (amountField.getText() == null || amountField.getText().isEmpty()) {
            errorMessage += "Amount is required.\n";
        } else {
            try {
                Integer.parseInt(amountField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Amount must be a number.\n";
            }
        }

        if (errorMessage.isEmpty()) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }

}
