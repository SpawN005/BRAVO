package com.example.PIDEV;

import entity.Event;
import entity.Oeuvre;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.PrimitiveIterator;
import java.util.ResourceBundle;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import service.ServiceEvent;



public class DetailsEventController implements Initializable {

    @FXML
    private Text DD;

    @FXML
    private Text DF;

    @FXML
    private Label back;

    @FXML
    private Text descriptionDetail;

    @FXML
    private AnchorPane leftAnchor;

    @FXML
    private ImageView mainImage;

    @FXML
    private Text nb_place;

    @FXML
    private AnchorPane rightAnchor;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private Label title;

    @FXML
    private Label type_event;
    private Event event;

    private ServiceEvent SE = new ServiceEvent();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        back.setOnMouseClicked(e -> backwardButton());
        back.setStyle("-fx-cursor: hand;");

    }

    public void SetEvent(Event e) {
        descriptionDetail.setText(e.getDescription());
        descriptionDetail.setWrappingWidth(400);
        title.setText(e.getTitle());
        type_event.setText(e.getType_event());

        nb_place.setText(Integer.toString(e.getNb_placeMax()));
        DD.setText(e.getDate_beg().toString());
        DF.setText(e.getDate_end().toString());


        Image image = new Image("file:src/main/resources/com/example/PIDEV/assets/" + e.getUrl());
        mainImage.setImage(image);
        event = new Event(e.getId(), e.getTitle(), e.getDescription(), e.getNb_placeMax(), e.getDate_beg(), e.getDate_end(), e.getType_event(), e.getUrl());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DetailsOeuvre.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        DetailsEventController dc = loader.getController();
        dc.SetEvent(event);
        title.getScene().setRoot(root);

    }


    protected void backwardButton() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AffichageEvent.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        leftAnchor.getScene().setRoot(root);

    }
    @FXML
    void delete() {

        SE.delete(event);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AffichageEvent.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }


        title.getScene().setRoot(root);


    }


    @FXML
    void modify() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ModificationEvent.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        ModifyEventController mec = loader.getController();
        mec.SetEvent(event);
        title.getScene().setRoot(root);

    }



}

