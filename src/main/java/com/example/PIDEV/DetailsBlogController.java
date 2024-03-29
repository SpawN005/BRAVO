package com.example.PIDEV;


import entity.Blog;
import entity.NoteBlog;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import org.controlsfx.control.Rating;
import service.LoggedInUser;
import service.ServiceBlog;
import service.ServiceNoteBlog;
import service.ServiceUser;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DetailsBlogController implements Initializable {
    @FXML
    private Button delete_btn;
    @FXML
    private Button modif_btn;
    @FXML
    private AnchorPane leftAnchor;

    @FXML
    private ImageView mainImage;

    @FXML
    private AnchorPane rightAnchor;

    @FXML
    private Label description;

    @FXML
    private Label back;

    @FXML
    private Label title;

    @FXML
    private Text content;

    private ServiceBlog sb;
    private ServiceNoteBlog snb;
    ServiceUser su = new ServiceUser();
    private Blog blog;
    Rating rating = new Rating();
    NoteBlog nb;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        back.setOnMouseClicked(e -> backwardButton());
        back.setStyle("-fx-cursor: hand;");

        rating.setMax(5);

    }

    public void SetBlog(Blog b) {

        ServiceNoteBlog snb= new ServiceNoteBlog();
        rating.setRating(snb.readAvg(b));

        double y = 100;
        title.setText(b.getTitle());

        description.setText(b.getDescription());
        description.setMinWidth(400);

        content.setText(b.getContent());
        content.setWrappingWidth(400);


        Image image = new Image("file:C:/xampp/htdocs/img/" + b.getUrl());
        mainImage.setImage(image);

        blog = new Blog(b.getId(), b.getTitle(), b.getDescription(), b.getContent(), b.getUrl(), b.getAuthor(),b.getCat());

        rating.setLayoutX(245);
        rating.setLayoutY(0);
        LoggedInUser loggedInUser = new LoggedInUser();
        rating.setOnMouseClicked(e-> {
            nb = new NoteBlog(rating.getRating(),b,loggedInUser.getUser().getId());  snb.insert(nb);
        });
        rightAnchor.getChildren().add(rating);



        if (!(loggedInUser.getUser().getId()==b.getAuthor().getId())){
            delete_btn.setVisible(false);
            modif_btn.setVisible(false);
        }

    }

    protected void backwardButton() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AffichageBlog.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        leftAnchor.getScene().setRoot(root);
    }


    @FXML
    public void delete() {
        sb = new ServiceBlog();
        snb= new ServiceNoteBlog();
        snb.deleteByBlog(blog);
        sb.delete(blog);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AffichageBlog.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        title.getScene().setRoot(root);

    }


    @FXML
    public void modify() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierBlog.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ModifierBlogController mbc = loader.getController();
        mbc.SetBlog(blog);
        title.getScene().setRoot(root);

    }

}


