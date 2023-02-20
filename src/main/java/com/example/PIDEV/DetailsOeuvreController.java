package com.example.PIDEV;

import entity.Oeuvre;
import javafx.event.ActionEvent;
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
import service.ServiceOeuvre;


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

    private ServiceOeuvre so;
    private Oeuvre oeuvre;

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
        oeuvre = new Oeuvre(o.getId(),o.getTitle(),o.getDescription(),o.getOwner(),o.getCategory(),o.getUrl());


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


    public void modify(ActionEvent actionEvent) {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("modifyOeuvre.fxml"));
        Parent root= null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ModifyOeuvreController moc = loader.getController();
        moc.SetOeuvre(oeuvre);
        title.getScene().setRoot(root);

    }
    @FXML
    public void delete(ActionEvent actionEvent) {
        so = new ServiceOeuvre();
        so.delete(oeuvre);
        FXMLLoader loader=new FXMLLoader(getClass().getResource("feed.fxml"));
        Parent root= null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        title.getScene().setRoot(root);


    }
}
