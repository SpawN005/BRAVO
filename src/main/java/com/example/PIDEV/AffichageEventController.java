package com.example.PIDEV;


import entity.Event;
import entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import service.LoggedInUser;
import service.ServiceEvent;
import service.ServiceUser;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AffichageEventController implements Initializable {
    @FXML
    private ImageView img1;

    @FXML
    private ImageView img2;

    @FXML
    private ImageView img3;

    @FXML
    private Text name1;

    @FXML
    private Text name2;

    @FXML
    private Text name3;
    @FXML
    private TextField chercher_type;

    @FXML
    private Button AjouterEvent;

    @FXML
    private Button ListeDesReservations;

    @FXML
    private ScrollPane scrollPane;
    private AnchorPane anchorPane;

    @FXML
    private AnchorPane feed;

    @FXML
    private BorderPane pane;
Event e = new Event();
    ServiceUser su = new ServiceUser();

    ServiceEvent SE=new ServiceEvent();
    @FXML
    void Affichage_reservation(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AffichageReservation.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();

    }

    @FXML
    void AjoutEvent(ActionEvent event) {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("AddEvent.fxml"));
        Parent root= null;
        try {
            root = loader.load();
        } catch ( IOException ex) {
            throw new RuntimeException(ex);
        }

        feed.getScene().setRoot(root);

    }

    @FXML

    protected void onImageClick(Event e) {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("DetailsEvent.fxml"));
        Parent root= null;
        try {
            root = loader.load();
        } catch ( IOException ex) {
            throw new RuntimeException(ex);
        }

        DetailsEventController dc=loader.getController();
        dc.SetEvent(e);
        feed.getScene().setRoot(root);

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       List<Event> list=SE.readAll();

        double  y = 274.0;
        double x =100;
        scrollPane.setPadding(new Insets(60,60,0,60));
        for (Event event : list){
            anchorPane= new AnchorPane();
            anchorPane.setLayoutX(x);

             Label title1  = new Label("Titre   :     ");
             title1.setLayoutY(y+20);
             title1.setLayoutX(-100);
            Label description2  = new Label("Description   :     ");
            description2.setLayoutY(y+110);
            description2.setLayoutX(-100);
            Label type2  = new Label("Type évènement :     ");
            type2.setLayoutY(y+40);
            type2.setLayoutX(-100);
            Label DD2  = new Label("Date début   :     ");
            DD2.setLayoutY(y+60);
            DD2.setLayoutX(-100);
            Label DF2  = new Label("Date fin   :     ");
            DF2.setLayoutY(y+90);
            DF2.setLayoutX(-100);
            Label nb_max2  = new Label("Nombre de place :");
            nb_max2.setLayoutY(y);
            nb_max2.setLayoutX(-100);

            Image image = new Image("file:C:/xampp/htdocs/img/"+event.getUrl());
            ImageView paint= new ImageView(image);

            paint.setX(-80);
            paint.setFitWidth(200);
            paint.setFitHeight(200);
            Label title=new Label(event.getTitle());
            Label type=new Label(event.getType_event());
            Label DD= new Label(event.getDate_beg().toString());
            Label DF= new Label(event.getDate_end().toString());
            Label description=new Label(event.getDescription());
            Label nb_max=new Label(Integer.toString(event.getNb_placeMax()));

            title.setLayoutY(y+20);
            type.setLayoutY(y+40);
            DD.setLayoutY(y+60);
            DF.setLayoutY(y+90);
            description.setLayoutY(y+110);
            nb_max.setLayoutY(y);
            anchorPane.getChildren().addAll(paint,title1,title,description2,description,nb_max2,nb_max,DD2,DD,DF2,DF,type2,type);
            anchorPane.setOnMouseClicked(mouseEvent -> onImageClick(event));
            anchorPane.setStyle("-fx-cursor: hand;");

            feed.getChildren().addAll(anchorPane);
            x+=263.0;
        }
        chercher_type.setOnKeyTyped(e->searchByType(chercher_type.getText()));

        List<User> lu = su.RandomArtists();
        Image image = new Image("file:C:/xampp/htdocs/img/"+lu.get(0).getimage());
        img1.setImage(image);
        name1.setText(lu.get(0).getFirstName()+" "+lu.get(0).getLastName());
        image = new Image("file:C:/xampp/htdocs/img/"+lu.get(1).getimage());
        img2.setImage(image);
        name2.setText(lu.get(1).getFirstName()+" "+lu.get(1).getLastName());
        image = new Image("file:C:/xampp/htdocs/img/"+lu.get(2).getimage());
        img3.setImage(image);
        name3.setText(lu.get(2).getFirstName()+" "+lu.get(2).getLastName());
        LoggedInUser loggedInUser = new LoggedInUser();
        if(!loggedInUser.getUser().getRole().equalsIgnoreCase("admin")){
            AjouterEvent.setVisible(false);
            ListeDesReservations.setVisible(false);
        }



    }

    private void searchByType(String text) {
        List<Event>list= SE.filterByType(text);
        double  y = 274.0;
        double x =200;
        feed.getChildren().clear();

        for (Event event : list){
            anchorPane= new AnchorPane();
            anchorPane.setLayoutX(x);
            Label title1  = new Label("Titre   :     ");
            title1.setLayoutY(y+20);
            title1.setLayoutX(-100);
            Label description2  = new Label("Description   :     ");
            description2.setLayoutY(y+110);
            description2.setLayoutX(-100);
            Label type2  = new Label("Type évènement :     ");
            type2.setLayoutY(y+40);
            type2.setLayoutX(-100);
            Label DD2  = new Label("Date début   :     ");
            DD2.setLayoutY(y+60);
            DD2.setLayoutX(-100);
            Label DF2  = new Label("Date fin   :     ");
            DF2.setLayoutY(y+90);
            DF2.setLayoutX(-100);
            Label nb_max2  = new Label("Nombre de place :");
            nb_max2.setLayoutY(y);
            nb_max2.setLayoutX(-100);

            Image image = new Image("file:C:/xampp/htdocs/img/"+event.getUrl());
            ImageView paint= new ImageView(image);

            paint.setX(-80);
            paint.setFitWidth(200);
            paint.setFitHeight(200);
            Label title=new Label(event.getTitle());
            Label type=new Label(event.getType_event());
            Label DD= new Label(event.getDate_beg().toString());
            Label DF= new Label(event.getDate_end().toString());
            Label description=new Label(event.getDescription());
            Label nb_max=new Label(Integer.toString(event.getNb_placeMax()));

            title.setLayoutY(y+20);
            type.setLayoutY(y+40);
            DD.setLayoutY(y+60);
            DF.setLayoutY(y+90);
            description.setLayoutY(y+110);
            nb_max.setLayoutY(y);
            anchorPane.getChildren().addAll(paint,title1,title,description2,description,nb_max2,nb_max,DD2,DD,DF2,DF,type2,type);
            anchorPane.setOnMouseClicked(mouseEvent -> onImageClick(event));
            anchorPane.setStyle("-fx-cursor: hand;");

            feed.getChildren().addAll(anchorPane);
            x+=263.0;
        }

    }
}


