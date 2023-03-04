package com.example.PIDEV;

import entity.Oeuvre;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;
import service.LoggedInUser;
import service.ServiceCommentaireOeuvre;
import service.ServiceOeuvre;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;



public class FeedController implements Initializable {
    @FXML
    private Button addOeuvre_btn;
    @FXML
    private BorderPane pane;
    @FXML
    private AnchorPane feed ;
    @FXML
    private ScrollPane scrollPane;
    AnchorPane anchorPane = null;
    ServiceOeuvre so=new ServiceOeuvre();
    List<Oeuvre> l;
    @FXML
    private TextField filtre;
    double  y = 274.0;
    double x =0;
    @FXML
    protected void onImageClick(Oeuvre o) {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("DetailsOeuvre.fxml"));
        Parent root= null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        DetailsOeuvreController dc=loader.getController();
        dc.SetOeuvre(o);
        feed.getScene().setRoot(root);

    }
    protected void filtre(String t) {
        x=0;
        feed.getChildren().clear();
        l=so.RechercheTitre(t);
        for (Oeuvre o : l){
            anchorPane = new AnchorPane();
            Image image = new Image("file:C:/xampp/htdocs/img/"+o.getUrl());
            ImageView paint= new ImageView(image);
            Label title=new Label(o.getTitle());
            Label owner=new Label(o.getOwner().getFirstName()+" "+o.getOwner().getLastName());
            Label description=new Label(o.getDescription());

            description.setWrapText(true);
            description.setMaxWidth(200);
            anchorPane.setLayoutX(x);
            title.setLayoutY(y);
            owner.setLayoutY(y+18);
            description.setLayoutY(y+36);
            anchorPane.getChildren().addAll(paint,title,owner,description);
            anchorPane.setOnMouseClicked(mouseEvent -> onImageClick(o));
            anchorPane.setStyle("-fx-cursor: hand;");



            feed.getChildren().addAll(anchorPane);
            x+=263.0;
        }
        double center = (feed.getBoundsInLocal().getWidth() - scrollPane.getViewportBounds().getWidth()) / 2;
        scrollPane.setHvalue(center / feed.getBoundsInLocal().getWidth());

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        scrollPane.setPadding(new Insets(0,60,0,60));

        l = so.readAll();
        for (Oeuvre o : l){
            anchorPane = new AnchorPane();
            Image image = new Image("file:C:/xampp/htdocs/img/"+o.getUrl());
            ImageView paint= new ImageView(image);
            Label title=new Label(o.getTitle());
            Label owner=new Label(o.getOwner().getFirstName()+" "+o.getOwner().getLastName());
            Label description=new Label(o.getDescription());
            description.setWrapText(true);
            description.setMaxWidth(200);
            anchorPane.setLayoutX(x);
            title.setLayoutY(y);
            owner.setLayoutY(y+18);
            description.setLayoutY(y+36);
            anchorPane.getChildren().addAll(paint,title,owner,description);
            anchorPane.setOnMouseClicked(mouseEvent -> onImageClick(o));

            anchorPane.setStyle("-fx-cursor: hand;");

       ;

            feed.getChildren().addAll(anchorPane);
            x+=263.0;
        }

        scrollPane.setStyle("-fx-background-color: transparent; -fx-font-size: 9;");
        feed.setStyle("-fx-font-size: 12;");
        double center = (feed.getBoundsInLocal().getWidth() - scrollPane.getViewportBounds().getWidth()) / 2;
        scrollPane.setHvalue(center / feed.getBoundsInLocal().getWidth());


        filtre.setOnKeyTyped(e->filtre(filtre.getText()));

        LoggedInUser loggedInUser = new LoggedInUser();
        if (!(loggedInUser.getUser().getRole().equalsIgnoreCase("artist"))){
            System.out.println(loggedInUser.getUser().getRole());
            addOeuvre_btn.setVisible(false);
        }









    }
    @FXML
    void addOeuvre(ActionEvent event) {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("addOeuvre.fxml"));
        Parent root= null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        feed.getScene().setRoot(root);

    }



}