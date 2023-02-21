package com.example.PIDEV;

import entity.Oeuvre;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import service.ServiceOeuvre;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class FeedController implements Initializable {
    @FXML
    private BorderPane pane;
    @FXML
    private AnchorPane feed ;
    @FXML
    private ScrollPane ScrollPane;
    AnchorPane anchorPane = null;
    ServiceOeuvre so=new ServiceOeuvre();
    List<Oeuvre> l=so.readAll();

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        double  y = 274.0;
        double x =58.0;
        for (Oeuvre o : l){
            anchorPane = new AnchorPane();
            Image image = new Image("file:src/main/resources/com/example/PIDEV/assets/"+o.getUrl());
            ImageView paint= new ImageView(image);
            Label title=new Label(o.getTitle());
            Label owner=new Label(o.getOwner());
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
        ScrollPane.setStyle("-fx-background-color: transparent;");
    }

}