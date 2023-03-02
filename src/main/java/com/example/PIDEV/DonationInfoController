package com.example.PIDEV;

import entity.Dons;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
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


    public void setData(Dons dons){
        DonTitle.setText(dons.getTitle());
        DonDesc.setText(dons.getDescription());
        DonAmount.setText(String.valueOf(dons.getAmount()));

    }
}
