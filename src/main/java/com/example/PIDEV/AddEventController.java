package com.example.PIDEV;

import entity.CategorieEvent;
import entity.Event;
import javafx.collections.FXCollections;
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
import service.ServiceCategorieEvent;
import service.ServiceEvent;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AddEventController implements Initializable {
    @FXML
    private Button button_event;
    @FXML
    private DatePicker setDD;

    @FXML
    private ComboBox categorie;

    @FXML
    private DatePicker setDF;
    @FXML
    private Button button_image;
    private File selectedFile = null;
    private Stage stage;
    @FXML
    private ImageView ImageView;

    @FXML
    private TextArea setDescription;

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
    Event e = new Event();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ServiceCategorieEvent sce = new ServiceCategorieEvent();
        List<CategorieEvent> l =sce.readAll();
        List<String> l1= new ArrayList<>();
        for (int i = 0; i < l.size(); i++) {

            l1.add(l.get(i).getNom());

        }
        categorie.setItems(FXCollections.observableArrayList(l1));
        categorie.setValue(categorie.getItems().get(0));
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
        private void CreerEvent (ActionEvent event) {

            String text = setTitre.getText();

            if (text.isEmpty()) {
                showAlert("Please enter a title",false);
            } else if (!text.matches("[a-zA-Z ]+")) {
                showAlert("Your title must contain only letters and spaces",false);
            }
            else if (setDescription.getText().isEmpty()) {
                showAlert("Please enter a description",false);
            } else if (!text.matches("[a-zA-Z ]+")) {
                showAlert("Your description must contain only letters and spaces",false);
            }


            else if (selectedFile==null){
                showAlert("Please enter an image",false);

            }else {
                File newFile = new File("src/main/resources/com/example/PIDEV/assets/" + selectedFile.getName());
                try {
                    Files.copy(selectedFile.toPath(), newFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            ServiceCategorieEvent sce = new ServiceCategorieEvent();
            Event e=new Event(setTitre.getText(),setDescription.getText(),Integer.parseInt(setNbPlace.getText()), setDD.getValue().atStartOfDay(), setDF.getValue().atStartOfDay(),sce.readByName(categorie.getValue().toString()), selectedFile.getName());


            SE.insert(e);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("AffichageEvent.fxml"));
                Parent root = null;
                try {
                    root = loader.load();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                showAlert("Insertion terminée", true);

                setTitre.getScene().setRoot(root);


        }



    }}
