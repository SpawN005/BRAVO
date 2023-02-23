package com.example.PIDEV;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;

import entity.Dons;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.ServiceDons;

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
        dateCreationPicker.setValue(don.getDate_creation().toLocalDateTime().toLocalDate());
        dateExpirationPicker.setValue(don.getDate_expiration().toLocalDateTime().toLocalDate());
        amountField.setText(Integer.toString(don.getAmount()));
    }

    @FXML
    private void handleSubmit() {
 if(isInputValid()){
     String title = titleField.getText();
     String description = descriptionArea.getText();
     LocalDate creationDate = dateCreationPicker.getValue();
     LocalDate expirationDate = dateExpirationPicker.getValue();

     Timestamp timestamp = Timestamp.valueOf(expirationDate.atStartOfDay());
     int amount = Integer.parseInt(amountField.getText());
     Timestamp now = new Timestamp(System.currentTimeMillis());
     don = new Dons(title, description,now, timestamp,amount,"amir");
     serviceDons.insert(don);
 }



    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (titleField.getText() == null || titleField.getText().isEmpty()) {
            errorMessage += "Title field is required.\n";
        }
        if (descriptionArea.getText() == null || descriptionArea.getText().isEmpty()) {
            errorMessage += "Description field is required.\n";
        }
        if (dateCreationPicker.getValue() == null || dateCreationPicker.getValue().toString().isEmpty()) {
            errorMessage += "Date field is required.\n";
        }
        if (dateExpirationPicker.getValue() == null || dateExpirationPicker.getValue().toString().isEmpty()) {
            errorMessage += "Date field is required.\n";
        }
        if (amountField.getText() == null || amountField.getText().isEmpty()) {
            errorMessage += "Amount field is required.\n";
        } else {
            try {
                Integer.parseInt(amountField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Amount must be an integer.\n";
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
