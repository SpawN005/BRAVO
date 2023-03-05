package com.example.PIDEV;

import entity.Event;

import entity.Reservation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
import javafx.stage.Stage;
import service.ServiceEvent;
import service.ServiceReservation;


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
        Stage stage = (Stage) btn_back.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("AffichageEvent.fxml"));
        primaryStage.setTitle("hello again");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
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
    private Label title;

    @FXML
    private Label type_event;
    private ServiceEvent SE;
    private Event event;
    private Reservation reservation;
    private ServiceReservation SR;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Generate the QR code image using the QRCodeGenerator utility class
        String data = "Hello, world!";
        int size = 250;


    }

    public void SetEvent(Event e) {
        double y=100;
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


        title.getScene().setRoot(root);

    }




}

