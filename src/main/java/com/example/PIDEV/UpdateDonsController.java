package com.example.PIDEV;

import java.time.LocalDate;

import entity.Dons;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.LoggedInUser;
import service.ServiceDons;

public class UpdateDonsController {

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
        System.out.println(isInputValid());
        if (isInputValid()) {
            String title = titleField.getText();
            String description = descriptionArea.getText();
            LocalDate creationDate = dateCreationPicker.getValue();
            LocalDate expirationDate = dateExpirationPicker.getValue();
            LoggedInUser loggedInUser = new LoggedInUser();
            int amount = Integer.parseInt(amountField.getText());
            Dons don1 = new Dons(don.getId(),title, description, creationDate, expirationDate, amount, loggedInUser.getUser());
            serviceDons.update(don1);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            alert.setTitle("Donation Successful");
            alert.setHeaderText(null);
            alert.setContentText("Donation updated ");
            alert.showAndWait();

            handleCancel();
        }
    }





    @FXML
    private void handleCancel() {
        Stage s = (Stage )submitButton.getScene().getWindow();
        s.close();
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (titleField.getText() == null || titleField.getText().isEmpty()) {
            errorMessage += "Title is required.\n";
        }
        if (descriptionArea.getText() == null || descriptionArea.getText().isEmpty()) {
            errorMessage += "Description is required.\n";
        }
        if (dateCreationPicker.getValue() == null || dateCreationPicker.getValue().toString().isEmpty()) {
            errorMessage += "Date is required.\n";
        }
        if (dateExpirationPicker.getValue() == null || dateExpirationPicker.getValue().toString().isEmpty()) {
            errorMessage += "Date is required.\n";
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
        LocalDate creationDate = dateCreationPicker.getValue();
        LocalDate expirationDate = dateExpirationPicker.getValue();

        if (creationDate != null && expirationDate != null && !creationDate.isBefore(expirationDate)) {
            errorMessage += "Creation date must be before expiration date.\n";
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
