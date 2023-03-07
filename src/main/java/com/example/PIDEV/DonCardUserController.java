package com.example.PIDEV;

import entity.Dons;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import service.EmailService;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class DonCardUserController{

    @FXML
    private Label DonAmount;

    @FXML
    private Label DonDesc;

    @FXML
    private Label DonExp;

    @FXML
    private Label DonTitle;
    @FXML
    private VBox box;
    @FXML
    private Button Donatebutton;

    @FXML
    void Donation(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Donationinfo.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String [] colors = {"B9E5FF", "BDB2FE", "FB9AA8", "FF5056"};



    public void setData(Dons dons) {
        DonTitle.setText(dons.getTitle());
        DonDesc.setText(dons.getDescription());
        DonAmount.setText(String.valueOf(dons.getAmount()));
        DonExp.setText(String.valueOf(dons.getDate_expiration().toString()));
        box.setStyle("-fx-background-color: #" + colors[(int) (Math.random() * colors.length)] + ";" +
                " -fx-background-radius: 15;" +
                "-fx-effect: dropShadow(three-pass-box, rgba(0,0,0,0.1), 10, 0 , 0 ,10);");

    }
}
