package com.example.PIDEV;

import entity.Oeuvre;
import entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import service.LoggedInUser;
import service.ServiceOeuvre;
import service.ServiceUser;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;



public class FeedController implements Initializable {

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
    private Button addOeuvre_btn;
    @FXML
    private BorderPane pane;
    @FXML
    private AnchorPane feed ;
    @FXML
    private ScrollPane scrollPane;
    AnchorPane anchorPane = null;
    ServiceOeuvre so=new ServiceOeuvre();
    ServiceUser su = new ServiceUser();
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
            paint.setFitHeight(200);
            paint.setFitWidth(200);
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


        scrollPane.setPadding(new Insets(60,60,0,60));

        l = so.readAll();
        for (Oeuvre o : l){
            anchorPane = new AnchorPane();
            Image image = new Image("file:C:/xampp/htdocs/img/"+o.getUrl());
            ImageView paint= new ImageView(image);
            Label title=new Label(o.getTitle());
            Label owner=new Label(o.getOwner().getFirstName()+" "+o.getOwner().getLastName());
            Label description=new Label(o.getDescription());
            description.setWrapText(true);
            paint.setFitHeight(200);
            paint.setFitWidth(200);
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
        if (!(loggedInUser.getUser().getRole().equalsIgnoreCase("[\"ROLE_ARTISTE\"]"))){
            System.out.println(loggedInUser.getUser().getRole());
            addOeuvre_btn.setVisible(false);
        }



        List<User> lu = su.RandomArtists();
        if (lu.size()>2){
            Image image = new Image("file:C:/xampp/htdocs/img/"+lu.get(0).getimage());
            img1.setImage(image);
            name1.setText(lu.get(0).getFirstName()+" "+lu.get(0).getLastName());
            Image image1 = new Image("file:C:/xampp/htdocs/img/"+lu.get(1).getimage());
            img2.setImage(image1);
            name2.setText(lu.get(1).getFirstName()+" "+lu.get(1).getLastName());
            Image image2 = new Image("file:C:/xampp/htdocs/img/"+lu.get(2).getimage());
            img3.setImage(image2);
            name3.setText(lu.get(2).getFirstName()+" "+lu.get(2).getLastName());
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