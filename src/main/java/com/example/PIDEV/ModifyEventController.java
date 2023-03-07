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
    private ImageView ImageView;

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


    private Event event;

    public void SetEvent(Event e) {
        description.setText(e.getDescription());

        title.setText(e.getTitle());
        type.setText(e.getType_event());
        nb_place.setText(Integer.toString(e.getNb_placeMax()));
        DD.setValue(e.getDate_beg().toLocalDate());
        DF.setValue(e.getDate_end().toLocalDate());
        uploadImage.setText(e.getUrl());

        ImageView.setImage(new Image("file:C:/xampp/htdocs/img/" + e.getUrl()));
        event = new Event(e.getId(), e.getTitle(), e.getDescription(), e.getNb_placeMax(), e.getDate_beg(), e.getDate_end(), e.getType_event(), e.getUrl());


    }

    @FXML
    void browse() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Upload an image");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image files", "*.jpg", "*.jpeg", "*.png");
        fileChooser.getExtensionFilters().add(extFilter);
        selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {
            uploadImage.setText(selectedFile.getName());


            ImageView.setImage(new Image("file:" + selectedFile));
            ImageView.setFitWidth(200);
            ImageView.setFitHeight(150);

        }
    }

    @FXML
    void cancel() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AffichageEvent.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        title.getScene().setRoot(root);
    }

    private void showAlert(String message, boolean b) {
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
    void submit() {
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
            File newFile = new File("file:C:/xampp/htdocs/img/" + selectedFile.getName());
            try {
                Files.copy(selectedFile.toPath(), newFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            Event e = new Event();
            e.setTitle(title.getText());
            e.setDescription(description.getText());
            e.setNb_placeMax(Integer.parseInt(nb_place.getText()));
            e.setDate_beg(DD.getValue().atStartOfDay());
            e.setDate_end(DF.getValue().atStartOfDay());
            e.setType_event(type.getText());
            e.setUrl(uploadImage.getText());
            e.setId(event.getId());
            Connection conn = DataSource.getInstance().getCnx();
            String query = "SELECT * FROM artwork WHERE title = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, title.getText());
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    // Email already exists, show popup
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    showAlert("title already taken",false);
                    return;
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                return;
            }
            SE.update(e);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("AffichageEvent.fxml"));
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            showAlert("Modification réussie", true);

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
