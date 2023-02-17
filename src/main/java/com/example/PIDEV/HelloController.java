package com.example.PIDEV;

import entity.Oeuvre;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import service.ServiceOeuvre;
import utils.DataSource;

import java.io.FileInputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private AnchorPane feed ;
    @FXML
    private Label title;
    @FXML
    private Label owner;
    @FXML
    private Label description;
    @FXML
    private ImageView paint ;
    ServiceOeuvre so=new ServiceOeuvre();
    List<Oeuvre> l=so.readAll();

    @FXML
    protected void onHelloButtonClick() {







    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        double  y = 274.0;
        double x =58.0;
        for (Oeuvre o : l){

            Image image = new Image("file:src/main/resources/com/example/PIDEV/assets/"+o.getUrl()+".png");
            ImageView paint= new ImageView(image);
            Label title=new Label(o.getTitle());
            Label owner=new Label(o.getOwner());
            Label description=new Label(o.getDescription());
            paint.setX(x);
            title.setLayoutX(x);
            owner.setLayoutX(x);
            description.setLayoutX(x);
            title.setLayoutY(y);
            owner.setLayoutY(y+18);
            description.setLayoutY(y+36);
            feed.getChildren().addAll(paint,title,owner,description);
            x+=263.0;
        }

    }
}