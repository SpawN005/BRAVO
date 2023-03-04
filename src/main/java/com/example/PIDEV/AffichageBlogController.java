package com.example.PIDEV;

import entity.Blog;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import service.ServiceBlog;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AffichageBlogController implements Initializable {
    @FXML
    private ScrollPane scrollPane;
    private AnchorPane anchorPane;

    @FXML
    private AnchorPane feed;

    @FXML
    private TextField filter;

    @FXML
    private BorderPane pane;

    ServiceBlog sb = new ServiceBlog();

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
            Label content3 = new Label("Content  :     ");
            content3.setLayoutY(y + 80);
            content3.setLayoutX(-100);

            Image image = new Image("file:src/main/resources/com/example/PIDEV/assets/" + b1.getUrl());
            ImageView paint = new ImageView(image);

            paint.setX(-80);
            paint.setFitWidth(200);
            paint.setFitHeight(200);
            Label title = new Label(b1.getTitle());
            Label description = new Label(b1.getDescription());


            Label content = new Label(b1.getContent());
            content.setWrapText(true);
            content.setMaxWidth(300);

            title.setLayoutY(y);
            description.setLayoutY(y + 40);
            content.setLayoutY(y + 80);

            anchorPane.getChildren().addAll(paint, title1, title, description2, description, content3, content);
            anchorPane.setOnMouseClicked(mouseEvent -> onImageClick(b1));
            anchorPane.setStyle("-fx-cursor: hand;");

            feed.getChildren().addAll(anchorPane);
            x += (263.0 + spacing);

        }
        filter.setOnKeyTyped(e->findByTitle(filter.getText()));
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
                Label content3 = new Label("Content  :     ");
                content3.setLayoutY(y + 40);
                content3.setLayoutX(-100);

                Image image = new Image("file:src/main/resources/com/example/PIDEV/assets/" + b.getUrl());
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
                content.setLayoutY(y + 80);

                anchorPane.getChildren().addAll(paint, title1, title, description2, description, content3, content);
                anchorPane.setOnMouseClicked(mouseEvent -> onImageClick(b));
                anchorPane.setStyle("-fx-cursor: hand;");

                feed.getChildren().addAll(anchorPane);
                x += 263.0+spacing;


            }

        }
    }
