package com.example.PIDEV;

import entity.Event;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import service.ServiceEvent;
import utils.DataSource;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AddEventController implements Initializable {
    @FXML
    private Button button_event;
    @FXML
    private DatePicker setDD;

    @FXML
    private DatePicker setDF;
    @FXML
    private Button button_image;
    private File selectedFile = null;
    private Stage stage;
    @FXML
    private ImageView ImageView;

    @FXML
    private TextField setDescription;

    @FXML
    private TextField setNbPlace;

    @FXML
    private TextField settype;
    @FXML
    private TextField setTitre;
    private ServiceEvent SE = new ServiceEvent();

    private Connection cnx = null;
    private ResultSet rs = null;
    private PreparedStatement pst = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setDescription.getText();
        settype.getText();
        setNbPlace.getText();
        setTitre.getText();

    }
        @FXML
        private void browse () {

            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Upload an image");
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image files", "*.jpg", "*.jpeg", "*.png");
            fileChooser.getExtensionFilters().add(extFilter);
            selectedFile = fileChooser.showOpenDialog(stage);
            if (selectedFile != null) {
                button_image.setText(selectedFile.getName());


                ImageView.setImage(new Image("file:" + selectedFile));
                ImageView.setFitWidth(200);
                ImageView.setFitHeight(150);


            }

        }

        @FXML
        private void CreerEvent (ActionEvent event) {


            Event e=new Event(setTitre.getText(),setDescription.getText(),Integer.parseInt(setNbPlace.getText()),setDD.getValue(),setDF.getValue(),settype.getText(), selectedFile.getName());


            SE.insert(e);


        }



    }
