package com.example.PIDEV;

import entity.Dons;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import service.ServiceDons;

import java.util.List;

public class DonationInfoController {

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
    private String [] colors = {"B9E5FF", "BDB2FE", "FB9AA8", "FF5056"};


    public void setData(Dons dons){
        DonTitle.setText(dons.getTitle());
        DonDesc.setText(dons.getDescription());
        DonAmount.setText(String.valueOf(dons.getAmount()));
        box.setStyle("-fx-background-color: #"+ colors[(int)(Math.random()*colors.length)] +";" +
                " -fx-background-radius: 15;" +
                "-fx-effect: dropShadow(three-pass-box, rgba(0,0,0,0.1), 10, 0 , 0 ,10);");

    }
}
