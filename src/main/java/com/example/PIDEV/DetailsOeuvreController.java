package com.example.PIDEV;

import entity.Oeuvre;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;


public class DetailsOeuvreController implements Initializable {

    @FXML
    private Label back;

    @FXML
    private Text descriptionDetail;

    @FXML
    private AnchorPane leftAnchor;

    @FXML
    private ImageView mainImage;

    @FXML
    private Label owner;

    @FXML
    private AnchorPane rightAnchor;

    @FXML
    private Label title;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        back.setOnMouseClicked(e->backwardButton());
        back.setStyle("-fx-cursor: hand;");

    }

    public void SetOeuvre(Oeuvre o) {
        descriptionDetail.setText(o.getDescription());
        descriptionDetail.setWrappingWidth(400);
        title.setText(o.getTitle());
        owner.setText(o.getOwner());
        Image image = new Image("file:src/main/resources/com/example/PIDEV/assets/"+o.getUrl());
        mainImage.setImage(image);


    }
    protected void backwardButton() {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("feed.fxml"));
        Parent root= null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }



        leftAnchor.getScene().setRoot(root);

    }



}
