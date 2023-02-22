package com.example.PIDEV;

import entity.Blog;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.ServiceBlog;
import service.ServiceUser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ModifierBlogController implements Initializable {

    @FXML
    private Button submitButton;

    @FXML
    private Button cancelButton;

    @FXML
    private TextField description;

    @FXML
    private TextField title;

    @FXML
    private TextArea content;


    private File selectedFile = null;
    private Stage stage;

    ServiceBlog sb= new ServiceBlog();
    ServiceUser su = new ServiceUser();
    private Blog blog ;

    public void SetBlog(Blog b){

        title.setText(b.getTitle());
        description.setText(b.getDescription());
        content.setText(b.getContent());
        b.setAuthor(su.readById(26));
        b = new Blog (b.getId(),b.getTitle(),b.getDescription(),b.getContent(),b.getAuthor());

    }

    @FXML
    void cancel() {
        FXMLLoader loader= new FXMLLoader(getClass().getResource("AffichageBlog.fxml"));
        Parent root = null;

        try {
            root= loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        title.getScene().setRoot(root);
    }

    private void showAlert(String message, boolean b){
        Alert alert;
        if(b)
            alert = new Alert(Alert.AlertType.INFORMATION);
        else
            alert = new Alert(Alert.AlertType.ERROR);

        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    void submit() {
        String text = title.getText();

        if (text.isEmpty()) {
            showAlert("Please enter a title", false);
        } else if (!text.matches("[a-zA-Z ]+")) {
            showAlert("Your title must contain only letters and spaces", false);
        } else if (description.getText().isEmpty()) {
            showAlert("Please enter a description", false);
        } else if (!text.matches("[a-zA-Z ]+")) {
            showAlert("Your title must contain only letters and spaces", false);

        }
            Blog b = new Blog();

            b.setTitle(title.getText());
            b.setDescription(description.getText());
            b.setContent(content.getText());
            b.setAuthor(su.readById(26));


            sb.update(b);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("AffichageBlog.fxml"));
            Parent root = null;
            try {
                root = loader.load();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            showAlert("Modification réussie", true);

            title.getScene().setRoot(root);


        }



        @Override
        public void initialize (URL url, ResourceBundle resourceBundle){
            title.getText();
            description.getText();
            content.getText();

        }
    }
