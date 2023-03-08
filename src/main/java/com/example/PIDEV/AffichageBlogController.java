package com.example.PIDEV;

import entity.Blog;
import entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import service.ServiceBlog;
import service.ServiceUser;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AffichageBlogController implements Initializable {
    @FXML
    private ScrollPane scrollPane;
    private AnchorPane anchorPane;
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
    private Button addBlog_btn;

    @FXML
    private AnchorPane feed;

    @FXML
    private TextField filter;

    @FXML
    private BorderPane pane;

    ServiceBlog sb = new ServiceBlog();
    ServiceUser su = new ServiceUser();

    List<Blog> lb;

    @FXML

    protected void onImageClick(Blog b) {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("DetailsBlog1.0.fxml"));
        Parent root= null;
        try {
            root = loader.load();
        } catch ( IOException e) {
            throw new RuntimeException(e);
        }

        DetailsBlogController dc=loader.getController();
        dc.SetBlog(b);
        feed.getScene().setRoot(root);

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Blog> list = sb.readAll();
        double y = 274.0;
        double x = 200;
        double spacing = 150.0;
        for (Blog b1 : list) {
            anchorPane = new AnchorPane();
            anchorPane.setLayoutX(x);
            Label title1 = new Label("Title  :     ");
            title1.setLayoutY(y);
            title1.setLayoutX(-100);
            Label description2 = new Label("Description   :     ");
            description2.setLayoutY(y + 40);
            description2.setLayoutX(-100);

            Image image = new Image("file:C:/xampp/htdocs/img/" + b1.getUrl());
            ImageView paint = new ImageView(image);

            paint.setX(-80);
            paint.setFitWidth(200);
            paint.setFitHeight(200);
            Label title = new Label(b1.getTitle());
            Label description = new Label(b1.getDescription());



            title.setLayoutY(y);
            description.setLayoutY(y + 40);

            anchorPane.getChildren().addAll(paint, title1, title, description2, description);
            anchorPane.setOnMouseClicked(mouseEvent -> onImageClick(b1));
            anchorPane.setStyle("-fx-cursor: hand;");
            feed.getChildren().addAll(anchorPane);
            x += (263.0 + spacing);

        }
        filter.setOnKeyTyped(e->findByTitle(filter.getText()));
        scrollPane.setStyle("-fx-background-color: transparent; -fx-font-size: 9;");
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
        if (!(loggedInUser.getUser().getRole().equalsIgnoreCase("artist"))){
            System.out.println(loggedInUser.getUser().getRole());
            addBlog_btn.setVisible(false);
        }
    }

        private void findByTitle(String s) {
            List<Blog> list= sb.recherchePartitre(s);
            double  y = 274.0;
            double x =200;
            double spacing= 150.0;
            feed.getChildren().clear();
            lb = sb.recherchePartitre(s);
            for (Blog b : lb) {
                anchorPane = new AnchorPane();
                anchorPane.setLayoutX(x);

                Label title1 = new Label("Title  :     ");
                title1.setLayoutY(y);
                title1.setLayoutX(-100);
                Label description2 = new Label("Description   :     ");
                description2.setLayoutY(y + 20);
                description2.setLayoutX(-100);


                Image image = new Image("file:C:/xampp/htdocs/img/" + b.getUrl());
                ImageView paint = new ImageView(image);

                paint.setX(-80);
                paint.setFitWidth(200);
                paint.setFitHeight(200);
                Label title = new Label(b.getTitle());

                Label description = new Label(b.getDescription());

                Label content = new Label(b.getContent());
                content.setWrapText(true);
                content.setMaxWidth(300);

                title.setLayoutY(y);
                description.setLayoutY(y + 40);
                description2.setLayoutY(y + 40);

                anchorPane.getChildren().addAll(paint, title1, title, description2, description);
                anchorPane.setOnMouseClicked(mouseEvent -> onImageClick(b));
                anchorPane.setStyle("-fx-cursor: hand;");

                feed.getChildren().addAll(anchorPane);
                x += 263.0+spacing;


            }


        }



    public void addBlog(ActionEvent actionEvent) {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("AddBlog.fxml"));
        Parent root= null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        feed.getScene().setRoot(root);
    }
}
