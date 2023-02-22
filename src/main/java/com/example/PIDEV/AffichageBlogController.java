package com.example.PIDEV;

import entity.Blog;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
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
    private BorderPane pane;

    ServiceBlog sb = new ServiceBlog();

    @FXML

    protected void onImageClick(Blog b) {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("DetailsBlog.fxml"));
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
        List<Blog> list= sb.readAll();
        double  y = 274.0;
        double x =200;
        for (Blog b : list){
            anchorPane= new AnchorPane();
            anchorPane.setLayoutX(x);
            Label title1  = new Label("Titre   :     ");
            title1.setLayoutY(y);
            title1.setLayoutX(-100);
            Label description2  = new Label("Description   :     ");
            description2.setLayoutY(y+20);
            description2.setLayoutX(-100);
            Label content3  = new Label("Content  :     ");
            content3.setLayoutY(y+40);
            content3.setLayoutX(-100);

            Label title  =new Label(b.getTitle()) ;
            Label description= new Label(b.getDescription());
            Label content = new Label(b.getContent());

            title.setLayoutY(y);
            description.setLayoutY(y+20);
            content.setLayoutY(y+40);

            anchorPane.getChildren().addAll(title1,title,description2,description,content3,content);
            feed.getChildren().addAll(anchorPane);
            x+=263.0;

        }


    }
}
