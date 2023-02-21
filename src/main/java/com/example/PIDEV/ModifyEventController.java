package com.example.PIDEV;

import entity.Event;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
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

public class ModifyEventController implements Initializable {
    @FXML
    private DatePicker DD;

    @FXML
    private DatePicker DF;

    @FXML
    private ImageView imageView;

    @FXML
    private Button cancelButton;

    @FXML
    private TextArea description;

    @FXML
    private TextField nb_place;

    @FXML
    private Button submitButton;

    @FXML
    private TextField title;

    @FXML
    private TextField type;

    @FXML
    private Button uploadImage;

    private File selectedFile = null;
    private Stage stage;


    private ServiceEvent SE = new ServiceEvent();

    private PreparedStatement pst = null;
    private Event event;
    public void SetEvent(Event e) {
        description.setText(event.getDescription());

        title.setText(event.getTitle());
        type.setText(event.getType_event());
        nb_place.setText(Integer.toString(event.getNb_placeMax()));
        DD.setValue(event.getDate_beg());
        DF.setValue(event.getDate_end());
        uploadImage.setText(event.getUrl());


        Image image = new Image("file:src/main/resources/com/example/PIDEV/assets/"+event.getUrl());
        imageView.setImage(image);
        event =new Event(e.getId(),e.getTitle(),e.getDescription(),e.getNb_placeMax(),e.getDate_beg(),e.getDate_end(),e.getType_event(),e.getUrl());


    }

    @FXML
    void browse(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Upload an image");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image files", "*.jpg", "*.jpeg", "*.png");
        fileChooser.getExtensionFilters().add(extFilter);
        selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {
            uploadImage.setText(selectedFile.getName());


            imageView.setImage(new Image("file:" + selectedFile));
            imageView.setFitWidth(200);
            imageView.setFitHeight(150);

        }
    }

        @FXML
         void cancel () {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("feed.fxml"));
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            title.getScene().setRoot(root);
        }
        private void showAlert(String message ,boolean b) {
            Alert alert;
            if (b)
                alert = new Alert(Alert.AlertType.INFORMATION);
            else
                alert = new Alert(Alert.AlertType.ERROR);


            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();

        }





    @FXML
    void submit(ActionEvent event) {
        String text = title.getText();

        if (text.isEmpty()) {
            showAlert("Please enter a title", false);
        } else if (!text.matches("[a-zA-Z ]+")) {
            showAlert("Your title must contain only letters and spaces", false);
        } else if (description.getText().isEmpty()) {
            showAlert("Please enter a description", false);
        } else if (!text.matches("[a-zA-Z ]+")) {
            showAlert("Your description must contain only letters and spaces", false);
        } else if (selectedFile == null) {
            showAlert("Please enter an image", false);

        } else {
            Event e= new Event();
            if (e.getUrl().isEmpty()) {

                File newFile = new File("src/main/resources/com/example/PIDEV/assets/" + selectedFile.getName());
                try {
                    Files.copy(selectedFile.toPath(), newFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }
                e.setTitle(title.getText());
                e.setDescription(description.getText());
                e.setNb_placeMax(Integer.parseInt(nb_place.getText()));
                e.setDate_beg(DD.getValue());
                e.setDate_end(DF.getValue());
                e.setType_event(type.getText());
                e.setUrl(uploadImage.getText());


                SE.update(e);

                FXMLLoader loader = new FXMLLoader(getClass().getResource("feed.fxml"));
                Parent root = null;
                try {
                    root = loader.load();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                showAlert("Registration success", true);

                title.getScene().setRoot(root);

            }
        }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        description.getText();
        type.getText();
        nb_place.getText();
        title.getText();

    }




  }
