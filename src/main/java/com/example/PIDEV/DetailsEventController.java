package com.example.PIDEV;

import entity.Event;
import entity.Reservation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import service.LoggedInUser;
import service.ServiceEvent;
import service.ServiceReservation;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class DetailsEventController implements Initializable {

    @FXML
    private Text DD;

    @FXML
    private Text DF;

    @FXML
    private Label back;
    @FXML
    private Button btn_back;
    @FXML
    void goBack(ActionEvent event) throws IOException {
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
    private Button modifier;
    @FXML
    private Button delete;
    @FXML
    private Button reserver;
    @FXML
    private Label title;

    @FXML
    private Label type_event;
    private ServiceEvent SE;
    private Event event;
    private Reservation reservation;
    private ServiceReservation SR;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        LoggedInUser loggedInUser = new LoggedInUser();
        if (!loggedInUser.getUser().getRole().equalsIgnoreCase("[\"ROLE_ADMIN\"]")){
            delete.setVisible(false);
            modifier.setVisible(false);

        }
        if (loggedInUser.getUser().getRole().equalsIgnoreCase("[\"ROLE_ADMIN\"]")){
            reserver.setVisible(false);
        }

    }

    public void SetEvent(Event e) {
        double y=100;
        descriptionDetail.setText(e.getDescription());
        descriptionDetail.setWrappingWidth(400);
        title.setText(e.getTitle());
        type_event.setText(e.getType_event().getNom());

        nb_place.setText(Integer.toString(e.getNb_placeMax()));
        DD.setText(e.getDate_beg().toString());
        DF.setText(e.getDate_end().toString());


        Image image = new Image("file:C:/xampp/htdocs/img/" + e.getUrl());
        mainImage.setImage(image);
        event = new Event(e.getId(), e.getTitle(), e.getDescription(), e.getNb_placeMax(), e.getDate_beg(), e.getDate_end(), e.getType_event(), e.getUrl());




    }



    @FXML
    void delete() {
        SE = new ServiceEvent();

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
    @FXML
    void reserve() {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddReservation.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        ReservationController mec = loader.getController();
        mec.setEvent(event);
        title.getScene().setRoot(root);

    }




}

