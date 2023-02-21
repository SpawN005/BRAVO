package com.example.PIDEV;

import entity.Event;
import entity.Oeuvre;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
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
import service.ServiceOeuvre;


public class DetailsEventController implements Initializable {

    @FXML
    private Label back;



    @FXML
    private AnchorPane leftAnchor;

    @FXML
    private ImageView mainImage;

    private Label DF;
    private Label nb_max;
    private Label url;


    @FXML
    private AnchorPane rightAnchor;

    @FXML
    private Label title;
    @FXML
    private Text descriptionDetail;
    private Label type_event;
    private Label DD;



private Event event;

    private ServiceEvent SE = new ServiceEvent();



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        back.setOnMouseClicked(e->backwardButton());
        back.setStyle("-fx-cursor: hand;");

    }

    public void SetEvent(Event e) {
        descriptionDetail.setText(e.getDescription());
        descriptionDetail.setWrappingWidth(400);
        title.setText(e.getTitle());
        type_event.setText(e.getType_event());;
        nb_max.setText(Integer.toString(e.getNb_placeMax()));
        DD.setText(e.getDate_beg().toString());
        DF.setText(e.getDate_end().toString());
        url.setText(e.getUrl());


        Image image = new Image("file:src/main/resources/com/example/PIDEV/assets/"+e.getUrl());
        mainImage.setImage(image);
        event =new Event(e.getId(),e.getTitle(),e.getDescription(),e.getNb_placeMax(),e.getDate_beg(),e.getDate_end(),e.getType_event(),e.getUrl());


    }
    protected void backwardButton() {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("AffichageEvent.fxml"));
        Parent root= null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }



        leftAnchor.getScene().setRoot(root);

    }


    public void modify(ActionEvent actionEvent) {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("ModificationEvent.fxml"));
        Parent root= null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ModifyEventController mec = loader.getController();
        mec.SetEvent(event);
        title.getScene().setRoot(root);

    }
    @FXML
    public void delete(ActionEvent actionEvent) {
SE = new ServiceEvent();
        SE.delete(event);
        FXMLLoader loader=new FXMLLoader(getClass().getResource("AffichageEvent.fxml"));
        Parent root= null;
        try {
            root = loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }


        title.getScene().setRoot(root);


    }
}
