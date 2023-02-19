package com.example.PIDEV;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class AddEventController implements Initializable {
    @FXML
    private Button button_event;

    @FXML
    private TextField setDD;

    @FXML
    private TextField setDF;

    @FXML
    private TextField setDescription;

    @FXML
    private ImageView setImg;

    @FXML
    private TextField setNbPlace;

    @FXML
    private TextField settype;

    @FXML
    void CreerEvent(ActionEvent event) {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/resources/com."));

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
