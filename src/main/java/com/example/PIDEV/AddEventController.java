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

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setDescription.getText();
        settype.getText();
        setNbPlace.getText();
        setTitre.getText();

    }
        @FXML
        private void browse () {
            System.out.println("aze");
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
        private void CreerEvent () {


            String text = setTitre.getText();
            if (text.isEmpty()) {
                showAlert("Veuillez ajouter un titre", false);
            } else if (!text.matches("[a-zA-Z ]+")) {
                showAlert("Le titre ne peut contenir ques des lettres et des espaces", false);
            }
            if (setDescription.getText().isEmpty()) {
                showAlert("Veuillez ajouter une description", false);
            } else if (!text.matches("[a-zA-Z ]+")) {
                showAlert("La description ne peut contenir que des lettres et des espaces", false);
            }
            if (settype.getText().isEmpty()) {
                showAlert("Veuillez ajouter un type", false);
            } else if (!text.matches("[a-zA-Z ]+")) {
                showAlert("Le type ne peut contenir que des lettres et des espaces", false);
            } else {
                Event e = new Event();
                File newFile = new File("src/main/resources/com/example/PIDEV/assets/" + selectedFile.getName());
                try {
                    Files.copy(selectedFile.toPath(), newFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                e.setTitle(setTitre.getText());
                e.setType_event(settype.getText());


                e.setDescription(setDescription.getText());
                e.setUrl(selectedFile.getName());
                e.setDate_beg(setDD.getValue());
                e.setDate_end(setDF.getValue());
                e.setNb_placeMax(Integer.parseInt(setNbPlace.getText()));
                SE.insert(e);
            }
        }


    private void showAlert(String s, boolean b) {
        Alert alert;
        if (b)
            alert = new Alert(Alert.AlertType.INFORMATION);
        else
            alert = new Alert(Alert.AlertType.ERROR);


        alert.setHeaderText(null);
        alert.setContentText(s);
        alert.showAndWait();
    }
    }
