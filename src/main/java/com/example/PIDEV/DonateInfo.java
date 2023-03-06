package com.example.PIDEV;

import entity.Dons;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import service.ServiceDons;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DonateInfo {
    @FXML
    private Label amountlabel;

    @FXML
    private TextField amounttextfield;

    @FXML
    private Button btnDonate;

    @FXML
    private Label descriptionlabel;

    @FXML
    private Label expirationlabel;

    @FXML
    private Label titlelabel;

    @FXML
    private Label expiration;

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

    public void setData(Dons dons) {
        this.don = dons;
        titlelabel.setText(dons.getTitle());
        descriptionlabel.setText(dons.getDescription());
        amountlabel.setText(String.valueOf(dons.getAmount()));
    }

    @FXML
    void showDonateDialog(ActionEvent event, Dons selectedDons) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DonateInfo.fxml"));
            AnchorPane root = loader.load();
            DonateInfo donateInfo = loader.getController();
            donateInfo.setDialogStage(new Stage());
            donateInfo.setData(selectedDons); // set the data to display
            Scene scene = new Scene(root);
            donateInfo.getDialogStage().setScene(scene);
            donateInfo.getDialogStage().showAndWait();
        } catch (IOException ex) {
            Logger.getLogger(DonateInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void Donate(ActionEvent event) {
        // Handle donation logic here
    }

    public Stage getDialogStage() {
        return dialogStage;
    }
}
