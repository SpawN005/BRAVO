package com.example.PIDEV;


import entity.Blog;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import service.ServiceBlog;
import service.ServiceUser;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DetailsBlogController implements Initializable {


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

    ServiceUser su = new ServiceUser();
    private Blog blog;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        back.setOnMouseClicked(e -> backwardButton());
        back.setStyle("-fx-cursor: hand;");
    }

    public void SetBlog(Blog b) {
        double y=100;
        title.setText(b.getTitle());
        description.setText(b.getDescription());
        description.setMinWidth(400);
        title.setText(title.getText());

        b = new Blog(b.getId(),b.getTitle(),b.getDescription(),b.getContent(),b.getAuthor());

    }

    protected void backwardButton() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AffichageBlog.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // leftAnchor.getScene().setRoot(root);
    }
    @FXML
    void modify() {
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

    @FXML
    void delete() {
        sb= new ServiceBlog();

        sb.delete(blog);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AffichageBlog.fxml"));
        Parent root = null;
        try {
            root= loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        title.getScene().setRoot(root);

    }




}
